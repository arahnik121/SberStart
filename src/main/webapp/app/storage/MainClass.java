package main.webapp.app.storage;

import main.webapp.app.Config;
import main.webapp.app.model.Card;

public class MainClass {
    public static void main(String[] args) {
        Storage storage = new SQLStorage("jdbc:h2:mem:storage", "root", "password");
        Config.initDatabase();
        //storage.clearCards();
        storage.getCard("4c0f8a96-e193-4a92-bd34-9dcfb02c276a");
    }
}
