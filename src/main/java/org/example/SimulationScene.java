package org.example;

import org.engine.maths.Vector3f;
import org.simulation.objects.*;

import java.util.ArrayList;
import java.util.List;

public class SimulationScene {
    private List<SceneObject> sceneObjects = new ArrayList<>();
    public SimulationScene()
    {
        Table tempNextTable;
        sceneObjects.add(new Conveyor(new Vector3f(0.4f, 0.2f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),5));
        //------
        sceneObjects.add(new Conveyor(new Vector3f(-0.2f, 0.5f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),3));
        tempNextTable = ((Conveyor) sceneObjects.get(1)).getFirstTable();
        sceneObjects.add(new Table(new Vector3f(-0.4f, 0.5f, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Table) sceneObjects.get(2));
        tempNextTable.setOccupied(false);
        sceneObjects.add(new Table(new Vector3f(-0.6f, 0.5f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Table) sceneObjects.get(3));
        sceneObjects.add(new Table(new Vector3f(-0.8f, 0.5f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Table) sceneObjects.get(4));
        sceneObjects.add(new Table(new Vector3f(-1f, 0.5f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));

        //sceneObjects.add(new Turntable(new Vector3f(0.6f, 0.2f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),new Vector3f(0, 0, 90)));
        sceneObjects.add(new Robot(new Vector3f(0, 0.2f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1)));
    }



    public void renderObject()
    {
        for(SceneObject sceneObject : sceneObjects) sceneObject.upadate();
    }
    public void destroy()
    {
        for(SceneObject sceneObject : sceneObjects) sceneObject.destroy();
    }
 }
