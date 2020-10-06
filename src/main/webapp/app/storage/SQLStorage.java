package main.webapp.app.storage;

import main.webapp.app.model.Account;
import main.webapp.app.model.Card;
import main.webapp.app.model.Client;
import main.webapp.app.sql.ConnectionFactory;
import main.webapp.app.sql.SQLHelper;
import main.webapp.app.sql.SQLTransaction;

import java.sql.*;
import java.util.List;
import java.util.UUID;

public class SQLStorage implements Storage {
    public final SQLHelper sqlHelper;

    public SQLStorage(final String dbUrl, final String dbUser, final String dbPassword) {
        this.sqlHelper = new SQLHelper(new ConnectionFactory() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }
        });
    }

    @Override
    public void clearCards() {
        sqlHelper.execute("DELETE FROM STORAGE.PUBLIC.CARD");
    }

    @Override
    public void saveCard(Account a, final Card c) {
        sqlHelper.transactionalExecute(new SQLTransaction<Object>() {
            @Override
            public Object wrap(Connection conn) throws SQLException {
                try (PreparedStatement ps = conn.prepareStatement("INSERT INTO CARD (id, NUMBER, ACCOUNT_ID) VALUES (?, ?, ?)")) {
                    ps.setString(1, c.getId().toString());
                    ps.setInt(2, c.getNumber());
                    ps.setString(3, c.getAccount_id().toString());
                    ps.execute();
                    return null;
                }
            }
        });
    }

    @Override
    public void updateCard(Card c) {
        sqlHelper.transactionalExecute(new SQLTransaction<Object>() {
            @Override
            public Object wrap(Connection conn) throws SQLException {
                try (PreparedStatement ps = conn.prepareStatement("UPDATE " )) {
                    return null;
                }
            }
        });
    }

            @Override
            public Card getCard(String id) {
                Card card = new Card();
                sqlHelper.transactionalExecute(new SQLTransaction<Object>() {

                    @Override
                    public Object wrap(Connection conn) throws SQLException {
                        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM CARD WHERE id = ?")) {
                            ps.setString(1, id);
                            ResultSet rs = ps.executeQuery();
                            rs.next();

                            card.setId(rs.getObject("id", UUID.class));
                            card.setAccount_id(rs.getObject("account_id", UUID.class));
                            card.setNumber(rs.getInt("number"));
                            System.out.println(card.getNumber());
                            return card;
                        }
                    }
                });
                return card;
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