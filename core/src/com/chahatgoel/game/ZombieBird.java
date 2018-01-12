package com.chahatgoel.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class ZombieBird extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background;
    Texture ghost;
    Texture gameover;
    Texture toptube;
    Texture bottomtube;
    float ghostY = 0;
    float velocity = 0;
    int gameState = 0;
    float gap = 500;
    Random randomGenerator;
    float maxTubeOffSet;
    float tubeVelocity = 4;
    int noOfTubes = 4;
    float[] tubeX = new float[noOfTubes];
    float[] tubeOffSet = new float[noOfTubes];
    float distance;
    Circle ghostCircle;
    Rectangle[] topTubeRect;
    Rectangle[] bottomTubeRect;
    float gravity=2;


    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("bg1.jpg");
        ghost = new Texture("ghost.png");
        toptube = new Texture("spider1.png");
        bottomtube = new Texture("bottomtube.png");

        randomGenerator = new Random();
        ghostCircle = new Circle();
        gameover = new Texture("download.jpg");
        topTubeRect = new Rectangle[noOfTubes];
        bottomTubeRect = new Rectangle[noOfTubes];
        distance = Gdx.graphics.getWidth() * 3 / 4;

        startgame();


    }

    public void startgame() {
        ghostY = Gdx.graphics.getHeight() / 2 - ghost.getHeight() / 2;
        for (int i = 0; i < noOfTubes; i++) {
            tubeOffSet[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 300);
            tubeX[i] = Gdx.graphics.getWidth() / 2 - toptube.getWidth() / 2 + Gdx.graphics.getWidth() + i * distance;

            topTubeRect[i] = new Rectangle();
            bottomTubeRect[i] = new Rectangle();

        }
    }

    @Override
    public void render() {

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (gameState == 1) {


            if (Gdx.input.justTouched()) {
                velocity = -30;
            }

            for (int i = 0; i < noOfTubes; i++)
            {
                if (tubeX[i] < -toptube.getWidth())
                {
                    tubeX[i] += noOfTubes * distance;
                    tubeOffSet[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
                }
                else
                {
                    tubeX[i] = tubeX[i] - tubeVelocity;
                }
                batch.draw(toptube, tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffSet[i]);
                batch.draw(bottomtube, tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomtube.getHeight() + tubeOffSet[i]);
                topTubeRect[i] = new Rectangle(tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffSet[i], toptube.getWidth(), toptube.getHeight());
                bottomTubeRect[i] = new Rectangle(tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomtube.getHeight() + tubeOffSet[i], bottomtube.getWidth(), bottomtube.getHeight());
            }

            if (ghostY > 0)
            {


                velocity= velocity+gravity;
                ghostY -= velocity;
            }
            else
            {
                gameState = 2;
            }


        } else if (gameState == 0)
        {
            if (Gdx.input.justTouched())
            {
                gameState = 1;
            }
        }
        else if (gameState == 2) {

            batch.draw(gameover, Gdx.graphics.getWidth() / 2 - gameover.getWidth() / 2, Gdx.graphics.getHeight() / 2 - gameover.getHeight() / 2);

            if (Gdx.input.justTouched()) {

                gameState = 1;
                startgame();

                velocity = 0;
            }
        }

            batch.draw(ghost, Gdx.graphics.getWidth() / 2 - ghost.getWidth() / 2, ghostY);


            ghostCircle.set(Gdx.graphics.getWidth() / 2, ghostY + ghost.getHeight() / 2, ghost.getWidth() / 2);



            for (int i = 0; i < noOfTubes; i++)
            {


                if (Intersector.overlaps(ghostCircle, topTubeRect[i]) || Intersector.overlaps(ghostCircle, bottomTubeRect[i]))
                {

                    Gdx.app.log("Collision", "Yes!");
                    gameState = 2;

                }

            }
            batch.end();


        }

    }

