import java.util.ArrayList;

abstract class Library {
    public ArrayList<Book> books = new ArrayList<Book>();
    public ArrayList<Member> members = new ArrayList<Member>();

    // Abstract
    public abstract void addMember(Member member);
    public abstract void giveBook(String bookId, String memberId);
    public abstract void receiveBook(String bookId, String memberId);

    // Overloading
    public void addMember(String name, String id) {
        Member member = new Member();
        this.members.add(member);
    }

    protected int getMemberIndex(Member member) {
        return this.members.indexOf(member);
    }

    protected Member getMemberById(String id) {
        for (Member member : this.members) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }

    protected Book getBookById(String id) {
        for (Book book : this.books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    protected Boolean isBookIdExist(String id) {
        Boolean isExist = false;
        for (Book book : this.books) {
            if (book.getId().equals(id)) {
                isExist = true;
            }
        }
        return isExist;
    }
}

// Interaction
class warisan extends Library {
    @Override
    public void addMember(Member member) {
        this.members.add(member);
    }

    @Override
    public void giveBook(String bookId, String memberId) {
        Book book = this.getBookById(bookId);

        Member member = this.getMemberById(memberId);
        int memberIndex = this.getMemberIndex(member);

        this.books.remove(book);
        this.members.get(memberIndex).borrowedBooks.add(book);
    }

    @Override
    public void receiveBook(String bookId, String memberId) {
        Member member = this.getMemberById(memberId);
        int memberIndex = this.getMemberIndex(member);

        Book book = this.members.get(memberIndex).getBookById(bookId);
        this.books.add(book);
        this.members.get(memberIndex).borrowedBooks.remove(book);
    }
}
