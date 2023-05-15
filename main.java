import java.util.HashSet;
import java.util.Scanner;

public class Main {
  static Scanner scan = new Scanner(System.in);
  static Library library = new Warisan();

  public static void main(String[] args) {
    initLibraryData();

    String isContinue = "y";

    while (isContinue.equals("y")) {
      showMenu();
      int selectedMenu = chooseMenu();

      if (selectedMenu == 1) {
        showBooks();
      } else if (selectedMenu == 2) {
        showMembers();
      } else if (selectedMenu == 3) {
        addMember();
      } else if (selectedMenu == 4) {
        borrowBook();
      } else if (selectedMenu == 5) {
        returnBook();
      } else if (selectedMenu == 6) {
        addBook();
      } else {
        System.out.println("Wrong input");
      }

      System.out.print("Continue? (y/n) ");
      isContinue = scan.next().toLowerCase();
    }
  }

  public static void showMenu() {
    System.out.println("================================");
    System.out.println("1. Show books list");
    System.out.println("2. Show members list");
    System.out.println("3. Add member");
    System.out.println("4. Borrow book");
    System.out.println("5. Return book");
    System.out.println("6. Add book");
    System.out.println("================================");
  }

  public static void initLibraryData() {
    Book book1 = new Book();
    book1.setId("1");
    book1.setTitle("Pemrograman Java");

    Book book2 = new Book();
    book2.setId("2");
    book2.setTitle("Pemrograman OOP");

    Book book3 = new Book();
    book3.setId("3");
    book3.setTitle("Pemrograman Android");

    Member member1 = new Member();
    member1.setId("1");
    member1.setName("Aka");

    Member member2 = new Member();
    member2.setId("2");
    member2.setName("Budi");

    Member member3 = new Member();
    member3.setId("3");
    member3.setName("Tono");

    library.addBook(book1);
    library.addBook(book2);
    library.addBook(book3);

    library.addMember(member1);
    library.addMember(member2);
    library.addMember(member3);
  }

  public static int chooseMenu() {
    System.out.print("Choose menu: ");
    int selectedMenu = scan.nextInt();
    return selectedMenu;
  }

  public static void showBooks() {
    for (Book book : library.getBooks()) {
      System.out.println(book.getId() + " " + book.getTitle());
    }
  }

  public static void showMembers() {
    for (Member member : library.getMembers()) {
      System.out.println(member.getId() + " " + member.getName());
    }
  }

  public static void addMember() {
    Member member = new Member();

    HashSet<String> memberIdSet = new HashSet<String>();
    for (Member user : library.getMembers()) {
      memberIdSet.add(user.getId());
    }

    System.out.print("ID: ");
    String memberId = scan.next();

    while (memberIdSet.contains(memberId)) {
      System.out.println("ID already exists");
      System.out.print("ID: ");
      memberId = scan.next();
    }

    member.setId(memberId);
    System.out.print("Name: ");
    member.setName(scan.next());

    library.addMember(member);
    System.out.println("Member added successfully.");
  }

  public static void borrowBook() {
    System.out.print("Enter member ID: ");
    String memberId = scan.next();

    System.out.print("Enter book ID: ");
    String bookId = scan.next();

    library.giveBook(bookId, memberId);
}

public static void returnBook() {
    System.out.print("Enter member ID: ");
    String memberId = scan.next();

    System.out.print("Enter book ID: ");
    String bookId = scan.next();

    library.receiveBook(bookId, memberId);
}

public static void addBook() {
    Book book = new Book();

    try {
        do {
            System.out.print("Enter book ID: ");
            String bookId = scan.next();
            
            if (library.isBookIdExist(bookId)) {
                System.out.println("Book ID already exists. Please try again.");
            } else {
                book.setId(bookId);
            }
        } while (book.getId() == null);

        System.out.print("Enter book title: ");
        String bookTitle = scan.next();
        book.setTitle(bookTitle);

        library.addBook(book);
        System.out.println("Book added successfully.");
    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}

