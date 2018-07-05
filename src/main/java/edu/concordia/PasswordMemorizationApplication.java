package edu.concordia;

import edu.concordia.model.Guess;
import edu.concordia.model.GuessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PasswordMemorizationApplication implements CommandLineRunner{
	@Autowired
	private GuessRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(PasswordMemorizationApplication.class, args);

	}

	@Override
	public void run(String... strings) throws Exception {

		repository.save(new Guess("abc","cde",1,"abbbbb","bbbc","abbbbb",1));
		repository.save(new Guess("abc","aad",1,"abbbbb","asdf","abbbbb",3));
		repository.save(new Guess("abc","uva",1,"abbbbb","dfasdf","abbbbb",3));
		//System.out.println("guess record :" +repository.getOne(1));
	}

}
