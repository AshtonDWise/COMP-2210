import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class AshtonGameTest {
    

     public static void main(String[] args) {
        AshtonGame bitch = new AshtonGame();
        bitch.loadLexicon("C:/Users/ashto/OneDrive/Documents/words.txt");
        String[] stringArray = {"a", "b", "c", "g", "r", "t", "w", "o", "p", "k", "l", "o", "e", "p", "y", "z"};
        //setBoard Test
        bitch.setBoard(stringArray);

        //getBoard test
        System.out.print(bitch.getBoard());
    }

    @Test
    public void loadLexiconTest() {
        AshtonGame bitch = new AshtonGame();
        bitch.loadLexicon("C:/Users/ashto/OneDrive/Desktop/COMP 2210/A5/A5/CSW12.txt");

        assertEquals(270163 , bitch.getLex().size());
    }

    @Test
    public void isValidWordTest() {
        AshtonGame bitch = new AshtonGame();
        bitch.loadLexicon("C:/Users/ashto/OneDrive/Documents/words.txt");

        assertEquals(true, bitch.isValidWord("ass"));
        assertEquals(false, bitch.isValidWord("whore"));
    }


    @Test
    public void isValidPrefixTest() {
        AshtonGame bitch = new AshtonGame();
        bitch.loadLexicon("C:/Users/ashto/OneDrive/Documents/words.txt");

        assertEquals(true, bitch.isValidPrefix("ass"));
        assertEquals(true, bitch.isValidPrefix("co"));
        assertEquals(false, bitch.isValidPrefix("bo"));
    }


    @Test 
    public void isOnBoardTest() {
        AshtonGame bitch = new AshtonGame();
        bitch.loadLexicon("C:/Users/ashto/OneDrive/Documents/words.txt");
        String[] stringArray = {"A", "S", "w", "o", "s", "h", "e", "p", "l", "r", "e", "s", "h", "i", "t", "s"};
        bitch.setBoard(stringArray);
        List<Integer> out = new ArrayList<Integer>();
        out.add(0);
        out.add(1);
        out.add(4);
        assertEquals(out, bitch.isOnBoard("ass"));
        
        AshtonGame assHole = new AshtonGame();
        assHole.loadLexicon("C:/Users/ashto/OneDrive/Documents/words.txt");
        String[] stringArray2 = {"c", "o", "f", "f", "e", "e", "e", "p", "l", "r", "e", "s", "h", "i", "t", "s"};
        assHole.setBoard(stringArray2);
        List<Integer> pussy = new ArrayList<Integer>();
        pussy.add(0);
        pussy.add(1);
        pussy.add(2);
        pussy.add(3);
        pussy.add(6);
        pussy.add(5);
        System.out.println(assHole.getBoard());
        assertEquals(pussy, assHole.isOnBoard("coffee"));

        String[] stringArray3 = {"O","Y","D","D","T","P","N","R","A","H","E","L","C","S","B","P","S","U","B","G","U","P","Y","H","R","R","X","R","E","F","H","D","H","T","K","X","K","O","Z","F","W","Y","H","Y","T","C","H","M","V","P","R","T","A","K","N","E","S","I","B","T","M","V","Y","Q","E","U","O","E","F","A","K","J","C","W","I","K","I","U","K","T","P","O","F","E","G","Z","T","X","O","Z","T","H","K","B","M","G","D","P","P","P","G","U","E","S","C","J","C","B","Q","F","T","R","I","P","N","I","E","W","P","K","H","K","G","B","B","L","Y","J","P","J","E","O","N","Q","V","N","B","S","H","R","N","Z","R","G","A","E","W","P","L","L","Z","R","G","I","E","T","U","N","R","L","I","K","T","J","K","J","F","C","I","T","M","R","D","T","R","E","G","L","J","G","I","K","H","L","C","V","P","P","D","S","Q","E","W","O","C","R","L","V","L","P","T","A","T","N","O","R","M","W","K","O","D","O","U","O","V","F","M","H","V","V","S","I","X","Z","L","O","T","Z","L","B","R","G","F","Q","P","A","Y","P","D","L","B","K","S","N","C","H","O","P","Y","K","H","C","R","R","I","C","S","B","J","X","R","F","I","Y","R","H","B","Z","I","P","C","K","I","N","O","E","C","C","U","C","P","I","J","R","E","Y","E","Z","U","R","R","M","F","S","M","R","N","J","I","B","T","Q","O","C","V","R","O","T","X","H","C","R","W","S","A","V","T","N","U","I","O","W","X","C","O","R","X","Q","A","S","A","S","S","E","M","B","L","Y","O","Z","F","P","L","S","C","I","T","L","U","M","O","N","I","T","O","R","J","W","I","N","L","L","L","E","L","J","R","R","E","M","M","O","B","D","X","I","J","D","S","R","L","C","H","S","H","Y","U","L","P","M","O","U","S","E","C","B","I","I","U","I"};
        assHole.setBoard(stringArray3);
        
        System.out.println(assHole.isOnBoard("CHURCHGOER"));


    }
}
