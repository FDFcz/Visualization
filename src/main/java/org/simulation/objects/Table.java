package org.simulation.objects;

import org.engine.grphic.Material;
import org.engine.grphic.Mesh;
import org.engine.grphic.Vertex;
import org.engine.maths.Vector2f;
import org.engine.maths.Vector3f;
import org.engine.objects.SceneObjectUI;

public class Table extends SceneObject{
    //Renderer renderer;
    private boolean isOccupied;
    public SceneObjectUI carObject;
    //public SceneObjectUI object2 = new SceneObjectUI(new Vector3f(0f, 0, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1), metalicMesh);

    private Mesh carMesh = new Mesh(new Vertex[] {
            new Vertex(new Vector3f(-0.062f,  0.05f, 0.0f),StatusColors.white, new Vector2f(0,0)),
            new Vertex(new Vector3f(-0.062f, -0.05f, 0.0f),StatusColors.white,new Vector2f(0,0.8f)),
            new Vertex(new Vector3f( 0.062f, -0.05f, 0.0f),StatusColors.white,new Vector2f(0.8f,0.8f)),
            new Vertex(new Vector3f( 0.062f,  0.05f, 0.0f),StatusColors.white,new Vector2f(0.8f,0))
    }, new int[] {
            0, 1, 2,
            0, 3, 2
    },new Material("/textures/car.png"));
    public Table(Vector3f position, Vector3f rotation, Vector3f scale)
    {
        super(position,rotation,scale);
        carObject = new SceneObjectUI(position,rotation,scale,carMesh);
        carMesh.create();
    }
    @Override
    public void renderSelf() {
        renderer.renderMesh(coloredUIObject);
        renderer.renderMesh(carObject);
    }
    public void destroy()
    {
        super.destroy();
        carMesh.destroy();
    }
}
