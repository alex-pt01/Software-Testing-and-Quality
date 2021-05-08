/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package euromillions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author ico0
 */
public class DipTest {

    private Dip instance;


    public DipTest() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2});
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }


    @Test
    public void testConstructorFromBadArrays() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Dip(new int[]{10, 20, 30, 40, 50, 60}, new int[]{1, 2});
        }, "IllegalArgumentException: invalid number of NUMBERS");
        assertThrows(IllegalArgumentException.class, () -> {
            new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2, 3});
        }, "IllegalArgumentException: invalid number of STARS");
    }

    @Test
    public void testFormat() {
        String result = instance.format();
        assertEquals("N[ 10 20 30 40 50] S[12", result, "format as string: formatted string not as expected. ");
    }

}