import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Main{
    final static boolean TEST = true;
    static ArrayList<char[][]> fields = new ArrayList<>();
    static ArrayList<Region> regions = new ArrayList<>();
    public static void main(String[] args){
        getInput();

        int counter = 0;
        for(Region r : regions){

            if(isPossible(r)) counter++;
        }

        System.out.println(counter);
    }

    private static boolean isPossible(Region r) {
        int sum = getSum(r.nums);
        for(int i = 0; i < sum; i++){
            
        }
    }

    private static int getSum(int[] arr){
        int count = 0;
        for (int j : arr) {
            count += j;
        }
        return count;
    }

    public static char[][] rotate(char[][] arr){
        char[][] newArr = new char[arr[0].length][arr.length];
        int i2 = 0;
        int j2 = arr[0].length - 1;
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                newArr[i2][j2] = arr[i][j];
                i2++;
            }
            j2--;
            i2 = 0;
        }
        return newArr;
    }

    public static char[][] stack(char[][] start, char[][] toStack){
        char[][] newStack = start.clone();
        for(int i = 0; i < start.length; i++){
            for(int j = 0; j < start[i].length; j++){
                if(toStack[i][j] == '#'){
                    if(start[i][j] == '#'){
                        return null;
                    } else{
                        newStack[i][j] = '#';
                    }
                }
            }
        }

        return newStack;
    }




    private static void getInput(){
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

        StringBuilder sb = new StringBuilder();
        while(sc.hasNextLine()){
            sb.append(sc.nextLine()).append("\n");
        }

        String[] inputFile = sb.toString().split("\n\n");


        for(int i = 0; i < inputFile.length; i++){
            if(inputFile[i].charAt(1) == ':'){
                String[] temp = inputFile[i].split("\n");
                char[][] field = new char[temp.length - 1][temp[0].length()];
                for(int j = 0; j < temp.length - 1; j++){
                    field[j] =  temp[j + 1].toCharArray();
                }
                fields.add(field);
            } else {
                String[] temp = inputFile[i].split("\n");
                for (String string : temp) {
                    int sizeX = Integer.parseInt(string.split("x")[0]);
                    int sizeY = Integer.parseInt(string.substring(string.indexOf('x') + 1, string.indexOf(':')));
                    String[] toParse = string.substring(string.indexOf(':') + 2).split(" ");
                    int[] nums = new int[toParse.length];
                    for (int k = 0; k < toParse.length; k++) {
                        nums[k] = Integer.parseInt(toParse[k]);
                    }
                    regions.add(new Region(nums, sizeX, sizeY));
                }
            }
        }

        int i = 0;

    }

}