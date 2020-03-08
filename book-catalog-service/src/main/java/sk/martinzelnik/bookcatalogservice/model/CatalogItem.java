package sk.martinzelnik.bookcatalogservice.model;

import java.util.List;

public class CatalogItem {
	
	private String isbn;
	
	private String title;
	
	private List<String> authors;
	
	private String description;

	private Integer rating;

	public CatalogItem(String isbn, String title, List<String> authors, String description, Integer rating) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.authors = authors;
		this.description = description;
		this.rating = rating;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void getTitle(String title) {
		this.title = title;
	}
	
	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
