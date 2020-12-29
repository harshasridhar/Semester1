package prog2;

import org.junit.jupiter.api.*;
import prog2.utils.Graph;
import prog2.utils.Queue;
import prog2.utils.Stack;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by harshams on 28/12/2020
 */
public class Prog2Test {

    private static final PrintStream standardOut = System.out;
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    public static void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterAll
    public static void tearDown() {
        outputStreamCaptor.reset();
        System.setOut(standardOut);
    }

    @Test
    public void testStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.printElements();
        Assertions.assertEquals("Stack{elements=[1, 2, 3], length=3}", stack.toString());
        Assertions.assertEquals(3, stack.peek().intValue());
        Assertions.assertEquals(3, stack.pop().intValue());
        Assertions.assertEquals(2, stack.pop().intValue());
        Assertions.assertEquals(1, stack.pop().intValue());
        Assertions.assertEquals(null, stack.pop());
    }

    @Test
    public void testQueue() {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        Assertions.assertEquals("Queue{elements=[1, 2, 3], size=3}", queue.toString());
        Assertions.assertEquals(1, queue.dequeue());
        Assertions.assertEquals(2, queue.dequeue());
        Assertions.assertEquals(3, queue.dequeue());
        Assertions.assertEquals(null, queue.dequeue());
    }

    @Test
    public void testGraph() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 9);
        graph.addEdge("A", "D", 7);
        graph.addEdge("B", "D", 6);
        graph.addEdge("C", "D", 8);
        String expected = "0 5 9 7\n" +
                "5 0 0 6\n" +
                "9 0 0 8\n" +
                "7 6 8 0\n";
        graph.print();
        Assertions.assertEquals(expected, outputStreamCaptor.toString());
        outputStreamCaptor.reset();
        graph.printEdges();
        expected = "A->B\n" +
                "A->C\n" +
                "A->D\n" +
                "B->A\n" +
                "B->D\n" +
                "C->A\n" +
                "C->D\n" +
                "D->A\n" +
                "D->B\n" +
                "D->C\n";
        Assertions.assertEquals(expected, outputStreamCaptor.toString());
        outputStreamCaptor.reset();
        graph.bfs("A");
        expected = "A\n" +
                "B\n" +
                "C\n" +
                "D\n";
        Assertions.assertEquals(expected, outputStreamCaptor.toString());
        outputStreamCaptor.reset();
        graph.printAvailabilityIfPresent("A", "D");
        expected = "Flight price is 7\n";
        Assertions.assertEquals(expected, outputStreamCaptor.toString());
    }


    @Test
    public void testGraph1() {
        /*
                   A
                  / \
                 B   C
                /   / \
               D   E   F
         */
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);
        graph.addEdge("B", "D", 1);
        graph.addEdge("C", "E", 1);
        graph.addEdge("C", "F", 1);
        outputStreamCaptor.reset();
        graph.print();
        String expected = "0 1 1 0 0 0\n" +
                "1 0 0 1 0 0\n" +
                "1 0 0 0 1 1\n" +
                "0 1 0 0 0 0\n" +
                "0 0 1 0 0 0\n" +
                "0 0 1 0 0 0\n";
        Assertions.assertEquals(expected, outputStreamCaptor.toString());
        outputStreamCaptor.reset();
        graph.bfs("A");
        expected = "A\n" +
                "B\n" +
                "C\n" +
                "D\n" +
                "E\n" +
                "F\n";
        Assertions.assertEquals(expected, outputStreamCaptor.toString());
        outputStreamCaptor.reset();
        graph.dfs("A");
        expected = "A\n" +
                "C\n" +
                "F\n" +
                "E\n" +
                "B\n" +
                "D\n";
        Assertions.assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    public void testProg2ViaCommandlineInput() {
        GraphClass graphClass = new GraphClass();
        graphClass.setInputStream(getClass().getClassLoader().getResourceAsStream("prog2/input.txt"));
        graphClass.run();
        InputStream outputStream = getClass().getClassLoader().getResourceAsStream("prog2/output.txt");
        Scanner sc = new Scanner(outputStream);
        String outputStr = "";
        while (sc.hasNext()) {
            outputStr += sc.nextLine() + "\n";
        }
        Assertions.assertEquals(outputStr, outputStreamCaptor.toString());
    }
}
