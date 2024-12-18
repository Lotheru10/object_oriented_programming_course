
package agh.ics.oop.presenter;


import agh.ics.oop.model.*;
import javafx.application.Platform;

public class SimulationPresenter implements MapChangeListener{
    private WorldMap map;

    public void setWorldMap(WorldMap map){
        this.map = map;
    }
    public void drawMap(){

    }
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        setWorldMap(worldMap);
        drawMap();
    };
}

