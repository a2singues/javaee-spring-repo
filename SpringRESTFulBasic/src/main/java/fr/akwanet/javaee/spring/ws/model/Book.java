package fr.akwanet.javaee.spring.ws.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString

@FieldDefaults(level=AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"bookId","isbn"})
@Builder
public class Book {
	@NonNull Long bookId;
	@NonNull String isbn;
	@NonNull String title;
	@NonNull BigDecimal price;
	@NonNull Date dtCreate;
	Date dtUpdate;
	
	public Book() {
		super();
	}
	
}
