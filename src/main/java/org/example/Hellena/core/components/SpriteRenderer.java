package org.example.Hellena.core.components;

import org.joml.Vector4f;

public class SpriteRenderer extends Component{

    Vector4f color;

    public SpriteRenderer(Vector4f color) {
        this.color = color;
    }

    @Override
    public void start() {

    }

    @Override
    public void update(float delta) {

    }

    public Vector4f getColor() {
        return this.color;
    }
}
