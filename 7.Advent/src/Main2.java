import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


class Main2{

    static HashMap<Position, Long> timeLines = new HashMap<>();
    static ArrayList<char[]> input = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");
        Scanner inputReader = new Scanner(inputFile);

        while (inputReader.hasNextLine()) {
            input.add(inputReader.nextLine().toCharArray());
        }

        int indexOfS = 0;

        for(int i = 0; i<input.getFirst().length; i++){
            if(input.getFirst()[i] == 'S'){
                indexOfS = i;
                break;
            }
        }


        Position start = new  Position(0, indexOfS);

        System.out.println(go(start));

    }


    private static long go(Position position){
        long counter = 0;
        if(timeLines.containsKey(position)){
            return counter + timeLines.get(position);
        }

        if(position.x >= input.size()){
            return counter + 1;
        }

        if(position.y >= input.getFirst().length){
            throw new RuntimeException("Gurr");
        }

        while(input.get(position.x)[position.y] != '^'){
            position.x++;
            if(position.x >= input.size()){
                return 1;
            }
        }
        if(timeLines.containsKey(position)){
            return counter + timeLines.get(position);
        }

        Position newPosition = position.clone();

        newPosition.y++;
        long amount = 0;
        long returnStatement = go(newPosition.clone());
        amount += returnStatement;
        counter += returnStatement;
        newPosition.y -= 2;
        returnStatement = go(newPosition.clone());
        amount += returnStatement;
        if(position.equals(new Position(8,6))){
            int x = 0;
        }
        timeLines.put(position,amount);
        counter += returnStatement;


        return counter;
    }

    private static boolean[] stringToBoolean(String s, int length){
        boolean[] toReturn = new boolean[length];
        for(int i = 0; i<s.length(); i++){
            toReturn[length - i - 1] = s.charAt(s.length() - 1 - i) == '1';
        }
        return toReturn;
    }



    private static int findLines() {
        int count = 0;
        for (char[] chars : input) {
            if (contains(chars, '^')) count++;
        }
        return count;
    }

    private static boolean contains(char[] arr, char target){
        for (char c : arr) {
            if (c == target) {
                return true;
            }
        }
        return false;
    }

    private static Way getCount(int indexOfS, ArrayList<char[]> input, boolean[] toGo) {
        HashSet<Integer> lines = new HashSet<>();
        lines.add(indexOfS);
        int count = 0;
        int index = 0;

        Way way = new Way();

        for(int i = 0; i< input.size() - 1; i++){
            HashSet<Integer> temp = (HashSet<Integer>) lines.clone();
            for(Integer integer : lines){
                if(input.get(i + 1)[integer] == '^'){
                    count++;
                    way.add(toGo[index]);
                    if(toGo[index]){
                        temp.add(integer + 1);
                    } else {
                        temp.add(integer - 1);
                    }
                    temp.remove(integer);
                    index++;
                }
            }
            lines = temp;
        }
        return way;
    }
}