package guru.springframework.msscbrewery.repositories;

import guru.springframework.msscbrewery.web.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {

}
