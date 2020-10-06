package main.webapp.app.storage;

public class MainClass {
    public static void main(String[] args) {
        Storage storage = new SQLStorage("jdbc:h2:mem:storage", "root", "password");
        storage.clearCards();
    }
}
