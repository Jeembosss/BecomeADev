package com.popov.portalOne.controller;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataController {

    public static List<Integer> readDataFromFile(File dataFile) {
        ArrayList<Integer> data = new ArrayList<>();

        try {
            Scanner scan = new Scanner(dataFile);
            while (scan.hasNext()) {
                data.add(scan.nextInt());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
