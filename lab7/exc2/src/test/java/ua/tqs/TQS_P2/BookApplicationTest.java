package ua.tqs.TQS_P2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Testcontainers
@SpringBootTest
class BookApplicationTest {

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer()
            .withUsername("admin")
            .withPassword("password")
            .withDatabaseName("test");

    @Autowired
    private BookRepository bookRepository;

    // requires Spring Boot >= 2.2.6
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    @Order(1)
    void saveBook() {

        Book book = new Book();
        book.setTitle("Testcontainers");
        Book book2 = new Book();
        book2.setTitle("Testcontainers2");

        bookRepository.save(book);
        bookRepository.save(book2);

    }

    @Test
    @Order(2)
    void listBooks() {

        List<Book> booksCollection = (List<Book>) bookRepository.findAll();
        assertFalse(booksCollection.isEmpty());
        assertThat(booksCollection.size(), Matchers.is(2));
    }

}