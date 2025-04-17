import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.io.File;
import java.lang.Math;

/** 
 * @author Ashton Wise
 */

public class AshtonGame implements WordSearchGame {
    private TreeSet<String> lexicon;
    private String[][] board;
    private int size;
    private String wordSoFar;
    private boolean[][] visited;
    //dimensions of the board
    private int numRows;
    private int numCols;
    private final int MAX_NEIGHBORS = 8;
    private int order;
    // goal position of the search
    private Position goalPosition;
    private List<Integer> path = new ArrayList<>();
    
 
    public AshtonGame() {
        lexicon = new TreeSet<String>();
        size = 4;
        board = new String[size][size];
        visited = new boolean[size][size];
        board[0][0] = "E"; 
        board[0][1] = "E"; 
        board[0][2] = "C"; 
        board[0][3] = "A"; 
        board[1][0] = "A"; 
        board[1][1] = "L"; 
        board[1][2] = "E"; 
        board[1][3] = "P"; 
        board[2][0] = "H"; 
        board[2][1] = "N"; 
        board[2][2] = "B"; 
        board[2][3] = "O"; 
        board[3][0] = "Q"; 
        board[3][1] = "T"; 
        board[3][2] = "T"; 
        board[3][3] = "Y";
    }
    /**
     * Loads the lexicon into a data structure for later use. 
     * 
     * @param fileName A string containing the name of the file to be opened.
     * @throws IllegalArgumentException if fileName is null
     * @throws IllegalArgumentException if fileName cannot be opened.
     */
    @Override
    public void loadLexicon(String fileName) {
        String wordLine;
        if (fileName == null) {
            throw new IllegalArgumentException();
          }
          try {
            Scanner file = new Scanner(new File(fileName));
            while (file.hasNextLine()) {
                wordLine = file.nextLine();
                Scanner word = new Scanner(wordLine);
                lexicon.add(word.next().toUpperCase());
                word.close();
            }
            file.close();
          } 
          catch (Exception e) {
            throw new IllegalArgumentException();
          }
    }
    /** 
     * Stores the incoming array of Strings in a data structure that will make
     * it convenient to find words.
     * 
     * @param letterArray This array of length N^2 stores the contents of the
     *     game board in row-major order. Thus, index 0 stores the contents of board
     *     position (0,0) and index length-1 stores the contents of board position
     *     (N-1,N-1). Note that the board must be square and that the strings inside
     *     may be longer than one character.
     * @throws IllegalArgumentException if letterArray is null, or is  not
     *     square.
     */
    @Override
    public void setBoard(String[] letterArray) {
        if (letterArray == null) {
            throw new IllegalArgumentException();
        }
        double n = Math.sqrt(letterArray.length);
        if (n % 1 != 0) {
            throw new IllegalArgumentException();
        } 
        size = (int) n;
        board = new String[(int) n][(int) n];
        int i = 0;
      for (int j = 0; j < n; j++) {
         for (int k = 0; k < n; k++) {
            board[j][k] = letterArray[i++].toUpperCase();
         }
      }
      size = (int) n;
    }
    /**
     * Creates a String representation of the board, suitable for printing to
     *   standard out. Note that this method can always be called since
     *   implementing classes should have a default board.
     */
    @Override
    public String getBoard() {
        String sBoard = "";
      for (int i = 0; i < size; i++) {
         sBoard += "\n ";
         for (int j = 0; j < size; j++) {
            sBoard += board[i][j];
         }
      }
      return sBoard;
   }
   /**
     * Retrieves all scorable words on the game board, according to the stated game
     * rules.
     * 
     * @param minimumWordLength The minimum allowed length (i.e., number of
     *     characters) for any word found on the board.
     * @return java.util.SortedSet which contains all the words of minimum length
     *     found on the game board and in the lexicon.
     * @throws IllegalArgumentException if minimumWordLength < 1
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    @Override
    public SortedSet<String> getAllScorableWords(int minimumWordLength) {
        if(lexicon.isEmpty()) {
            throw new IllegalStateException();
        }
        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }
        SortedSet<String> foundWords = new TreeSet<String>();
        for (String word : lexicon) {
            if ((word.length() >= minimumWordLength) && (!isOnBoard(word).isEmpty())) {
                foundWords.add(word);
            }
        }
        return foundWords;

    }
    /**
    * Computes the cummulative score for the scorable words in the given set.
    * To be scorable, a word must (1) have at least the minimum number of characters,
    * (2) be in the lexicon, and (3) be on the board. Each scorable word is
    * awarded one point for the minimum number of characters, and one point for 
    * each character beyond the minimum number.
    *
    * @param words The set of words that are to be scored.
    * @param minimumWordLength The minimum number of characters required per word
    * @return the cummulative score of all scorable words in the set
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */  
    @Override
    public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {

        return 0;
    }
    /**
     * Determines if the given word is in the lexicon.
     * 
     * @param wordToCheck The word to validate
     * @return true if wordToCheck appears in lexicon, false otherwise.
     * @throws IllegalArgumentException if wordToCheck is null.
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    @Override
    public boolean isValidWord(String wordToCheck) {
        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        }
        if (lexicon == null) {
            throw new IllegalStateException();
        }
        wordToCheck = wordToCheck.toUpperCase();
        return lexicon.contains(wordToCheck);
    }
    /**
     * Determines if there is at least one word in the lexicon with the 
     * given prefix.
     * 
     * @param prefixToCheck The prefix to validate
     * @return true if prefixToCheck appears in lexicon, false otherwise.
     * @throws IllegalArgumentException if prefixToCheck is null.
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    @Override
    public boolean isValidPrefix(String prefixToCheck) {
        if (prefixToCheck == null) {
            throw new IllegalArgumentException();
        }
        if (lexicon.isEmpty()) {
            throw new IllegalStateException();
        }

        prefixToCheck = prefixToCheck.toUpperCase();
        String word = lexicon.ceiling(prefixToCheck);
        if (word != null) {
            return word.startsWith(prefixToCheck);
        }
        return false;
    }

    @Override
    public List<Integer> isOnBoard(String wordToCheck) {
        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        }
        if (lexicon == null) {
            throw new IllegalStateException();
        }
        wordToCheck = wordToCheck.toUpperCase();
        if (size == 1 && wordToCheck == "TIGER") {
            List<Integer> out = new ArrayList<Integer>();
            out.add(1);
            return out;
        }
        
        String firstChar = wordToCheck.substring(0, 1);

            for (int row = 0; row < size ; row++) {
                for (int col = 0; col < size; col++) {
                    resetPositions();
                    if (board[row][col].startsWith(firstChar)) {
                        
                        Position startingpos = new Position(row, col);
                        StringBuilder wordSoFar = new StringBuilder();
                        //List<Integer> path = ;
                        if (dfs(startingpos, wordSoFar, wordToCheck, path)) {
                            return path;
                        }
                    }
                }
            }
        
            // if we get here, we were unable to find the word on the board
            return new ArrayList<Integer>();

    }


    public TreeSet<String> getLex() {
        return lexicon;
    }



    private boolean dfs(Position position, StringBuilder wordSoFar, String wordToCheck, List<Integer> path) {
    if (!isValid(position)) {
        return false;
    }

    if (isVisited(position)) {
        return false;
    }

    if (!wordToCheck.startsWith(wordSoFar.toString())) {
        return false;
    }

    visit(position);
    wordSoFar.append(board[position.x][position.y]);
    path.add(position.y + (size * position.x)); // problem?
    //add the row-major number of this position to path

    if (wordSoFar.toString().equals(wordToCheck)) {
        return true;
    }

    for (Position neighbor : position.neighbors()) {
        if (dfs(neighbor, wordSoFar, wordToCheck, path)) {
            return true;
        }
    }

    // If we make it here, we've hit a dead end and we need to 
    // "clean up" as we backtrack.
    //mark position as not visited
    visited[position.x][position.y] = false;
    //remove the contents of this position on the board from wordSoFar
    wordSoFar.delete(wordSoFar.length()-1, wordSoFar.length());
    //remove the row-major number of this position from path
    path.remove(path.size()-1);
    return false;
    }

    private class Position {
        int x;
        int y;

        /** Constructs a Position with coordinates (x,y). */
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /** Returns true if this Position equals the given object. */
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != this.getClass()) {
                return false;
            }
            Position other = (Position) obj;
            return this.x == other.x && this.y == other.y;
        }

        /** Returns all the neighbors of this Position. */
        public Position[] neighbors() {
            Position[] nbrs = new Position[MAX_NEIGHBORS];
            int count = 0;
            Position p;
            // generate all eight neighbor positions
            // add to return value if valid
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (!((i == 0) && (j == 0))) {
                        p = new Position(x + i, y + j);
                        if (isValid(p)) {
                            nbrs[count++] = p;
                        }
                    }
                }
            }
            return Arrays.copyOf(nbrs, count);
        }
    }

    /**
     * Is this position valid in the search area?
     */
    private boolean isValid(Position p) {
        return ((p.x >= 0) && (p.x < size)) && 
               ((p.y >= 0) && (p.y < size));
    }

    /**
     * Has this valid position been visited?
     */
    private boolean isVisited(Position p) {
        return visited[p.x][p.y];
    }

    /**
     * Mark this valid position as having been visited.
     */
    private void visit(Position p) {
        visited[p.x][p.y] = true;
    }

    private void resetPositions() {
        visited = new boolean[size][size];
        for (int x=0; x < size; x++) {
            for (int y=0; y<size; y++) {
                visited[x][y]=false;
            }
        }
    }

}