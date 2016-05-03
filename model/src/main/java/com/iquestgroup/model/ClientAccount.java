package com.iquestgroup.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model class which models accounts of clients.
 *
 * @author Stefan Pamparau
 */
public class ClientAccount {
    private Integer id;

    @NotNull
    @Size(min = 3, max = 20, message = "Should contain a minimum of 3 character and a maximum of 20 characters")
    private String email;

    @NotNull
    @Size(min = 3, max = 20, message = "Should contain a minimum of 3 character and a maximum of 20 characters")
    private String password;

    @NotNull
    private Timestamp creationDate;

    @NotNull
    private Integer balance;

    private Set<Purchase> purchases;
    private Set<Book> books;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientAccount that = (ClientAccount) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null)
            return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null)
            return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        if (purchases != null ? !purchases.equals(that.purchases) : that.purchases != null)
            return false;
        return books != null ? books.equals(that.books) : that.books == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (purchases != null ? purchases.hashCode() : 0);
        result = 31 * result + (books != null ? books.hashCode() : 0);
        return result;
    }
}
