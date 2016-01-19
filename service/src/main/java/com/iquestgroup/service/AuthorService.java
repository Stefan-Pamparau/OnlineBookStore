package com.iquestgroup.service;

import com.iquestgroup.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();

    void insertAuthor(Author author);

    void deleteAuthor(Author author);
}
