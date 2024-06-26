package org.simulation.objects;

import org.engine.maths.Vector3f;
import java.util.Random;

public abstract class TactebleObject extends SceneObject{
    protected long tactTime = 10000;
    protected long comonDoneTime = 8000;
    protected long actualTacTime=0;
    protected long lastSystemTime = System.currentTimeMillis();
    protected float faultPersent =0.05f;
    protected float deleyedPersent= 0.08f;
    private Random rnd=new Random(System.nanoTime());
    private boolean faultChecked,deleyedChecked;

    protected abstract boolean isTiming();
    protected abstract boolean isDone();
    public void updateTimes(long comonDoneTime,long tactTime){
        this.comonDoneTime=comonDoneTime;
        this.tactTime=tactTime;
    }

    public TactebleObject(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(position, rotation, scale);
    }

    protected void resetTimer()
    {
        //updateStatus(StatusColor.READY);
        updateStatus(StatusColor.WORKING);
        lastSystemTime = System.currentTimeMillis();
        actualTacTime=0;
        faultChecked=false;
        deleyedChecked=false;
    }

    protected void updateTime()
    {
        long currentTimeMill = System.currentTimeMillis();
        if (status==StatusColor.WORKING||status==StatusColor.DELAYED)
        {
            long deltaTime = currentTimeMill - lastSystemTime;
            actualTacTime+=deltaTime;
            //System.out.println(actualTacTime>=comonDoneTime);
            if(actualTacTime%70==0&&!faultChecked)
            {
                float res = rnd.nextFloat();
                if(res<faultPersent)
                {
                    updateStatus(StatusColor.FAULT);
                    actualTacTime+=2000;
                }
                faultChecked=true;
            }
            if(actualTacTime>=comonDoneTime&&!deleyedChecked)
            {
                float res = rnd.nextFloat();
                if(res>deleyedPersent)
                {
                    updateStatus(StatusColor.READY);
                    actualTacTime = 3000;
                }
                deleyedChecked=true;
            }
            if(actualTacTime>=tactTime)
            {
                if(!isDone()) updateStatus(StatusColor.DELAYED);
                deleyedChecked=false;
                actualTacTime-=6000;
            }
        }
        else if(status==StatusColor.FAULT)
        {
            long deltaTime = currentTimeMill - lastSystemTime;
            actualTacTime-=deltaTime;
            if(actualTacTime<0)
            {
                updateStatus(StatusColor.MAINTENANCE);
                actualTacTime = rnd.nextInt(8000,16000);
            }
        }
        else if(status==StatusColor.MAINTENANCE)
        {
            long deltaTime = currentTimeMill - lastSystemTime;
            actualTacTime-=deltaTime;
            if(actualTacTime<0)
            {
                updateStatus(StatusColor.WORKING);
                actualTacTime = rnd.nextInt(3000,7000);
            }
        }
        else if(status==StatusColor.READY)
        {
            long deltaTime = currentTimeMill - lastSystemTime;
            actualTacTime-=deltaTime;
            if(actualTacTime<0)
            {
                updateStatus(StatusColor.WAITING);
            }
        }
        lastSystemTime = currentTimeMill;
    }
}
