package main.webapp.app.storage;

import main.webapp.app.Config;
import main.webapp.app.model.Card;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*;

public class SQLStorageTest {
    protected Storage storage = new SQLStorage("jdbc:h2:mem:storage1", "root", "password");
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
        Assert.assertEquals(5, storage.getAllCardsSorted().size());
        storage.clearCards();
        Assert.assertEquals(0, storage.getAllCardsSorted().size());
    }

    @Test
    public void saveCard() {
        storage.saveCard(card1);
        Assert.assertEquals(card1.getId(), storage.getCard(UUID.fromString("6be896e4-08ae-11eb-adc1-0242ac120002")).getId());
        Assert.assertEquals(3, storage.getAllCardsSorted().size());
    }

    @Test
    public void updateCardBalance() {
        Card card = storage.getCard(UUID.fromString("4c0f8a96-e193-4a92-bd34-9dcfb02c276a"));
        card.changeBalance((SQLStorage) storage, BigDecimal.valueOf(10055));
        Assert.assertEquals(card.getBalance(), storage.getCard(UUID.fromString("4c0f8a96-e193-4a92-bd34-9dcfb02c276a")).getBalance());
    }

    @Test
    public void getCard() {
        storage.saveCard(card1);
        Assert.assertEquals(storage.getCard(UUID.fromString("6be896e4-08ae-11eb-adc1-0242ac120002")).getId(), card1.getId());
    }

    @Test
    public void getAllCardsSorted() {
        List<Card> list = storage.getAllCardsSorted();
        Assert.assertEquals(2, list.size());
        storage.saveCard(card1);
        storage.saveCard(card2);
        list = storage.getAllCardsSorted();
        Assert.assertEquals(4, list.size());
    }

    @Test
    public void deleteCard() {
        storage.saveCard(card1);
        Assert.assertEquals(3, storage.getNumberOfCards());
        storage.deleteCard(UUID.fromString("6be896e4-08ae-11eb-adc1-0242ac120002"));
        Assert.assertEquals(2, storage.getNumberOfCards());
    }

    @Test
    public void getNumberOfCards() {
        Assert.assertEquals(storage.getAllCardsSorted().size(), storage.getNumberOfCards());
    }

}