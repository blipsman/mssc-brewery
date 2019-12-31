package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.Owner;
import java.util.List;


/**
 * Created by jt on 7/18/18.
 */
public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
