package info2.blob.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import info2.blob.Blob;
import info2.blob.Maze;

/**
 * This class starts the maze and the evolution of the blob.
 * @author Sebastian Otte
 */
public class BlobGUI {
    
    /**
     * Shows the main window that contains the maze panel. The frame is redrawn
     * roughly 15 times per second. Additionally a stepDelay can be given. This
     * value effectively blocks the setCellValue-Method of the class Maze (via
     * an internal listener notification) such that the back tracking process
     * is visible.
     * @param maze Instance of Maze.
     * @param stepDelay Delay of blocking listener notification.
     */
    public static void showGUI(final Maze maze, int stepDelay) {
        final MazePanel panel = new MazePanel(maze, stepDelay);
        maze.registerListener(panel);
        final JFrame frame = new JFrame("Praktische Informatik 2 - The Blob");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        final Timer timer = new Timer(1000 / 15, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                panel.repaint();
            }
        });
        timer.start();
        
    }
    
    public static void main(String[] args) {
        //
        // Size of the maze.
        //
        final int cols = 20;
        final int rows = 15;
        //
        // Starting position of the blob (and the maze generator).
        //
        final int startX = 10;
        final int startY = 13;
        //
        // Generate the maze and show the GUI window. 
        //
        final Maze maze = Maze.generateRandomMaze(new Random(1234), cols, rows, startX, startY, 400);
        showGUI(maze, 100);
        //
        // Create the blob and start infesting the maze.
        //
        final Blob blob = new Blob();
        if (blob.infest(maze, startX, startY)) {
            System.out.println("Finally some food! YUMMY!");
        } else {
            System.out.println("Nothing to eat in this maze. Guess I'll die ...");
        }
    }
}

