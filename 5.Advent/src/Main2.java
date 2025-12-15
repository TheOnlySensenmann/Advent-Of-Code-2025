import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main2 {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");
        Scanner sc = new Scanner(inputFile);

        ArrayList<String> inputReader = new  ArrayList<>();
        ArrayList<String> freshStrings = new ArrayList<>();



        while (sc.hasNextLine()) {
            String line =  sc.nextLine();
            if(line.isEmpty()){
                freshStrings = (ArrayList<String>) inputReader.clone();
                inputReader.clear();
                continue;
            }

            inputReader.add(line);

        }

        ArrayList<long[]> fresh = new ArrayList<>();


        for (String freshString : freshStrings) {
            long[] i = new long[2];
            i[0] = Long.parseLong(freshString.split("-")[0]);
            i[1] = Long.parseLong(freshString.split("-")[1]);
            fresh.add(i);
        }

        fresh.sort(Comparator.comparingLong(i -> i[0]));
        long last = -1;
        for(int i = 0; i<fresh.size();i++){
            if(fresh.get(i)[0] <= last){
                fresh.get(i)[0] = last+1;
            }
            if(fresh.get(i)[0] > fresh.get(i)[1]){
                fresh.remove(i);
                i--;
            }
            last = fresh.get(i)[1];
        }

        long count = 0;
        for (long[] longs : fresh) {
            count += longs[1] - longs[0] + 1;
        }
        System.out.println(count);







    }

    private static boolean findIndex(long fruit, ArrayList<long[]> fresh) {
        for (long[] longs : fresh) {
            if (fruit >= longs[0] && fruit <= longs[1]) {
                return true;
            }
        }
        return false;
    }
}