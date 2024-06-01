package org.example;

import org.engine.maths.Vector3f;
import org.simulation.objects.*;

import java.util.ArrayList;
import java.util.List;

public class SimulationScene {
    private Table firstTable,lastTable;
    private List<SceneObject> sceneObjects = new ArrayList<>();
    public SimulationScene()
    {
        Table tempNextTable;
//-----END------------------------
        sceneObjects.add(new Table(new Vector3f(1f, -0.55f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1)));
        tempNextTable = (Table) sceneObjects.get(sceneObjects.size()-1);
        sceneObjects.add(new Conveyor(new Vector3f(0.8f, -0.4f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),2,false,tempNextTable));
        tempNextTable = ((Conveyor) sceneObjects.get(sceneObjects.size() - 1)).getFirstTable();
        sceneObjects.add(new Table(new Vector3f(0.6f, -0.4f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = (Table) sceneObjects.get(sceneObjects.size()-1);
        sceneObjects.add(new Table(new Vector3f(0.4f, -0.4f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = (Table) sceneObjects.get(sceneObjects.size()-1);
        sceneObjects.add(new Conveyor(new Vector3f(0.2f, -0.4f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),2,true,tempNextTable));
        tempNextTable = ((Conveyor) sceneObjects.get(sceneObjects.size() - 1)).getLastTable();
        sceneObjects.add(new Workplace(new Vector3f(0f, -0.55f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        tempNextTable.setOccupied(false);
        sceneObjects.add(new Table(new Vector3f(-0.2f, -0.55f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = (Table) sceneObjects.get(sceneObjects.size()-1);
        tempNextTable.setOccupied(false);
        sceneObjects.add(new Table(new Vector3f(-0.4f, -0.55f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = (Table) sceneObjects.get(sceneObjects.size()-1);
        sceneObjects.add(new Workplace(new Vector3f(-0.6f, -0.55f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        sceneObjects.add(new Table(new Vector3f(-0.8f, -0.55f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Table) sceneObjects.get(sceneObjects.size()-1));
        sceneObjects.add(new Conveyor(new Vector3f(-1f, -0.1f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),4,false,tempNextTable));
        tempNextTable = ((Conveyor) sceneObjects.get(sceneObjects.size() - 1)).getFirstTable();
        sceneObjects.add(new Workplace(new Vector3f(-0.8f, -0.1f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        sceneObjects.add(new Workplace(new Vector3f(-0.6f, -0.1f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        sceneObjects.add(new Workplace(new Vector3f(-0.4f, -0.1f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        sceneObjects.add(new Workplace(new Vector3f(-0.2f, -0.1f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        sceneObjects.add(new Workplace(new Vector3f(0.0f, -0.1f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        sceneObjects.add(new Workplace(new Vector3f(0.2f, -0.1f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        sceneObjects.add(new Workplace(new Vector3f(0.4f, -0.1f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        sceneObjects.add(new Workplace(new Vector3f(0.6f, -0.1f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        sceneObjects.add(new Workplace(new Vector3f(0.8f, -0.1f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        sceneObjects.add(new Conveyor(new Vector3f(1f, 0.5f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),5,false,tempNextTable));
        tempNextTable =((Conveyor) sceneObjects.get(sceneObjects.size()-1)).getFirstTable();
        sceneObjects.add(new Workplace(new Vector3f(0.8f, 0.5f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        sceneObjects.add(new Workplace(new Vector3f(0.6f, 0.5f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        sceneObjects.add(new Conveyor(new Vector3f(0.4f, 0.5f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),3,true,tempNextTable));
        tempNextTable =((Conveyor) sceneObjects.get(sceneObjects.size()-1)).getLastTable();
        sceneObjects.add(new Workplace(new Vector3f(0.2f, 0.2f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        sceneObjects.add(new Workplace(new Vector3f(0f, 0.2f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        sceneObjects.add(new Conveyor(new Vector3f(-0.2f, 0.5f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),3,false,tempNextTable));
        tempNextTable =((Conveyor) sceneObjects.get(sceneObjects.size()-1)).getFirstTable();
        sceneObjects.add(new Table(new Vector3f(-0.4f, 0.5f, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = (Table) sceneObjects.get(sceneObjects.size()-1);
        sceneObjects.add(new Table(new Vector3f(-0.6f, 0.5f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = (Table) sceneObjects.get(sceneObjects.size()-1);
        sceneObjects.add(new Table(new Vector3f(-0.8f, 0.5f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = (Table) sceneObjects.get(sceneObjects.size()-1);
        sceneObjects.add(new Table(new Vector3f(-1f, 0.5f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        //--make cycle
        tempNextTable = (Table) sceneObjects.get(0);
        tempNextTable.setNextTable((Table) sceneObjects.get(sceneObjects.size()-1));

        //fill gaps
        tempNextTable =((Conveyor) sceneObjects.get(1)).getLastTable();
        sceneObjects.add(new Table(new Vector3f(0.6f, -0.55f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = (Table) sceneObjects.get(sceneObjects.size()-1);
        sceneObjects.add(new Table(new Vector3f(0.4f, -0.55f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = (Table) sceneObjects.get(sceneObjects.size()-1);
        ((Conveyor)sceneObjects.get(4)).getFirstTable().setNextTable(tempNextTable);
        //---------------
        tempNextTable =((Conveyor) sceneObjects.get(23)).getFirstTable();
        sceneObjects.add(new Workplace(new Vector3f(0.2f, 0.5f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        //tempNextTable.updateTimes(26000,32000);
        sceneObjects.add(new Workplace(new Vector3f(0f, 0.5f, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1),tempNextTable));
        tempNextTable = ((Workplace) sceneObjects.get(sceneObjects.size()-1)).getTable();
        ((Conveyor) sceneObjects.get(26)).getFirstTable().setNextTable(tempNextTable);
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
