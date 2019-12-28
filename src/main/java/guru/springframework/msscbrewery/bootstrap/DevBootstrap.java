package guru.springframework.msscbrewery.bootstrap;

import guru.springframework.msscbrewery.repositories.AuthorRepository;
import guru.springframework.msscbrewery.repositories.BookRepository;
import guru.springframework.msscbrewery.repositories.PublisherRepository;
import guru.springframework.msscbrewery.web.model.Author;
import guru.springframework.msscbrewery.web.model.Book;
import guru.springframework.msscbrewery.web.model.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {


    AuthorRepository authorRepository;
    BookRepository bookRepository;
    PublisherRepository publisherRepository;


    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        log.debug("component is being scanned, wiring is done");
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }


    private void initData(){

        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher pub1 = new Publisher("Harper Collins", "New York");
        Book ddd = new Book("Domain Driven Design", "1234", pub1);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(pub1);
        bookRepository.save(ddd);



        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher pub2 = new Publisher("Wrox", "Washington DC" );
        Book noEJB = new Book("J2EE Development without EJB", "23444", pub2);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        publisherRepository.save(pub2);
        bookRepository.save(noEJB);



    }
}
