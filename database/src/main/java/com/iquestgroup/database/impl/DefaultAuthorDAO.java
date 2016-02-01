package com.iquestgroup.database.impl;

import com.iquestgroup.database.AuthorDAO;
import com.iquestgroup.model.Author;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
public class DefaultAuthorDAO implements AuthorDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Author> getAllAuthors() {
        List<Author> resultList = new ArrayList<>();

        List authorList = entityManager.createQuery("SELECT a FROM Author a").getResultList();

        for (Object author : authorList) {
            resultList.add((Author) author);
        }

        return resultList;
    }

    @Override
    public Author getAuthorByID(Integer authorID) {
        return entityManager.find(Author.class, authorID);
    }

    @Override
    public void insertAuthor(Author author) {
        entityManager.persist(author);
    }

    @Override
    public void deleteAuthor(Integer authorID) {
        Author author = entityManager.find(Author.class, authorID);
        entityManager.remove(author);
    }
}
