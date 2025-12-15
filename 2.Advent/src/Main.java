import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main{
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner sc = new Scanner(file);
        String inputLine = sc.nextLine();
        String[] ids =  inputLine.split(",");
        String[][] input =  new String[ids.length][2];

        ArrayList<Long> invalidIds = new ArrayList<>();
        for(int i=0; i<ids.length; i++){
            input[i] = ids[i].split("-");
        }
        for(int i=0; i<input.length; i++){
            long start = Long.parseLong(input[i][0]);
            long end = Long.parseLong(input[i][1]);
            for(long j = start; j<= end; j++){
                String stringId = String.valueOf(j);
                int length = stringId.length();
                if(length == 1){
                    continue;
                }
                if(isTrue(stringId)){
                    invalidIds.add(j);
                }

            }


        }

        long result = 0;
        for(Long id: invalidIds){
            result += id;
        }

        System.out.println(result);
    }

    private static ArrayList<String> getSub(String stringId, int k){
        ArrayList<String> res = new ArrayList<>();
        for(int j=0; j*k +k<=stringId.length(); j++){
            res.add(stringId.substring(j*k, j*k+k));
        }
        return res;
    }

    private static boolean isSubEquals(ArrayList<String> sub){
        String lastI = sub.getFirst();
        for(String i : sub){
            if(!i.equals(lastI)){
                return false;
            }
        }
        return true;
    }

    private static boolean isTrue(String stringId){
        for(int k=1; k<=stringId.length()/2; k++){
            if(stringId.length()%k!=0){
                continue;
            }
            ArrayList<String> sub = getSub(stringId, k);
            if(isSubEquals(sub)){
                return true;
            }
        }
        return false;
    }
}