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

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        ClientAccount that = (ClientAccount) o;

        return balance != null ? balance.equals(that.balance) : that.balance == null;

    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }
}
