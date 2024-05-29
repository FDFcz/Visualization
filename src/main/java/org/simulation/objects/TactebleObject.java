package org.simulation.objects;

import org.engine.maths.Vector3f;

import java.sql.Time;

public abstract class TactebleObject extends SceneObject{
    protected float tactTime = 20f;
    protected float actualTacTime=0f;
    protected Time lastTime = new Time(0);
    protected  boolean isTiming;

    abstract protected boolean isTiming();

    public TactebleObject(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(position, rotation, scale);
    }

    @Override
    public void renderSelf() {

    }
}
