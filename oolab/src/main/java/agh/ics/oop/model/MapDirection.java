package agh.ics.oop.model;

public enum MapDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    private static final Vector2d NORTH_VECTOR = new Vector2d(0, 1);
    private static final Vector2d SOUTH_VECTOR = new Vector2d(0, -1);
    private static final Vector2d EAST_VECTOR = new Vector2d(1, 0);
    private static final Vector2d WEST_VECTOR = new Vector2d(-1, 0);

    public String toString() {
        return switch (this) {
            case NORTH -> "Północ";
            case SOUTH -> "Południe";
            case EAST -> "Wschód";
            case WEST -> "Zachód";
        };
    }

    public MapDirection next() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }

    public MapDirection previous() {
        return switch (this) {
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case EAST -> NORTH;
        };
    }

    public Vector2d toUnitVector() {
        return switch (this) {
            case NORTH -> NORTH_VECTOR;
            case SOUTH -> SOUTH_VECTOR;
            case EAST -> EAST_VECTOR;
            case WEST -> WEST_VECTOR;
        };
    }
}
