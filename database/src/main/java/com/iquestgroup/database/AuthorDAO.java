package com.iquestgroup.database;

import com.iquestgroup.model.Author;

import java.util.List;

public interface AuthorDAO {
    List<Author> getAllAuthors();

    void insertAuthor(Author author);

    void deleteAuthor(Author author);
}
