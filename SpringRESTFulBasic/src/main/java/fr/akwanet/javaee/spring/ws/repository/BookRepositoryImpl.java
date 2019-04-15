package fr.akwanet.javaee.spring.ws.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.akwanet.javaee.spring.ws.model.Book;
import lombok.NonNull;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @Resource
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<Book> getAll() {
		String GET_ALL_ARTICLES = "select * from book";
		return jdbcTemplate.query(GET_ALL_ARTICLES, BeanPropertyRowMapper.newInstance(Book.class));
		//return jdbcTemplate.queryForList(GET_ALL_ARTICLES, Book.class);
	}

	@Override
	public Book get(Long id) {
	      String GET_ARTICLE_BY_ID = "select * from book where book_id=?";
	      try {
	    	  return  jdbcTemplate.queryForObject(GET_ARTICLE_BY_ID, BeanPropertyRowMapper.newInstance(Book.class), id) ;
	      }
	      catch (Exception e) {
	    	  System.out.println("*** EXC: "+e.getMessage());
			e.printStackTrace();
	    	  System.out.println("*******************************");
	      }
	      return null;
    }

	@Override
	public Book getByIsbn(String isbn) {
	      String GET_ARTICLE_BY_ISBN = "select * from book where isbn=?";
	      return  jdbcTemplate.queryForObject(GET_ARTICLE_BY_ISBN, BeanPropertyRowMapper.newInstance(Book.class), isbn) ;	
	}

	@Override
	public void create(Book book) {
		String CREATE_ARTICLE = "insert into BOOK (ISBN, TITLE, PRICE, DT_CREATE, DT_UPDATE) VALUES(?, ?, ?, ?, ?)";
		jdbcTemplate.update(CREATE_ARTICLE, book.getIsbn(), book.getTitle(), book.getPrice(), book.getDtCreate(), book.getDtUpdate());
	}

	@Override
	public void delete(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Book book) {
		// TODO Auto-generated method stub

	}

}
