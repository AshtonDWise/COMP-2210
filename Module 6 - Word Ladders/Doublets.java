import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Your Name (you@auburn.edu)
 */
public class Doublets implements WordLadderGame {
    private HashSet<String> lexicon;
    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
    /////////////////////////////////////////////////////////////////////////////
    // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
    // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
    // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
    // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
    // table with chaining).
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
    public Doublets(InputStream in) {
        try {
            lexicon = new HashSet<String>();
            Scanner s = new Scanner(new BufferedReader(new InputStreamReader(in)));
            while (s.hasNext()) {
                String str = s.next();
                lexicon.add(str.toLowerCase());
                s.nextLine();
            }
            in.close();
        }
        catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }


    //////////////////////////////////////////////////////////////
    // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
    //////////////////////////////////////////////////////////////

    
    @Override
    public int getWordCount() {
        return lexicon.size();
    }

    @Override
    public boolean isWord(String str) {
        if(lexicon.contains(str.toLowerCase())) {
            return true;
        }
        return false;
    }

    @Override
    public int getHammingDistance(String str1, String str2) {
        int hamDist = 0;
        if (str1.length() != str2.length()) {
            return -1;
        }
        else{
            for (int i = 0; i < str1.length(); i++) {
                if(str1.charAt(i) != str2.charAt(i)) {
                    hamDist ++;
                }
            }
        }
        return hamDist;
    }

    @Override
    public List<String> getNeighbors(String word) {
        ArrayList<String> neighbors = new ArrayList<String>();
        for (String lexWord : lexicon) {
            if(getHammingDistance(lexWord, word) == 1) {
                neighbors.add(lexWord);
            }
        }
        return neighbors;
    }

    @Override
    public boolean isWordLadder(List<String> sequence) {
        if(sequence.isEmpty()) {
            return false;
        }
        for (int i = 0; i < sequence.size() - 1; i++) {
            String s1 = sequence.get(i);
            String s2 = sequence.get(i+1);
        
            if (!isWord(s1)){
                return false;
            }
            if(getHammingDistance(s1, s2) != 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> getMinLadder(String start, String end) {
        if(start.equals(end)) {
           List<String> o = new ArrayList<String>();
           o.add(start);
           return o;
        }

        start = start.toLowerCase();
        end = end.toLowerCase();

        return BFS(start, end);
    }

    private List<String> BFS(String startW, String endW) {
        Deque<Node> q = new ArrayDeque<Node>();
        HashSet<Node> visited = new HashSet<Node>();
        List<String> path = new ArrayList<String>();
        Node start = new Node(startW, null);
        q.add(start);
        
        while(!q.isEmpty()){
            Node current = q.removeFirst();
            for (String neighbor : getNeighbors(current.word)) {
                Node n = new Node(neighbor, current);
                if(neighbor.equals(endW)){
                    while(n != null){
                    path.add(0, n.word);
                    n = n.previous;
                    }
                    return path;
                }
                if(!visited.contains(n)){
                    visited.add(n);
                    q.add(n);
                }
            }
        }
        return path;
    }




    private class Node {
        Node previous;
        String word;

        public Node(String wordIn, Node prevNode) {
            word = wordIn;
            previous = prevNode;
        }
        
    }


}
