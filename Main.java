package main;

import effects.*;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        Grayscale grayscale = new Grayscale(new File("src/resources/input.jpg"));
        boolean isReadGray = grayscale.readImage();
        grayscale.applyEffects();
        boolean isWriteGray = grayscale.writeImage("grayscaleOutput.jpg");

        if (isWriteGray)
            System.out.println("Grayscale Done.");
        else
            System.out.println("Problem in Grayscale!");


        Average average = new Average(new File("src/resources/input.jpg"));
        boolean isReadAverage = average.readImage();
        average.applyEffects();
        boolean isWriteAverage = average.writeImage("averageOutput.jpg");

        if (isWriteAverage)
            System.out.println("Average Done.");
        else
            System.out.println("Problem in Average!");

        Sepia sepia = new Sepia(new File("src/resources/input.jpg"));
        boolean isReadSepia = sepia.readImage();
        sepia.applyEffects();
        boolean isWriteSepia = sepia.writeImage("sepiaOutput.jpg");

        if (isWriteSepia)
            System.out.println("Sepia Done.");
        else
            System.out.println("Problem in Sepia!");

        Negative negative = new Negative(new File("src/resources/input.jpg"));
        boolean isReadNegative = negative.readImage();
        negative.applyEffects();
        boolean isWriteNegative = negative.writeImage("negativeOutput.jpg");

        if (isWriteNegative)
            System.out.println("Negative Done.");
        else
            System.out.println("Problem in Negative!");

        BackgroundChanger backgroundChanger = new BackgroundChanger(new File("src/resources/image.jpg"), new File("src/resources/background.jpg"));
        boolean isBackgroundChangerRead = backgroundChanger.readImages();
        backgroundChanger.applyEffects();
        boolean isBackgroundChangerWrite = backgroundChanger.writeImage("changedBackground.jpg");

        if(isBackgroundChangerWrite)
            System.out.println("BackgroundChanger Done.");
        else
            System.out.println("Problem in BackgroundChanger!");

        System.out.println();
        System.out.println("Program finished.");

    }
}
