package jeu2d;

/**
 * Created by junderwood42 on 5/18/16.
 */
public class Tile {
    public enum VertexPosition {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    }

    public enum RotationDirection {
        CLOCK_WISE(1),
        COUNTER_CLOCK_WISE(-1);

        private int sign;

        RotationDirection(int sign) {
            this.sign = sign;
        }

        public int getSign() {
            return sign;
        }
    }

    private final TileVertex[] vertices;

    private Tile(TileVertex[] vertices) {
        this.vertices = vertices;
    }

    public Tile(TileVertex left, TileVertex top, TileVertex right, TileVertex bottom) {
        TileVertex[] vertices = new TileVertex[4];
        vertices[VertexPosition.LEFT.ordinal()] = left;
        vertices[VertexPosition.TOP.ordinal()] = top;
        vertices[VertexPosition.RIGHT.ordinal()] = right;
        vertices[VertexPosition.BOTTOM.ordinal()] = bottom;
        this.vertices = vertices;
    }

    public TileVertex getVertex(VertexPosition position) {
        return vertices[position.ordinal()];
    }

    public TileVertex getLeftVertex() {
        return getVertex(VertexPosition.LEFT);
    }

    public TileVertex getTopVertex() {
        return getVertex(VertexPosition.TOP);
    }

    public TileVertex getRightVertex() {
        return getVertex(VertexPosition.RIGHT);
    }

    public TileVertex getBottomVertex() {
        return getVertex(VertexPosition.BOTTOM);
    }

    public Tile rotateClockwise() {
        return rotate(RotationDirection.CLOCK_WISE);
    }

    public Tile rotateCounterClockwise() {
        return rotate(RotationDirection.COUNTER_CLOCK_WISE);
    }

    public Tile rotate(RotationDirection direction) {
        VertexPosition[] positions = VertexPosition.values();
        TileVertex[] rotatedVertices = new TileVertex[4];

        for(int i = 0; i < positions.length; i++) {
            rotatedVertices[i] = vertices[(i + direction.getSign()) % positions.length];
        }

        return new Tile(rotatedVertices);
    }
}
