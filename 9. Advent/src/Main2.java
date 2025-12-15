import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;



public class Main2 {
    static final boolean example = false;
    static HashSet<Position> green = new HashSet<>();
    static ArrayList<Position> input = getInput();
    public static void main(String[] args) {

        int maxX = 0;
        int maxY = 0;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for(Position p : input){
            if(p.x < minX) minX = p.x;
            if(p.y < minY) minY = p.y;
            if(p.x > maxX){
                maxX = p.x;
            }
            if(p.y > maxY){
                maxY = p.y;
            }
        }

        Position leftUp = null;
        int mostLeft = Integer.MAX_VALUE;
        for(Position p : input){
            if(p.x == minX && p.y < mostLeft){
                leftUp = p.copy();
                mostLeft = p.y;
            }
        }
        leftUp.x += 1;
        leftUp.y += 1;





        for(int i = 0; i < input.size(); i++){
            Position p1 = input.get(i);
            Position p2;
            if(i == input.size()-1){
                p2 = input.getFirst();
            } else {
                p2 = input.get(i + 1);
            }
            if(p1.y == p2.y){
                for(int j = Integer.min(p1.x, p2.x); j < Integer.max(p1.x, p2.x); j++){
                    green.add(new Position(j, p1.y));
                }
            } else if(p1.x == p2.x){
                for(int j = Integer.min(p1.y, p2.y); j < Integer.max(p1.y, p2.y); j++){
                    green.add(new Position(p1.x, j));
                }
            }
        }


        System.out.println(String.valueOf(leftUp.x) + leftUp.y);

        //printMap(maxX, maxY, minX, minY);

        go(leftUp);

        //printMap(maxX,maxY, minX, minY);






    }

    private static void go(Position leftUp) {
        //printMap(8, 15, 0,0);
        if(green.contains(leftUp)){
            return;
        }


        green.add(leftUp.copy());
        Position newPosition = leftUp.copy();
        newPosition.x += 1;
        go(newPosition);
        newPosition.x -= 2;
        go(newPosition);
        newPosition.x += 1;
        newPosition.y += 1;
        go(newPosition);
        newPosition.y -= 2;
        go(newPosition);
    }

    private static long getArea(Position pos1, Position pos2) {
        long dif1 = Math.abs(pos1.x - pos2.x) + 1;
        long dif2 = Math.abs(pos1.y - pos2.y) + 1;
        return dif1 * dif2;
    }

    private static void printMap(int maxX, int maxY, int minX, int minY) {
        for(int i = minX; i < maxX + 2; i++){
            for(int j = minY; j < maxY + 2; j++){
                if(green.contains(new Position(i, j)) || input.contains(new Position(i, j))){
                    System.out.print('X');
                }else{
                    System.out.print(".");
                }

            }
            System.out.println();
        }
    }


    public static ArrayList<Position> getInput() {
        File inputFile = new File("input.txt");
        if (example) {
            inputFile = new File("input_example.txt");
        }
        Scanner input = null;
        try {
            input = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
        }


        ArrayList<Position> list = new ArrayList<>();
        while (input.hasNextLine()){
            String[] line = input.nextLine().split(",");
            list.add(new Position(Integer.parseInt(line[1]), Integer.parseInt(line[0])));
        }

        return list;

    }
}