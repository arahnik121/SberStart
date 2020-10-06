package main.webapp.app.model;

public class Account {
    private String id;
    private int number;
    private String client_id;

    public Account(String id, int number) {
        this.id = id;
        this.number = number;
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
}
