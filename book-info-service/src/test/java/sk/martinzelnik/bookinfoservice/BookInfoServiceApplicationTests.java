package sk.martinzelnik.bookinfoservice;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import sk.martinzelnik.bookinfoservice.model.GoodreadsResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookInfoServiceApplicationTests {

	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	public void contextLoads() { 
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<GoodreadsResponse> response = restTemplate.exchange("https://www.goodreads.com/book/isbn/0441172717?key=fPSdIxOogyrTyvZTMKWZQ", HttpMethod.GET, entity, GoodreadsResponse.class);
		GoodreadsResponse responseBody = response.getBody();
		Assert.assertNotNull(responseBody);
	}	
}
