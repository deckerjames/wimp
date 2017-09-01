package com.libertymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.wimp.api.MovieApiController;
import com.libertymutual.goforcode.wimp.api.StuffNotFoundException;
import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

public class MovieApiControllerTest {

    private MovieRepository movieRepo;
    private ActorRepository actorRepo;
    private MovieApiController controller;

    @Before
    public void setUp() {
        movieRepo = mock(MovieRepository.class);
        actorRepo = mock(ActorRepository.class);

        controller = new MovieApiController(movieRepo, actorRepo); 
    }

    @Test
    public void test_getAll_returns_all_Movies_returned_by_the_repo() {
        // Arrange
        ArrayList<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie());
        movies.add(new Movie());
        when(movieRepo.findAll()).thenReturn(movies);

        // Act
        List<Movie> actual = controller.getAll();

        // Assert
        assertThat(movies.size()).isEqualTo(2);
        assertThat(actual.get(0)).isSameAs(movies.get(0));
        verify(movieRepo).findAll();
    }

    @Test
    public void test_getOne_returns_Movie_returned_from_repo() throws StuffNotFoundException {
        // Arrange
        Movie pinocchio = new Movie();
        when(movieRepo.findOne(7l)).thenReturn(pinocchio);

        // Act
        Movie actual = controller.getOne(7l);

        // Assert
        assertThat(actual).isSameAs(pinocchio);
        verify(movieRepo).findOne(7l);
    }

    @Test
    public void test_getOne_throws_StuffNotFound_when_no_movie_returned_from_repo() {
        try {
            controller.getOne(1);

            // this line of code will only run if test fails
            fail("the controller did not throw StuffNotFoundException");
        } catch (StuffNotFoundException snfe) {
        }
    }

    @Test
    public void test_delete_returns_movie_deleted_when_found() {
        // Arrange
        Movie movie = new Movie();
        when(movieRepo.findOne(3l)).thenReturn(movie);

        // Act
        Movie actual = controller.deleteOne(3l);

        // Assert
        assertThat(movie).isSameAs(actual);
        verify(movieRepo).delete(3l);
        verify(movieRepo).findOne(3l);
    }

    @Test
    public void test_that_null_is_returned_when_findOne_throws_EmptyResultDataAccessException() throws StuffNotFoundException {
        // Arrange
        when(movieRepo.findOne(7l)).thenThrow(new EmptyResultDataAccessException(0));

        // Act
        Movie actual = controller.deleteOne(7l);

        // Assert
        assertThat(actual).isNull();
        verify(movieRepo).findOne(7l);
    }
    
    @Test
    public void test_associateAnActor_returns_associated_actor_for_given_movie() {
        //Arrange
        Movie movie = new Movie();
        when(movieRepo.findOne(7l)).thenReturn(movie);        
        Actor actor = new Actor();
        actor.setId(7l);
        when(actorRepo.findOne(7l)).thenReturn(actor);
        
        //Act
        Movie actual = controller.associateAnActor(7l, actor);        
        
        //Assert
        assertThat(movie.getActors()).isSameAs(actual.getActors());
        assertThat(movie.getActors()).contains(actor);
        verify(movieRepo).save(actual);
        verify(movieRepo).findOne(7l);  
    }

    @Test
    public void test_create_saves_and_returns_a_movie() {
        // Arrange
        Movie movie = new Movie();
        when(movieRepo.save(movie)).thenReturn(movie);

        // Act
        Movie actual = controller.create(movie);

        // Assert
        assertThat(actual).isSameAs(movie);
    }

    @Test
    public void test_update_saves_a_movie() {
        // Arrange
        Movie movie = new Movie();
        when(movieRepo.save(movie)).thenReturn(movie);

        // Act
        Movie actual = controller.update(movie, 7l);

        // Assert
        assertThat(actual.getId()).isEqualTo(movie.getId());
    }

}
