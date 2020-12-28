package prog1;

/**
 * Created by harshams on 18/11/2020
 */

import prog1.utils.LinkedList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class SongPlaylist {

    private static InputStream inputStream;

    SongPlaylist(InputStream inputStream) {

        this.inputStream = inputStream;
        this.ll = new LinkedList();
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private LinkedList ll;

    public void addSong(String t, String dur) {
        if (ll == null)
            ll = new LinkedList();
        ll.addNode(t, dur);
    }

    public int findSong(String t) {
        return ll.findNode(t);
    }

    public void deleteAtPos(int pos) {
        int index = ll.deleteNodeAtPos(pos);
        if (index == -1)
            System.out.println("Invalid index");
        else
            System.out.println("Track at position " + pos + " deleted");
    }

    public void sortList() {
        ll.sort();
    }

    public void traverse(int pos) {
        ll.traverseList(pos);
    }

    public void display() {
        ll.display();
    }

    public void run() {
        Scanner sc = new Scanner(inputStream);
        String choice = "";
        do {
            System.out.println("Playlist Operations:\n");
            System.out.println("1. Add a song to the playlist");
            System.out.println("2. Delete a song from the playlist");
            System.out.println("3. Find a song by name");
            System.out.println("4. Next track / Previous track");
            System.out.println("5. Sort playlist by song title");
            System.out.println("6. Display playlist");
            System.out.println("7. Exit");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter Song Title:");
                    String name = sc.nextLine();
                    System.out.println("Enter Song Duration:");
                    String duration = sc.nextLine();
                    addSong(name, duration);
                    break;
                case "2":
                    System.out.println("Enter the track number to be deleted:");
                    int index = Integer.parseInt(sc.nextLine());
                    deleteAtPos(index);
                    break;
                case "3":
                    System.out.println("Enter the track name:");
                    name = sc.nextLine();
                    findSong(name);
                    break;
                case "4":
                    System.out.println("Enter the track number to start from:");
                    int num = Integer.parseInt(sc.nextLine());
                    traverse(num);
                    break;
                case "5":
                    sortList();
                    break;
                case "6":
                    display();
                    break;
                case "7":
                    System.out.println("Exiting!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (!choice.equals("7"));
    }


    public static void main(String[] args) {
        if (inputStream == null)
            inputStream = System.in;
        SongPlaylist playList = new SongPlaylist(inputStream);
        playList.run();
    }

}

