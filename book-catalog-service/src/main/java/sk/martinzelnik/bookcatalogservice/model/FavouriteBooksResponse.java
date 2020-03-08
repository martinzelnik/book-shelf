package sk.martinzelnik.bookcatalogservice.model;

import java.util.List;

public class FavouriteBooksResponse {

	private List<FavouriteBook> favouriteBooks;

	public List<FavouriteBook> getFavouriteBooks() {
		return favouriteBooks;
	}

	public void setRatings(List<FavouriteBook> favouriteBooks) {
		this.favouriteBooks = favouriteBooks;
	}
}
