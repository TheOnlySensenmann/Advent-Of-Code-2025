import java.util.ArrayList;

public class Positions {
    private int x;
    private int y;
    public Positions(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isAllowed(int x, int y, ArrayList<char[]> field){
        return this.x + x >= 0 && this.y + y >= 0 && this.x + x < field.size() && this.y + y < field.getFirst().length;
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
}
