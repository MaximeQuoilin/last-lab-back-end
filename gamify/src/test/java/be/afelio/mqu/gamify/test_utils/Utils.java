package be.afelio.mqu.gamify.test_utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.afelio.mqu.gamify.api.dto.simple.UserSimpleDto;
import be.afelio.mqu.gamify.api.dto.total.VideogameDto;
import be.afelio.mqu.gamify.persistence.entities.EditorEntity;
import be.afelio.mqu.gamify.persistence.entities.GenreEntity;
import be.afelio.mqu.gamify.persistence.entities.PegiEntity;
import be.afelio.mqu.gamify.persistence.entities.PlatformEntity;
import be.afelio.mqu.gamify.repositories.interfaces.UserApplicationRepositoryInterface;

@Component
public class Utils {
	
	@Autowired UserApplicationRepositoryInterface repo;

	public List<VideogameDto> createListVideoGameDto () {
		ArrayList<VideogameDto> list = new ArrayList<VideogameDto>();
		
		List<PegiEntity> pegis = new ArrayList<PegiEntity>();
		PegiEntity pegi = new PegiEntity(1, "18+", "realy violent");
		pegis.add(pegi);
		
		List<PlatformEntity> platforms = new ArrayList<PlatformEntity>();
		platforms.add(new PlatformEntity(1,"PS4"));
		platforms.add(new PlatformEntity(2,"PC"));
		platforms.add(new PlatformEntity(3, "Xbox 360"));
		
		VideogameDto v = new VideogameDto(20,"Call of duty", "War game", null, new EditorEntity(2,"Activision"), new GenreEntity(1, "FPS"), pegis, platforms);
		list.add(v);
		
		pegi = new PegiEntity(2, "16+", "A little violent");
		pegis = new ArrayList<PegiEntity>();
		pegis.add(pegi);
		
		platforms = new ArrayList<PlatformEntity>();
		platforms.add(new PlatformEntity(2, "PC"));
		platforms.add(new PlatformEntity(1, "PS4"));
		
		v = new VideogameDto(21, "Fortnite", "Battle Royale",null, new EditorEntity(1, "Epic Games"), new GenreEntity(1, "FPS"), pegis, platforms);
		

		list.add(v);
		return list;
		
	}
	
	public UserSimpleDto createListOwnersOfVideogame(int gameId){
		return  repo.findUserForOneVideoGame(gameId);		
	}
	
	
}
