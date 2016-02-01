package com.iquestgroup.database.impl;

import com.iquestgroup.database.ClientDAO;
import com.iquestgroup.model.Book;
import com.iquestgroup.model.Client;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
public class DefaultClientDAO implements ClientDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Client> listAllClients() {
        List<Client> result = new ArrayList<>();
        Transaction transaction = null;

        List clientList = entityManager.createQuery("SELECT c FROM Client c").getResultList();
        for (Object client : clientList) {
            result.add((Client) client);
        }

        return result;
    }

    @Override
    public void insertClient(Client client) {
        entityManager.persist(client);
    }

    @Override
    public void deleteClient(Integer clientID) {
        Client client = entityManager.find(Client.class, clientID);
        entityManager.remove(client);
    }

    @Override
    public void purchaseBook(Integer clientID, Integer bookID) {
        Book book = entityManager.find(Book.class, bookID);
        Client client = entityManager.find(Client.class, clientID);

        if (book.getInStock() > 0) {
            book.setInStock(book.getInStock() - 1);
            client.getBooks().add(book);
        }
    }
}
