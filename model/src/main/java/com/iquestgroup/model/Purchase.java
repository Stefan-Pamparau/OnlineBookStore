package com.iquestgroup.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Purchase implements Serializable {
    private Timestamp purchaseDate;
    private ClientAccount clientAccount;
    private Book book;

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public ClientAccount getClientAccount() {
        return clientAccount;
    }

    public void setClientAccount(ClientAccount clientAccount) {
        this.clientAccount = clientAccount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Purchase purchase = (Purchase) o;

        return purchaseDate != null ? purchaseDate.equals(purchase.purchaseDate) : purchase.purchaseDate == null;

    }

    @Override public int hashCode() {
        return purchaseDate != null ? purchaseDate.hashCode() : 0;
    }
}
