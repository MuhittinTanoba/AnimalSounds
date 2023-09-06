package com.muhittintanoba.animalsound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    int score = 0;

    ImageView image1, image2, image3, image4;
    MediaPlayer music;
    TextView scoreText;
    TextView bestScoreText;
    Bitmap myBitmap;


    int[] animalVoices = {R.raw.dog, R.raw.cat, R.raw.bird, R.raw.bear, R.raw.cow, R.raw.chick,
            R.raw.chicken, R.raw.donkey, R.raw.duck, R.raw.elephant, R.raw.frog, R.raw.goat,
            R.raw.horse, R.raw.lion, R.raw.pig, R.raw.rooster, R.raw.sheep, R.raw.tiger, R.raw.wolf};

    int[] animalImages = {R.drawable.dog, R.drawable.cat, R.drawable.bird, R.drawable.bear, R.drawable.cow, R.drawable.chick,
            R.drawable.chicken, R.drawable.donkey, R.drawable.duck, R.drawable.elephant, R.drawable.frog, R.drawable.goat,
            R.drawable.horse, R.drawable.lion, R.drawable.pig, R.drawable.rooster, R.drawable.sheep, R.drawable.tiger, R.drawable.wolf};

    List<Integer> playedSounds = new ArrayList<Integer>();

    int randomVoice, randomForFirst, randomForSecond, randomForThird, randomForFourth;

    SharedPreferences sharedPreferences;
    int bestScore;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        scoreText = (TextView) findViewById(R.id.scoreText);
        bestScoreText = (TextView) findViewById(R.id.bestScoreText);

        sharedPreferences= this.getSharedPreferences("com.muhittintanoba.animalsound", Context.MODE_PRIVATE);
        bestScore = sharedPreferences.getInt("bestScore", 0);
        bestScoreText.setText("Best Score: " + bestScore);


        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);

        changeImage();
        changeSound();

    }

    public void save(View view){
        sharedPreferences.edit().putInt("bestScore", score).apply();
    }

    public void changeImage(){
        do{
            randomForFirst = (int)Math.floor(Math.random() * (19));
            randomForSecond = (int)Math.floor(Math.random() * (19));
            randomForThird = (int)Math.floor(Math.random() * (19));
            randomForFourth = (int)Math.floor(Math.random() * (19));
        }while (randomForFirst == randomForSecond ||
                randomForFirst == randomForThird ||
                randomForSecond == randomForThird ||
                randomForFourth == randomForThird ||
                randomForFourth == randomForSecond ||
                randomForFourth == randomForFirst ||
        checkArray(randomForFirst, randomForSecond, randomForThird, randomForFourth, playedSounds));

        image1.setImageBitmap(convertToBitmap(animalImages[randomForFirst]));
        image2.setImageBitmap(convertToBitmap(animalImages[randomForSecond]));
        image3.setImageBitmap(convertToBitmap(animalImages[randomForThird]));
        image4.setImageBitmap(convertToBitmap(animalImages[randomForFourth]));


    }

    public Bitmap convertToBitmap(int image){
        Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(),image);
        return resizeImage(bitmapImage, 100);
    }
    public Bitmap resizeImage (Bitmap image, int maximumSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitMapRatio = width/height;

        if(bitMapRatio > 1) {
            width = maximumSize;
            height = (int) (width / bitMapRatio);
        }else {
            height = maximumSize;
            width = (int) (height * bitMapRatio);
        }

        return image.createScaledBitmap(image, width, 100, true);
    }

    public void changeSound(){
        // Choosing random voice
        do{
            randomVoice = (int)Math.floor(Math.random() * (19));
        } while(randomForFirst!=randomVoice
                & randomForSecond!=randomVoice
                & randomForThird!=randomVoice
                & randomForFourth!=randomVoice);
        music = MediaPlayer.create(GameActivity.this, animalVoices[randomVoice]);
    }

    public void playSound(View view){
        music.start();
    }

    public void checkVoice(int index){
        if(randomVoice == index){
            score += 1;
            scoreText.setText("Score: "+ score);

            if(bestScore<=score) {
                bestScore = score;
                sharedPreferences.edit().putInt("bestScore", bestScore).apply();
                bestScoreText.setText("Best Score: " + bestScore);
            };

            if(playedSounds.size() < 10){
                playedSounds.add(index);
            }else{
                playedSounds.remove(0);
                playedSounds.add(index);
            }
            changeImage();
            changeSound();
        }else{
            score = 0;
            scoreText.setText("Score: "+ score);
        }
    }


    public boolean checkArray(int first, int second, int third, int fourth, @NonNull List<Integer> array){
        if(array.contains(first)
                || array.contains(second)
                || array.contains(third)
                || array.contains(fourth)){
            return true;
        }
        return false;
    }

    public void clickedFirstImage(View view){
        checkVoice(randomForFirst);
    }


    public void clickedSecondImage(View view){
        checkVoice(randomForSecond);
    }

    public void clickedThirdImage(View view){
        checkVoice(randomForThird);
    }

    public void clickedFourthImage(View view){
        checkVoice(randomForFourth);
    }



    }
