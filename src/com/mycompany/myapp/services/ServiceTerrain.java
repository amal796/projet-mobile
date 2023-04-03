/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Terrain;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceTerrain{

    public ArrayList<Terrain> terrains;

    public static ServiceTerrain instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ArrayList<Object> Terrain;

    private ServiceTerrain() {
        req = new ConnectionRequest();
    }

    public static ServiceTerrain getInstance() {
        if (instance == null) {
            instance = new ServiceTerrain();
        }
        return instance;
    }

    public boolean addTerrain(Terrain t) {

        String name = t.getName();
        String description = t.getDescription();
       
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "create/" + description + "/" + name;

        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Object> parseTerrains(String jsonText) {
          try {

                    System.out.println(jsonText);
            Terrain=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Terrain a = new Terrain();
                                       
                
      

      
                  
                a.setId((int) Float.parseFloat(obj.get("id").toString()));                
                a.setName(obj.get("name").toString());     
                 


                Terrain.add(a);
            }
        } catch (IOException ex) {
            
        }
        return Terrain ;
    }

    public ArrayList<Terrain> getAllTerrains() {
        String url = Statics.BASE_URL + "get/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ArrayList<Object> Terrains = parseTerrains(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return terrains;
    }
}
