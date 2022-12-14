package com.example.demo.service;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
@CrossOrigin
public class FirebaseInitialize {
    
    @PostConstruct
    public void initialize() {
        try{
 
            FileInputStream serviceAccount =
            new FileInputStream("./serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://my-proj-c6436-default-rtdb.europe-west1.firebasedatabase.app")
            .build();

            FirebaseApp.initializeApp(options);




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
