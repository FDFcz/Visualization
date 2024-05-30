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
    //protected Robot[] robots;
    private boolean isOccupied = true;
    public SceneObjectUI carObject;
    Mesh carMesh;
    protected Table previousTable;
    protected List<Table> nextTable = new ArrayList<Table>();

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
        setOccupied(true);
        //previousTable = previos;
        return true;
    }
    @Override
    public void upadate() {
        if(isOccupied)
        {
            renderer.renderMesh(carObject);
            updateTime();
        }
        renderer.renderMesh(coloredUIObject);

        if(isDone())
        {
            for (Table nextTable : nextTable) {
                if (nextTable.TryToMoveOn(this)) {
                    isOccupied = false;
                    //updateStatus(StatusColor.READY);
                    resetTimer();
                    break;
                }
            }
            //if(status!=StatusColor.READY)updateStatus(StatusColor.WAITING);
        }
    }
    public void destroy()
    {
        super.destroy();
        carMesh.destroy();
    }
}
