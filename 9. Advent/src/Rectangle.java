import java.awt.*;

public class Rectangle implements Comparable<Rectangle>{
    Position pos1;
    Position pos2;
    long area;

    Rectangle(Position pos1, Position pos2, long area) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.area = area;
    }

    @Override
    public int compareTo(Rectangle o) {
        return Long.compare(this.area,o.area);
    }
}
