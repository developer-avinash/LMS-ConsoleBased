package com.avi.librarymanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    private int id;

    private String title;

    private String author;

    private String category;

    private double price;

    private int quantity;

    public Book() {
    }

    public Book(
            int id,
            String title,
            String author,
            String category,
            double price,
            int quantity
    ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {

        return "\nBook Details" +
                "\n------------------------" +
                "\nID       : " + id +
                "\nTitle    : " + title +
                "\nAuthor   : " + author +
                "\nCategory : " + category +
                "\nPrice    : " + price +
                "\nQuantity : " + quantity;
    }
}