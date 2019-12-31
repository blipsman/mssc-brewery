package guru.springframework.msscbrewery.services.map;

import guru.springframework.msscbrewery.web.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    Long ownerId = 1L;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(Owner.builder().id(ownerId).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(1, owner.getId());
    }

    @Test
    void save() {
        Owner owner2 = Owner.builder().id(2L).build();
        Owner owner2Saved = ownerMapService.save(owner2);
        assertEquals(2, owner2Saved.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner3 = Owner.builder().id(3l).lastName("Fisher").build();
        Owner ownerSaved3 = ownerMapService.save(owner3);
        Owner foundOwner = ownerMapService.findByLastName("Fisher");
        assertTrue(foundOwner.getLastName().equalsIgnoreCase("Fisher"));
    }

    @Test
    void findAllByLastNameLike() {
    }
}