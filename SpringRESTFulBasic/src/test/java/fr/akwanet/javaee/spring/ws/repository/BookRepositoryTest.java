package fr.akwanet.javaee.spring.ws.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

//import org.junit.Assert;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.AssertThrows;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.akwanet.javaee.spring.ws.config.ContextConfig;
import fr.akwanet.javaee.spring.ws.model.Book;
import lombok.NonNull;

import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ContextConfig.class})
public class BookRepositoryTest {
		@Rule
		public ExpectedException thrown = ExpectedException.none();

	   @Resource
	    private BookRepository bookRepository;

	    @Test
	    public void getBook(){
	        Long id = 1L;
	        Book books = bookRepository.get(id);
	        assertNotNull(books);
	        assertThat(id, is(books.getBookId()));
	    }

	    @Test
	    public void readAll(){
	        List<Book> books = bookRepository.getAll();
	        assertNotNull(books);
	        assertTrue(books.size() > 0);
	    }
	    
	    @Test
	    public void createBook() {
	    	Book book = new Book(1L, "isbn-test1", "Title1", new BigDecimal(1), new Date(), new Date());
//	    	try {
		    	bookRepository.create(book);
		    	Book b = bookRepository.getByIsbn("isbn-test1");
		    	assertNotNull(b);
		    	assertThat("isbn-test1", is(b.getIsbn()));
		    	
		    	thrown.expect(DuplicateKeyException.class);
		    	thrown.expectMessage(startsWith("PreparedStatementCallback; SQL "));
		    	
		    	bookRepository.create(book);
		    	b = bookRepository.getByIsbn("isbn-test1");
		    	
		    	//PreparedStatementCallback; SQL [insert into BOOK (ISBN, TITLE, PRICE, DT_CREATE, DT_UPDATE) VALUES(?, ?, ?, ?, ?)]; Violation of unique constraint SYS_CT_46: duplicate value(s) for column(s) ISBN in statement [insert into BOOK (ISBN, TITLE, PRICE, DT_CREATE, DT_UPDATE) VALUES(?, ?, ?, ?, ?)]
		    			
		    	assertNotNull(b);
		    	assertThat("isbn-test1", is(b.getIsbn()));
//	    	}catch (Exception e) {
//				e.printStackTrace();
//			}
	    }
}
