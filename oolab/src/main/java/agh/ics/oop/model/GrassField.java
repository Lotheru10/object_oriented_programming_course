package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap {
    int strawAmount;
    private Map<Vector2d, Animal> animals= new HashMap<>();
    private List<Grass> grasses= new ArrayList<>();

    public GrassField(int strawAmount) {
        this.strawAmount = strawAmount;

        int placed = 0;
        int limit = (int) Math.sqrt(strawAmount * 10);
        while (placed < strawAmount) {
            int x = (int) (Math.random() * limit);
            int y = (int) (Math.random() * limit);
            Vector2d generatedPosition = new Vector2d(x, y);
            Grass newGrass = new Grass(generatedPosition);
            if (!grasses.contains(generatedPosition)) {
                grasses.add(newGrass);
                placed++;
            }
        }
    }

        @Override
    public WorldElement objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return animals.get(position);
        }
        for (Grass grass : grasses) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        Vector2d bottom = new Vector2d(upperRight.getX(), upperRight.getY()); //moze do poprawy
        Vector2d top = new Vector2d(lowerLeft.getX(), lowerLeft.getY());
        List<WorldElement> elements = getElements();
        for (WorldElement element: elements) {
            bottom = bottom.lowerLeft(element.getPosition());
            top = top.upperRight(element.getPosition());
        }
        return mapVisualizer.draw(bottom, top);
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> elements = super.getElements();
        return List.copyOf(Stream.concat(elements.stream(), grasses.stream()).toList());
    }
}
