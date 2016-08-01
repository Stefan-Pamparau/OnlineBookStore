package com.iquestgroup.database.builder;

import com.iquestgroup.model.Book;

/**
 * Builder used to create {@link com.iquestgroup.model.Book} instances.
 *
 * @author Stefan Pamparau
 */
public class BookBuilder {
    private Integer id;
    private String title;
    private String genre;
    private Integer inStock;
    private Integer price;

    public BookBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public BookBuilder setInStock(Integer inStock) {
        this.inStock = inStock;
        return this;
    }

    public BookBuilder setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Book build() {
        Book book = new Book();

        book.setId(id);
        book.setTitle(title);
        book.setGenre(genre);
        book.setInStock(inStock);
        book.setPrice(price);

        return book;
    }

    public Book build(Integer id, String title, String genre, Integer inStock, Integer price) {
        Book book = new Book();

        book.setId(id);
        book.setTitle(title);
        book.setGenre(genre);
        book.setInStock(inStock);
        book.setPrice(price);

        return book;
    }
}
