package org.simulation.objects;

import org.engine.maths.Vector3f;

import java.sql.Time;
import java.util.Random;

public abstract class TactebleObject extends SceneObject{
    protected long tactTime = 10000;
    protected long comonDoneTime = 8000;
    protected long actualTacTime=0;
    protected long lastTime = System.currentTimeMillis();
    protected float faultPersent =0.2f;
    protected float deleyedPersent= 0.2f;
    private Random rnd=new Random(System.nanoTime());
    private boolean faultChecked,deleyedChecked;

    protected abstract boolean isTiming();
    protected abstract boolean isDone();

    public TactebleObject(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(position, rotation, scale);
        //actualTacTime=tactTime;
    }

    protected void checkTime()
    {
        long actualTime = System.currentTimeMillis();
        if (status==StatusColor.WORKING)
        {
            long deltaTime = actualTime - lastTime;
            actualTacTime+=deltaTime;
            //System.out.println(actualTacTime>=comonDoneTime);
            if(actualTacTime%50==0&&!faultChecked)
            {
                float res = rnd.nextFloat();
                if(res<faultPersent) updateStatus(StatusColor.FAULT);
                faultChecked=true;
            }
            if(actualTacTime>=comonDoneTime&&!deleyedChecked)
            {
                float res = rnd.nextFloat();
                if(res>deleyedPersent) updateStatus(StatusColor.READY);
                deleyedChecked=true;
            }
            if(actualTacTime>=tactTime)
            {
                updateStatus(StatusColor.DELAYED);

            }
        }
        lastTime = actualTime;
    }
}
