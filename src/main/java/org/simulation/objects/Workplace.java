package org.simulation.objects;

import org.engine.maths.Vector3f;

public class Workplace extends SceneObject{
    Table table;
    //Fence fence;
    Robot[] robots;
    public Workplace(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(position, rotation, scale);
        table = new Table(position, rotation, scale);
        //fence = new Fence(position, rotation, scale);
        robots = new Robot[1];
        position = new Vector3f(position.getX()-0.06f,position.getY()+0.1f,position.getZ());
        robots[0] = new Robot(position, rotation, scale);

    }

    @Override
    public void upadate() {
        table.upadate();
        //fence.upadate();
        robots[0].upadate();
    }

    @Override
    public void destroy() {
        table.destroy();
        //fence.destroy();
        robots[0].destroy();
    }
}
