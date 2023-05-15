import java.util.ArrayList;

public class Member {
    private String id;
    private String name;
    public ArrayList<Book> borrowedBooks = new ArrayList<Book>();

    public void receiveBook(Book book) {
        this.borrowedBooks.add(book);
    }

    public void giveBook(Book book) {
        this.borrowedBooks.remove(book);
    }
    
    // Encapsulation
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    // Check array
    public Book getBookById(String Id) {
        for (Book book : this.borrowedBooks) {
            if (book.getId().equals(Id)) {
                return book;
            }
        }
        return null;
    }
}
