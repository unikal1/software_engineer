package books;

import java.util.*;
import exception.DuplicateValueException;

public class BookManager {
	private List<Book> books = new ArrayList<>();

    public void addBook(Book book) throws DuplicateValueException {
        if(!isAlreadyExist(book.getId())) {
            books.add(indexOfBook(book.getId()), book);
            return;
        }
        throw new DuplicateValueException(searchBook(book.getId()).get().printInfo());
    }
    

    public Optional<Book> searchBook(int id) {
        for(Book book : books) {
            if(book.getId() == id) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }
    public void removeBook(int id) {
        Book book = searchBook(id).orElseThrow(NoSuchElementException::new);
        books.remove(book);
    }
    private boolean isAlreadyExist(int id) {
        if(books.isEmpty())
            return false;

        for (Book book : books) {
            if (book.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private int indexOfBook(int id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() > id) {
                return i;
            }
        }
        return books.size();
    }

}
