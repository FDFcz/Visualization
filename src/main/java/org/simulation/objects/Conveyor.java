package org.simulation.objects;

import org.engine.grphic.Material;
import org.engine.grphic.Mesh;
import org.engine.grphic.Vertex;
import org.engine.maths.Vector2f;
import org.engine.maths.Vector3f;
import org.engine.objects.SceneObjectUI;

import java.util.ArrayList;
import java.util.List;

public class Conveyor extends SceneObject{

    Table[] tables;
    public Conveyor(Vector3f position, Vector3f rotation, Vector3f scale,int carCount) {
        super(position, rotation, scale);
        tables = new Table[carCount];
        Vector3f tempPosstion = new Vector3f(position.getX(),position.getY(),position.getZ());
        for(int i = 0; i < carCount; i++)
        {
            tables[i] = new Table(tempPosstion,rotation,scale);
            tempPosstion = new Vector3f(position.getX(),tempPosstion.getY()-0.15f,position.getZ());
        }

        metalicMesh = new Mesh(new Vertex[] {
                new Vertex(new Vector3f(-0.05f,  0.09f, 0.0f),StatusColors.green, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(-0.04f,  0.09f, 0.0f),StatusColors.green, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(-0.05f,  -0.09f-(carCount-1)*0.15f, 0.0f),StatusColors.green, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(-0.04f,  -0.09f-(carCount-1)*0.15f, 0.0f),StatusColors.green, new Vector2f(0.4f,0.4f)),

                new Vertex(new Vector3f(0.05f,  0.09f, 0.0f),StatusColors.green, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(0.04f,  0.09f, 0.0f),StatusColors.green, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(0.05f,  -0.09f-(carCount-1)*0.15f, 0.0f),StatusColors.green, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(0.04f,  -0.09f-(carCount-1)*0.15f, 0.0f),StatusColors.green, new Vector2f(0.4f,0.4f))
        }, new int[] {
                0, 1, 2,
                1, 2, 3,

                4, 5 ,6,
                5, 6, 7
        },new Material("/textures/metalic.jpg"));
        metalicMesh.create();
        coloredUIObject = new SceneObjectUI(position,rotation,scale,metalicMesh);

    }

    @Override
    public void renderSelf() {
        for (Table table : tables)
        {
            table.renderSelf();
        }
        renderer.renderMesh(coloredUIObject);
    }
}
