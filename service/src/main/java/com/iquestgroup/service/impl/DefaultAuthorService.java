package com.iquestgroup.service.impl;

import com.iquestgroup.database.AuthorDAO;
import com.iquestgroup.model.Author;
import com.iquestgroup.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultAuthorService implements AuthorService {
    @Autowired
    private AuthorDAO authorDAO;

    @Override
    public List<Author> getAllAuthors() {
        return authorDAO.getAllAuthors();
    }

    @Override
    public void insertAuthor(Author author) {

    }

    @Override
    public void deleteAuthor(Author author) {

    }
}
