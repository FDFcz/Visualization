package org.simulation.objects;
import org.engine.maths.Vector3f;

public class Turntable extends Table{
    private Vector3f secondRotation;
    public Turntable(Vector3f position, Vector3f rotation, Vector3f scale,Vector3f secondRotation) {
        super(position, rotation, scale);
        this.secondRotation = secondRotation;
    }

    @Override
    public void renderSelf() {
        super.renderSelf();
        swapRotation();
    }
    private void swapRotation()
    {
        Vector3f tempRot = new Vector3f(secondRotation.getX(), secondRotation.getY(), secondRotation.getZ());
        secondRotation = coloredUIObject.getRotation();
        Vector3f tempPoss = coloredUIObject.getPosition();
        coloredUIObject.setPosition(new Vector3f(0,0,0)); //todo
        coloredUIObject.setRotation(tempRot);
        coloredUIObject.setPosition(tempPoss);

    }
}
