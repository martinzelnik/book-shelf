package sk.martinzelnik.bookcatalogservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import sk.martinzelnik.bookcatalogservice.model.ApiMessage;
import sk.martinzelnik.bookcatalogservice.model.Author;
import sk.martinzelnik.bookcatalogservice.model.BookCatalogResponse;
import sk.martinzelnik.bookcatalogservice.model.BookInfo;
import sk.martinzelnik.bookcatalogservice.model.CatalogItem;
import sk.martinzelnik.bookcatalogservice.model.FavouriteBook;
import sk.martinzelnik.bookcatalogservice.model.FavouriteBooksResponse;

@RestController
@RequestMapping("/mybookcatalog")
public class BookCatalogServiceController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(BookCatalogServiceController.class);
	
	private static final String BOOK_INFO_SERVICE_URL = "http://book-info-service/bookinfo/";
	private static final String RATING_DATA_SERVICE_URL = "http://ratings-data-service/ratingsdata";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/favourites")
	public BookCatalogResponse getCatalog() {
		// book data stored as favourites fetched from the local DB
		FavouriteBooksResponse favBooksResponse = restTemplate.getForObject(RATING_DATA_SERVICE_URL,
				FavouriteBooksResponse.class);
		
		List<CatalogItem> bookCatalog = favBooksResponse.getFavouriteBooks().stream().map(favBook -> {
			// getting information about each book from an external API
			BookInfo bookInfo = restTemplate.getForObject(BOOK_INFO_SERVICE_URL + favBook.getIsbn(), BookInfo.class);
			
			List<String> authors = bookInfo.getAuthors().stream().map(Author::getName).collect(Collectors.toList());
			
			return new CatalogItem(favBook.getIsbn(), bookInfo.getTitle(), authors,
					bookInfo.getDescription(), favBook.getRating());
			}).collect(Collectors.toList());
		
		BookCatalogResponse bookCatalogResponse = new BookCatalogResponse();
		bookCatalogResponse.setBookCatalog(bookCatalog);
		
		return bookCatalogResponse;
	}
	
	@PostMapping("/favourites")
	public ResponseEntity<?> addBook(@RequestBody @Valid FavouriteBook favBook) {
		// checks if the particular book exists
		BookInfo bookInfo = restTemplate.getForObject(BOOK_INFO_SERVICE_URL + favBook.getIsbn(), BookInfo.class);	
		// if not null, the book data has been received therefore it exists and can be stored in the DB
		if(bookInfo != null) {
			// book saved as favourite to the local DB
			restTemplate.postForObject(RATING_DATA_SERVICE_URL, favBook, ResponseEntity.class);
			
			List<String> authors = bookInfo.getAuthors().stream().map(Author::getName).collect(Collectors.toList());
			
			CatalogItem body = new CatalogItem(favBook.getIsbn(), bookInfo.getTitle(), authors,
					bookInfo.getDescription(), favBook.getRating());
			return new ResponseEntity<CatalogItem>(body, HttpStatus.OK);
		} else {
			LOGGER.warn("Incorrect isbn");
			return new ResponseEntity<ApiMessage>(new ApiMessage("Incorrect isbn"), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/favourites/{isbn}")
	public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
		restTemplate.delete(RATING_DATA_SERVICE_URL + "/" + isbn);
		return new ResponseEntity<ApiMessage>(new ApiMessage("Record deleted"), HttpStatus.OK);
	}
}
