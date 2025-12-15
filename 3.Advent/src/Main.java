import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

class Main{
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");
        Scanner sc = new Scanner(inputFile);
        ArrayList<String> list = new ArrayList<String>();
        while(sc.hasNextLine()){
            list.add(sc.nextLine());
        }
        long result = 0;
        for(String s : list){
            long highest = getHighest(s);
            result += highest;
        }
        System.out.println(result);
    }

    private static long getHighest(String s) {
        int needed = 12;

        char[] arr = s.toCharArray();

        ArrayList<Integer> list = new ArrayList<>();
        for (char c : arr) {
            list.add(Integer.parseInt(String.valueOf(c)));
        }


        ArrayList<Integer> result = new ArrayList<>();



        for(int i = 0; i < 12; i++){
            result.add(getMaxIndex(list, needed - 1));
            needed--;

        }

        if(result.size()!= 12){
            throw new RuntimeException("Gurr");
        }


        StringBuilder sb = new StringBuilder();
        for (Integer integer : result) {
            sb.append(integer);
        }

        return Long.parseLong(sb.toString());
    }

    private static int getMaxIndex(ArrayList<Integer> list, int mustLeft) {
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < list.size()-mustLeft; i++) {
            if (list.get(i) > max) {
                max = list.get(i);
                maxIndex = i;
            }
        }
        for(int i = 0; i<=maxIndex;i++){
            list.removeFirst();
        }
        return max;
    }
}