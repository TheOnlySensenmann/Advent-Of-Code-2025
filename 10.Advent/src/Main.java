import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Main{
    final static boolean TEST = false;
    public static void main(String[] args){

        var input = getInput();


        ArrayList<Integer> res = new ArrayList<>();
        for(Machine m : input){
            ArrayList<int[]> buttons = m.buttons;

            boolean[] indicators = m.indicators;


            int max = Integer.MAX_VALUE;
            for(int i = 0; i < Math.pow(2, buttons.size()); i++){
                boolean[] binary = stringToBoolean(Integer.toBinaryString(i), buttons.size());
                boolean[] pattern = getPattern(binary, buttons, indicators.length);
                if(booleanEquals(indicators, pattern)){
                    int size = countTrue(binary);
                    if(max > size){
                        max = size;
                    }
                }
            }
            System.out.println(max);
            res.add(max);

        }

        int solution = 0;
        for(Integer i : res){
            solution += i;
        }

        System.out.println(solution);

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

    private static boolean[] getPattern(boolean[] binary, ArrayList<int[]> buttons, int length) {
        boolean[] pattern = new boolean[length];
        for(int i = 0; i < buttons.size(); i++){
            if(!binary[i]){
                continue;
            }
            for(int j = 0; j < buttons.get(i).length; j++){
                pattern[buttons.get(i)[j]] = !pattern[buttons.get(i)[j]];
            }
        }

        return pattern;
    }

    private static boolean booleanEquals(boolean[] a, boolean[] b){
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