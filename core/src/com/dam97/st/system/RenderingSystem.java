package com.dam97.st.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.dam97.st.components.ColorComponent;
import com.dam97.st.components.SpriteComponent;
import com.dam97.st.components.TextureComponent;
import com.dam97.st.components.TransformComponent;

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

    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;

    public RenderingSystem(SpriteBatch batch) {
        super(Family.all(TransformComponent.class).one(SpriteComponent.class, TextureComponent.class).get());
        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        spriteMapper = ComponentMapper.getFor(SpriteComponent.class);
        colorMapper = ComponentMapper.getFor(ColorComponent.class);

        renderQueue = new Array<Entity>();

        this.batch = batch;

        //camera = new OrthographicCamera(GameWorld.GAME_WIDTH, GameWorld.GAME_HEIGHT);
        //camera.position.x = GameWorld.GAME_WIDTH / 2;
        //camera.position.y = GameWorld.GAME_HEIGHT / 2;

        camera = new OrthographicCamera(480, 800);
        camera.position.x = 480 / 2;
        camera.position.y = 800 / 2;


        comparator = new Comparator<Entity>() {
            @Override
            public int compare(Entity entityA, Entity entityB) {
                return (int)Math.signum(transformMapper.get(entityB).getZIndex() -
                        transformMapper.get(entityA).getZIndex());
            }
        };
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        renderQueue.sort(comparator);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (Entity entity : renderQueue) {
            if (textureMapper.has(entity)) {
                if(!textureRegionDraw(entity)) continue;
            } else if (spriteMapper.has(entity)) {
                if(!spriteDraw(entity)) continue;
            }
        }
        batch.end();

        renderQueue.clear();
    }

    private boolean textureRegionDraw(Entity entity) {
        TextureComponent textureComponent = textureMapper.get(entity);

        if (textureComponent.getTextureRegion() == null) {
            return false;
        }
        TransformComponent transformComponent = transformMapper.get(entity);
        float width = transformComponent.getBounds().width;
        float height = transformComponent.getBounds().height;
        float originX = transformComponent.getBounds().width * 0.5f;
        float originY = transformComponent.getBounds().height * 0.5f;
        batch.draw(textureComponent.getTextureRegion(),
                transformComponent.getPosition().x - originX, transformComponent.getPosition().y - originY,
                originX, originY,
                width, height,
                transformComponent.getScale().x, transformComponent.getScale().y,
                MathUtils.radiansToDegrees * transformComponent.getRotation());
        return true;
    }

    private boolean spriteDraw(Entity entity) {
        SpriteComponent spriteComponent = spriteMapper.get(entity);
        if(spriteComponent.getSprite() == null){
            return false;
        }
        Sprite sprite = spriteComponent.getSprite();


        TransformComponent transformComponent = transformMapper.get(entity);
        float width = transformComponent.getBounds().width;
        float height = transformComponent.getBounds().height;
        float originX = transformComponent.getBounds().width * transformComponent.getOrigin().x;
        float originY = transformComponent.getBounds().height * transformComponent.getOrigin().y;
        sprite.setPosition(transformComponent.getPosition().x - originX, transformComponent.getPosition().y - originY);
        sprite.setOrigin(originX, originY);
        sprite.setSize(width, height);
        sprite.setScale(transformComponent.getScale().x, transformComponent.getScale().y);
        sprite.setRotation(MathUtils.radiansToDegrees * transformComponent.getRotation());
        if(colorMapper.has(entity)){
            sprite.setColor(colorMapper.get(entity).getColor());
        }
        sprite.draw(batch);
        return true;
    }
}
