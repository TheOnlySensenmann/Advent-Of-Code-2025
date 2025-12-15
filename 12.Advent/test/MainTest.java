import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void rotate() {
        char[][] original = {{'#','.','#'},{'#','.','.'},{'.','#','#'}};

        printArr(original);
        char[][] rotated = Main.rotate(original);
        System.out.println("\n");
        printArr(rotated);

        Assertions.assertEquals(original.length, rotated.length);
    }

    void printArr(char[][] arr){
        for (char[] chars : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
    }
}