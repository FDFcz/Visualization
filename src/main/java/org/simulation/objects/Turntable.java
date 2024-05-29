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
    }
    private void swapRotation()
    {
        Vector3f temp = new Vector3f(secondRotation.getX(), secondRotation.getY(), secondRotation.getZ());
        secondRotation = coloredUIObject.getRotation();
        coloredUIObject.setRotation(temp);
    }
}
