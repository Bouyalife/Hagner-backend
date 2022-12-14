package com.example.demo.model;

public class AddNote {
    String note;
    String id;

    public AddNote(){}

    public AddNote(String note, String id) {
        this.note = note;
        this.id = id;
    }

    public String getNote(){
        return this.note;
    }

    public String getId(){
        return this.id;
    }
    
}
