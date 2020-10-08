package main.webapp.app.storage;

import main.webapp.app.model.Account;
import main.webapp.app.model.Client;
import main.webapp.app.model.Card;

import java.util.List;
import java.util.UUID;

public interface Storage {

    void clearCards();

    void saveCard(Card c);

    void updateCardBalance(Card c);

    Card getCard(UUID id);

    List<Card> getAllCardsSorted();

    void deleteCard(UUID id);

    int getNumberOfCards();

    void saveClient(Client a);

    void updateClient(Client a);

    Client getClient(UUID id);

    List<Client> getAllClientsSorted();

    void deleteClient(UUID id);

    void saveAccount(Account a);

    Account getAccount(UUID id);

    List<Account> getAllAccountsSorted();

    void deleteAccount(UUID id);
}
