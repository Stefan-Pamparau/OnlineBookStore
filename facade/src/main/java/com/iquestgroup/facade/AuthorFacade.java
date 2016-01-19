package com.iquestgroup.facade;

import com.iquestgroup.model.Author;

import java.util.List;

public interface AuthorFacade {
    List<Author> getAllAuthors();

    void insertAuthor(Author author);

    void deleteAuthor(Author author);
}
