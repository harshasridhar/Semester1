package prog2.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by harshams on 28/12/2020
 */
public class Graph {

    private List<String> vertices;
    private int[][] edges;

    public Graph() {
        this.vertices = new LinkedList<>(); //to maintain insertion order
        this.edges = new int[][]{};
    }

    public void addVertex(String vertexName) {
        this.vertices.add(vertexName);
        if (this.edges.length == 0)
            this.edges = new int[1][1];
        else
            this.edges = addRows(addCols(this.edges, 1), 1);
    }

    public void addEdge(String vertex1, String vertex2, int weight) {
        int vertex1Index = vertices.indexOf(vertex1), vertex2Index = vertices.indexOf(vertex2);
        if (vertex1Index == -1 || vertex2Index == -1)
            System.out.println("Vertex not found");
        this.edges[vertex1Index][vertex2Index] = weight;
        this.edges[vertex2Index][vertex1Index] = weight;
    }

    public void removeEdge(String vertex1, String vertex2) {
        int vertex1Index = vertices.indexOf(vertex1), vertex2Index = vertices.indexOf(vertex2);
        if (vertex1Index == -1 || vertex2Index == -1)
            System.out.println("Vertex not found");
        this.edges[vertex1Index][vertex2Index] = 0;
        this.edges[vertex2Index][vertex1Index] = 0;
    }

    public void printEdges() {
        int rowCount = this.edges.length, colCount = rowCount == 0 ? 0 : this.edges[0].length;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (this.edges[i][j] != 0)
                    System.out.println(this.vertices.get(i) + "->" + this.vertices.get(j));
            }
        }
    }

    public void bfs(String sourceVertex) {
        int visited[] = new int[this.edges.length], visitedVertexCount = 0;
        int sourceVertexIndex = this.vertices.indexOf(sourceVertex);//assume != -1
        Queue<Integer> queue = new Queue<Integer>();
        queue.enqueue(sourceVertexIndex);
        while (visitedVertexCount <= this.vertices.size() && !queue.isEmpty()) {
            int vertexIndex = queue.dequeue();
            if (visited[vertexIndex] != 1) {
                System.out.println(this.vertices.get(vertexIndex));
                visited[vertexIndex] = 1;
                visitedVertexCount++;
                int[] row = this.edges[vertexIndex];
                for (int i = 0; i < this.edges[0].length; i++) {
                    if (row[i] != 0 && visited[i] != 1)
                        queue.enqueue(i);
                }
            }
        }
    }

    public void dfs(String sourceVertex) {
        List<Integer> visitedIndices = new ArrayList<Integer>();
        int sourceVertexIndex = this.vertices.indexOf(sourceVertex);
        Stack<Integer> stack = new Stack();
        stack.push(sourceVertexIndex);
        while (visitedIndices.size() <= this.vertices.size() && stack.length() != 0) {
            int vertexIndex = stack.pop();
            if (!visitedIndices.contains(vertexIndex)) {
                System.out.println(this.vertices.get(vertexIndex));
                visitedIndices.add(vertexIndex);
                int[] row = this.edges[vertexIndex];
                for (int i = 0; i < this.edges[0].length; i++) {
                    if (row[i] != 0 && !visitedIndices.contains(i))
                        stack.push(i);
                }

            }
        }
    }

    public void printAvailabilityIfPresent(String source, String destination) {
        int sourceVertexIndex = this.vertices.indexOf(source);
        int destVertexIndex = this.vertices.indexOf(destination);
        if (this.edges[sourceVertexIndex][destVertexIndex] != 0) {
            System.out.println("Flight price is " + this.edges[sourceVertexIndex][destVertexIndex]);
        } else {
            System.out.println("Flight not available");
        }
    }

    private static int[][] addRows(final int[][] matrix, final int numRowsToAdd) {
        if (numRowsToAdd == 0)
            return matrix;
        int[][] copy = new int[matrix.length + numRowsToAdd][];
        int columnCount = matrix[0].length;
        int updatedRowCount = matrix.length + numRowsToAdd;
        System.arraycopy(matrix, 0, copy, 0, numRowsToAdd > 0 ? matrix.length : updatedRowCount);
        for (int i = matrix.length; i < updatedRowCount; i++) {
            copy[i] = new int[columnCount];
        }
        return copy;
    }

    private static int[][] addCols(final int[][] matrix, final int numColsToAdd) {
        if (numColsToAdd == 0)
            return matrix;
        int currentColSize = matrix[0].length, newColSize = currentColSize + numColsToAdd;
        int[][] copy = new int[matrix.length][newColSize];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = new int[newColSize];
            System.arraycopy(matrix[i], 0, copy[i], 0, numColsToAdd > 0 ? currentColSize : newColSize);
        }
        return copy;
    }

    public void print() {
        int vertexCount = this.edges.length;
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (j != vertexCount - 1)
                    System.out.print(edges[i][j] + " ");
                else
                    System.out.print(edges[i][j]);
            }
            System.out.println();
        }
    }

}
