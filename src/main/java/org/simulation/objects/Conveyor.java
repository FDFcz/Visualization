package org.simulation.objects;

import org.engine.grphic.Material;
import org.engine.grphic.Mesh;
import org.engine.grphic.Vertex;
import org.engine.maths.Vector2f;
import org.engine.maths.Vector3f;
import org.engine.objects.SceneObjectUI;

public class Conveyor extends SceneObject{

    Table[] tables;

    public Table getFirstTable() {
        return tables[0];
    }
    public Table getLastTable() {
        return tables[tables.length - 1];
    }
    public Conveyor(Vector3f position, Vector3f rotation, Vector3f scale,int carCount,boolean isReverse,Table nextTable)
    {
        this(position,rotation,scale,carCount,isReverse);
         if(isReverse)tables[0].setNextTable(nextTable);
         else tables[tables.length-1].setNextTable(nextTable);
    }
    public Conveyor(Vector3f position, Vector3f rotation, Vector3f scale,int carCount,boolean isReverse) {
        super(position, rotation, scale);
        tables = new Table[carCount];
        if(isReverse)
        {
            Vector3f tempPosstion = new Vector3f(position.getX(),position.getY()-0.15f*(carCount-1),position.getZ());
            tables[tables.length-1] = new Table(tempPosstion, rotation, scale);
            tables[tables.length-1].updateTimes(6000,10000);
            for (int i = tables.length-2; i >= 0; i--) {
                tempPosstion = new Vector3f(position.getX(), tempPosstion.getY() + 0.15f, position.getZ());
                tables[i] = new Table(tempPosstion, rotation, scale);
                tables[i + 1].setNextTable(tables[i]);
                tables[i].updateTimes(6000,10000);
            }
        }
        else {
            Vector3f tempPosstion = new Vector3f(position.getX(),position.getY(),position.getZ());
            tables[0] = new Table(tempPosstion, rotation, scale);
            tables[0].updateTimes(6000,10000);
            for (int i = 1; i < carCount; i++) {
                tempPosstion = new Vector3f(position.getX(), tempPosstion.getY() - 0.15f, position.getZ());
                tables[i] = new Table(tempPosstion, rotation, scale);
                tables[i - 1].setNextTable(tables[i]);
                tables[i].updateTimes(6000,10000);
            }
        }
        tables[tables.length-1].setOccupied(false);
        tables[0].setOccupied(false);

        metalicMesh = new Mesh(new Vertex[] {
                new Vertex(new Vector3f(-0.05f,  0.09f, 0.0f),StatusColors.purple, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(-0.04f,  0.09f, 0.0f),StatusColors.purple, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(-0.05f,  -0.09f-(carCount-1)*0.15f, 0.0f),StatusColors.purple, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(-0.04f,  -0.09f-(carCount-1)*0.15f, 0.0f),StatusColors.purple, new Vector2f(0.4f,0.4f)),

                new Vertex(new Vector3f(0.05f,  0.09f, 0.0f),StatusColors.purple, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(0.04f,  0.09f, 0.0f),StatusColors.purple, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(0.05f,  -0.09f-(carCount-1)*0.15f, 0.0f),StatusColors.purple, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(0.04f,  -0.09f-(carCount-1)*0.15f, 0.0f),StatusColors.purple, new Vector2f(0.4f,0.4f))
        }, new int[] {
                0, 1, 2,
                1, 2, 3,

                4, 5 ,6,
                5, 6, 7
        },new Material("/textures/metalic.jpg"));
        metalicMesh.create();
        coloredUIObject = new SceneObjectUI(position,rotation,scale,metalicMesh);
        updateStatus(StatusColor.READY);
    }

    @Override
    public void upadate() {
        for (Table table : tables)
        {
            table.upadate();
        }
        renderer.renderMesh(coloredUIObject);
    }

    @Override
    public void destroy() {
        for (Table table : tables) table.destroy();
        super.destroy();
    }
}
