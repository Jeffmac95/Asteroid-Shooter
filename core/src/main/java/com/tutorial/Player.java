package com.tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;


class Player {
    public Sprite playerSprite; // 70x70
    public Vector2 position;
    public final float playerWidth = 70;
    public final float playerHeight = 70;
    public float speed = 250.0f;

    public Player(TextureAtlas atlas) {
        playerSprite = new Sprite(atlas.findRegion("rocket"));
        position = new Vector2(Main.WIDTH / 2 - playerWidth / 2, 0);
    }

    public void update(float deltaTime, ArrayList<Sprite> bullets) {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= deltaTime * speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += deltaTime * speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fireBullet(bullets);
        }

        if (position.x < 0) {
            position.x = Main.WIDTH - playerWidth;
        } else if (position.x > Main.WIDTH - playerWidth ) {
            position.x = 0;
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        playerSprite.setPosition(position.x, position.y);
        playerSprite.draw(spriteBatch);

    }

    public void fireBullet(ArrayList<Sprite> bullets) {
        Sprite newBullet = new Sprite(new Texture("bullet.png"));
        newBullet.setPosition(position.x, position.y + playerHeight);

        bullets.add(newBullet);
    }
}
