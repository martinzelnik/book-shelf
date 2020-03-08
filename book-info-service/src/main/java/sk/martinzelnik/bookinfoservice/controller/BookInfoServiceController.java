package sk.martinzelnik.bookinfoservice.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import sk.martinzelnik.bookinfoservice.model.BookInfo;
import sk.martinzelnik.bookinfoservice.model.GoodreadsResponse;

@RestController
@RequestMapping("/bookinfo")
public class BookInfoServiceController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(BookInfoServiceController.class);
	
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(value = "/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BookInfo getBookInfo(@PathVariable String isbn) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		try {
			// gets book info from an external api (good reads)
			ResponseEntity<GoodreadsResponse> response = restTemplate.exchange("https://www.goodreads.com/book/isbn/" + isbn + "?key=" + apiKey, HttpMethod.GET,
					entity, GoodreadsResponse.class);
			
			GoodreadsResponse responseBody = response.getBody();	
			return responseBody.getBook();
		} catch (RestClientException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}		
	}
}
