package sk.martinzelnik.ratingsdataservice.model;

import java.util.List;

public class FavouriteBooksResponse {

	private List<FavouriteBook> favouriteBooks;

	public List<FavouriteBook> getFavouriteBooks() {
		return favouriteBooks;
	}

	public void setFavouriteBooks(List<FavouriteBook> favouriteBooks) {
		this.favouriteBooks = favouriteBooks;
	}
}
