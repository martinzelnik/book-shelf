package sk.martinzelnik.ratingsdataservice.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.martinzelnik.ratingsdataservice.model.FavouriteBook;

public interface FavouriteBooksServiceRepository extends JpaRepository<FavouriteBook, String> {

}
