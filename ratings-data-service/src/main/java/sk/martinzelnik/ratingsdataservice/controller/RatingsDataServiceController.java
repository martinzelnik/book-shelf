package sk.martinzelnik.ratingsdataservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sk.martinzelnik.ratingsdataservice.model.FavouriteBook;
import sk.martinzelnik.ratingsdataservice.model.FavouriteBooksResponse;
import sk.martinzelnik.ratingsdataservice.repositary.FavouriteBooksServiceRepository;

@RestController
@RequestMapping
public class RatingsDataServiceController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(RatingsDataServiceController.class);
	
	@Autowired
	FavouriteBooksServiceRepository repository;

	@GetMapping("/ratingsdata")
	public FavouriteBooksResponse getFavouriteBooks() {
		List<FavouriteBook> favBooks = repository.findAll();
		
		FavouriteBooksResponse favouriteBooksResponse= new FavouriteBooksResponse();
		// wrapped into a wrapper class, good practice with regard to future prospective modifications
		favouriteBooksResponse.setFavouriteBooks(favBooks);
		
		return favouriteBooksResponse;
	}
	
	@PostMapping("/ratingsdata")
	public void addBook(@RequestBody FavouriteBook favBook) {
		repository.save(favBook);
		LOGGER.info("a new book added into favourites:" + favBook);
	}
	
	@PutMapping("/ratingsdata/{isbn}")
	public void updateRating(@RequestBody FavouriteBook favBook) {
		repository.save(favBook);
		LOGGER.info("rating updated:" + favBook);
	}
	
	@DeleteMapping("/ratingsdata/{isbn}")
	public void deleteBook(@PathVariable String isbn) {
		repository.deleteById(isbn);
		LOGGER.info("a book deleted from favourites:" + isbn);
	}
}
