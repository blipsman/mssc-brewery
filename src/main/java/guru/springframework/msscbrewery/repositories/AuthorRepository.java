package guru.springframework.msscbrewery.repositories;

import guru.springframework.msscbrewery.web.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
