/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.IconHolder;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Terrain;
import com.mycompany.myapp.services.ServiceTerrain;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListTerrainsForm extends Form {

    public ListTerrainsForm(Form previous) {
        setTitle("List terrains");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<Terrain> terrains = ServiceTerrain.getInstance().getAllTerrains();
        for (Terrain t : terrains) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Terrain terrain) {

        CheckBox cb = new CheckBox(terrain.getName());
        CheckBox cb1 = new CheckBox(terrain.getDescription());

        cb.setEnabled(false);
                cb1.setEnabled(false);


        add(cb);
        add(cb1);

    }

}
