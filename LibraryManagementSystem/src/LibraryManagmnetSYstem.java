import java.util.*;

class BookInfo{ // All Book attributes in this class
    // all attributes
    private int id;
    private String title;
    private String author;
    private int publicationYear;

    public BookInfo(int id, String title, String author, int publicationYear){
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
    //through this we can call the private variable outside the class
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public int getPublicationYear(){
        return publicationYear;
    }

    @Override
    public String toString(){ // For Print book detail
        return "Book { " + 
        "ID = " + id + " | "+
        ", Title = " + title + " | "+
        ", Author = " + author + " | "+
        ", Publication Year = " + publicationYear +
        "}";
    }

}
// Main Class
public class LibraryManagmnetSYstem {
    private List<BookInfo> books;

    public LibraryManagmnetSYstem(){
        this.books = new ArrayList<>();
    }
    // Functions
    public void addBook(BookInfo book){
        books.add(book);
    }

    public void viewAllBooks(){
        for(BookInfo book : books){
            System.out.println(book.toString());
        }
    }

    public void deleteBook(int ID){
        for(BookInfo book : books){
            if(book.getId() == ID){
                books.remove(book);
                System.out.println("Book with ID " + ID + "is Deleted Succesfully");
                return;
            }
        }
        System.out.println("Book with ID "+ ID + "is not found.");
    }

    public static void main(String[] args) {
        LibraryManagmnetSYstem Library = new LibraryManagmnetSYstem(); // New instance for Library book
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("------------------------------------------------");
            System.out.println("Library Management System");
            System.out.println("1. Add a new book");
            System.out.println("2. View all books");
            System.out.println("3. Delete a book by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
        
            int choice = sc.nextInt();
            sc.nextLine();
        
            switch (choice) {
                case 1 : 
                    System.out.print("Enter Book ID : ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter the Title of Book : ");
                    String title = sc.nextLine();
                    System.out.print("Enter the author name : ");
                    String author = sc.nextLine();
                    System.out.print("Enter the publication Year : ");
                    int publicationYear =  sc.nextInt();

                    BookInfo book = new BookInfo(id, title, author, publicationYear);
                    Library.addBook(book);

                    break;
                case 2 :
                    System.out.println("------------------------------------------------");
                    Library.viewAllBooks();
                    break;
                case 3 :
                    System.out.print("Enter the ID to delete : ");
                    int IDdelete = sc.nextInt();
                    Library.deleteBook(IDdelete);
                    break;
                case 4 :
                    sc.close();
                    System.exit(0);
                break;

                default:
                    System.out.println("Invalid choice. Pls try again...");   
            }
        }
    }
}