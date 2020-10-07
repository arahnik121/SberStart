package main.webapp.app.storage;

import main.webapp.app.Config;
import main.webapp.app.model.Account;
import main.webapp.app.model.Card;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class SQLStorageTest {
    protected Storage storage = new SQLStorage("jdbc:h2:mem:storage1", "root", "password");
    Card card1 = new Card(UUID.fromString("6be896e4-08ae-11eb-adc1-0242ac120002"), 123123, UUID.fromString("14aa7075-7077-49d6-a621-9d91fa65ef79"), 100);
    Card card2 = new Card(UUID.fromString("703842c6-08ae-11eb-adc1-0242ac120002"), 123123, UUID.fromString("14aa7075-7077-49d6-a621-9d91fa65ef79"), 200);
    Card card3 = new Card(UUID.fromString("72f3fc9e-08ae-11eb-adc1-0242ac120002"), 123123, UUID.fromString("14aa7075-7077-49d6-a621-9d91fa65ef79"), 300);

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
    public void updateCard() {
    }

    @Test
    public void getCard() {
    }

    @Test
    public void getAllCardsSorted() {
    }

    @Test
    public void deleteCard() {
    }

    @Test
    public void getNumberOfCards() {
    }

}