package com.iquestgroup.onlineBookStore.mappers;

import com.iquestgroup.onlineBookStore.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by stefan.pamparau on 10/15/2015.
 */
public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBookName(rs.getString("book_name"));
        book.setBookAuthor(rs.getString("book_author"));
        return book;
    }
}
