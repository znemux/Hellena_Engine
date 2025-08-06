package org.example.Hellena.core.Scenes;

import org.example.Hellena.core.Camera;
import org.example.Hellena.core.GameObject;
import org.example.Hellena.core.components.SpriteRenderer;
import org.example.Hellena.core.components.SpriteSheet;
import org.example.Hellena.core.components.Transform;
import org.example.Hellena.core.util.AssetPool;
import org.joml.Vector2f;

public class LevelEditorScene extends Scene {

    private GameObject obj;

    private SpriteSheet sprites;

    public LevelEditorScene() {

    }

    @Override
    public void init() {
        loadResources();

        sprites = AssetPool.getSpriteSheet("assets/textures/spritesheet.png");

        setName("LevelEditorScene");
        this.camera = new Camera(new Vector2f());

        obj = new GameObject("Obj 1", new Transform(new Vector2f(100,100), new Vector2f(256,256)));
        obj.addComponent(new SpriteRenderer(sprites.getSprite(3)));
        this.addGameObjectToScene(obj);

        GameObject obj2 = new GameObject("Obj 2", new Transform(new Vector2f(400,100), new Vector2f(256,256)));
        obj2.addComponent(new SpriteRenderer(sprites.getSprite(15)));
        this.addGameObjectToScene(obj2);
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpriteSheet("assets/textures/spritesheet.png", new SpriteSheet(AssetPool.getTexture("assets/textures/spritesheet.png"), 16, 16, 26, 0));
    }


    private int spritIndex = 1;
    private float spriteFlipTime = 0.2f;
    private float spriteFlipTimeLeft = 0.0f;
    @Override
    public void update(float delta) {
        spriteFlipTimeLeft -= delta;
        if (spriteFlipTimeLeft <= 0) {
            spriteFlipTimeLeft = spriteFlipTime;
            spritIndex++;
            if (spritIndex > 3) {
                spritIndex = 1;
            }
            obj.getComponent(SpriteRenderer.class).setSprite(sprites.getSprite(spritIndex));
        }

        for (GameObject go : this.gameObjects) {
            go.update(delta);
        }

        this.renderer.render();
    }
}
