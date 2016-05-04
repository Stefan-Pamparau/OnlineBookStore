package com.iquestgroup.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {
    private Integer id;

    @NotNull
    private String title;

    @NotNull
    @Size(min = 3, max = 20, message = "Should contain a minimum of 3 character and a maximum of 20 characters")
    private String genre;

    @NotNull
    private Integer inStock;

    @NotNull
    private Integer price;

    private Author author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (genre != null ? !genre.equals(book.genre) : book.genre != null) return false;
        if (inStock != null ? !inStock.equals(book.inStock) : book.inStock != null) return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        return author != null ? author.equals(book.author) : book.author == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (inStock != null ? inStock.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
