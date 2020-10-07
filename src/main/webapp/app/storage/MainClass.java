package main.webapp.app.storage;

import main.webapp.app.Config;
import main.webapp.app.model.Account;
import main.webapp.app.model.Card;
import main.webapp.app.model.Client;

import java.util.UUID;

public class MainClass {
    public static void main(String[] args) {
        Config.initDatabase();
        Storage storage = new SQLStorage("jdbc:h2:mem:storage", "root", "password");

        Client client1 = new Client(UUID.fromString("1d664308-0887-11eb-adc1-0242ac120002"), "Ivan", "Ivanov");
        Account account1 = new Account(UUID.fromString("90350cb2-0886-11eb-adc1-0242ac120002"), 123321, client1.getId());
        Card card1 = new Card(UUID.fromString("9b2661d4-0886-11eb-adc1-0242ac120002"), 123123, UUID.fromString("14aa7075-7077-49d6-a621-9d91fa65ef79"), 120);

        System.out.println(storage.getCard(UUID.fromString("4c0f8a96-e193-4a92-bd34-9dcfb02c276a")).getBalance());
        storage.saveCard(card1);
        System.out.println(storage.getCard(UUID.fromString("9b2661d4-0886-11eb-adc1-0242ac120002")).getBalance());

        System.out.println(account1.getId().toString().equals(card1.getAccount_id().toString()));
        System.out.println("Account ID: " + account1.getId().toString() + " and Card_ID: " + card1.getId());
        System.out.println("Hello");
    }
}
