import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Main{
    // 2662548702750900 to high
    final static boolean TEST = false;
    static HashMap<String, String[]> input = new HashMap<>();
    static HashMap<Position, Long> saved = new HashMap<>();
    public static void main(String[] args){
        getInput();
        long res = go("svr", false, false);

        System.out.println(res);
    }

    private static long go(String start, boolean visitedDac, boolean visitedFft){
        if(saved.get(new Position(start, visitedDac, visitedFft)) != null){
            return saved.get(new Position(start, visitedDac, visitedFft));
        }
        long count = 0;
        for(int i = 0; i<input.get(start).length; i++){
            boolean nextDac = visitedDac || input.get(start)[i].equals("dac");
            boolean nextFft = visitedFft || input.get(start)[i].equals("fft");

            if(input.get(start)[i].equals("out")) {
                if(visitedDac && visitedFft){
                    return count + 1;
                }else{
                    return count;
                }
            } else {
                count += go(input.get(start)[i], nextDac, nextFft);
            }
        }
        saved.put(new Position(start, visitedDac, visitedFft), count);
        return count;

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

        ArrayList<Connection>  connections = new ArrayList<>();

        while (sc.hasNextLine()){
            String line =  sc.nextLine();
            String start = line.substring(0, line.indexOf(":"));
            String[] ends = line.substring(line.indexOf(":")+2).split(" ");
            input.put(start, ends);
        }


    }

}