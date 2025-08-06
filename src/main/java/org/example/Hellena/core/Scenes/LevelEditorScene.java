package org.example.Hellena.core.Scenes;

import org.example.Hellena.core.Camera;
import org.example.Hellena.core.GameObject;
import org.example.Hellena.core.components.Sprite;
import org.example.Hellena.core.components.SpriteRenderer;
import org.example.Hellena.core.components.SpriteSheet;
import org.example.Hellena.core.components.Transform;
import org.example.Hellena.core.util.AssetPool;
import org.joml.Vector2f;

public class LevelEditorScene extends Scene {

    public LevelEditorScene() {

    }

    @Override
    public void init() {
        loadResources();

        setName("LevelEditorScene");
        this.camera = new Camera(new Vector2f());

        SpriteSheet sprite = AssetPool.getSpriteSheet("assets/textures/spritesheet.png");

        GameObject obj1 = new GameObject("Obj 1", new Transform(new Vector2f(100,100), new Vector2f(256,256)));
        obj1.addComponent(new SpriteRenderer(sprite.getSprite(0)));
        this.addGameObjectToScene(obj1);

        GameObject obj2 = new GameObject("Obj 2", new Transform(new Vector2f(400,100), new Vector2f(256,256)));
        obj2.addComponent(new SpriteRenderer(sprite.getSprite(15)));
        this.addGameObjectToScene(obj2);
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpriteSheet("assets/textures/spritesheet.png", new SpriteSheet(AssetPool.getTexture("assets/textures/spritesheet.png"), 16, 16, 26, 0));
    }

    @Override
    public void update(float delta) {
        for (GameObject go : this.gameObjects) {
            go.update(delta);
        }

        this.renderer.render();
    }
}
