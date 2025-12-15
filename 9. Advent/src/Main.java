import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Main {
    static final boolean example = false;
    public static void main(String[] args) {
        var input = getInput();

        long max = 0;
        for (int i = 0; i<input.size(); i++) {
            for(int j = i + 1; j<input.size(); j++) {
                long res = getArea(input.get(i), input.get(j));
                if (res > max) {
                    max = res;
                }
            }
        }

        System.out.println(max);
    }

    private static long getArea(int[] ints, int[] ints1) {
        long dif1 = Math.abs(ints[0] - ints1[0]) + 1;
        long dif2 = Math.abs(ints[1] - ints1[1]) + 1;
        return dif1 * dif2;
    }


    public static ArrayList<int[]> getInput() {
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


        ArrayList<int[]> list = new ArrayList<>();
        while (input.hasNextLine()){
            String[] line = input.nextLine().split(",");
            int[] arr = new int[2];
            arr[0] = Integer.parseInt(line[0]);
            arr[1] = Integer.parseInt(line[1]);
            list.add(arr);
        }

        return list;

    }
}