package fr.akwanet.javaee.spring.ws.web.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.akwanet.javaee.spring.ws.model.Book;
import fr.akwanet.javaee.spring.ws.repository.BookRepository;

@RestController
@RequestMapping(value="/api")
public class BookController {
	private Logger logger = LoggerFactory.getLogger(BookController.class);
	
    @Resource
    private BookRepository bookRepository;

    @RequestMapping(value="/book/{id}", method=RequestMethod.GET)
    public Object getBook(@PathVariable Long id){
    	Book book = bookRepository.get(id);
    	
    	logger.info("@@ getBook ...");
    	System.out.println("@@ getBook ...");
//    	Optional<Book> bookOptional = Optional.of(bookRepository.get(id));
//    	if (! bookOptional.isPresent()) {
//    		
//    	}
    	if (book == null) {
    		//response.setStatus( HttpStatus.SC_NO_CONTENT);    	
    		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    	}
        return book;
    }

    @RequestMapping(value="/books", method=RequestMethod.GET)
    public List<Book> getBooks(){
    	logger.info("@@ getBooks ...");
    	System.out.println("@@ getBooks ...");
        return bookRepository.getAll();
    }
}
