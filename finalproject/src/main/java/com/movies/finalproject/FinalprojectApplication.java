package com.movies.finalproject;

import java.util.List;

import javax.swing.tree.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class FinalprojectApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		
		System.out.println("run baby run");
		SpringApplication.run(FinalprojectApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		String sql="SELECT * FROM Movies";
		List<Movies> mMovies =jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Movies.class));

		mMovies.forEach(System.out::println);
	}

}
