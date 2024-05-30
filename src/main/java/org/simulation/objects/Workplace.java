package org.simulation.objects;

import org.engine.maths.Vector3f;

public class Workplace extends TactebleObject{
    Table table;
    Fence fence;
    Robot[] robots = new Robot[4];
    boolean newCycle = false;

    public Table getTable(){return table;}

    @Override
    protected boolean isTiming() {
        return false;
    }

    @Override
    protected boolean isDone() {
        boolean done=true;
            for (Robot robot : robots) done = (done&&robot.isDone());
            return (table.isDone()&&done);
    }
    public Workplace(Vector3f position, Vector3f rotation, Vector3f scale,Table nexTable) {
        this(position, rotation, scale);
        table.setNextTable(nexTable);
    }
    public Workplace(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(position, rotation, scale);
        table = new Table(position, rotation, scale);
        table.setCanBeMoved(false);
        fence = new Fence(position, rotation, scale);
        robots[0] = new Robot(new Vector3f(position.getX()-0.06f,position.getY()+0.1f,position.getZ()), rotation, scale);
        robots[1] = new Robot(new Vector3f(position.getX()+0.025f,position.getY()+0.1f,position.getZ()), rotation, scale);
        robots[2] = new Robot(new Vector3f(position.getX()+0.025f,position.getY()-0.1f,position.getZ()), rotation, scale);
        robots[3] = new Robot(new Vector3f(position.getX()-0.06f,position.getY()-0.1f,position.getZ()), rotation, scale);
    }

    @Override
    public void upadate() {
        if(isDone()) table.setCanBeMoved(true);
        if(table.hasStatus(StatusColor.PREPARING))
        {
            newCycle = true;
        }
        else if (newCycle && table.isTiming()) {
            newCycle = false;
            table.setCanBeMoved(false);
            for (Robot robot : robots) robot.resetTimer();
        }
        table.upadate();
        fence.upadate();
        for (Robot robot :robots)robot.upadate();
    }

    @Override
    public void destroy() {
        table.destroy();
        fence.destroy();
        for (Robot robot :robots)robot.destroy();
    }
}
