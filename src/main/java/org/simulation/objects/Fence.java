package org.simulation.objects;

import org.engine.grphic.Material;
import org.engine.grphic.Mesh;
import org.engine.grphic.Vertex;
import org.engine.maths.Vector2f;
import org.engine.maths.Vector3f;
import org.engine.objects.SceneObjectUI;

public class Fence extends SceneObject{
    public Fence(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(position, rotation, scale);
        metalicMesh = new Mesh(new Vertex[] {
                new Vertex(new Vector3f(-0.083f,  0.15f, 0.0f),StatusColors.purple, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(-0.083f, 0.156f, 0.0f),StatusColors.purple,new Vector2f(0.4f,0.6f)),
                new Vertex(new Vector3f( 0.083f, 0.15f, 0.0f),StatusColors.purple,new Vector2f(0.6f,0.6f)),
                new Vertex(new Vector3f( 0.083f, 0.156f, 0.0f),StatusColors.purple,new Vector2f(0.6f,0.6f)),

                new Vertex(new Vector3f(-0.083f,  -0.06f, 0.0f),StatusColors.purple, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(-0.083f, -0.066f, 0.0f),StatusColors.purple,new Vector2f(0.4f,0.6f)),
                new Vertex(new Vector3f( 0.083f, -0.06f, 0.0f),StatusColors.purple,new Vector2f(0.6f,0.6f)),
                new Vertex(new Vector3f( 0.083f, -0.066f, 0.0f),StatusColors.purple,new Vector2f(0.6f,0.6f)),
        }, new int[] {
                0, 1, 2,
                1, 2, 3,

                4,5,6,
                5,6,7
        },new Material("/textures/metalic.jpg"));
        metalicMesh.create();
        coloredUIObject = new SceneObjectUI(position,rotation,scale,metalicMesh);
    }

    @Override
    public void upadate() {
        renderer.renderMesh(coloredUIObject);
    }
}
