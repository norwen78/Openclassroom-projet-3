package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    private List<Neighbour> favoriteNeighbour = createFavoriteList();


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
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
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
    public List<Neighbour> createFavoriteList() {
        List<Neighbour> favoriteNeighbour = new ArrayList<>();
        return favoriteNeighbour;
    }

    @Override
    public List<Neighbour> getFavoriteNeighbour() {
        return favoriteNeighbour;
    }

    @Override
    public void addFavorite(Neighbour neighbour) {
        favoriteNeighbour.add(neighbour);
    }

    @Override
    public void removeFavorite(Neighbour neighbour) {
        favoriteNeighbour.remove(neighbour);
    }

}


