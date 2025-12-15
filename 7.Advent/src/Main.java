import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

class Main{
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");
        Scanner inputReader = new Scanner(inputFile);

        ArrayList<char[]> input = new ArrayList<char[]>();
        while (inputReader.hasNextLine()) {
            input.add(inputReader.nextLine().toCharArray());
        }

        int indexOfS = 0;

        for(int i = 0; i<input.getFirst().length; i++){
            if(input.getFirst()[i] == 'S'){
                indexOfS = i;
                break;
            }
        }

        HashSet<Integer> lines = new HashSet<Integer>();
        lines.add(indexOfS);
        int count = 0;

        for(int i = 0; i<input.size() - 1; i++){
            HashSet<Integer> temp = (HashSet<Integer>) lines.clone();
            for(Integer integer : lines){
                if(input.get(i + 1)[integer] == '^'){
                    count++;
                    temp.add(integer + 1);
                    temp.add(integer - 1);
                    temp.remove(integer);
                }
            }
            lines = temp;
        }
        System.out.println(count);

    }
}