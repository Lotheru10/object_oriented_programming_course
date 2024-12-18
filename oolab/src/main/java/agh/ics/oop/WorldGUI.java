package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import javafx.application.Application;

import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class WorldGUI {
    public static void main(String[] args) {
        try{
            Application.launch(SimulationApp.class, args);
        }
        catch (Exception e) {

        }
    }
}
