package com.iquestgroup.model;

import java.util.Set;

public class Client {
    private Integer id;
    private String name;
    private String address;
    private String email;
    private String password;
    private Set<Book> books;
    private Set<PurchaseHistory> purchases;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<PurchaseHistory> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<PurchaseHistory> purchases) {
        this.purchases = purchases;
    }
}
