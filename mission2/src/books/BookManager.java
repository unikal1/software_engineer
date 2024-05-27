package books;

import java.util.*;
import exception.DuplicateValueException;

public class BookManager {
	private List<Book> books = new ArrayList<>();

    public void addBook(Book book) throws DuplicateValueException {
        if(!isAlreadyExist(book.getId())) {
            books.add(book);
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
    
    public Optional<Book> search_bs(int id) {
        int left = 0;
        int right = books.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Book midBook = books.get(mid);

            if (midBook.getId() == id) {
                return Optional.of(midBook);
            }

            if (midBook.getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return Optional.empty();
    }
    
    private boolean isAlreadyExist(int id) {
        if(books.isEmpty())
            return false;

        if(search_bs(id).isPresent()) { return true; }
        else { return false; }
    }


}
