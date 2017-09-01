package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.meanbean.test.BeanTester;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.wimp.api.MovieApiController;
import com.libertymutual.goforcode.wimp.api.StuffNotFoundException;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

public class MovieTest {

    private MovieRepository movieRepo;
    private MovieApiController controller;
    private ActorRepository actorRepo;

    @Before
    public void setUp() {
        movieRepo = mock(MovieRepository.class);
        controller = new MovieApiController(movieRepo, actorRepo);
    }
    
    @Test
    public void test_all_gets_and_sets() {
        new BeanTester().testBean(Movie.class);
    }
    
    @Test
    public void test_addActors_creates_list_of_actors_is_null() {
        // Arrange
        Movie movie = new Movie();
        Actor actor = new Actor();
              
        // Act
        movie.addActor(actor);
        
        // Assert
        assertThat(movie.getActors()).isNotNull();         
    }  
    
    @Test
    public void test_addActors_when_list_of_actors_is_not_null() {
        // Arrange
        Movie movie = new Movie();
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        ArrayList<Actor> actors = new ArrayList<Actor>();
        actors.add(actor1);
        movie.setActors(actors);
              
        // Act
        movie.addActor(actor2);
        
        // Assert
        assertThat(movie.getActors()).contains(actor1, actor2);         
    }
}