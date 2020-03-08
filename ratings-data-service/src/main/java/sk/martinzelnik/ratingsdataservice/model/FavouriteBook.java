package sk.martinzelnik.ratingsdataservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ratings")
public class FavouriteBook {
	
	@Id
	private String isbn;
	
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

	@Override
	public String toString() {
		return "FavouriteBook [isbn=" + isbn + ", rating=" + rating + "]";
	}
}
