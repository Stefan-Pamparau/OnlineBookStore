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
    public void insertAuthor(Author author) {

    }

    @Override
    public void deleteAuthor(Author author) {

    }
}
