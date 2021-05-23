package ua.tqs.TQS_P2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByAuthor(@Param("author") String author);
    List<Book> findByPublisher(@Param("publisher") String publisher);
    List<Book> findByTitle(@Param("title") String title);

    Book findById(long id);

}