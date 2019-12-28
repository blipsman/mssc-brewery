package guru.springframework.msscbrewery.repositories;

import guru.springframework.msscbrewery.web.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
