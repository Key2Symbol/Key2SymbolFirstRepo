package com.tcs.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;

public class GroupMain {

    public static final String INPUT_FILE = "D:\\IncidentGrouping\\Group_File\\Group.txt";

    private static ArrayList<Group> groups = new ArrayList<Group>();

    public static void main(String[] args) {
        try {
            if (retrieveData()) {

                merge();

                System.out.println("Grouped Incidents With Files......\n");
                printGroupFiles();

                System.out.println("Grouped Incidents......\n");
                printGroups();
            } else {
                System.err.println("Invalid File!");
                System.out.println("File must be started with an incident name. Ex. INC123454");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean retrieveData() throws FileNotFoundException, 
                                                IOException {
        FileReader fr = new FileReader(INPUT_FILE);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();

        if (line != null && line.matches("[iI][Nn][cC][0-9]+")) {
            Group g = null;
            do {
                line = line.trim();
                if (!line.equals("")) {
                    if (line.matches("[iI][Nn][cC][0-9]+")) {
                        g = new Group();
                        g.addIncident(line);
                        groups.add(g);
                    } else {
                        g.addFile(line);
                    }
                }
                line = br.readLine();
            } while (line != null);

            return true;
        } else
            return false;
    }


    public static void printGroups() {
        int i = 1;
        for (Group g: groups) {
            System.out.println("@@@@ GROUP " + (i++) + " @@@@");
            g.printGroup();
        }
    }
    
    public static void printGroupFiles() {
        int i = 1;
        for (Group g: groups) {
            System.out.println("@@@@ GROUP " + (i++) + " @@@@");
            g.printGroupFiles();
            System.out.println("--------------------------------------------------------------\n");
        }
    }

    public static void printIncidentFiles() {
        int i = 1;
        for (Group g: groups) {
            g.printGroupFiles();
            System.out.println("--------------------------------------------------------------\n");
        }
    }
    private static boolean merge() {

        boolean repeat = false;
        for (int i = 0; i < groups.size() - 1; i++) {
            Group g1 = groups.get(i);
            HashSet<String> g1Files = g1.getFiles();
            lable1:
            for (int j = i + 1; j < groups.size(); j++) {
                Group g2 = groups.get(j);
                HashSet<String> g2Files = g2.getFiles();
                for (String f: g2Files) {
                    if (g1Files.contains(f)) {
                        System.out.println("Merging "+g1.getIncidents()+" and "+g2.getIncidents()+ " Common item : " + f);
                        g1.getIncidents().addAll(g2.getIncidents());
                        g1.getFiles().addAll(g2.getFiles());
                        groups.remove(j);
                        i--;
                        repeat = true;
                        break lable1;
                    }
                }
            }
        }

        if (repeat)
            return merge();
        else
            return false;
    }

}
