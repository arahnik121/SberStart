package main.webapp.app.model;

import main.webapp.app.storage.SQLStorage;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;


public class Card implements Comparable<Card>, Serializable {
    private UUID id;
    private int number;
    private UUID account_id;
    private BigDecimal balance;

    public Card(UUID id, int number, UUID account_id, BigDecimal balance) {
        this.id = id;
        this.number = number;
        this.account_id = account_id;
        this.balance = balance;
    }

    private void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public UUID getAccount_id() {
        return account_id;
    }

    public void setAccount_id(UUID account_id) {
        this.account_id = account_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void changeBalance(SQLStorage storage, BigDecimal money) {
        setBalance(getBalance().add(money));
        storage.updateCard(this);
    }

    @Override
    public int compareTo(@NotNull Card card) {
        int cmp = id.compareTo(card.id);
        return cmp != 0 ? cmp : id.compareTo(card.id);
    }
}
