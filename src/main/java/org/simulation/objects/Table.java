package org.simulation.objects;

import org.engine.grphic.Material;
import org.engine.grphic.Mesh;
import org.engine.grphic.Vertex;
import org.engine.maths.Vector2f;
import org.engine.maths.Vector3f;
import org.engine.objects.SceneObjectUI;

import java.util.ArrayList;
import java.util.List;

public class Table extends TactebleObject{
    private boolean isOccupied = true;
    protected boolean canBeMoved = true;
    public SceneObjectUI carObject;
    Mesh carMesh;
    protected Table previousTable;
    protected List<Table> nextTable = new ArrayList<Table>();

    protected long travelTime=3000;
    protected long actualTravelTime=0;
    //protected long lastSystemTime;


    public void setCanBeMoved(boolean canBeMoved) {this.canBeMoved = canBeMoved;}
    public void setNextTable(Table nextTable) {this.nextTable.add(nextTable);}
    public void setOccupied(boolean isOccupied)
    {
        if(isOccupied)
        {
            resetTimer();
            updateStatus(StatusColor.WORKING);
        }
        else updateStatus(StatusColor.READY);
        this.isOccupied = isOccupied;
    }

    @Override
    protected boolean isTiming() {
        return isOccupied;
    }

    @Override
    protected boolean isDone() {
        return (isOccupied&&(status==StatusColor.WAITING||status==StatusColor.READY));
    }
    public Table(Vector3f position, Vector3f rotation, Vector3f scale,Table nextTable)
    {
        this(position,rotation,scale);
        this.nextTable.add(nextTable);
    }

    public Table(Vector3f position, Vector3f rotation, Vector3f scale)
    {
        super(position,rotation,scale);
        metalicMesh = new Mesh(new Vertex[] {
                new Vertex(new Vector3f(-0.072f,  0.06f, 0.0f),StatusColors.purple, new Vector2f(0.4f,0.4f)),
                new Vertex(new Vector3f(-0.072f, -0.06f, 0.0f),StatusColors.purple,new Vector2f(0.4f,0.6f)),
                new Vertex(new Vector3f( 0.072f, -0.06f, 0.0f),StatusColors.purple,new Vector2f(0.6f,0.6f)),
                new Vertex(new Vector3f( 0.072f,  0.06f, 0.0f),StatusColors.purple,new Vector2f(0.6f,0.4f))
        }, new int[] {
                0, 1, 2,
                0, 3, 2
        },new Material("/textures/metalic.jpg"));
        metalicMesh.create();
        coloredUIObject = new SceneObjectUI(position,rotation,scale,metalicMesh);

        carMesh = new Mesh(new Vertex[] {
                new Vertex(new Vector3f(-0.062f,  0.05f, 0.0f),StatusColors.white, new Vector2f(0,0)),
                new Vertex(new Vector3f(-0.062f, -0.05f, 0.0f),StatusColors.white,new Vector2f(0,0.8f)),
                new Vertex(new Vector3f( 0.062f, -0.05f, 0.0f),StatusColors.white,new Vector2f(0.8f,0.8f)),
                new Vertex(new Vector3f( 0.062f,  0.05f, 0.0f),StatusColors.white,new Vector2f(0.8f,0))
        }, new int[] {
                0, 1, 2,
                0, 3, 2
        },new Material("/textures/car.png"));
        carMesh.create();
        carObject = new SceneObjectUI(position,rotation,scale,carMesh);
        updateStatus(StatusColor.WORKING);
    }

    public boolean TryToMoveOn(Table previos)
    {
        if(isOccupied || previousTable != null) return false;
        actualTravelTime = travelTime;
        previousTable = previos;
        updateStatus(StatusColor.PREPARING);
        return true;
    }
    @Override
    public void upadate() {
        if(previousTable!=null)
        {
            long deltatime= System.currentTimeMillis()-lastSystemTime;
            actualTravelTime=travelTime-deltatime;
            if(actualTravelTime<=0)
            {
                setOccupied(true);
                previousTable.setOccupied(false);
                previousTable = null;
            }
        }
        else if(canBeMoved && isDone())
        {
            for (Table nextTable : nextTable) {
                if (nextTable.TryToMoveOn(this)) {
                    updateStatus(StatusColor.FINISHING);
                    break;
                }
            }
        }
        if(isOccupied)
        {
            renderer.renderMesh(carObject);
            updateTime();
        }
        renderer.renderMesh(coloredUIObject);
    }
    public void destroy()
    {
        super.destroy();
        carMesh.destroy();
    }
}
