package com.iquestgroup.model;

import java.util.Set;

import javax.validation.constraints.NotNull;

/**
 * Model class which represents client accounts.
 *
 * @author Stefan Pamparau
 */
public class ClientAccount extends UserAccount {
    private Set<Purchase> purchases;
    private Set<Book> books;
    @NotNull
    private Integer balance;

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
