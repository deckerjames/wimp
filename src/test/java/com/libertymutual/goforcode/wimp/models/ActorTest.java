package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.meanbean.test.BeanTester;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.wimp.api.ActorApiController;
import com.libertymutual.goforcode.wimp.api.StuffNotFoundException;
import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

public class ActorTest {

    private ActorRepository actorRepo;
    private ActorApiController controller;
    private MovieRepository movieRepo;

    @Before
    public void setUp() {
        actorRepo = mock(ActorRepository.class);
        controller = new ActorApiController(actorRepo);
    }

    @Test
    public void test_all_gets_and_sets() {
        new BeanTester().testBean(Actor.class);
    } 
}