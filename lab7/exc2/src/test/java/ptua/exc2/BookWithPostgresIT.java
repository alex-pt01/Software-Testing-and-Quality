package ptua.exc2;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertySource;

import java.util.List;

@SpringBootTest
public class BookWithPostgresIT {
    @Autowired
    BookRepository bookRepository;

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
            .withUsername("duke")
            .withPassword("password")
            .withDatabaseName("test");

    @DynamicPropertySource
    static void properties(DynamicPropertySource registry){
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);

    }

    @Test
    @Order(1)
    void saveBook(){
        Book book = new Book();
        book.setName("Tests");
        bookRepository.save(book);
    }
    @Test
    @Order(2)
    void getBooks(){
        List<Book> books = bookRepository.findAll();
        assertFalse(books.isEmpty());
        assertTrue(books.size(), is(2));

    }
}
