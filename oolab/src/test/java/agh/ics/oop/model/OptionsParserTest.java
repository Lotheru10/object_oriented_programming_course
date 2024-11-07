package agh.ics.oop.model;
import agh.ics.oop.OptionsParser;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class OptionsParserTest {
    @Test
    public void testParseValidDirections() {
        String[] args1 = {"f", "b", "l", "r"};
        List<MoveDirection> result1 = OptionsParser.parse(args1);
        List<MoveDirection> correct1 = new ArrayList<>() {{
            add(MoveDirection.FORWARD);
            add(MoveDirection.BACKWARD);
            add(MoveDirection.LEFT);
            add(MoveDirection.RIGHT);
        }};

        Assertions.assertEquals(result1, correct1);

        String[] args2 = {"b", "k", "r", "l", "f", "b"};
        List<MoveDirection> result2 = OptionsParser.parse(args2);
        List<MoveDirection> correct2 = new ArrayList<>() {{
            add(MoveDirection.BACKWARD);
            add(MoveDirection.RIGHT);
            add(MoveDirection.LEFT);
            add(MoveDirection.FORWARD);
            add(MoveDirection.BACKWARD);
        }};

        Assertions.assertEquals(result2, correct2);
    }
}