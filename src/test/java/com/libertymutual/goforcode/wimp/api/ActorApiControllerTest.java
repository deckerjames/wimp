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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.libertymutual.goforcode.wimp.api.ActorApiController;
import com.libertymutual.goforcode.wimp.api.StuffNotFoundException;
import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;

public class ActorApiControllerTest {

    private ActorRepository actorRepo;
    private ActorApiController controller;

    @Before
    public void setUp() {
        actorRepo = mock(ActorRepository.class);
        controller = new ActorApiController(actorRepo);
    }

    @Test
    public void test_getAll_returns_all_Actors_returned_by_the_repo() {
        // Arrange
        ArrayList<Actor> actors = new ArrayList<Actor>();
        actors.add(new Actor());
        actors.add(new Actor());
        when(actorRepo.findAll()).thenReturn(actors);

        // Act
        List<Actor> actual = controller.getAll();

        // Assert
        assertThat(actors.size()).isEqualTo(2);
        assertThat(actual.get(0)).isSameAs(actors.get(0));
        verify(actorRepo).findAll();
    }

    @Test
    public void test_getOne_returns_Actor_returned_from_repo() throws StuffNotFoundException {
        // Arrange
        Actor pinocchio = new Actor();
        when(actorRepo.findOne(7l)).thenReturn(pinocchio);

        // Act
        Actor actual = controller.getOne(7l);

        // Assert
        assertThat(actual).isSameAs(pinocchio);
        verify(actorRepo).findOne(7l);
    }

    @Test
    public void test_getOne_throws_StuffNotFound_when_no_actor_returned_from_repo() {
        try {
            controller.getOne(1);

            // this line of code will only run if test fails
            fail("the controller did not throw StuffNotFoundException");
        } catch (StuffNotFoundException snfe) {
        }
    }

    @Test
    public void test_delete_returns_actor_deleted_when_found() {
        // Arrange
        Actor actor = new Actor();
        when(actorRepo.findOne(3l)).thenReturn(actor);

        // Act
        Actor actual = controller.deleteOne(3l);

        // Assert
        assertThat(actor).isSameAs(actual);
        verify(actorRepo).delete(3l);
        verify(actorRepo).findOne(3l);
    }

    @Test
    public void test_that_null_is_returned_when_findOne_throws_EmptyResultDataAccessException() throws StuffNotFoundException {
        // Arrange
        when(actorRepo.findOne(7l)).thenThrow(new EmptyResultDataAccessException(0));

        // Act
        Actor actual = controller.deleteOne(7l);

        // Assert
        assertThat(actual).isNull();
        verify(actorRepo).findOne(7l);
    }

    @Test
    public void test_create_saves_and_returns_an_actor() {
        // Arrange
        Actor actor = new Actor();
        when(actorRepo.save(actor)).thenReturn(actor);
        
        // Act
        Actor actual = controller.create(actor);
        
        // Assert
        assertThat(actual).isSameAs(actor);
    }

    @Test
    public void test_update_saves_actor() {
     // Arrange
        Actor actor = new Actor();
        when(actorRepo.save(actor)).thenReturn(actor);
        
        // Act
        Actor actual = controller.update(actor, 7l);
        
        // Assert
        assertThat(actual.getId()).isEqualTo(actor.getId());
    }

}
