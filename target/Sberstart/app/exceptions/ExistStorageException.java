package main.webapp.app.exceptions;

public class ExistStorageException extends main.webapp.app.exceptions.StorageException {
    public ExistStorageException(String id) {
        super("Card " + id + " already exist", id);
    }
}
