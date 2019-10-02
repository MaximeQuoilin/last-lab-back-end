package be.afelio.mqu.gamify.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.afelio.mqu.gamify.api.dto.total.VideogameDto;
import be.afelio.mqu.gamify.repositories.VideogameApplicationRepository;



@RunWith(SpringRunner.class)
@SpringBootTest()
public class FindAllVideoGameTest {
	
	@Autowired VideogameApplicationRepository repository;
	
	@Test
	public void testNotNullAndSize() {
		List<VideogameDto> list = repository.findAllVideogames();
		assertNotNull(list);
		assertTrue(!list.isEmpty());
		assertTrue(list.size() == 2);
	}

}
