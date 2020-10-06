package main.webapp.app.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;


public class Card implements Comparable<Card>, Serializable {
    private UUID id;
    private int number;
    private UUID account_id;

    public Card() {

    }


    public Card(UUID id, int number, UUID account_id) {
        this.id = id;
        this.number = number;
        this.account_id = account_id;
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

    @Override
    public int compareTo(@NotNull Card card) {
        int cmp = id.compareTo(card.id);
        return cmp != 0 ? cmp : id.compareTo(card.id);
    }
}
