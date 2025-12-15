import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Main{

    static ArrayList<Character> operators = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> numbers = new ArrayList<>();

    //16815738482 to low

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");
        Scanner inputReader = new Scanner(inputFile);
        ArrayList<String> lines = new  ArrayList<>();
        while (inputReader.hasNextLine()) {
            lines.add(inputReader.nextLine());
        }

//        ArrayList<Integer> spaces = getIntegers(lines);
//
//        ArrayList<int[]> numbers = new ArrayList<>();
//
//        spaces.set(spaces.size()-1, spaces.getLast() + 1);
//
//
//        int from = 0;
//        int to = spaces.getFirst();
//        for (int i = 1; i < spaces.size() + 1; i++) {
//            int[] num = new int[lines.size() - 1];
//            for (int j = 0; j < lines.size() - 1; j++) {
//                String string = lines.get(j).substring(from, to);
//                try {
//                    num[j] = Integer.parseInt(string.trim());
//                } catch (NumberFormatException e) {
//                    System.out.println(string);
//                }
//            }
//            numbers.add(num);
//            if(i == spaces.size()){
//                continue;
//            }
//            from = to + 1;
//            to += spaces.get(i) + 1;
//        }

        getInput(lines);
        long res = 0;

        for(int i = 0; i < numbers.getFirst().size(); i++){
            long sum = numbers.getFirst().get(i);
            for(int j = 1; j < numbers.size(); j++){
                switch(operators.get(i)){
                    case '+':
                        sum += numbers.get(j).get(i);
                        break;
                    case '*':
                        sum *= numbers.get(j).get(i);

                }
            }
            res += sum;
        }
        System.out.println(res);
    }

    public static long sum(ArrayList<Integer> list){
        long res = 0;
        for(int i = 0; i < numbers.size(); i++){
            res += list.get(i);
        }
        return res;
    }

    public static long multi(ArrayList<Integer> list){
        long res = list.getFirst();
        for(int i = 1; i < numbers.size(); i++){
            res *= list.get(i);
        }
        return res;
    }

    private static ArrayList<Integer> getIntegers(ArrayList<String> lines) {
        char[] lastLine = lines.getLast().toCharArray();
        ArrayList<Integer> spaces = new ArrayList<>();
        int indexCounter = -1;
        for (char c : lastLine) {
            if (c != ' ') {
                spaces.add(0);
                indexCounter++;
                operators.add(c);
            } else {
                spaces.set(indexCounter, spaces.get(indexCounter) + 1);
            }
        }


        return spaces;
    }

    public static void getInput(ArrayList<String> lines){

        for(int i = 0; i<lines.size()-1; i++){
            ArrayList<Integer> n = new ArrayList<>();
            String[] split =  lines.get(i).split(" ");
            for(String s : split){
                s = s.trim();
                if(!s.isEmpty()){
                    n.add(Integer.parseInt(s));
                }
            }
            numbers.add(n);
        }

        String[] split =  lines.getLast().split(" ");
        for(int i = 0; i<split.length; i++){
            split[i] = split[i].trim();
            if(!split[i].isEmpty()){
                operators.add(split[i].charAt(0));
            }
        }

    }
}