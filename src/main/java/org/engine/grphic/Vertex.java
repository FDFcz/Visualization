package org.engine.grphic;
import org.engine.maths.Vector2f;
import org.engine.maths.Vector3f;

public class Vertex {
    private Vector3f position,color;
    private Vector2f textureCoord;


    public Vector3f getPosition() {return position;}
    public Vector3f getColor() {return color;}
    public Vector2f getTextureCoord() {return textureCoord;}

    public Vertex(Vector3f position,Vector3f color,Vector2f textureCoord) {
        this.position = position;
        this.color = color;
        this.textureCoord = textureCoord;
    }
}
