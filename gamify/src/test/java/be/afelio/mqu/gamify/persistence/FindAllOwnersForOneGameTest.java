package be.afelio.mqu.gamify.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.InvalidParameterException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.mqu.gamify.api.dto.simple.UserSimpleDto;
import be.afelio.mqu.gamify.api.exceptions.notFound.VideogameNotFoundException;
import be.afelio.mqu.gamify.repositories.interfaces.UserApplicationRepositoryInterface;
import be.afelio.mqu.gamify.test_utils.Utils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FindAllOwnersForOneGameTest {

	@Autowired
	TestRestTemplate restTemplate;
	@Autowired
	Utils utils;
	@Autowired
	UserApplicationRepositoryInterface repo;
	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testOwnersOfGameId21() {
		List<UserSimpleDto> actual = repo.findAllUsersForOneVideoGame(21);
		assertEquals(actual, utils.createListOwnersOfVideogame(21));
	}

	@Test
	public void testGameWithoutOwner() {
		List<UserSimpleDto> actual = repo.findAllUsersForOneVideoGame(24);
		assertTrue(actual.isEmpty());
	}

	@Test(expected = InvalidParameterException.class)
	public void testGameWithInvalidParameters() {
		repo.findAllUsersForOneVideoGame(null);
	}

	@Test(expected = VideogameNotFoundException.class)
	public void testInexistingGame() {
		repo.findAllUsersForOneVideoGame(100000);
	}
}
