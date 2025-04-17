import java.util.Comparator;


/**
 * Autocomplete term representing a (query, weight) pair.
 * 
 */
public class Term implements Comparable<Term> {
    String query;
    long weight;
    /**
     * Initialize a term with the given query and weight.
     * This method throws a NullPointerException if query is null,
     * and an IllegalArgumentException if weight is negative.
     */
    public Term(String query, long weight) {
        if (query == null) {
            throw new NullPointerException("Query should not be null");
            }
            if (weight < 0) {
            throw new IllegalArgumentException("Weight should not be a negative");
            }
            this.query = query;
            this.weight = weight;
            
    }

    /**
     * Compares the two terms in descending order of weight.
     */
    public static Comparator<Term> byDescendingWeightOrder() {
        return new ComparatorByDecendingWeight();
    }

    /**
     * Compares the two terms in ascending lexicographic order of query,
     * but using only the first length characters of query. This method
     * throws an IllegalArgumentException if length is less than or equal
     * to zero.
     */
    public static Comparator<Term> byPrefixOrder(int length) {
        if (length <= 0)
        throw new IllegalArgumentException("Length should be a natural number");
        return new ComparatorByPrefixOrder(length);
    }

    /**
     * Compares this term with the other term in ascending lexicographic order
     * of query.
     */
    @Override
    public int compareTo(Term other) {
        return query.compareToIgnoreCase(other.query);    
    }

    /**
     * Returns a string representation of this term in the following format:
     * query followed by a tab followed by weight
     */
    @Override
    public String toString(){
        return String.format("%s\t%d", query, weight);    
    }

    private static class ComparatorByPrefixOrder implements Comparator<Term> {

        private int length;

        private ComparatorByPrefixOrder(int length) {
            this.length = length;
        }

        @Override
        public int compare(Term a, Term b) {
            String prefixA;
            String prefixB;

            if (a.query.length() < length) {prefixA = a.query;}
            else prefixA = a.query.substring(0, length);

            if (a.query.length() < length) {prefixB = a.query;}
            else prefixB = a.query.substring(0, length);

            return prefixA.compareTo(prefixB);
        }
    }

    private static class ComparatorByDecendingWeight implements Comparator<Term> {
        

        @Override
        public int compare(Term W1, Term W2) {
            if (W1.weight > W2.weight) {
                return -1;
            }
            else if (W1.weight < W2.weight) {
                return 1;
            }
            else return 0;
        }

    }

}

