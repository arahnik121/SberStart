package main.webapp.app.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Card implements Comparable<Card>, Serializable {
    private String id;
    private int number;
    private String account_id;

    public Card(String id, int number, String account_id) {
        this.id = id;
        this.number = number;
        this.account_id = account_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(@NotNull Card card) {
        int cmp = id.compareTo(card.id);
        return cmp != 0 ? cmp : id.compareTo(card.id);
    }
}
