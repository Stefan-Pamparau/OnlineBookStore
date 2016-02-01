package com.iquestgroup.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

public class PurchaseHistory implements Serializable {
    private Timestamp purchaseDate;
    private Client client;
    private Book book;

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
    public String toString() {
        return "PurchaseHistory{" +
                "purchaseDate=" + purchaseDate +
                ", book=" + book +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseHistory that = (PurchaseHistory) o;

        if (purchaseDate != null ? !purchaseDate.equals(that.purchaseDate) : that.purchaseDate != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        return book != null ? book.equals(that.book) : that.book == null;

    }

    @Override
    public int hashCode() {
        int result = purchaseDate != null ? purchaseDate.hashCode() : 0;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        return result;
    }
}
