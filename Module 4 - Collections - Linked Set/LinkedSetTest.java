import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class LinkedSetTest {
    @Test
    public void addTest() {
        LinkedSet j = new LinkedSet<>();
        j.add(1);
        assertEquals(true, j.contains(1));
    }
    @Test
    public void addTest2() {
        LinkedSet j = new LinkedSet<>();
        j.add(4040);

        assertEquals(false, j.add(4040));
    }

    @Test
    public void removeTest() {
        LinkedSet j = new LinkedSet<>();
        j.add(7);
        j.add(19);
        j.remove(7);
        
        assertEquals(true, !j.contains(7));
    }

    @Test
    public void removeTest2() {
        LinkedSet j = new LinkedSet<>();
        j.add(7);
        
        assertEquals(true, j.remove(7));
    }












}