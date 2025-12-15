import java.util.Objects;

public class Relation implements Comparable<Relation>{
    public Position start;
    public Position end;
    public double distance;
    public Relation(Position start, Position end) {
        this.start = start;
        this.end = end;
        this.distance = start.getDistance(end);
    }

    public Relation() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Relation relation = (Relation) o;
        return this.start.equals(relation.start) || this.end.equals(relation.end) || this.start.equals(relation.end) || this.end.equals(relation.start);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(distance);
    }

    @Override
    public int compareTo(Relation o) {
        return Double.compare(distance, o.distance);
    }
}
