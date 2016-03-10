package com.dam97.st.artemis.components.graphics;

import com.artemis.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dam97 on 2016-02-07.
 * Used to rendering things on 2D screen.
 * Contains data for draw quad textures.
 */
public class TransformComponent extends Component {
    public final Vector2 position = new Vector2(0f, 0f);
    public final Vector2 size = new Vector2(0f, 0f);
    public final Vector2 scale = new Vector2(1.0f, 1.0f);
    public final Vector2 origin = new Vector2(0.5f, 0.5f);
    public int ZIndex = 0;
    public float rotation = 0.0f;

    public void setBounds(Rectangle rectangle) {
        this.position.x = rectangle.x + rectangle.width * 0.5f;
        this.position.y = rectangle.y + rectangle.height * 0.5f;
        this.size.x = rectangle.width;
        this.size.y = rectangle.height;
    }

    public Rectangle getBounds() {
        Rectangle rectangle = new Rectangle(position.x - size.x * 0.5f, position.y - size.y * 0.5f, size.x, size.y);
        return rectangle;
    }

}
