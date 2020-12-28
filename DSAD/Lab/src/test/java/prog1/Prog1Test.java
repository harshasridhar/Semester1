package prog1;

import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Scanner;

/**
 * Created by harshams on 18/11/2020
 */
public class Prog1Test {

    private static final PrintStream standardOut = System.out;
    private static final InputStream standardInput = System.in;
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    public static void setUp() {
//        System.out.println("Setting up");
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterAll
    public static void tearDown() {
        outputStreamCaptor.reset();
//        System.out.println("Clearing now");
        System.setOut(standardOut);
    }

    private static void populatePlaylist(SongPlaylist songPlaylist) {
        songPlaylist.addSong("Believer", "3:23");
        songPlaylist.addSong("Shotgun", "3:21");
        songPlaylist.addSong("Titanium", "4:07");
        songPlaylist.addSong("Kiki do you love me", "3:39");
        songPlaylist.addSong("Shape of you", "3:52");
    }

    @Test
    @Disabled("As this functionality is not implemented totally")
    public void testViaUserInput() {
        InputStream inputFileStream = getClass().getClassLoader().getResourceAsStream("prog1/prog1Input.txt");
        SongPlaylist playlist = new SongPlaylist(inputFileStream);
        playlist.run();
        InputStream output = getClass().getClassLoader().getResourceAsStream("prog1/prog1Output.txt");
        Scanner sc = new Scanner(output);
        String outputStr = "";
        while (sc.hasNext()) {
            outputStr += sc.nextLine().trim() + "\n";
        }
        Assertions.assertEquals(outputStreamCaptor.toString(), outputStr);
    }

    @Test @Disabled
    public void testAddSong() {
        SongPlaylist songPlaylist = new SongPlaylist(System.in);
        populatePlaylist(songPlaylist);

        songPlaylist.display();
        String expectedOutput = "Playlist currently contains 5 song(s)\n" +
                "1. Believer <3:23>\n" +
                "2. Shotgun <3:21>\n" +
                "3. Titanium <4:07>\n" +
                "4. Kiki do you love me <3:39>\n" +
                "5. Shape of you <3:52>\n";
        Assertions.assertEquals(outputStreamCaptor.toString(), expectedOutput);
    }

    @Test
    public void testDeleteSong() {
        SongPlaylist playlist = new SongPlaylist(null);
        populatePlaylist(playlist);

        playlist.deleteAtPos(2);
        playlist.display();
        String expectedOutput = "Track at position 2 deleted\nPlaylist currently contains 4 song(s)\n" +
                "1. Believer <3:23>\n" +
                "2. Titanium <4:07>\n" +
                "3. Kiki do you love me <3:39>\n" +
                "4. Shape of you <3:52>\n";
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testSortPlaylist() {
        SongPlaylist songPlaylist = new SongPlaylist(System.in);
        populatePlaylist(songPlaylist);
        songPlaylist.sortList();
        songPlaylist.display();
        String expectedOutput = "Playlist currently contains 5 song(s)\n" +
                "1. Believer <3:23>\n" +
                "2. Kiki do you love me <3:39>\n" +
                "3. Shape of you <3:52>\n" +
                "4. Shotgun <3:21>\n" +
                "5. Titanium <4:07>\n";
        Assertions.assertEquals(outputStreamCaptor.toString(), expectedOutput);
    }
}
