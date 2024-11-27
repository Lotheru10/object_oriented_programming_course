package agh.ics.oop.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrassField extends AbstractWorldMap {
    private final int strawAmount;
    private final Map<Vector2d, Grass> grasses= new HashMap<>();

    public GrassField(int strawAmount) {
        this.strawAmount = strawAmount;

        int placed = 0;
        int limit = (int) Math.sqrt(strawAmount * 10);
        while (placed < strawAmount) {
            int x = (int) (Math.random() * limit);
            int y = (int) (Math.random() * limit);
            Vector2d generatedPosition = new Vector2d(x, y);
            Grass newGrass = new Grass(generatedPosition);
            if (!grasses.containsKey(generatedPosition)) {
                grasses.put(generatedPosition, newGrass);
                placed++;
            }
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return super.objectAt(position);
        }
        return grasses.get(position);
    }

    @Override
    public String toString() {
        Vector2d bottom = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d top = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        List<WorldElement> elements = getElements();
        for (WorldElement element : elements) {
            bottom = bottom.lowerLeft(element.getPosition());
            top = top.upperRight(element.getPosition());
        }
        return mapVisualizer.draw(bottom, top);
    }


    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> elements = super.getElements();
        elements.addAll(grasses.values());
        return elements;
    }
}
