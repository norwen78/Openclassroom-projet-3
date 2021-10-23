package com.openclassrooms.entrevoisins.service;

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

import java.util.ArrayList;
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
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray())); // Tu peux même faire un assertEquals ici, comme ça tu vérifies l'ordre et les données
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void createNeighbourWithSuccess(){
        Neighbour neighbourToCreate = service.getNeighbours().get(0); // Pas bon là : crée un neighbour par toi même dans ton GIVEN et vérifie qu'il est bien là dans ton THEN
        service.createNeighbour(neighbourToCreate);
        assertTrue(service.getNeighbours().contains(neighbourToCreate));
    }
   @Test
    public void addFavoriteWithSuccess(){
        Neighbour favoriteToAdd = service.getNeighbours().get(0);
        service.addFavorite(favoriteToAdd);
        boolean favorite = favoriteToAdd.getFavorite(); // Pas bon non plus, récupères ton neighbour depuis ton service
        assertTrue(true); // typo
   }

    @Test
    public void getFavoriteWithSuccess(){
        List<Neighbour> neighbour = service.getNeighbours();
        List<Neighbour> expectedFavoriteNeighbour = service.getFavoriteNeighbour(); // Vérifie juste que ta liste est vide par défaut :)
        assertFalse(expectedFavoriteNeighbour.equals(neighbour));
    }

    @Test
    public void removeFavoriteWithSuccess() {
        Neighbour favoriteToRemove = service.getNeighbours().get(0);
        service.removeFavorite(favoriteToRemove);
        assertFalse(service.getFavoriteNeighbour().contains(favoriteToRemove));
    }


    @Test
    public void getNeighboursByIdWithSuccess() {
        assertEquals(1, service.getNeighboursById(1).getId()); // A découper en 3 tests (oui c'est nul mais c'est un test UNITAIRE ! :p
        assertNotEquals(1, service.getNeighboursById(3).getId());
        assertNull(service.getNeighboursById(-1));
    }


}
