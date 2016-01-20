package com.iquestgroup.facade.impl;

import com.iquestgroup.facade.AuthorFacade;
import com.iquestgroup.model.Author;
import com.iquestgroup.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultAuthorFacade implements AuthorFacade {
    @Autowired
    private AuthorService authorService;

    @Override
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @Override
    public Author getAuthorByID(Integer authorID) {
        return authorService.getAuthorByID(authorID);
    }

    @Override
    public void insertAuthor(Author author) {
        authorService.insertAuthor(author);
    }

    @Override
    public void deleteAuthor(Integer authorID) {
        authorService.deleteAuthor(authorID);
    }
}
