package com.sda.jdbc;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookPostgresDao implements BookRepository {

    // protocol:db://host:port/db_name
    private static final String URL = "jdbc:postgresql://localhost:5432/jdbc_tutorial";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "postgres";

    @Override
    public Book create(Book book) {
        // open connection
        // prepare statement
        String sql = "INSERT INTO book (title, author, publish_date) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            // set params on statement
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, String.valueOf(book.getPublishDate()));

            // execute statement
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("failed to create book");
            }

            // create book to return
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setId(generatedKeys.getLong(1));
                } else {
                    throw new RuntimeException("failed to create book");
                }
            }
            // close resources
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return book;
    }

    @Override
    public List<Book> findAll() {

        List<Book> result;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // open connection
            // connections - creates statements
            // driver manager - opens connections
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // query
            String sql = "select * from book";

            // prepare statement
            statement = connection.prepareStatement(sql);

            // execute statement
            // result set - contains returned data
            resultSet = statement.executeQuery();

            // create list from result set
            result = new ArrayList<>();

            // iterate result set
            while (resultSet.next()) {
                // extract data from result set
                long id = resultSet.getLong(1);
                String title = resultSet.getString(2);
                String author = resultSet.getString(3);
                LocalDate publishedDate = resultSet.getDate(4).toLocalDate();

                // create boot from result set
                Book book = new Book();
                book.setId(id);
                book.setTitle(title);
                book.setAuthor(author);
                book.setPublishDate(publishedDate);

                // add to the list
                result.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // result set
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // statement
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return null;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public Book update(Long id, Book book) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
