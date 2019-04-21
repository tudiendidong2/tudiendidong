package vn.edu.tdc.listmusic;

import java.util.ArrayList;

public class Song {

    private String tilte;
    private int File;

    public Song(String tilte, int file) {
        this.tilte = tilte;
        File = file;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public int getFile() {
        return File;
    }

    public void setFile(int file) {
        File = file;
    }




}