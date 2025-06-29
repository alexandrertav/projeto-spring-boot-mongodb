package com.alextavares.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.alextavares.workshopmongo.domain.Post;
import com.alextavares.workshopmongo.domain.User;
import com.alextavares.workshopmongo.dto.AuthorDTO;
import com.alextavares.workshopmongo.repository.PostRepository;
import com.alextavares.workshopmongo.repository.UserRepository;


//cargas iniciais do banco de dados
@Configuration
public class Instantiation implements CommandLineRunner {
	
	
	//repositorios injetados
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;


	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		//instanciando usuarios
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Tavares", "alex@gmail.com");
		User laura = new User(null, "Laura Maria", "laura@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, laura));
		
		//instanciando novos posts
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar paraSão Paulo. Abraços!", new AuthorDTO(laura));
		Post post2 = new Post(null, sdf.parse("03/03/2020"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(laura));
				
		
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}
