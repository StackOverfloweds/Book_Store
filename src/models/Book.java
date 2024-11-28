package models;

public class Book extends Identifiable {
    private String title;
    private String author;
    private String category;
    private String isbn;
    private int stock;
    private double price;

    public Book(int id, String title, String author, String category, String isbn, int stock, double price) {
        super(id);
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.stock = stock;
        this.price = price;
    }

     // Constructor without id (for adding new books)
     public Book(String title, String author, String category, String isbn, int stock, double price) {
        super(0);  // Set id as 0 (for new books; database can auto-generate)
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.stock = stock;
        this.price = price;
    }

    
    // Getters and Setters...
 
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

       

    @Override
    public String toString() {
        return "Book{" +
               "id=" + getId() +
               ", title='" + title + '\'' +
               ", author='" + author + '\'' +
               ", category='" + category + '\'' +
               ", isbn='" + isbn + '\'' +
               ", stock=" + stock +
               ", price=" + price +
               '}';
    }

}
