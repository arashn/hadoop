/**
 * INF 215 Class Project
 * Apache Hadoop Common component
 * 
 * Story 1
 * As a software developer,
 *   I want to use the IntrusiveCollection class;
 * So that I can save on memory usage during execution.
 */
package org.apache.hadoop.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.hadoop.util.IntrusiveCollection.Element;
import org.junit.Test;

public class TestIntrusiveCollection {
  public class TestElement implements IntrusiveCollection.Element {
    private Map<IntrusiveCollection<? extends Element>, Element> prevMap, nextMap;
    private Map<IntrusiveCollection<? extends Element>, Boolean> isMemberMap;
    
    public TestElement() {
      prevMap = new HashMap<>();
      nextMap = new HashMap<>();
      isMemberMap = new HashMap<>();
    }

    @Override
    public void insertInternal(IntrusiveCollection<? extends Element> list, Element prev, Element next) {
      isMemberMap.put(list, true);
      prevMap.put(list, prev);
      nextMap.put(list, next);
      
    }

    @Override
    public void setPrev(IntrusiveCollection<? extends Element> list, Element prev) {
      prevMap.put(list, prev);
      
    }

    @Override
    public void setNext(IntrusiveCollection<? extends Element> list, Element next) {
      nextMap.put(list, next);
      
    }

    @Override
    public void removeInternal(IntrusiveCollection<? extends Element> list) {
      prevMap.remove(list);
      nextMap.remove(list);
      isMemberMap.remove(list);
      
    }

    @Override
    public Element getPrev(IntrusiveCollection<? extends Element> list) {
      return prevMap.getOrDefault(list, null);
    }

    @Override
    public Element getNext(IntrusiveCollection<? extends Element> list) {
      return nextMap.getOrDefault(list, null);
    }

    @Override
    public boolean isInList(IntrusiveCollection<? extends Element> list) {
      return isMemberMap.getOrDefault(list, false);
    }
  }
  
  /**
   * Scenario S1.1: Adding an element
   * Given  an IntrusiveCollection has been created
   *  and    the IntrusiveCollection is empty
   * When    I insert an element
   * Then    the IntrusiveCollection contains the newly added element.
   */
  @Test
  public void testShouldAddElement() {
    IntrusiveCollection<TestElement> intrusiveCollection = new IntrusiveCollection<>();
    
    TestElement element = new TestElement();
    intrusiveCollection.add(element);
    
    assertFalse("Collection should not be empty", intrusiveCollection.isEmpty());
    assertTrue("Collection should contain added element", intrusiveCollection.contains(element));
  }
  
  /**
   * Scenario S1.2: Removing an element
   * Given  an IntrusiveCollection has been created
   *  and    the InstrusiveCollection contains a single element
   * When    I remove the element
   * Then    the IntrusiveCollection is empty.
   */
  @Test
  public void testShouldRemoveElement() {
    IntrusiveCollection<TestElement> intrusiveCollection = new IntrusiveCollection<>();
    TestElement element = new TestElement();
    intrusiveCollection.add(element);
    
    intrusiveCollection.remove(element);
    
    assertTrue("Collection should be empty", intrusiveCollection.isEmpty());
    assertFalse("Collection should not contain removed element", intrusiveCollection.contains(element));
  }
  
  /**
   * Scenario S1.3: Removing all elements
   * Given  an IntrusiveCollection has been created
   *  and    the IntrusiveCollection contains multiple elements
   * When    I remove all elements
   * Then    the IntrusiveCollection is empty.
   */
  @Test
  public void testShouldRemoveAllElements() {
    IntrusiveCollection<TestElement> intrusiveCollection = new IntrusiveCollection<>();
    intrusiveCollection.add(new TestElement());
    intrusiveCollection.add(new TestElement());
    intrusiveCollection.add(new TestElement());
    
    intrusiveCollection.clear();
    
    assertTrue("Collection should be empty", intrusiveCollection.isEmpty());
  }
  
  /**
   * Scenario S1.4: Iterating through elements
   * Given  an IntrusiveCollection has been created
   *  and    the IntrusiveCollection contains multiple elements
   * When    I iterate through the IntrusiveCollection
   * Then    I get each element in the collection, successively.
   */
  @Test
  public void testIterateShouldReturnAllElements() {
    IntrusiveCollection<TestElement> intrusiveCollection = new IntrusiveCollection<>();
    TestElement elem1 = new TestElement();
    TestElement elem2 = new TestElement();
    TestElement elem3 = new TestElement();
    intrusiveCollection.add(elem1);
    intrusiveCollection.add(elem2);
    intrusiveCollection.add(elem3);
    
    Iterator<TestElement> iterator = intrusiveCollection.iterator();
    
    assertEquals("First element returned is incorrect", elem1, iterator.next());
    assertEquals("Second element returned is incorrect", elem2, iterator.next());
    assertEquals("Third element returned is incorrect", elem3, iterator.next());
    assertFalse("Iterator should not have next element", iterator.hasNext());
  }
}
