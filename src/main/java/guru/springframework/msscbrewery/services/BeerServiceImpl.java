package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        System.out.println("saveNewBeer() is invoked with Json provided in Postman");
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        System.out.println("updateBeer() is invoked");
    }

    @Override
    public void deleteById(UUID beerId) {
        System.out.println("deleteById() is invoked");
        log.debug("we are using slf4j annotation from the Lomback");
    }

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle("Pale Ale")
                .upc(1L)
                .build();
    }
}
