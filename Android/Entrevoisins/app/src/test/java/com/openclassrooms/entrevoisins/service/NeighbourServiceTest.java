package com.openclassrooms.entrevoisins.service;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void createNeighbourWithSuccess() {
        Neighbour neighbourToCreate = service.getNeighbours().get(0);
        service.createNeighbour(neighbourToCreate);
        assertTrue(service.getNeighbours().contains(neighbourToCreate));
    }

    @Test
    public void addFavoriteWithSuccess() {
        List<Neighbour> favoriteList = service.getFavoriteNeighbour();
        Neighbour favoriteToAdd = service.getNeighbours().get(0);
        assertThat(favoriteList, hasSize(0));
        service.addFavorite(favoriteToAdd);
        favoriteList = service.getFavoriteNeighbour();
        assertThat(favoriteList, hasSize(1));
    }

    @Test
    public void getFavoriteWithSuccess() {
        List<Neighbour> myFavoriteList = service.getFavoriteNeighbour();
        Neighbour favoriteToAdd = service.getNeighbours().get(0);
        assertThat(myFavoriteList, hasSize(0));
        service.addFavorite(favoriteToAdd);
        myFavoriteList = service.getFavoriteNeighbour();
        assertThat(myFavoriteList, hasSize(1));
        service.removeFavorite(favoriteToAdd);
        myFavoriteList = service.getFavoriteNeighbour();
        assertThat(myFavoriteList, hasSize(0));
    }

    @Test
    public void removeFavoriteWithSuccess() {
        List<Neighbour> myFavoriteList = service.getFavoriteNeighbour();
        Neighbour favoriteToAdd = service.getNeighbours().get(0);
        service.addFavorite(favoriteToAdd);
        myFavoriteList = service.getFavoriteNeighbour();
        assertThat(myFavoriteList, not(empty()));
        service.removeFavorite(favoriteToAdd);
        myFavoriteList = service.getFavoriteNeighbour();
        assertThat(myFavoriteList, is(empty()));
    }


    @Test
    public void getNeighboursByIdWithSuccess() {
        assertEquals(1, service.getNeighboursById(1).getId());
        assertNotEquals(1, service.getNeighboursById(3).getId());
        assertNull(service.getNeighboursById(-1));
    }


}
