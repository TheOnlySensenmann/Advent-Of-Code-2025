import javax.swing.text.Position;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");
        Scanner sc = new Scanner(inputFile);


        ArrayList<char[]> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine().toCharArray());
        }

        ArrayList<Positions> positions = new ArrayList<>();

        for(int i = 0; i < input.size(); i++) {
            for(int j = 0; j < input.get(i).length; j++) {
                if(input.get(i)[j] == '@') {
                    positions.add(new Positions(i, j));
                }
            }
        }
        int wholeRes = 0;
        while(true) {
            ArrayList<Integer> toRemove = new ArrayList<>();

            int res = 0;

            for (int i = 0; i < positions.size(); i++) {
                if (countAround(positions.get(i), input) < 4) {
                    toRemove.add(i);
                    res++;
                }
            }
            for (int i = toRemove.size() - 1; i >= 0; i--) {
                int index = toRemove.get(i);
                input.get(positions.get(index).getX())[positions.get(index).getY()] = '.';
                positions.remove(index);

            }
            wholeRes += res;
            if (res == 0){
                System.out.println(wholeRes);
                System.exit(0);
            }
        }



    }

    private static int countAround(Positions positions, ArrayList<char[]> input) {
        int count = 0;
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(i == 0 && j == 0){
                    continue;
                }
                if(positions.isAllowed(i,j,input)){
                    if(input.get(i + positions.getX())[j +  positions.getY()] == '@'){
                        count++;
                    }
                }
            }
        }
        return count;
    }


    public static ArrayList<Integer> toInt(ArrayList<String> input) {
        ArrayList<Integer> res = new  ArrayList<>();
        for (String string : input) {
            res.add(Integer.parseInt(string));
        }
        return res;
    }

    public static int[] toInt(String[] input){
        int[] res = new  int[input.length];
        for(int i = 0; i < input.length; i++){
            res[i] = Integer.parseInt(input[i]);
        }
        return res;
    }
}