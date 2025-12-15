import java.util.Objects;

public class Position {
    String key;
    boolean visitedSvr;
    boolean visitedFft;

    public Position(String key, boolean visitedSvr, boolean visitedFft) {
        this.key = key;
        this.visitedSvr = visitedSvr;
        this.visitedFft = visitedFft;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return this.key.equals(position.key) && visitedSvr == position.visitedSvr && visitedFft == position.visitedFft;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, visitedSvr, visitedFft);
    }
}
