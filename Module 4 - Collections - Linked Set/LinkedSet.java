import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author Ashton Wise (adw0082@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
    if(element == null) {
        throw new NullPointerException();
     }
         
     if(this.contains(element)) {
        return false;
     }
     Node n = new Node(element);
     if (isEmpty()) {
        front = n;
        rear = n;
     } else if (element.compareTo(rear.element) > 0) {
        n.prev = rear;
        rear.next = n;
        rear = n;
     } else if (element.compareTo(front.element) < 0) {
        n.next = front;
        front.prev = n;
        front = n;
     } else {
        Node p = front;
        for (int i = 0; i < size(); i++) {
           if (p.element.compareTo(element) > 0) {
              n.prev = p.prev;
              n.next = p;
              p.prev.next = n;
              p.prev = n;
              break;
           }
           p = p.next;
        }
     }
     size++;
     return true;
        
   }

   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
      if (!this.contains(element)) {
         return false;
      }
      if (element == null) {
         throw new NullPointerException();
      }
      if (size == 1) {
         front = null;
         rear = null;
         size --;
         return true;
      }
      Node n = this.Locator(element);
      if (element.compareTo(rear.element) == 0) {
         rear.prev.next = null;
         rear = rear.prev; //change to n.prev if doesn't work
      } else if (element.compareTo(front.element) == 0) {
         front.next.prev = null;
         front = front.next; //change to n.next if doesn't work
      } else {
         n.next.prev = n.prev;
         n.prev.next = n.next;
      }
      if (!this.contains(element)) {
         size--;
         return true;
      }
      return false;
   }


   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
    if (isEmpty()) {
        return false;
     }
     if (front == null) {
        return false;
     }
     Node n = front;
     while (n != null) {
        if (n.element.equals(element)) {
           return true;
        }
        n = n.next;
     }
     return false;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
    if(size == s.size() && complement(s).size() == 0) {
        return true;
     }
     return false;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
    if(size == s.size() && complement(s).size() == 0) {
        return true;
     }
     return false;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s){
    if(s == null) {
        throw new NullPointerException();
     }
      LinkedSet<T> j = new LinkedSet<T>();
      for (T item : s) {
         j.add(item);
      }
      for (T item : this) {
         j.add(item);
      }
      return j;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s){
    if(s == null) {
        throw new NullPointerException();
     }
      LinkedSet<T> j = new LinkedSet<T>();
      for (T item : s) {
         j.add(item);
      }
      for (T item : this) {
         j.add(item);
      }
      return j;
   }


   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
    if(s == null) {
        throw new NullPointerException();
     }
      LinkedSet<T> j = new LinkedSet<T>();
      for (T item : s) {
         if (this.contains(item)) {
            j.add(item);
         }
      }
      return j;
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) {
    if(s == null) {
        throw new NullPointerException();
     }
      LinkedSet<T> j = new LinkedSet<T>();
      for (T item : s) {
         if (this.contains(item)) {
            j.add(item);
         }
      }
      return j;
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      LinkedSet<T> j = new LinkedSet<T>();
      for (T item : this) {
         if (!s.contains(item)) {
            j.add(item);
         }
      }
      return j;
   }


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
      LinkedSet<T> j = new LinkedSet<T>();
      for (T item : this) {
         if (!s.contains(item)) {
            j.add(item);
         }
      }
      return j;
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() {
      return new upIterator();
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      return new downIterator();
   }


   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
    return new myPowerIterator();
   }



   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////

   // Feel free to add as many private methods as you need.
   
   private Node Locator(T element) {
      Node p = front;
      for (int i = 0; i < size(); i++) {
         if (p.element.compareTo(element) == 0) {
            return p;
         }
         p = p.next;
      }
      return null;
   }
   ////////////////////
   // Nested classes //
   ////////////////////
   
   private class upIterator implements Iterator<T> {
      Node p = front;
      @Override
      public boolean hasNext() {
         if (p != null) {
            return true;
         }
         return false;
      }
      @Override
      public T next() {
         T currentElement = p.element;
         p = p.next;
         return currentElement;
      }
   }
   private class downIterator implements Iterator<T> {
      Node p = rear;
      @Override
      public boolean hasNext() {
         if (p != null) {
            return true;
         }
         return false;
      }
      @Override
      public T next() {
         T currentElement = p.element;
         p = p.prev;
         return currentElement;
      }
   }

   private class myPowerIterator implements Iterator<Set<T>> {
      private int bSet;
      private Node p = front;
      
      public myPowerIterator() {
         bSet = 0;
      }
      
      @Override
      public boolean hasNext() {
         return bSet < Math.pow(2, size());
      }
      @Override
      public Set<T> next() {
         LinkedSet<T> returnSet = new LinkedSet<>();
         
         for (int i = 0; i < size(); i++) {
            if ((bSet & 1<<i) != 0) {
               returnSet.add(p.element);
            }
            p = p.next;
         }
         bSet++;
         p = front;
         return returnSet;
      }
   }
   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }

}