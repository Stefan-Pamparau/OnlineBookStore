package com.iquestgroup.onlineBookStore.DAO;

import com.iquestgroup.onlineBookStore.mappers.BookMapper;
import com.iquestgroup.onlineBookStore.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by stefan.pamparau on 10/15/2015.
 */
public class BookDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void addBook(Book book) {
        String SQL = "INSERT INTO book(book_name, book_author) VALUES(?, ?)";
        jdbcTemplate.update(SQL, book.getBookName(), book.getBookAuthor());
    }

    public List<Book> getBooks() {
        String bookSQLquery = "SELECT * FROM book;";
        return jdbcTemplate.query(bookSQLquery, new BookMapper());
    }


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        initializeJdbcTemplate();
    }

    private void initializeJdbcTemplate() {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
