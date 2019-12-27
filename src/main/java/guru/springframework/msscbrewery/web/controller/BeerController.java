package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;


@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    Logger LOG = LoggerFactory.getLogger(BeerController.class);

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }


    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @GetMapping("/details")
    public ResponseEntity<BeerDto> getBeerMore(
            @RequestHeader("x-ibm-client-id") String clientId,
            @RequestHeader("x-ibm-client-secret") String clientSecret,
            @RequestHeader("Content-Type") String contentType,
            @RequestHeader("Origin") String origin,
            @RequestHeader("Accept") String accept,
            @RequestParam(required = true) UUID beerId) {
        System.out.println("clientId " + clientId + " clientSecret " + clientSecret + " contentType " + contentType);

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }


    @GetMapping("/listHeaders")
    public ResponseEntity<String> listAllHeaders(
            @RequestHeader Map<String, String> headers,
            @RequestParam(required = true) UUID beerId) {
        headers.forEach((key, value) -> {
            LOG.info(String.format("Header '%s' = %s", key, value));
        });

        headers.entrySet().forEach(System.out::println);
        headers.values().forEach(System.out::println);
       return new ResponseEntity<String>(
                String.format("Listed %d headers", headers.size()), HttpStatus.OK);
    }


    @PostMapping // POST - create new beer
    public ResponseEntity handlePost(@RequestBody BeerDto beerDto){

        BeerDto savedDto = beerService.saveNewBeer(beerDto);

        HttpHeaders headers = new HttpHeaders();
        //todo add hostname to url
        headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{beerId}"})
    public ResponseEntity handleUpdate (@PathVariable ("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
        beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);
    }
}

