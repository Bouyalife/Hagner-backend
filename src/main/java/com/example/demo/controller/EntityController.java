package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AddNote;
import com.example.demo.model.Entity;
import com.example.demo.service.EntityService;

@RestController
@CrossOrigin
public class EntityController {
    
    @Autowired
    private EntityService service;

    // bytt namn på funktion
    ////////////////////
    @GetMapping("/getentity") 
    public Object helloWorld(@RequestParam String id) {
        return service.getEntity(Integer.valueOf(id));
    }

    @GetMapping("/getallentities")
    public List<Entity> getAllEntitites(){
        return service.getAllEntities();
    }

    @PostMapping(value="/createproduct")
    public boolean addNewEntity(@RequestBody Entity entity) {
        if(service.addNewEntityService(entity)){
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/addNotes")
    public boolean addNotes(@RequestBody AddNote body){
        if (service.addNote(body)){
            return true;
        } else {
            return false;
        }
    }
}
