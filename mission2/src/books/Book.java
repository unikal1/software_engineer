package books;

public class Book {
    private final int id;
    private final String title;
    private final String author;
    private final int year;

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getYear() {
        return year;
    }
    public String printInfo() {
        return String.format(
                "Book{id: '%d', 제목: '%s', 저자: '%s', 출판년도: %d}",
                id, title, author, year);
    }


}
