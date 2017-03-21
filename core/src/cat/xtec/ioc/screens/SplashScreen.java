package cat.xtec.ioc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.FloatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import cat.xtec.ioc.SpaceRace;
import cat.xtec.ioc.helpers.AssetManager;
import cat.xtec.ioc.utils.Settings;


public class SplashScreen implements Screen {

    private Stage stage;

    private SpaceRace game;

    private Label.LabelStyle textStyle;
    private Label textLbl;
    Container dFacil;
    Container dNormal;
    Container dHard;
    private Label easy;
    private Label normal;
    private Label hard;

    public SplashScreen(SpaceRace game) {

        this.game = game;

        // Creem la càmera de les dimensions del joc
        OrthographicCamera camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        // Posant el paràmetre a true configurem la càmera per a
        // que faci servir el sistema de coordenades Y-Down
        camera.setToOrtho(true);

        // Creem el viewport amb les mateixes dimensions que la càmera
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);

        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport);

        // Afegim el fons
        stage.addActor(new Image(AssetManager.background));

        // Creem l'estil de l'etiqueta i l'etiqueta
        textStyle = new Label.LabelStyle(AssetManager.font, null);
        textLbl = new Label("SpaceRace Game", textStyle);
        easy = new Label("Easy", textStyle);
        normal = new Label("Normal", textStyle);
        hard = new Label("Hard", textStyle);

        // Creem el contenidor necessari per aplicar-li les accions
        Container container = new Container(textLbl);
        dFacil = new Container(easy);
        dNormal = new Container(normal);
        dHard = new Container(hard);

        dFacil.setTransform(true);
        dNormal.setTransform(true);
        dHard.setTransform(true);
        container.setTransform(true);

        dFacil.center();
        dNormal.center();
        dHard.center();
        container.center();

        container.setPosition(Settings.GAME_WIDTH / 2, Settings.GAME_HEIGHT / 2);
        dFacil.setPosition(35,Settings.GAME_HEIGHT-20);
        dNormal.setPosition(Settings.GAME_WIDTH/2,Settings.GAME_HEIGHT-20);
        dHard.setPosition(Settings.GAME_WIDTH-35,Settings.GAME_HEIGHT-20);

        // Afegim les accions de escalar: primer es fa gran i després torna a l'estat original ininterrompudament
        container.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(Actions.scaleTo(1.5f, 1.5f, 1), Actions.scaleTo(1, 1, 1))));

        stage.addActor(dFacil);
        stage.addActor(dNormal);
        stage.addActor(dHard);
        stage.addActor(container);

        // Creem la imatge de la nau i li assignem el moviment en horitzontal
        Image spacecraft = new Image(AssetManager.spacecraft);
        float y = Settings.GAME_HEIGHT / 2 + textLbl.getHeight();
        spacecraft.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(Actions.moveTo(0 - spacecraft.getWidth(), y), Actions.moveTo(Settings.GAME_WIDTH, y, 5))));

        stage.addActor(spacecraft);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.draw();
        stage.act(delta);

        // Si es fa clic en la pantalla, canviem la pantalla
        /*if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(stage.getBatch(), stage.getViewport()));
            dispose();
        }*/
        //TODO tendriamos que hacer un vector con las posiciones de gdx.input.getX y getY, lo pongo de esta forma funcional
        //pero poco efectivo
        if(Gdx.input.isTouched())
        {
            if (Gdx.input.getX()/2 >= dFacil.getRight()-23 && Gdx.input.getX()/2 <= dFacil.getRight()+23)
            {
                Settings.SPACECRAFT_VELOCITY = 60;
                Settings.MAX_ASTEROID = 1.5f;
                Settings.MIN_ASTEROID = 0.5f;
                Settings.ASTEROID_SPEED = -150;
                game.setScreen(new GameScreen(stage.getBatch(), stage.getViewport()));
                dispose();
            }
            else if(Gdx.input.getX()/2 >= dNormal.getRight()-30&& Gdx.input.getX()/2 <= dNormal.getRight()+26)
            {
                Settings.SPACECRAFT_VELOCITY = 50;
                Settings.MAX_ASTEROID = 2f;
                Settings.MIN_ASTEROID = 0.5f;
                Settings.ASTEROID_SPEED = -125;
                game.setScreen(new GameScreen(stage.getBatch(), stage.getViewport()));
                dispose();
            }
            else if(Gdx.input.getX()/2 >= dHard.getRight()-23&& Gdx.input.getX()/2 <= dHard.getRight()+23)
            {
                Settings.SPACECRAFT_VELOCITY = 40;
                Settings.MAX_ASTEROID = 2.5f;
                Settings.MIN_ASTEROID = 0.5f;
                Settings.ASTEROID_SPEED = -100;
                game.setScreen(new GameScreen(stage.getBatch(), stage.getViewport()));
                dispose();
            }

        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
