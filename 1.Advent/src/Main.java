import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Main{
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> input = new  ArrayList<>();
        File inputFile = new File("input.txt");
        Scanner sc = new Scanner(inputFile);
        while(sc.hasNextLine()){
            input.add(sc.nextLine());
        }

        int pointer = 50;
        int result = 0;

        for (String s : input) {
            char[] inArray = s.toCharArray();
            int dir = findDir(inArray[0]);
            int step = toInt(inArray);
            for(int i = 0; i < step; i++){
                pointer+= dir;
                if(pointer < 0){
                    pointer+= 100;
                }
                if(pointer >= 100){
                    pointer -= 100;
                }
                if (pointer == 0) {
                    result++;
                }
            }

        }

        System.out.println(result);
        sc.close();
    }

    private static int toInt(char[] inArray){
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < inArray.length; i++){
            sb.append(inArray[i]);
        }
        return Integer.parseInt(sb.toString());
    }

    private static int findDir(char dir){
        return switch (dir){
            case 'L' -> -1;
            case 'R' -> 1;
            default -> throw new IllegalArgumentException();
        };
    }
}