package com.iquestgroup.service;

import com.iquestgroup.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author getAuthorByID(Integer authorID);

    void insertAuthor(Author author);

    void deleteAuthor(Integer authorID);
}
