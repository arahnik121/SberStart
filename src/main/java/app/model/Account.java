package app.model;

import java.util.UUID;

public class Account {
    private UUID id;
    private int number;
    private UUID client_id;

    public Account(UUID id, int number, UUID client_id) {
        this.id = id;
        this.number = number;
        this.client_id = client_id;
    }

    public UUID getClient_id() {
        return client_id;
    }

    public void setClient_id(UUID client_id) {
        this.client_id = client_id;
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
}
