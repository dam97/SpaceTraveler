package com.dam97.st.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

import java.util.UUID;

/**
 * Created by dam97 on 2016-02-07.
 */
public class UUIDComponent implements Component, Pool.Poolable{
    UUID uuid = UUID.randomUUID();

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void reset() {
        uuid = UUID.randomUUID();
    }
}
