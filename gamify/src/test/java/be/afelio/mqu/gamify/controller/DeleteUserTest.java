package be.afelio.mqu.gamify.controller;

import java.net.URI;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import be.afelio.mqu.gamify.api.dto.classic.UserDto;
import be.afelio.mqu.gamify.persistence.ApplicationRepository;
import be.afelio.mqu.gamify.persistence.entities.UserEntity;
import be.afelio.mqu.gamify.persistence.repositories.UserRepository;
import be.afelio.mqu.gamify.test_utils.Utils;
import jdk.jfr.internal.RequestEngine;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class DeleteUserTest {

	@Autowired TestRestTemplate restTemplate;
	@Autowired JdbcTemplate jdbcTemplate;
	@Autowired ApplicationRepository repository;
	@Autowired UserRepository userRepository;
	@Autowired Utils utils;
	
	@Test
	public void test() {
		UserEntity user = userRepository.findOneByUsername("ticus");
		
		RequestEntity<UserDto> requestEntity 
		= new RequestEntity<UserDto>(userDto, HttpMethod.DELETE, URI.create("/"));
		
	
	}
}
