package com.dam97.st.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.dam97.st.artemis.components.graphics.ColorComponent;
import com.dam97.st.artemis.components.graphics.SpriteComponent;
import com.dam97.st.artemis.components.graphics.TextureComponent;
import com.dam97.st.artemis.components.graphics.TransformComponent;

import java.util.Comparator;

/**
 * Created by dam97 on 2016-02-03.
 */
public class RenderingSystem extends IteratingSystem {
    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<TransformComponent> transformMapper;
    private ComponentMapper<SpriteComponent> spriteMapper;
    private ComponentMapper<ColorComponent> colorMapper;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Array<Integer> renderQueue;
    private Comparator<Integer> comparator;

    public RenderingSystem(SpriteBatch batch) {
        super(Aspect.all(TransformComponent.class).one(SpriteComponent.class, TextureComponent.class));
        /*textureMapper = ComponentMapper.getFor(TextureComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        spriteMapper = ComponentMapper.getFor(SpriteComponent.class);
        colorMapper = ComponentMapper.getFor(ColorComponent.class);*/

        renderQueue = new Array<Integer>();
        this.batch = batch;
        //camera = new OrthographicCamera(GameWorld.GAME_WIDTH, GameWorld.GAME_HEIGHT);
        //camera.position.x = GameWorld.GAME_WIDTH / 2;
        //camera.position.y = GameWorld.GAME_HEIGHT / 2;

        camera = new OrthographicCamera(480, 800);
        camera.position.x = 480 / 2;
        camera.position.y = 800 / 2;


        comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer entityA, Integer entityB) {
                return (int)Math.signum(transformMapper.get(entityB).ZIndex -
                        transformMapper.get(entityA).ZIndex);
            }
        };
    }

    @Override
    protected void process(int entity) {
        renderQueue.add(entity);
    }


    @Override
    protected void end() {
        super.end();
        renderQueue.sort(comparator);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int entity : renderQueue) {
            if (textureMapper.has(entity)) {
                if(!textureRegionDraw(entity)) continue;
            } else if (spriteMapper.has(entity)) {
                if(!spriteDraw(entity)) continue;
            }
        }
        batch.end();

        renderQueue.clear();
    }

    private boolean textureRegionDraw(int entity) {
        TextureComponent textureComponent = textureMapper.get(entity);

        if (textureComponent.textureRegion == null) {
            return false;
        }
        TransformComponent transformComponent = transformMapper.get(entity);
        float width = transformComponent.getBounds().width;
        float height = transformComponent.getBounds().height;
        float originX = transformComponent.getBounds().width * 0.5f;
        float originY = transformComponent.getBounds().height * 0.5f;
        batch.draw(textureComponent.textureRegion,
                transformComponent.position.x - originX, transformComponent.position.y - originY,
                originX, originY,
                width, height,
                transformComponent.scale.x, transformComponent.scale.y,
                MathUtils.radiansToDegrees * transformComponent.rotation);
        return true;
    }

    private boolean spriteDraw(int entity) {
        SpriteComponent spriteComponent = spriteMapper.get(entity);
        if(spriteComponent.sprite == null){
            return false;
        }
        Sprite sprite = spriteComponent.sprite;


        TransformComponent transformComponent = transformMapper.get(entity);
        float width = transformComponent.getBounds().width;
        float height = transformComponent.getBounds().height;
        float originX = transformComponent.getBounds().width * transformComponent.origin.x;
        float originY = transformComponent.getBounds().height * transformComponent.origin.y;
        sprite.setPosition(transformComponent.position.x - originX, transformComponent.position.y - originY);
        sprite.setOrigin(originX, originY);
        sprite.setSize(width, height);
        sprite.setScale(transformComponent.scale.x, transformComponent.scale.y);
        sprite.setRotation(MathUtils.radiansToDegrees * transformComponent.rotation);
        if(colorMapper.has(entity)){
            sprite.setColor(colorMapper.get(entity).color);
        }
        sprite.draw(batch);
        return true;
    }
}
