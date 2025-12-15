import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");
        Scanner sc = new Scanner(inputFile);

        ArrayList<String> inputReader = new  ArrayList<>();
        ArrayList<String> freshStrings = new ArrayList<>();
        ArrayList<String> availableString = new ArrayList<>();



        while (sc.hasNextLine()) {
            String line =  sc.nextLine();
            if(line.isEmpty()){
                freshStrings = (ArrayList<String>) inputReader.clone();
                inputReader.clear();
                continue;
            }

            inputReader.add(line);

        }
        availableString = (ArrayList<String>) inputReader.clone();

        ArrayList<long[]> fresh = new ArrayList<>();



        for (String freshString : freshStrings) {
            long[] i = new long[2];
            i[0] = Long.parseLong(freshString.split("-")[0]);
            i[1] = Long.parseLong(freshString.split("-")[1]);
            fresh.add(i);
        }

        fresh.sort(Comparator.comparingLong(a -> a[0]));

        long countFresh = 0;
        for(String i: availableString){
            long fruit =  Long.parseLong(i);
            if(findIndex(fruit, fresh)){
                countFresh++;
            }
        }

        System.out.println(countFresh);







    }

    private static boolean findIndex(long fruit, ArrayList<long[]> fresh) {
        for(int i = 0; i < fresh.size(); i++){
            if(fruit >= fresh.get(i)[0] && fruit <= fresh.get(i)[1]){
                return true;
            }
        }
        return false;
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