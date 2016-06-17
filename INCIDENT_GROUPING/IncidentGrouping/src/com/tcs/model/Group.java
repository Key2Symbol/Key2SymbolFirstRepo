package com.tcs.model;

import java.util.ArrayList;
import java.util.HashSet;

public class Group {


    private ArrayList<String> incidents;
    private HashSet<String> files;

    public Group() {
        incidents = new ArrayList<String>();
        files = new HashSet<String>();
    }

    public void setIncidents(ArrayList<String> incidents) {
        this.incidents = incidents;
    }

    public ArrayList<String> getIncidents() {
        return incidents;
    }

    public void setFiles(HashSet<String> files) {
        this.files = files;
    }

    public HashSet<String> getFiles() {
        return files;
    }


    public boolean addIncident(String inc) {
        return incidents.add(inc);
    }

    public boolean addFile(String line) {
        return files.add(line);
    }

    public void printGroup() {
        System.out.println("-------------------------");
        for (String inc: incidents) {
            System.out.println(inc);
        }
        System.out.println("------------X------------\n");
    }


    void printGroupFiles() {
        System.out.print("Incidents: " + incidents + "\n");
        System.out.println("Files: " + files.size());
        for (String file: files) {
            System.out.println(file);
        }
    }
}
