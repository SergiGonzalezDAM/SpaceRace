package cat.xtec.ioc.objects;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import cat.xtec.ioc.helpers.AssetManager;
import cat.xtec.ioc.utils.Settings;


public class Shoot extends Actor
{
    private Vector2 position;
    private int width, height;
    private Rectangle collisionRect;
    Spacecraft space;

    public Shoot(float x, float y, int width, int height)
    {
        // Inicialitzem els arguments segons la crida del constructor
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        // Creem el rectangle de col·lisions
        collisionRect = new Rectangle();

        // Per a la gestio de hit
        setBounds(position.x, position.y, this.width, this.height);
        setTouchable(Touchable.enabled);

    }
    public void act(float delta)
    {
        this.position.x += Settings.VELOCITYBULLET*delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.bullet, position.x+38, position.y+5, 5, 5);
    }

    public float getX() {
        return position.x;
    }

    public Rectangle getCollisionRect() {
        return collisionRect;
    }
}
