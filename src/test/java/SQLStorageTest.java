import app.Config;
import app.model.Account;
import app.model.Card;
import app.model.Client;
import app.storage.SQLStorage;
import app.storage.Storage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class SQLStorageTest {
    protected Storage storage = new SQLStorage("jdbc:h2:mem:storage1", "root", "password");
    Client client1 = new Client(UUID.fromString("189daee2-0972-11eb-adc1-0242ac120002"), "Ivan", "Semenov");
    Account account1 = new Account(UUID.fromString("34b8817e-0972-11eb-adc1-0242ac120002"), 12332112, UUID.fromString("7ae53c7b-0002-483e-897d-3e21f8ca16eb"));
    Card card1 = new Card(UUID.fromString("6be896e4-08ae-11eb-adc1-0242ac120002"), 123123, UUID.fromString("14aa7075-7077-49d6-a621-9d91fa65ef79"), BigDecimal.valueOf(100));
    Card card2 = new Card(UUID.fromString("703842c6-08ae-11eb-adc1-0242ac120002"), 123123, UUID.fromString("14aa7075-7077-49d6-a621-9d91fa65ef79"), BigDecimal.valueOf(200));
    Card card3 = new Card(UUID.fromString("72f3fc9e-08ae-11eb-adc1-0242ac120002"), 123123, UUID.fromString("14aa7075-7077-49d6-a621-9d91fa65ef79"), BigDecimal.valueOf(300));

    @Before
    public void setUp() throws Exception {
        Config.initDatabase();
    }

    @Test
    public void clearCards() {
        storage.saveCard(card1);
        storage.saveCard(card2);
        storage.saveCard(card3);
        assertEquals(5, storage.getAllCardsSorted().size());
        storage.clearCards();
        assertEquals(0, storage.getAllCardsSorted().size());
    }

    @Test
    public void saveCard() {
        storage.saveCard(card1);
        assertEquals(card1.getId(), storage.getCard(UUID.fromString("6be896e4-08ae-11eb-adc1-0242ac120002")).getId());
        assertEquals(3, storage.getAllCardsSorted().size());
    }

    @Test
    public void updateCardBalance() {
        Card card = storage.getCard(UUID.fromString("4c0f8a96-e193-4a92-bd34-9dcfb02c276a"));
        card.changeBalance((SQLStorage) storage, BigDecimal.valueOf(10055));
        assertEquals(card.getBalance(), storage.getCard(UUID.fromString("4c0f8a96-e193-4a92-bd34-9dcfb02c276a")).getBalance());
    }

    @Test
    public void getCard() {
        storage.saveCard(card1);
        assertEquals(storage.getCard(UUID.fromString("6be896e4-08ae-11eb-adc1-0242ac120002")).getId(), card1.getId());
    }

    @Test
    public void getAllCardsSorted() {
        List<Card> list = storage.getAllCardsSorted();
        assertEquals(2, list.size());
        storage.saveCard(card1);
        storage.saveCard(card2);
        list = storage.getAllCardsSorted();
        assertEquals(4, list.size());
    }

    @Test
    public void deleteCard() {
        storage.saveCard(card1);
        assertEquals(3, storage.getNumberOfCards());
        storage.deleteCard(card1.getId());
        assertEquals(2, storage.getNumberOfCards());
    }

    @Test
    public void getNumberOfCards() {
        assertEquals(storage.getAllCardsSorted().size(), storage.getNumberOfCards());
    }

    @Test
    public void saveClient() {
        storage.saveClient(client1);
        assertEquals(client1.getId(), storage.getClient(UUID.fromString("189daee2-0972-11eb-adc1-0242ac120002")).getId());
        assertEquals(2, storage.getAllClientsSorted().size());
    }

    @Test
    public void updateClient() {
        Client client = storage.getClient(UUID.fromString("7ae53c7b-0002-483e-897d-3e21f8ca16eb"));
        client.setName("Vladislav");
        client.setSurname("Gubernik");
        storage.updateClient(client);
        assertEquals(client.getName(), storage.getClient(UUID.fromString("7ae53c7b-0002-483e-897d-3e21f8ca16eb")).getName());
    }

    @Test
    public void getAllClientsSorted() {
        List<Client> list = storage.getAllClientsSorted();
        assertEquals(1, list.size());
        storage.saveClient(client1);
        list = storage.getAllClientsSorted();
        assertEquals(2, list.size());
    }

    @Test
    public void deleteClient() {
        storage.saveClient(client1);
        assertEquals(2, storage.getAllClientsSorted().size());
        storage.deleteClient(client1.getId());
        assertEquals(1, storage.getAllClientsSorted().size());
    }

    @Test
    public void saveAccount() {
        storage.saveAccount(account1);
        assertEquals(account1.getId(), storage.getAccount(UUID.fromString("34b8817e-0972-11eb-adc1-0242ac120002")).getId());
        assertEquals(2, storage.getAllAccountsSorted().size());
    }

    @Test
    public void deleteAccount() {
        storage.saveAccount(account1);
        assertEquals(2, storage.getAllAccountsSorted().size());
        storage.deleteAccount(account1.getId());
        assertEquals(1, storage.getAllAccountsSorted().size());
    }
}