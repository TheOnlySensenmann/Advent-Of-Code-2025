import java.util.ArrayList;
import java.util.Objects;

public class Way {
    private ArrayList<Boolean> arr;

    public Way() {
        arr = new ArrayList<>();
    }

    public ArrayList<Boolean> getArr() {
        return arr;
    }

    public void add(boolean val) {
        arr.add(val);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Way way = (Way) o;

        if(arr.size() != way.arr.size()) return false;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) != way.arr.get(i)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(arr);
    }
}

