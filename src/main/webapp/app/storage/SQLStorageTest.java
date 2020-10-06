package main.webapp.app.storage;

import main.webapp.app.model.Account;
import main.webapp.app.model.Card;
import org.junit.Before;

import static org.junit.Assert.*;

public class SQLStorageTest {
    protected SQLStorage storage;

//    @Before
//    public void setUp() throws Exception {
//        Card card = new Card("4c0f8a96-e193-4a92-bd34-9dcfb02c2777", 427655014, "4c0fsd96-e193-4a92-df34-9dcfb02c276a");
//        Account account1 = new Account("321", 123);
//        storage.saveCard(account1, card);
//    }

    @org.junit.Test
    public void clearCards() {
        storage.clearCards();
    }

    @org.junit.Test
    public void saveCard() {
    }

    @org.junit.Test
    public void updateCard() {
    }

    @org.junit.Test
    public void getCard() {
    }

    @org.junit.Test
    public void getAllCardsSorted() {
    }

    @org.junit.Test
    public void deleteCard() {
    }

    @org.junit.Test
    public void getNumberOfCards() {
    }

    @org.junit.Test
    public void saveClient() {
    }

    @org.junit.Test
    public void updateClient() {
    }

    @org.junit.Test
    public void getAllClientsSorted() {
    }
}