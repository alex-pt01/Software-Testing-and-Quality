/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ico0
 */
public class SetOfNaturalsTest {
    private SetOfNaturals setA;
    private SetOfNaturals setB;
    private SetOfNaturals setC;

    @BeforeEach
    public void setUp() {
        setA = new SetOfNaturals();
        setB = SetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});

        setC = new SetOfNaturals();
        for (int i = 5; i < 50; i++) {
            setC.add(i * 10);
        }
        SetOfNaturals.fromArray(new int[]{30, 40, 50, 60, 10, 20});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        setB.add(11);
        assertTrue(setB.contains(11), "add: added element not found in set.");
        assertEquals(7, setB.size(), "add: elements count not as expected.");

        assertThrows(IllegalArgumentException.class, () -> {
            setA.add(-1);
        }, "add: added non-natural element (-1) and exception was not thrown");

        assertThrows(IllegalArgumentException.class, () -> {
            setA.add(99);
        }, "add: added duplicate element (99) to set and exception was not thrown");
    }

    @Test
    public void testAddBadArray() {
        int[] elems = new int[]{10, 20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }

    @Test
    public void testAddDuplicateArray() {
        SetOfNaturals setD = SetOfNaturals.fromArray(new int[]{1, 10, 20, 20, 30});
        assertEquals(SetOfNaturals.fromArray(new int[]{1, 10, 20, 30}).toString(), setD.toString());
    }


    @Test
    public void testIntersectForNoIntersection() {
        assertFalse(setA.intersects(setB), "no intersection but was reported as existing");
    }

    @Test
    public void testIntersectForSameSet() {
        assertTrue(setB.intersects(setB), "existing intersection but was reported as non-existing");
    }

    @Test
    public void testIntersectForDifferentSets() {
        assertTrue(setB.intersects(setC), "existing intersection but was reported as non-existing");
    }

    @Test
    public void testContainsOperation() {
        assertTrue(setB.contains(10), "set B, actually contains number 10 and function provided a false negative");

        assertFalse(setB.contains(1000), "set B, actually contains number 1000 and function provided a false positive");
    }
}