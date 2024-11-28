package models;

import java.time.LocalDateTime;

public class Sale extends Identifiable {
    private User seller;
    private Book book;
    private String customer;
    private LocalDateTime time;
    private double total;

    public Sale(int id, User seller, Book book, String customer, LocalDateTime time, double total) {
        super(id);
        this.seller = seller;
        this.book = book;
        this.customer = customer;
        this.time = time;
        this.total = total;
    }


    

    // Getters and Setters...
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Sale{" +
               "id=" + getId() +
               ", seller=" + seller +
               ", book=" + book +
               ", customer=" + customer +
               ", time=" + time +
               ", total=" + total +
               '}';
    }
}
