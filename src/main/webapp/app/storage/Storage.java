package main.webapp.app.storage;

import main.webapp.app.model.Account;
import main.webapp.app.model.Client;
import main.webapp.app.model.Card;

import java.util.List;

public interface Storage {

    void clearCards();

    void saveCard(Account a, Card c);

    void updateCard(Card c);

    Card getCard(String id);

    List<Card> getAllCardsSorted();

    void deleteCard(int id);

    int getNumberOfCards();

    void saveClient(Client a);

    void updateClient(Client a);

    List<Client> getAllClientsSorted();

}
