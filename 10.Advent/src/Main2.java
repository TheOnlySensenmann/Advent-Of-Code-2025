import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class Main2{
    final static boolean TEST = false;
    public static void main(String[] args){

        var input = getInput();


        ArrayList<Integer> res = new ArrayList<>();
        for(Machine m : input){


            ArrayList<Integer> buttonsToPress = m.getAllButtonsWith(getMaxIndex(m.joltages));


            res.add(getPossible(m.buttons, m.joltages, buttonsToPress));

            System.out.println(res.getLast());
        }

        int solution = 0;
        for(Integer i : res){
            solution += i;
        }

        System.out.println(solution);

    }

    private static int getMax(int[] arr) {
        int max = 0;
        for (int j : arr) {
            if (j > max) {
                max = j;
            }
        }
        return max;
    }

    private static int getMaxIndex(int[] arr) {
        int max = 0;
        int index = 0;
        for(int i = 0; i< arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
                index = i;
            }
        }
        return index;
    }

    private static int getPossible(ArrayList<int[]> buttons, int[] joltages, ArrayList<Integer> buttonsToPress) {
        ArrayList<LinkedList<Integer>> min = getAllPossibilities(getMax(joltages), buttonsToPress.size());
        ArrayList<int[]> patterns = new ArrayList<>();
        for(int j = 0;  j < min.size(); j++){
            int[] pattern = getPattern(min.get(j), buttonsToPress, joltages.length, buttons);
            if(patternToGreat(pattern, joltages)){
               continue;
            }
            patterns.add(pattern);
            if(intEquals(pattern, joltages)){
                return getMax(joltages);
            }
        }

        ArrayList<int[]> buttonsRemaining = getRemaining(buttons, buttonsToPress);
        for(int i = 1; true; i++){
            ArrayList<LinkedList<Integer>> possible = getAllPossibilities(i, buttonsRemaining.size());
            for(int j = 0; j < patterns.size(); j++){
                for(int k = 0; k < possible.size(); k++){
                    if(intEquals(addUpToPattern(possible.get(k), buttonsRemaining, patterns.get(j)), joltages)){
                        return getMax(joltages) + i;
                    }
                }
            }

        }

    }


    private static int[] addUpToPattern(LinkedList<Integer> presses, ArrayList<int[]> buttons, int[] pattern) {

        int[] newPattern = pattern.clone();


        for(int j = 0; j < presses.size(); j++){
            for(int k = 0; k < buttons.get(j).length; k++){
                newPattern[buttons.get(j)[k]] += presses.get(j);
            }
        }

        return newPattern;
    }

    private static boolean patternToGreat(int[] pattern, int[] comparator){
        for(int j = 0; j < pattern.length; j++){
            if(pattern[j] > comparator[j]){
                return true;
            }
        }
        return false;
    }

    private static ArrayList<int[]> getRemaining(ArrayList<int[]> buttons, ArrayList<Integer> buttonsToPress) {
        ArrayList<int[]> result = new ArrayList<>();
        for(int j = 0; j < buttons.size(); j++){
            if(!buttonsToPress.contains(j)){
                result.add(buttons.get(j));
            }
        }
        return result;
    }

    public static ArrayList<LinkedList<Integer>> getAllPossibilities(int toInsert, int length) {
        if(length == 0){
            return new ArrayList<>();
        }
        ArrayList<LinkedList<Integer>> possibilities = new ArrayList<>();
        if(length == 1){
            LinkedList<Integer> temp = new LinkedList<>();
            temp.add(toInsert);
            possibilities.add(temp);
            return possibilities;
        }

        for(int i = 0; i<=toInsert; i++){
            ArrayList<LinkedList<Integer>> smaller= getAllPossibilities(toInsert - i, length - 1);
            for (LinkedList<Integer> temp : smaller) {
                temp.addFirst(i);
                possibilities.add(temp);
            }
        }


        return possibilities;
    }

    private static int countTrue(boolean[] pattern) {
        int count = 0;
        for (boolean b : pattern) {
            if (b) {
                count++;
            }
        }
        return count;
    }

    private static int[] getPattern(LinkedList<Integer> presses, ArrayList<Integer> buttonsToPress, int length, ArrayList<int[]> buttons) {
        int[] pattern = new  int[length];

        for(int i = 0; i<buttonsToPress.size(); i++){
            int[] button = buttons.get(buttonsToPress.get(i));
            for (int k : button) {
                pattern[k] += presses.get(i);
            }
        }

        return pattern;
    }

    private static boolean intEquals(int[] a, int[] b){
        for(int i = 0; i < a.length; i++){
            if(a[i] != b[i]){
                return false;

            }
        }
        return true;
    }

    private static boolean[] stringToBoolean(String s, int length){
        boolean[] toReturn = new boolean[length];
        for(int i = 0; i<s.length(); i++){
            toReturn[length - i - 1] = s.charAt(s.length() - 1 - i) == '1';
        }
        return toReturn;
    }


    private static ArrayList<Machine> getInput(){
        File file = new File("input.txt");
        if(TEST){
            file = new File("input_example.txt");
        }

        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
        }

        ArrayList<Machine> machines = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();

        while (sc.hasNextLine()){
            lines.add(sc.nextLine());

            String indi = lines.getLast().substring(1, lines.getLast().indexOf(']'));

            boolean[] indicators = new boolean[indi.length()];
            for(int i = 0; i < indi.length(); i++){
                indicators[i] = indi.charAt(i) == '#';
            }
            ArrayList<int[]> buttons = new ArrayList<>();
            String but = lines.getLast().substring(lines.getLast().indexOf(']') + 2, lines.getLast().indexOf('{') - 1);
            int i = 0;
            while(i < but.length() - 1){
                String temp = but.substring(but.indexOf('(', i) + 1, but.indexOf(')', i));
                i = but.indexOf(')', i) + 1;
                String[] arr = temp.split(",");
                int[] button = new int[arr.length];
                for(int j = 0; j < arr.length; j++){
                    button[j] = Integer.parseInt(arr[j]);
                }
                buttons.add(button);
            }

            String jolt = lines.getLast().substring(lines.getLast().indexOf('{') + 1, lines.getLast().indexOf('}'));
            String[] arr = jolt.split(",");
            int[] joltages = new int[arr.length];
            for(int j = 0; j < arr.length; j++){
                joltages[j] = Integer.parseInt(arr[j]);
            }

            machines.add(new Machine(indicators, buttons, joltages));


        }


        return machines;
    }

}