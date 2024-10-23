package com.tutorial;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Rock {
    public enum RockType {
        SMALL, MEDIUM, LARGE
    }

    public Sprite rockSprite;
    public Vector2 position;
    public float speed;
    public RockType rockType;
    public float rockSize;

    public Rock(TextureAtlas atlas, RockType rockType, float startX, float startY) {

        position = new Vector2(startX, startY);

        switch (rockType) {
            case SMALL:
                rockSprite = new Sprite(atlas.findRegion("smRock"));
                rockSize = 32;
                rockSprite.setSize(32, 32);
                break;
            case MEDIUM:
                rockSprite = new Sprite(atlas.findRegion("medrock"));
                rockSize = 40;
                rockSprite.setSize(40, 40);
                break;
            case LARGE:
                rockSprite = new Sprite(atlas.findRegion("lrgrock"));
                rockSize = 60;
                rockSprite.setSize(60, 60);
                break;
        }
        rockSprite.setPosition(position.x, position.y);

        speed = 1000.0f / rockSize;
    }

    public void update(float deltaTime) {
        position.y -= speed * deltaTime;

        rockSprite.setPosition(position.x, position.y);
    }

    public void draw(SpriteBatch spriteBatch) {

        rockSprite.draw(spriteBatch);
    }

    public boolean isOffScrn() {
        return position.y + rockSprite.getHeight() < 0;

    }

}
