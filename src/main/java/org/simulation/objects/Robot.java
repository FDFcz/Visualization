package org.simulation.objects;

import org.engine.grphic.Material;
import org.engine.grphic.Mesh;
import org.engine.grphic.Vertex;
import org.engine.maths.Vector2f;
import org.engine.maths.Vector3f;
import org.engine.objects.SceneObjectUI;

public class Robot extends TactebleObject {

    @Override
    protected boolean isTiming() {
        return false;
    }

    @Override
    protected boolean isDone() {
        return hasStatus(StatusColor.READY);
    }

    public Robot(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(position, rotation, scale);
        metalicMesh = new Mesh(new Vertex[] {
                new Vertex(new Vector3f(0.0f,  0.02f, 0.0f),StatusColors.purple, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(-0.02f, 0.0f, 0.0f),StatusColors.purple,new Vector2f(0.4f,0.6f)),
                new Vertex(new Vector3f( 0.0f, -0.02f, 0.0f),StatusColors.purple,new Vector2f(0.6f,0.6f)),
                new Vertex(new Vector3f( 0.02f,  0.0f, 0.0f),StatusColors.purple,new Vector2f(0.6f,0.4f)),
                new Vertex(new Vector3f( 0.03f, 0.025f, 0.0f),StatusColors.purple,new Vector2f(0.6f,0.6f)),
                new Vertex(new Vector3f( 0.06f,  0.0f, 0.0f),StatusColors.purple,new Vector2f(0.6f,0.4f))
        }, new int[] {
                0, 1, 2,
                0, 3, 2,
                0, 4, 3,
                3, 4, 5
        },new Material("/textures/metalic.jpg"));
        metalicMesh.create();
        coloredUIObject = new SceneObjectUI(position,rotation,scale,metalicMesh);
        updateStatus(StatusColor.WORKING);
        comonDoneTime+=3000;
        tactTime+=3000;
    }
    @Override
    public void upadate() {
        if(status!=StatusColor.READY) updateTime();
        renderer.renderMesh(coloredUIObject);
    }
}
