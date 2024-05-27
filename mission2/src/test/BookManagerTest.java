package test;

import exception.DuplicateValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import books.*;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
class BookManagerTest {
    private BookManager bookManager;

    @BeforeEach
    void setup() {
        bookManager = new BookManager();
    }

    @Test
    void testAddBook() {

        Book newBook1 = new Book(1, "자바 기초", "Jane", 2021);
        Book newBook2 = new Book(1, "자바 심화", "Jane", 2022);


        assertDoesNotThrow(() ->
            bookManager.addBook(newBook1)
        );
        System.out.println(newBook1.printInfo() + "도서가 추가되었습니다.");

        DuplicateValueException exception = assertThrows(DuplicateValueException.class, () -> bookManager.addBook(newBook2));
        System.out.println("해당 ID(" + newBook2.getId() + ") 는 이미 존재합니다.");
        System.out.println("검색 결과: \n" + exception.getMessage());
    }

    @Test
    void testSearchBook() {
        //given
        Book newBook1 = new Book(1, "자바 기초", "Jane", 2021);
        Book newBook2 = new Book(2, "소프트웨어 공학", "Tom", 2014);
        Book newBook3 = new Book(3, "분산 컴퓨팅", "Yoon", 2024);

        //when
        assertDoesNotThrow(() ->
            bookManager.addBook(newBook1)
        );
        System.out.println(newBook1.printInfo() + "도서가 추가되었습니다.");
        assertDoesNotThrow(() ->
            bookManager.addBook(newBook2)
        );
        System.out.println(newBook2.printInfo() + "도서가 추가되었습니다.");
        assertDoesNotThrow(() ->
            bookManager.addBook(newBook3)
        );
        System.out.println(newBook3.printInfo() + "도서가 추가되었습니다.");


        //then
        Book searchBook1 = assertDoesNotThrow(() -> {
            return bookManager.searchBook(1).orElseThrow(Exception::new);
                }
        );
        assertEquals(newBook1, searchBook1);
        System.out.println("검색 결과\n" + searchBook1.printInfo());

        Book searchBook2 = assertDoesNotThrow(() -> {
                    return bookManager.searchBook(2).orElseThrow(Exception::new);
                }
        );
        assertEquals(newBook2, searchBook2);
        System.out.println("검색 결과\n" + searchBook2.printInfo());

        Book searchBook3 = assertDoesNotThrow(() -> {
                    return bookManager.searchBook(3).orElseThrow(Exception::new);
                }
        );
        assertEquals(newBook3, searchBook3);
        System.out.println("검색 결과\n" + searchBook3.printInfo());

        assertThrows(Exception.class, () -> bookManager.searchBook(4).orElseThrow(Exception::new));
        System.out.println("검색된 도서가 없습니다.");
    }

    @Test
    void testRemoveBook() {
        Book newBook1 = new Book(1, "자바 기초", "Jane", 2021);
        Book newBook2 = new Book(2, "소프트웨어 공학", "Tom", 2014);

        assertDoesNotThrow(() ->
                bookManager.addBook(newBook1)
        );
        System.out.println(newBook1.printInfo() + "도서가 추가되었습니다.");
        assertDoesNotThrow(() ->
                bookManager.addBook(newBook2)
        );
        System.out.println(newBook2.printInfo() + "도서가 추가되었습니다.");

        assertDoesNotThrow(() ->
                bookManager.removeBook(1));

        System.out.println(newBook1.printInfo() + "도서를 삭제했습니다.");

        assertThrows(NoSuchElementException.class,() ->
                bookManager.removeBook(1));
        System.out.println("해당 ID(1)의 도서를 찾을 수 없습니다.");
        assertThrows(Exception.class, () -> bookManager.searchBook(1).orElseThrow(Exception::new));
        System.out.println("검색된 도서가 없습니다.");
    }
    
    @Test
    void testSearchBookBinarySearch() {
        // given
        Book newBook1 = new Book(1, "자바 기초", "Jane", 2021);
        Book newBook2 = new Book(2, "소프트웨어 공학", "Tom", 2014);
        Book newBook3 = new Book(3, "분산 컴퓨팅", "Yoon", 2024);

        // when
        assertDoesNotThrow(() ->
                bookManager.addBook(newBook1)
        );
        System.out.println(newBook1.printInfo() + " 도서가 추가되었습니다.");
        assertDoesNotThrow(() ->
                bookManager.addBook(newBook2)
        );
        System.out.println(newBook2.printInfo() + " 도서가 추가되었습니다.");
        assertDoesNotThrow(() ->
                bookManager.addBook(newBook3)
        );
        System.out.println(newBook3.printInfo() + " 도서가 추가되었습니다.");

        // then
        Book searchBook1 = assertDoesNotThrow(() -> {
                    return bookManager.search_bs(1).orElseThrow(Exception::new);
                }
        );
        assertEquals(newBook1, searchBook1);
        System.out.println("이진 탐색 검색 결과\n" + searchBook1.printInfo());

        Book searchBook2 = assertDoesNotThrow(() -> {
                    return bookManager.search_bs(2).orElseThrow(Exception::new);
                }
        );
        assertEquals(newBook2, searchBook2);
        System.out.println("이진 탐색 검색 결과\n" + searchBook2.printInfo());

        Book searchBook3 = assertDoesNotThrow(() -> {
                    return bookManager.search_bs(3).orElseThrow(Exception::new);
                }
        );
        assertEquals(newBook3, searchBook3);
        System.out.println("이진 탐색 검색 결과\n" + searchBook3.printInfo());

        assertThrows(Exception.class, () -> bookManager.search_bs(4).orElseThrow(Exception::new));
        System.out.println("이진 탐색으로 검색된 도서가 없습니다.");
    }

}
