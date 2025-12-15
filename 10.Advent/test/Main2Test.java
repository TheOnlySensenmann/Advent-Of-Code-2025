import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class Main2Test {
    @Test
    void testGetAllPossibilities() {
        ArrayList<LinkedList<Integer>> allPossibilities = Main2.getAllPossibilities(5,4);

        for (LinkedList<Integer> allPossibility : allPossibilities) {
            System.out.println(allPossibility);
        }

        assertEquals(4, allPossibilities.getFirst().size());
    }

    @Test
    void testGetAllPossibilities2() {
        ArrayList<LinkedList<Integer>> allPossibilities = Main2.getAllPossibilities(2,10);

        for (LinkedList<Integer> allPossibility : allPossibilities) {
            System.out.println(allPossibility);
        }

        assertEquals(10, allPossibilities.getFirst().size());
    }
}