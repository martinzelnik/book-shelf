package sk.martinzelnik.bookcatalogservice.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.lang.NonNull;

public class FavouriteBook {
	
	@NonNull
	private String isbn;
	
	@Min(1)
	@Max(5)
	private Integer rating;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
