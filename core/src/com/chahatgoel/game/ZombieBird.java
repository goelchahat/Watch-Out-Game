package com.chahatgoel.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class ZombieBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture bird;
	Texture toptube;
	Texture bottomtube;
	float birdY=0;
	float velocity =0;
	int gameState=0;
	float gap=400;
	Random randomGenerator ;
	float maxTubeOffSet;
	float tubeVelocity=4;
	int noOfTubes=4;
	float[] tubeX= new float[noOfTubes];
	float[] tubeOffSet= new float[noOfTubes];
	float distance;




	@Override
	public void create() {
		batch = new SpriteBatch();
		background = new Texture("bg.jpg");
		bird = new Texture("bird.png");
		toptube= new Texture("toptube.png");
		bottomtube= new Texture("bottomtube.png");
		birdY= Gdx.graphics.getHeight()/2-bird.getHeight()/2;
		randomGenerator = new Random();
		distance = Gdx.graphics.getWidth()*3/4;
		for (int i=0;i< noOfTubes;i++)
        { tubeOffSet[i]= (randomGenerator.nextFloat()-0.5f )*(Gdx.graphics.getHeight()-gap-200);
        tubeX[i]= Gdx.graphics.getWidth()/2-toptube.getWidth()/2+Gdx.graphics.getWidth() + i * distance;





        }}

	@Override
	public void render() {

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if(gameState!=0)
        {


            if(Gdx.input.justTouched())
            {velocity=-30;}

            for (int i=0;i<noOfTubes;i++)
            {
                if(tubeX[i]<-toptube.getWidth())
                {
                tubeX[i] += noOfTubes * distance;
                tubeOffSet[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
            }
            else
            {
                tubeX[i]=  tubeX[i]- tubeVelocity;
            }
                batch.draw(toptube,tubeX[i],Gdx.graphics.getHeight()/2+gap/2+tubeOffSet[i]);
                batch.draw(bottomtube,tubeX[i],Gdx.graphics.getHeight()/2-gap/2- bottomtube.getHeight()+tubeOffSet[i]);
            }

                if(birdY>0 || velocity<0)
            {


            velocity++;
	    birdY-=velocity;}

        }


	else {
            if(Gdx.input.justTouched())
            {
                gameState=1;
            }
        }

        batch.draw(bird,Gdx.graphics.getWidth()/2- bird.getWidth()/2,birdY);

        batch.end();


    }

}
