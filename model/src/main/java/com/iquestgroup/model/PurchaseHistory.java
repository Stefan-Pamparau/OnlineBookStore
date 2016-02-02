package com.iquestgroup.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "purchase_history")
@IdClass(PurchaseHistoryId.class)
public class PurchaseHistory implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "client_id")
    private Integer clientId;
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "purchase_date")
    private Timestamp purchaseDate;

    @ManyToOne
    @JoinColumn(name = "client_id", updatable = false, insertable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "book_id", updatable = false, insertable = false)
    private Book book;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseHistory that = (PurchaseHistory) o;

        if (clientId != null ? !clientId.equals(that.clientId) : that.clientId != null) return false;
        if (bookId != null ? !bookId.equals(that.bookId) : that.bookId != null) return false;
        if (purchaseDate != null ? !purchaseDate.equals(that.purchaseDate) : that.purchaseDate != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        return book != null ? book.equals(that.book) : that.book == null;

    }

    @Override
    public int hashCode() {
        int result = clientId != null ? clientId.hashCode() : 0;
        result = 31 * result + (bookId != null ? bookId.hashCode() : 0);
        result = 31 * result + (purchaseDate != null ? purchaseDate.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        return result;
    }
}
