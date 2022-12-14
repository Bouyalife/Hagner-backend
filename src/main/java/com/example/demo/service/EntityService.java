package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.model.AddNote;
import com.example.demo.model.Entity;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.cloud.FirestoreClient;

@Service
@CrossOrigin
public class EntityService {
    
    Firestore db;
    
    // add entity to firebase
    public boolean addNewEntityService(Entity entity){
        try{
            db = FirestoreClient.getFirestore();
            ApiFuture<DocumentSnapshot> query = db.collection("test").document("id").get();
            Long id = (long) -1;
            if(query.get().get("id") != null){
                 id = (Long) query.get().get("id");
            }
            
            Map<String,String> object = new HashMap<>();
            object.put("name",entity.getName());
            object.put("company",entity.getCompany());
            object.put("country",entity.getCountry());
            object.put("id",Long.toString(id));
            
            List<String> test2 = new ArrayList<String>();
            Map<String,List<String>> test = new HashMap<>();
            test.put("note",test2);

            db.collection("test").document(Long.toString(id)).set(object);
            db.collection("test").document(Long.toString(id)).update("note",test);
            id++;
            Map<String,Long> idObject = new HashMap<>();
            idObject.put("id",id);
            db.collection("test").document("id").set(idObject);
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // get entity from firebase
    public Object getEntity(int id) {
        try{
            db = FirestoreClient.getFirestore();
            Map<String,Object> returnValue = db.collection("test").document(Integer.toString(id)).get().get().getData();
            return returnValue;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // get all entites from firebase
    public List<Entity> getAllEntities(){
        try{
            List<Entity> list  = new ArrayList<>();
            
            FirestoreClient.getFirestore().collection("test").orderBy("name").get().get().getDocuments().stream().forEach(k ->{
                if(k == null){
                    System.out.println("test");
                } else {

                    System.out.println(k.get("note"));
                    Map<String,List<String>> t = (Map<String, List<String>>) k.get("note");

                    list.add(new Entity(k.get("name").toString(), k.get("country").toString(), k.get("company").toString(), Long.valueOf(k.get("id").toString()),t.get("note"))); 
                }
            });;

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addNote(AddNote body) {
        try{
            HashMap<String,List<String>> test = (HashMap<String, List<String>>) FirestoreClient.getFirestore().collection("test").document(body.getId()).get().get().get("note");
            List<String> test1 = new ArrayList<>();;

            try{
                if(test.get("note") != null){
                    test1 = test.get("note");
                }
            } catch(Exception e){
                System.out.println("biatch");
            }

            test1.add(java.time.LocalDate.now() + ": " + body.getNote());
            test.put("note",test1);
            FirestoreClient.getFirestore().collection("test").document(body.getId()).update("note",test); 

            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
