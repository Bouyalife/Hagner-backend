package com.example.demo.model;

import java.util.List;

public class Entity {
    private String name;
    private String country;
    private String company;
    private Long id;
    private List<String> note;

    public Entity(){
        
    }

    public Entity(String name, String country, String company) {
        this.name = name;
        this.country = country;
        this.company = company;
    }

    public Entity(String name, String country, String company, long id) {
        this.name = name;
        this.country = country;
        this.company = company;
        this.id = id;
    }

    public Entity(String name, String country, String company, long id,List<String> note) {
        this.name = name;
        this.country = country;
        this.company = company;
        this.id = id;
        this.note = note;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return this.name;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCompany() {
        return this.company;
    }

    public void addNoteToList(String note){
        this.note.add(note);
    }
    
    public void removeNoteFromList(int noteId){
        this.note.remove(noteId);
    }

    public List<String> getNotes() {
        return note;
    }

    public void setNotes(List<String> note) {
        this.note = note;
    }
    
}
