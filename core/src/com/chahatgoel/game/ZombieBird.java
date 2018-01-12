package com.chahatgoel.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ZombieBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture bird;
	Texture spider;
	Texture tube;
	float birdY=0;
	float velocity =0;
	int gameState=0;
	float gap=400;



	@Override
	public void create() {
		batch = new SpriteBatch();
		background = new Texture("bg.jpg");
		bird = new Texture("bird.png");
		spider= new Texture("spider.png");
		tube= new Texture("toptube.png");
		birdY= Gdx.graphics.getHeight()/2-bird.getHeight()/2;



	}

	@Override
	public void render() {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if(gameState!=0)
        {

            batch.draw(spider,Gdx.graphics.getWidth()/2-spider.getWidth()/2,Gdx.graphics.getHeight()/2+gap/2);
            batch.draw(tube,Gdx.graphics.getWidth()/2-tube.getWidth()/2,Gdx.graphics.getHeight()/2-gap/2- tube.getHeight());
            if(Gdx.input.justTouched())
            {velocity=-30;}
            if(birdY>0 || velocity<0)
            {


            velocity++;
	    birdY-=velocity;}}


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
