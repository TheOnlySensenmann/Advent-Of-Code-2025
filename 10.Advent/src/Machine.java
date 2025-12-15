import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Machine {
    final boolean[] indicators;
    final ArrayList<int[]> buttons;
    final int[] joltages;

    public Machine(boolean[] indicators, ArrayList<int[]> buttons, int[] joltages) {
        this.indicators = indicators;
        this.buttons = buttons;
        this.joltages = joltages;
    }

    public ArrayList<Integer> getAllButtonsWith(int index){
        ArrayList<Integer> result = new ArrayList<>();
        for(int j=0;j<buttons.size();j++){
            if(contains(buttons.get(j), index)){
                result.add(j);
            }
        }
//        int[] pattern = getPattern(result);
//        for(int j=0;j<pattern.length;j++){
//            if(pattern[j] > joltages[j]){
//                return null;
//            }
//        }
        return result;

    }

    public int[] getPattern(ArrayList<Integer> presses){
        int[] result = new int[joltages.length];
        for(int i=0;i<presses.size();i++){
            for(int j=0;j<buttons.get(i).length;j++){
                result[buttons.get(i)[j]] += presses.get(i);
            }
        }
        return result;
    }

    private static boolean contains(int[] ints, int index) {
        for (int anInt : ints) {
            if (anInt == index) {
                return true;
            }
        }
        return false;
    }
}
