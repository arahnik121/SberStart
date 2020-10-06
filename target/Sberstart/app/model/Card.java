package main.webapp.app.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Card implements Comparable<Card>, Serializable {
    private String id;
    private int number;
    private float money;

    public Card(String id, int number, float money) {
        this.id = id;
        this.number = number;
        this.money = money;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
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
