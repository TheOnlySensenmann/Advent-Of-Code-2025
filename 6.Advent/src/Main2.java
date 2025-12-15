import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Main2{

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



        getInput(lines);
        long res = 0;

        for(int i = 0; i < numbers.size(); i++){
            long sum = switch (operators.get(i)){
                case '+' -> sum(numbers.get(i));
                case '*' -> multi(numbers.get(i));
                default -> 0;
            };

            res += sum;
        }
        System.out.println(res);
    }

    public static long sum(ArrayList<Integer> list){
        long res = 0;
        for (Integer integer : list) {
            res += integer;
        }
        return res;
    }

    public static long multi(ArrayList<Integer> list){
        long res = list.getFirst();
        for(int i = 1; i < list.size(); i++){
            res *= list.get(i);
        }
        return res;
    }





    public static void getInput(ArrayList<String> lines){
        ArrayList<String> list = new ArrayList<>();

        for(int i = 0; i < lines.getFirst().length(); i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < lines.size()-1; j++){
                if(lines.get(j).charAt(i) != ' ') {
                    sb.append(lines.get(j).charAt(i));
                }
            }
            list.add(sb.toString());
        }
        ArrayList<Integer> temp = new ArrayList<>();
        for (String string : list) {
            if (string.isEmpty()) {
                numbers.add(temp);
                temp = new ArrayList<>();
            } else {
                temp.add(Integer.parseInt(string));
            }
        }
        numbers.add(temp);


        String[] split =  lines.getLast().split(" ");
        for(int i = 0; i<split.length; i++){
            split[i] = split[i].trim();
            if(!split[i].isEmpty()){
                operators.add(split[i].charAt(0));
            }
        }

    }
}