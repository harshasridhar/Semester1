package prog1.utils;

/**
 * Created by harshams on 18/11/2020
 */

import java.util.Scanner;

public class LinkedList {
    private Node head;
    private int nodeCount;

    public LinkedList() {
        this.head = null;
        this.nodeCount = 0;
    }

    public void addNode(String name, String duration) {
        Node node = new Node(name, duration);
        if (head == null) {
            head = node;
            head.setPrevious(null);
            head.setNext(null);
            ;
        } else {
            Node tmpNode = head;
            while (tmpNode.getNext() != null)
                tmpNode = tmpNode.getNext();
            tmpNode.setNext(node);
            node.setPrevious(tmpNode);
            node.setNext(null);
        }
        nodeCount++;

    }

    public int deleteNode(String name) {
        int index = 0;
        Node previousNode = head, currentNode = head;
        while (currentNode != null) {
            if (currentNode.getName().equals(name)) {
                deleteNode(previousNode, currentNode);
                nodeCount--;
                return index;
            }
            previousNode = currentNode;
            currentNode = currentNode.getNext();
            index++;
        }
        return -1;
    }

    private void deleteNode(Node previousNode, Node currentNode) {
        previousNode.setNext(currentNode.getNext());
        currentNode.getNext().setPrevious(previousNode);
        currentNode.setNext(null);
        currentNode.setPrevious(null);
        currentNode = null;
    }

    public int deleteNodeAtPos(int index) {
        Node currentNode = head, previousNode = head;
        if (currentNode == null || nodeCount < index)
            return -1;
        int localIndex = 0;
        index -= 1;
        while (currentNode != null) {
            if (localIndex == index) {
                deleteNode(previousNode, currentNode);
                nodeCount--;
                return localIndex;
            }
            previousNode = currentNode;
            currentNode = currentNode.getNext();
            localIndex++;
        }
        return -1;
    }

    public int findNode(String name) {
        Node currentNode = head;
        int index = 0;
        while (currentNode != null) {
            if (currentNode.getName().equals(name))
                return index;
            currentNode = currentNode.getNext();
            index++;
        }
        return -1;
    }

    public void traverseList(int index) {
        Node currentNode = head;
        int localIndex = 0;
        if (index > this.nodeCount) {
            System.out.println("Invalid track number");
        } else {
            while (currentNode != null) {
                if (localIndex + 1 == index)
                    break;
                localIndex++;
                currentNode = currentNode.getNext();
            }
            if (currentNode != null) {
                System.out.println("Current song is " + index + ". " + currentNode.getName());
                System.out.println("Enter n for next, p for previous, e to exit");
                Scanner sc = new Scanner(System.in);
                String choice = sc.nextLine();
                if ("n".equals(choice)) {
                    if (currentNode.getNext() != null)
                        System.out.println("Current song is " + (index + 1) + ". " + currentNode.getNext().getName());
                    else
                        System.out.println("End of playlist, no next song");
                } else if ("p".equals(choice)) {
                    if (currentNode.getPrevious() != null)
                        System.out.println("Previous song is " + (index - 1) + ". " + currentNode.getPrevious().getName());
                    else
                        System.out.println("No previous song");
                } else if ("e".equals(choice)) {
                } else {
                    System.out.println("Invalid choice");
                }
            }
        }

    }

    public void sort() {
        head = mergeSort(head);
    }

    private static Node mergeSort(Node head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        Node midNode = getMidNode(head);
        Node secondHalfHead = midNode.getNext();
        midNode.setNext(null);
        Node firstHalf = mergeSort(head);
        Node secondHalf = mergeSort(secondHalfHead);
        return merge(firstHalf, secondHalf);
    }

    private static Node getMidNode(Node head) {
        if (head == null)
            return head;
        Node fastPtr = head, slowPtr = head;

        while (fastPtr.getNext() != null && fastPtr.getNext().getNext() != null) {
            slowPtr = slowPtr.getNext();
            fastPtr = fastPtr.getNext().getNext();
        }
        return slowPtr;
    }

    private static Node merge(Node first, Node second) {
        Node result = null;
        if (first == null)
            return second;
        if (second == null)
            return first;

        if (first.getName().compareTo(second.getName()) <= 0) {
            result = first;
            first.setNext(merge(first.getNext(), second));
        } else {
            result = second;
            second.setNext(merge(first, second.getNext()));
        }
        return result;
    }

    public void display() {
        System.out.println("Playlist currently contains " + nodeCount + " song(s)");
        int index = 1;
        Node tmpNode = head;
        while (tmpNode != null) {
            System.out.println(index + ". " + tmpNode);
            tmpNode = tmpNode.getNext();
            index++;
        }
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.addNode("ABC", "04:30");
        ll.addNode("BCD", "05:40");
        ll.addNode("CDE", "06:20");
        ll.addNode("DEF", "03:50");
        ll.display();
        int deletedIndex = ll.deleteNode("BCD");
        if (deletedIndex != -1)
            System.out.println("Song deleted with index " + (deletedIndex + 1));
        else
            System.out.println("Song not found");
        ll.display();
        int index = ll.findNode("DEYF");
        if (index != -1)
            System.out.println("Song found at index " + (index + 1));
        else
            System.out.println("Song not found");
        ll.traverseList(3);
    }
}
