package main.webapp.app.storage;

import main.webapp.app.exceptions.NotExistStorageException;
import main.webapp.app.model.Account;
import main.webapp.app.model.Card;
import main.webapp.app.model.Client;
import main.webapp.app.sql.ConnectionFactory;
import main.webapp.app.sql.SQLExecutor;
import main.webapp.app.sql.SQLHelper;
import main.webapp.app.sql.SQLTransaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SQLStorage implements Storage {
    public final SQLHelper sqlHelper;

    public SQLStorage(final String dbUrl, final String dbUser, final String dbPassword) {
        try {
            Class.forName("org.h2.Driver");
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
    public void saveCard(Card c) {
        sqlHelper.transactionalExecute(new SQLTransaction<Object>() {
            @Override
            public Object wrap(Connection conn) throws SQLException {
                try (PreparedStatement ps = conn.prepareStatement("INSERT INTO CARD (id, NUMBER, ACCOUNT_ID, BALANCE) VALUES (?, ?, ?, ?)")) {
                    ps.setString(1, c.getId().toString());
                    ps.setInt(2, c.getNumber());
                    ps.setString(3, c.getAccount_id().toString());
                    ps.setFloat(4, c.getBalance());
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
                try (PreparedStatement ps = conn.prepareStatement("UPDATE CARD SET BALANCE = ? where ID = ?")) {
                    ps.setFloat(1, c.getBalance());
                    ps.setString(2, c.getId().toString());
                    ps.execute();
                    if (ps.executeUpdate() == 0) {
                        throw new NotExistStorageException(c.getId().toString());
                    }
                    return null;
                }
            }
        });
    }

    @Override
    public Card getCard(UUID id) {
        return sqlHelper.execute("SELECT * FROM CARD a where a.id = ?", new SQLExecutor<Card>() {
            @Override
            public Card wrap(PreparedStatement ps) throws SQLException {
                ps.setString(1, id.toString());
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(id.toString());
                }
                return new Card(id, rs.getInt("number"), rs.getObject("account_id", UUID.class), rs.getFloat("balance"));
            }
        });
    }

    @Override
    public void deleteCard(UUID id) {
        sqlHelper.execute("DELETE FROM CARD where ID = ?", new SQLExecutor<Object>() {
            @Override
            public Object wrap(PreparedStatement ps) throws SQLException {
                ps.setString(1, id.toString());
                ps.executeUpdate();
                return null;
            }
        });
    }

    @Override
    public List<Card> getAllCardsSorted() {
        return sqlHelper.execute("SELECT * FROM CARD ORDER BY ID", new SQLExecutor<List<Card>>() {
            @Override
            public List<Card> wrap(PreparedStatement ps) throws SQLException {
                ResultSet rs = ps.executeQuery();
                List<Card> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new Card(rs.getObject("id", UUID.class), rs.getInt("number"), rs.getObject("account_id", UUID.class), rs.getFloat("balance")));
                }
                return list;
            }
        });
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