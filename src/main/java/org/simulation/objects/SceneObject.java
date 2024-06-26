package org.simulation.objects;

import org.engine.grphic.Material;
import org.engine.grphic.Mesh;
import org.engine.grphic.Renderer;
import org.engine.grphic.Vertex;
import org.engine.maths.Vector2f;
import org.engine.maths.Vector3f;
import org.engine.objects.SceneObjectUI;

public abstract class SceneObject {
    protected SceneObjectUI coloredUIObject;
    protected static Renderer renderer;

    public enum StatusColor{PREPARING,READY,WORKING,FINISHING,FAULT,WAITING,MAINTENANCE,DELAYED,OFFLINE};
    public record StatusColors() {
        public static Vector3f green = new Vector3f(0,1,0);
        public static Vector3f lightGreen = new Vector3f(0.5f,1,0.5f);
        public static Vector3f red = new Vector3f(1,0,0);
        public static Vector3f blue = new Vector3f(0,0,1);
        public static Vector3f yellow = new Vector3f(1,1,0);
        public static Vector3f white = new Vector3f(1,1,1);
        public static Vector3f cyan = new Vector3f(0,1,1);
        public static Vector3f purple = new Vector3f(1,0,1);
    }
    protected StatusColor status = StatusColor.OFFLINE;
    public boolean hasStatus(StatusColor statusColor) {return status == statusColor;}


    protected Mesh metalicMesh;

    public static void intRenderer(Renderer rnd)
    {
        renderer = rnd;
    }

    protected SceneObject() {}
    public SceneObject(Vector3f position, Vector3f rotation, Vector3f scale) {

    }
    public void updateStatus(StatusColor c){
        switch (c){
            case READY:
                coloredUIObject.setStatusColor(StatusColors.green);
                break;
            case WORKING,PREPARING,FINISHING:
                coloredUIObject.setStatusColor(StatusColors.lightGreen);
                break;
            case FAULT:
                coloredUIObject.setStatusColor(StatusColors.red);
                break;
            case WAITING:
                coloredUIObject.setStatusColor(StatusColors.cyan);
                break;
            case MAINTENANCE:
                coloredUIObject.setStatusColor(StatusColors.yellow);
                break;
            case DELAYED:
                coloredUIObject.setStatusColor(StatusColors.blue);
                break;
            case OFFLINE:
                coloredUIObject.setStatusColor(StatusColors.purple);
                break;
        }
        status = c;
    }
    public abstract void upadate();

    public void destroy()
    {
        metalicMesh.destroy();
    }
}
