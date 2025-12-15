import java.util.ArrayList;
import java.util.Objects;

public class Connection {
    final String start;
    final String[] ends;

    public Connection(String start, String[] ends) {
        this.start = start;
        this.ends = ends;
    }

    @Override
    public boolean equals(Object o) {
        if(! (o instanceof String that)) {
            return false;
        }
        return that.equals(this.start);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(start);
    }
}

