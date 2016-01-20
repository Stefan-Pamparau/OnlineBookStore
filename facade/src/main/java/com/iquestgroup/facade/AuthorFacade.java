package com.iquestgroup.facade;

import com.iquestgroup.model.Author;

import java.util.List;

public interface AuthorFacade {
    List<Author> getAllAuthors();

    Author getAuthorByID(Integer authorID);

    void insertAuthor(Author author);

    void deleteAuthor(Integer authorID);
}
