package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public List<Neighbour> getFavoriteNeighbour() {
        ArrayList<Neighbour> favoriteNeighbours = new ArrayList<>();
        for (Neighbour neighbour : neighbours)
            if (neighbour.isFavorite()) {
                favoriteNeighbours.add(neighbour);
            }
        return favoriteNeighbours;
    }

    @Override
    public Neighbour getNeighboursById(long id) {
        for (Neighbour neighbour : neighbours) {
            if (neighbour.getId() == id) {
                return neighbour;
            }
        }
        return null;
    }
    @Override
    public void changeStatus(long neigbhourId) {
        Neighbour neighbour = getNeighboursById(neigbhourId);
        neighbour.setFavorite(!neighbour.isFavorite());
    }
}


