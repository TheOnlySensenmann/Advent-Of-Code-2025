import java.util.Objects;

public class Position {
    private int x;
    private int y;
    private int z;
    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public double getDistance(Position p) {
        int xDiff = Math.abs(this.x - p.x);
        int yDiff = Math.abs(this.y - p.y);
        int zDiff = Math.abs(this.z - p.z);

        double hypotenuse = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
        return Math.sqrt(Math.pow(hypotenuse, 2) + Math.pow(zDiff, 2));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y && z == position.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
