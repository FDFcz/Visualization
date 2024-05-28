package org.example;

import org.engine.maths.Vector3f;
import org.simulation.objects.Robot;
import org.simulation.objects.SceneObject;
import org.simulation.objects.Table;

import java.util.ArrayList;
import java.util.List;

public class SimulationScene {
    private List<SceneObject> sceneObjects = new ArrayList<>();
    public SimulationScene()
    {
        sceneObjects.add(new Table(new Vector3f(0, 0, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1)));
        sceneObjects.add(new Table(new Vector3f(-1f, 0, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1)));
        sceneObjects.add(new Robot(new Vector3f(0, 0.2f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1)));
        sceneObjects.get(2).updateStatus(SceneObject.StatusColor.FAULT);
        sceneObjects.get(0).updateStatus(SceneObject.StatusColor.WAITING);
    }


    public void renderObject()
    {
        for(SceneObject sceneObject : sceneObjects) sceneObject.renderSelf();
    }
    public void destroy()
    {
        for(SceneObject sceneObject : sceneObjects) sceneObject.destroy();
    }
 }
