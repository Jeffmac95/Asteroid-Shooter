package com.tutorial;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static com.badlogic.gdx.Gdx.gl;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public SpriteBatch spriteBatch;
    public TextureAtlas atlas;
    Player player;
    public Texture background;
    public Rock rock;
    public ArrayList<Rock> rocks;
    public float spawnTimer;
    public Random random;
    public Sprite bullet;
    public Vector2 bulletPosition;
    public ArrayList<Sprite> bullets;




    @Override
    public void create() {

        spriteBatch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("Atlas/game_atlas.atlas"));
        player = new Player(atlas);
        background = new Texture("starrybg.png");
        rocks = new ArrayList<>();
        spawnTimer = 0;
        random = new Random();
        bullet = new Sprite(new Texture("bullet.png"));
        bulletPosition = new Vector2();
        bullets = new ArrayList<>();

    }

    @Override
    public void render() {
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        float deltaTime = Gdx.graphics.getDeltaTime();

        spawnTimer += deltaTime;
        if (spawnTimer > 3.0f) {
            spawnRocks();
            spawnTimer = 0;
        }

        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);

        Iterator<Rock> iterator = rocks.iterator();
        while (iterator.hasNext()) {
            Rock rock = iterator.next();
            rock.update(deltaTime);
            rock.draw(spriteBatch);

            if (rock.isOffScrn()) {
                iterator.remove();
            }
        }

        Iterator<Sprite> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Sprite bullet = bulletIterator.next();
            bullet.setY(bullet.getY() + deltaTime * player.speed);

            if (bullet.getY() > HEIGHT) {
                bulletIterator.remove();
            } else {
                bullet.draw(spriteBatch);
            }
        }


        player.draw(spriteBatch);
        player.update(deltaTime, bullets);


        spriteBatch.end();

    }



    public void spawnRocks() {
        Rock.RockType rockType = Rock.RockType.values()[random.nextInt(Rock.RockType.values().length)];
        float startX = random.nextInt(Main.WIDTH);
        float startY = Main.HEIGHT - 20.0f;

        Rock newRock = new Rock(atlas, rockType, startX, startY);
        rocks.add(newRock);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        atlas.dispose();
        background.dispose();
    }
}
