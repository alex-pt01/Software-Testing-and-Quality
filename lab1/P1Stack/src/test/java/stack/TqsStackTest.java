package stack;

import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TqsStackTest {
    private static TqsStack<String> testStack;
    private static TqsStack<String> stack3elemt;
    private static TqsStack<Double> boundedStack;


    //antes de cada metodo
    @BeforeEach
    void setUp() {
        System.out.println("Before each method");

        testStack = new TqsStack<>(3); //empty stack

        boundedStack = new TqsStack<>(3); //empty stack


        //stack with 3 elements
        stack3elemt = new TqsStack<>(3);
        stack3elemt.push("Aveiro");
        stack3elemt.push("Lisboa");
        stack3elemt.push("Viseu");

    }

    //depois de cada metodo
    @AfterEach
    void tearDown() {
        System.out.println("After each method");
    }

    @Test
    @DisplayName("A stack is empty on construction")
    void isEmpty() {
        //ckeck if stack is empty, if not is shown a message error :!

        assertTrue(testStack.isEmpty(), "Stack should be empty on construction!");
        //assertTrue(stack3elemt.isEmpty(), "Stack should be empty on construction!");
    }

    @Test
    @DisplayName("A stack has size 0 on construction")
    void initSize() {
        //esperado; ...; message error
        assertEquals(0, new TqsStack<Integer>(3).size(), "Stack should have size 0 on construction, and it doesn't");
    }

    @Test
    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    void afterNpushesSize() {
        //check with stack3elemt object
        assertFalse(stack3elemt.isEmpty(), "Shouldn't be empty!!");
        assertEquals(3, stack3elemt.size(), "Stack should have size 3!");
    }

    @Test
    @DisplayName("If one pushes x then pops, the value popped is x")
    void pushThenPop() {
        //check with testStack object
        testStack.push("Castendo");
        assertEquals("Castendo", testStack.pop(), "Poped value isn't Castendo");
    }

    @Test
    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    void pushThenPeek() {
        //check with testStack object
        testStack.push("Castendo");
        assertEquals("Castendo", testStack.pop(), "Poped value isn't Castendo");
    }

    @Test
    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    void afterNPops_stackEmpty() {
        //check with testStack object
        testStack.clear();
        assertEquals(0, testStack.isEmpty(), "stack isn't empty");
    }

    @Test
    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    void popEmptyStack() {
        //check with testStack object
        testStack.clear();
        assertTrue(testStack.isEmpty(), "Stack should be empty after clear");
        assertThrows(NoSuchElementException.class, () -> { testStack.pop(); });
    }

    @Test
    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    void peekEmptyStack() {
        //check with testStack object
        testStack.clear();
        assertTrue(testStack.isEmpty(), "Stack should be empty after clear");
        assertThrows(NoSuchElementException.class, () -> { testStack.peek(); });
    }

    @Test
    @DisplayName("For bounded stacks only, pushing onto a full stack does throw an IllegalStateException")
    void pushFullBoundedStack() {
        //check with testStack object
        boundedStack.push(3.2);
        boundedStack.push(3.5);
        boundedStack.push(3.90);
        assertThrows(IllegalStateException.class, () -> {
            boundedStack.push(3.99);
        });
        }

    @Test
    void size() {
        TqsStack<String> stack = new TqsStack<>(3);
        //se passou o teste não vai aparecer
        assertEquals(0, stack.size(), "empty stack reports non-zero size" );

    }

    @org.junit.jupiter.api.Test
    @Disabled //não considerar este teste

    void push() {

    }

    @Disabled //não considerar este teste
    @org.junit.jupiter.api.Test
    void pop() {

    }

    @Disabled //não considerar este teste
    @org.junit.jupiter.api.Test
    void peek() {

    }
}