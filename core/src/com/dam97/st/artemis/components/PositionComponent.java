package com.dam97.st.artemis.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by dam97 on 2016-02-19.
 */
public class PositionComponent extends Component {
    public Vector3 position = new Vector3();

    public void setPosition(float x, float y, float z) {
        position.set(x, y, z);
    }
}
