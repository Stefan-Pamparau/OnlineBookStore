package com.iquestgroup.database.impl.jpa;

import com.iquestgroup.database.AuthorDAO;
import com.iquestgroup.model.Author;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
public class DefaultAuthorDAO implements AuthorDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Author> getAllAuthors() {
        return null;
    }

    @Override
    public Author getAuthorByID(Integer authorID) {
        return null;
    }

    @Override
    public void insertAuthor(Author author) {

    }

    @Override
    public void deleteAuthor(Integer authorID) {

    }
}
