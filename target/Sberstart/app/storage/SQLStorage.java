package main.webapp.app.storage;

import main.webapp.app.model.Account;
import main.webapp.app.model.Card;
import main.webapp.app.exceptions.NotExistStorageException;
import main.webapp.app.model.Client;
import main.webapp.app.sql.ConnectionFactory;
import main.webapp.app.sql.SQLExecutor;
import main.webapp.app.sql.SQLHelper;
import main.webapp.app.sql.SQLTransaction;
import main.webapp.app.storage.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLStorage implements Storage {
    public final SQLHelper sqlHelper;

    public SQLStorage(final String dbUrl, final String dbUser, final String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        this.sqlHelper = new SQLHelper(new ConnectionFactory() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }
        });
    }

    @Override
    public void clearCards() {
        sqlHelper.execute("DELETE FROM CARD");
    }

    @Override
    public void saveCard(Account a, Card c) {

    }

    @Override
    public void updateCard(Card c) {

    }

    @Override
    public Card getCard(int id) {
        return null;
    }

    @Override
    public List<Card> getAllCardsSorted() {
        return null;
    }

    @Override
    public void deleteCard(int id) {

    }

    @Override
    public int getNumberOfCards() {
        return 0;
    }

    @Override
    public void saveClient(Client a) {

    }

    @Override
    public void updateClient(Client a) {

    }

    @Override
    public List<Client> getAllClientsSorted() {
        return null;
    }
}