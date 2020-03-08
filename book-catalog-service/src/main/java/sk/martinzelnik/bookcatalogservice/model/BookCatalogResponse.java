package sk.martinzelnik.bookcatalogservice.model;

import java.util.List;

public class BookCatalogResponse {

	private List<CatalogItem> bookCatalog;

	public List<CatalogItem> getBookCatalog() {
		return bookCatalog;
	}

	public void setBookCatalog(List<CatalogItem> bookCatalog) {
		this.bookCatalog = bookCatalog;
	}
}
