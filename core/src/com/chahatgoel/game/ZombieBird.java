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
    Texture bird;
    Texture gameover;
    Texture toptube;
    Texture bottomtube;
    float birdY = 0;
    float velocity = 0;
    int gameState = 0;
    float gap = 400;
    Random randomGenerator;
    float maxTubeOffSet;
    float tubeVelocity = 6;
    int noOfTubes = 4;
    float[] tubeX = new float[noOfTubes];
    float[] tubeOffSet = new float[noOfTubes];
    float distance;
    Circle birdCircle;
    Rectangle[] topTubeRect;
    Rectangle[] bottomTubeRect;


    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("bg1.jpg");
        bird = new Texture("ghost.png");
        toptube = new Texture("toptube.png");
        bottomtube = new Texture("bottomtube.png");

        randomGenerator = new Random();
        birdCircle = new Circle();
        gameover = new Texture("download.jpg");
        topTubeRect = new Rectangle[noOfTubes];
        bottomTubeRect = new Rectangle[noOfTubes];
        distance = Gdx.graphics.getWidth() * 3 / 4;

        startgame();


    }

    public void startgame() {
        birdY = Gdx.graphics.getHeight() / 2 - bird.getHeight() / 2;
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

            if (birdY > 0)
            {


                velocity++;
                birdY -= velocity;
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

            batch.draw(bird, Gdx.graphics.getWidth() / 2 - bird.getWidth() / 2, birdY);


            birdCircle.set(Gdx.graphics.getWidth() / 2, birdY + bird.getHeight() / 2, bird.getWidth() / 2);



            for (int i = 0; i < noOfTubes; i++)
            {


                if (Intersector.overlaps(birdCircle, topTubeRect[i]) || Intersector.overlaps(birdCircle, bottomTubeRect[i]))
                {

                    Gdx.app.log("Collision", "Yes!");
                    gameState = 2;

                }

            }
            batch.end();


        }

    }

