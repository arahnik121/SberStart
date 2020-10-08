package app.storage;
import app.exceptions.NotExistStorageException;
import app.model.Account;
import app.model.Card;
import app.model.Client;
import app.sql.ConnectionFactory;
import app.sql.SQLExecutor;
import app.sql.SQLHelper;
import app.sql.SQLTransaction;

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
                    ps.setBigDecimal(4, c.getBalance());
                    ps.execute();
                    return null;
                }
            }
        });
    }

    @Override
    public void updateCardBalance(Card c) {
        sqlHelper.transactionalExecute(new SQLTransaction<Object>() {
            @Override
            public Object wrap(Connection conn) throws SQLException {
                try (PreparedStatement ps = conn.prepareStatement("UPDATE CARD SET BALANCE = ? where ID = ?")) {
                    ps.setBigDecimal(1, c.getBalance());
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
                return new Card(id, rs.getInt("number"), rs.getObject("account_id", UUID.class), rs.getBigDecimal("balance"));
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
                    list.add(new Card(rs.getObject("id", UUID.class), rs.getInt("number"), rs.getObject("account_id", UUID.class), rs.getBigDecimal("balance")));
                }
                return list;
            }
        });
    }

    @Override
    public int getNumberOfCards() {
        List<Card> list = this.getAllCardsSorted();
        return list.size();
    }

    @Override
    public void saveClient( Client a) {
        sqlHelper.transactionalExecute(new SQLTransaction<Object>() {
            @Override
            public Object wrap(Connection conn) throws SQLException {
                try (PreparedStatement ps = conn.prepareStatement("INSERT INTO CLIENT (id, NAME, SURNAME) VALUES (?, ?, ?)")) {
                    ps.setString(1, a.getId().toString());
                    ps.setString(2, a.getName());
                    ps.setString(3, a.getSurname());
                    ps.execute();
                    return null;
                }
            }
        });
    }

    @Override
    public void updateClient( Client a) {
        sqlHelper.transactionalExecute(new SQLTransaction<Object>() {
            @Override
            public Object wrap(Connection conn) throws SQLException {
                try (PreparedStatement ps = conn.prepareStatement("UPDATE CLIENT SET NAME = ?, SURNAME = ? where ID = ?")) {
                    ps.setString(1, a.getName());
                    ps.setString(2, a.getSurname());
                    ps.setString(3, a.getId().toString());
                    ps.execute();
                    if (ps.executeUpdate() == 0) {
                        throw new NotExistStorageException(a.getId().toString());
                    }
                    return null;
                }
            }
        });
    }

    @Override
    public Client getClient( UUID id) {
        return sqlHelper.execute("SELECT * FROM CLIENT where id = ?", new SQLExecutor<Client>() {
            @Override
            public Client wrap(PreparedStatement ps) throws SQLException {
                ps.setString(1, id.toString());
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(id.toString());
                }
                return new Client(id, rs.getString("name"), rs.getString("surname"));
            }
        });
    }

    @Override
    public List<Client> getAllClientsSorted() {
        return sqlHelper.execute("SELECT * FROM CLIENT ORDER BY ID", new SQLExecutor<List<Client>>() {
            @Override
            public List<Client> wrap(PreparedStatement ps) throws SQLException {
                ResultSet rs = ps.executeQuery();
                List<Client> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new Client(rs.getObject("id", UUID.class), rs.getString("name"), rs.getString("surname")));
                }
                return list;
            }
        });
    }

    @Override
    public void deleteClient( UUID id) {
        sqlHelper.execute("DELETE FROM CLIENT where ID = ?", new SQLExecutor<Object>() {
            @Override
            public Object wrap(PreparedStatement ps) throws SQLException {
                ps.setString(1, id.toString());
                ps.executeUpdate();
                return null;
            }
        });
    }

    @Override
    public void saveAccount(Account a) {
        sqlHelper.transactionalExecute(new SQLTransaction<Object>() {
            @Override
            public Object wrap(Connection conn) throws SQLException {
                try (PreparedStatement ps = conn.prepareStatement("INSERT INTO ACCOUNT (id, NUMBER, CLIENT_ID) VALUES (?, ?, ?)")) {
                    ps.setString(1, a.getId().toString());
                    ps.setInt(2, a.getNumber());
                    ps.setString(3, a.getClient_id().toString());
                    ps.execute();
                    return null;
                }
            }
        });
    }

    @Override
    public Account getAccount(UUID id) {
        return sqlHelper.execute("SELECT * FROM ACCOUNT where id = ?", new SQLExecutor<Account>() {
            @Override
            public Account wrap(PreparedStatement ps) throws SQLException {
                ps.setString(1, id.toString());
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(id.toString());
                }
                return new Account(id, rs.getInt("number"), rs.getObject("client_id", UUID.class));
            }
        });
    }

    @Override
    public List<Account> getAllAccountsSorted() {
        return sqlHelper.execute("SELECT * FROM ACCOUNT ORDER BY ID", new SQLExecutor<List<Account>>() {
            @Override
            public List<Account> wrap(PreparedStatement ps) throws SQLException {
                ResultSet rs = ps.executeQuery();
                List<Account> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new Account(rs.getObject("id", UUID.class), rs.getInt("number"), rs.getObject("client_id", UUID.class)));
                }
                return list;
            }
        });
    }

    @Override
    public void deleteAccount(UUID id) {
        sqlHelper.execute("DELETE FROM ACCOUNT where ID = ?", new SQLExecutor<Object>() {
            @Override
            public Object wrap(PreparedStatement ps) throws SQLException {
                ps.setString(1, id.toString());
                ps.executeUpdate();
                return null;
            }
        });
    }

}