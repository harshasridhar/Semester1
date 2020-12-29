package prog2;

import prog2.utils.Graph;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by harshams on 29/12/2020
 */
public class GraphClass {

    private static InputStream inputStream;

    private Graph graph;

    public GraphClass() {
        this.graph = new Graph();
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void addVertex(String vertexName) {
        this.graph.addVertex(vertexName);
    }

    public void removeVertex(String vertexName) {

    }

    public void addEdge(String vertex1, String vertex2, int weight) {
        this.graph.addEdge(vertex1, vertex2, weight);
    }

    public void removeEdge(String vertex1, String vertex2) {
        this.graph.removeEdge(vertex1, vertex2);
    }

    public void displayAdjacencyMatrix() {
        this.graph.print();
    }

    public void displayEdges() {
        this.graph.printEdges();
    }

    public void bfs(String sourceVertex) {
        this.graph.bfs(sourceVertex);
    }

    public void dfs(String sourceVertex) {
        this.graph.dfs(sourceVertex);
    }

    public void checkFlightAvailability(String vertex1, String vertex2) {
        this.graph.printAvailabilityIfPresent(vertex1, vertex2);
    }

    public void run() {
        String menu = "1.AddVertex\n" +
                "2.removeVertex\n" +
                "3.AddEdge\n" +
                "4.removeEdge\n" +
                "5.DisplayAdjacentMatrix\n" +
                "6.DisplayEdges\n" +
                "7.BFS\n" +
                "8.DFS\n" +
                "9.Check Flight availability\n" +
                "0.exit()\n" +
                "Enter your choice";
        String choice = "";
        Scanner scanner = new Scanner(this.inputStream);
        do {
            System.out.println(menu);
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter vertex");
                    String vertex = scanner.nextLine();
                    addVertex(vertex);
                    break;
                case "2":
                    System.out.println("Enter vertex");
                    vertex = scanner.nextLine();
                    removeVertex(vertex);
                    break;
                case "3":
                    System.out.println("Enter vertex1:");
                    vertex = scanner.nextLine();
                    System.out.println("Enter vertex2");
                    String vertex2 = scanner.nextLine();
                    System.out.println("Enter the weight:");
                    int weight = Integer.parseInt(scanner.nextLine());
                    addEdge(vertex, vertex2, weight);
                    break;
                case "4":
                    System.out.println("Enter vertex1:");
                    vertex = scanner.nextLine();
                    System.out.println("Enter vertex2");
                    vertex2 = scanner.nextLine();
                    removeEdge(vertex, vertex2);
                    break;
                case "5":
                    displayAdjacencyMatrix();
                    break;
                case "6":
                    displayEdges();
                    break;
                case "7":
                    System.out.println("Enter source vertex:");
                    vertex = scanner.nextLine();
                    bfs(vertex);
                    break;
                case "8":
                    System.out.println("Enter source vertex:");
                    vertex = scanner.nextLine();
                    dfs(vertex);
                    break;
                case "9":
                    System.out.println("Enter source vertex:");
                    vertex = scanner.nextLine();
                    System.out.println("Enter destination vertex");
                    vertex2 = scanner.nextLine();
                    checkFlightAvailability(vertex, vertex2);
                    break;
                case "0":
                    System.out.println("Exiting");
                    break;
            }
        } while (!choice.equals("0"));
    }

    public static void main(String[] args) {
        GraphClass graphClass = new GraphClass();
        graphClass.setInputStream(System.in);
        graphClass.run();
    }
}
