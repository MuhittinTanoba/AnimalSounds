package com.muhittintanoba.animalsound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    int[] animalVoices = {
            R.raw.dog,
            R.raw.cat,
            R.raw.bird,
            R.raw.bear,
            R.raw.cow,
            R.raw.chick,
            R.raw.donkey,
            R.raw.duck,
            R.raw.elephant,
            R.raw.frog,
            R.raw.horse,
            R.raw.lion,
            R.raw.pig,
            R.raw.rooster,
            R.raw.sheep,
            R.raw.tiger,
            R.raw.alligator,
            R.raw.amazonmacaw,
            R.raw.bee,
            R.raw.bison,
            R.raw.bull,
            R.raw.canarybird,
            R.raw.chipmunks,
            R.raw.cows,
            R.raw.coyote,
            R.raw.cricket,
            R.raw.crickets,
            R.raw.crows,
            R.raw.elefant,
            R.raw.elk,
            R.raw.finch,
            R.raw.fox,
            R.raw.geese,
            R.raw.geier,
            R.raw.goat,
            R.raw.gorilla,
            R.raw.grasshopper,
            R.raw.jay,
            R.raw.kapuzineraffe,
            R.raw.kingcobra,
            R.raw.lamb,
            R.raw.lapwing,
            R.raw.leopard,
            R.raw.loewen,
            R.raw.mockingbird,
            R.raw.moewe,
            R.raw.mosquito,
            R.raw.nightingale,
            R.raw.osprey,
            R.raw.owl,
            R.raw.peacock,
            R.raw.pigeons,
            R.raw.pony,
            R.raw.puma,
            R.raw.rattlesnake,
            R.raw.raven,
            R.raw.redlori,
            R.raw.redparrot,
            R.raw.rhinozerus,
            R.raw.rooster,
            R.raw.schimpanse,
            R.raw.snowyowl,
            R.raw.sparrow,
            R.raw.squirrel,
            R.raw.swallow,
            R.raw.tawnyfrogmouth,
            R.raw.turkey,
            R.raw.wolf,
            R.raw.woodpecker,
            R.raw.yellowrumpedwarbler
    };

    int[] animalImages = {
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.bird,
            R.drawable.bear,
            R.drawable.cow,
            R.drawable.chick,
            R.drawable.donkey,
            R.drawable.duck,
            R.drawable.elephant,
            R.drawable.frog,
            R.drawable.horse,
            R.drawable.lion,
            R.drawable.pig,
            R.drawable.rooster,
            R.drawable.sheep,
            R.drawable.tiger,
            R.drawable.alligator,
            R.drawable.amazonmacaw,
            R.drawable.bee,
            R.drawable.bison,
            R.drawable.bull,
            R.drawable.canarybird,
            R.drawable.chipmunks,
            R.drawable.cows,
            R.drawable.coyote,
            R.drawable.cricket,
            R.drawable.crickets,
            R.drawable.crows,
            R.drawable.elefant,
            R.drawable.elk,
            R.drawable.finch,
            R.drawable.fox,
            R.drawable.geese,
            R.drawable.geier,
            R.drawable.goat,
            R.drawable.gorilla,
            R.drawable.grasshopper,
            R.drawable.jay,
            R.drawable.kapuzineraffe,
            R.drawable.kingcobra,
            R.drawable.lamb,
            R.drawable.lapwing,
            R.drawable.leopard,
            R.drawable.loewen,
            R.drawable.mockingbird,
            R.drawable.moewe,
            R.drawable.mosquito,
            R.drawable.nightingale,
            R.drawable.osprey,
            R.drawable.owl,
            R.drawable.peacock,
            R.drawable.pigeons,
            R.drawable.pony,
            R.drawable.puma,
            R.drawable.rattlesnake,
            R.drawable.raven,
            R.drawable.redlori,
            R.drawable.redparrot,
            R.drawable.rhinozerus,
            R.drawable.rooster,
            R.drawable.schimpanse,
            R.drawable.snowyowl,
            R.drawable.sparrow,
            R.drawable.squirrel,
            R.drawable.swallow,
            R.drawable.tawnyfrogmouth,
            R.drawable.turkey,
            R.drawable.wolf,
            R.drawable.woodpecker,
            R.drawable.yellowrumpedwarbler
    };
    ImageView image1, image2, image3, image4, heartImageView;
    MediaPlayer music;
    TextView scoreText, bestScoreText, healthText;
    private InterstitialAd mInterstitialAd;
    List<Integer> playedSounds = new ArrayList<Integer>();
    int randomVoice, randomForFirst, randomForSecond, randomForThird, randomForFourth, bestScore, health = 10, score = 0;
    SharedPreferences sharedPreferences;
    Animation heartBeatAnimation;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initAdmob();
        loadIntersititial();

        heartImageView = findViewById(R.id.heartImageView);
        heartBeatAnimation = AnimationUtils.loadAnimation(this, R.anim.heart_beat);
        scoreText = (TextView) findViewById(R.id.scoreText);
        bestScoreText = (TextView) findViewById(R.id.bestScoreText);
        healthText = (TextView) findViewById(R.id.healthText);
        healthText.setText(Integer.toString(health));

        sharedPreferences= this.getSharedPreferences("com.muhittintanoba.animalsound", Context.MODE_PRIVATE);
        bestScore = sharedPreferences.getInt("bestScore", 0);
        bestScoreText.setText("Best Score: " + bestScore);


        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);

        changeImage();
        changeSound();
        if (mInterstitialAd != null) {
            mInterstitialAd.show(GameActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }

        heartBeatAnim(600,300);

    }



    // ------------------ GAME ------------------
    public void changeImage(){
        do{
            randomForFirst = (int)Math.floor(Math.random() * (animalImages.length));
            randomForSecond = (int)Math.floor(Math.random() * (animalImages.length));
            randomForThird = (int)Math.floor(Math.random() * (animalImages.length));
            randomForFourth = (int)Math.floor(Math.random() * (animalImages.length));
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
    public void changeSound(){
        // Choosing random voice
        do{
            randomVoice = (int)Math.floor(Math.random() * (animalImages.length));
        } while(randomForFirst!=randomVoice
                & randomForSecond!=randomVoice
                & randomForThird!=randomVoice
                & randomForFourth!=randomVoice);
        music = MediaPlayer.create(GameActivity.this, animalVoices[randomVoice]);
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
            music.pause();
            changeImage();
            changeSound();
        }else{
            health -=1 ;
            score = 0;
            scoreText.setText("Score: "+ score);
            healthText.setText(Integer.toString(health));
            checkHeathAndShowAd();
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
    public void checkHeathAndShowAd(){
        if(health <= 4){
            handler.removeCallbacksAndMessages(null);
            heartBeatAnim(300, 150);
        }
        if(health <= 0 & mInterstitialAd != null){
            mInterstitialAd.show(GameActivity.this);
            health += 3;
            healthText.setText(Integer.toString(health));
        }else if(mInterstitialAd == null) {
            Log.i("Admob", "Ad dismiss");
        }
    }
    public void playSound(View view){
        music.start();
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




    // ------------------ ANIMATION ------------------
    public void heartBeatAnim(int beatSpeed, int durationTime){
        int newDuration = durationTime;
        heartBeatAnimation.setDuration(newDuration);

        // Animasyonu görünüme uygula
        heartImageView.startAnimation(heartBeatAnimation);

        handler = new Handler();
        final int delay = beatSpeed;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                heartImageView.startAnimation(heartBeatAnimation);
                handler.postDelayed(this, delay);
            }
        }, delay);
    }


    // ------------------ IMAGE RESIZER ------------------
    public Bitmap convertToBitmap(int image){
        Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(),image);
        return resizeImage(bitmapImage, 100);
    }
    public Bitmap resizeImage (@NonNull Bitmap image, int maximumSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        if(width <= 0 || height <= 0 ) System.out.println("TESPİT");

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



    // ------------------ ADMOB ------------------

    public void initAdmob(){
        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                Log.i("Admob","onInitializationComplete");
            }
        });

    }
    public void loadIntersititial() {

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(getApplicationContext(), "ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        super.onAdLoaded(interstitialAd);
                        Log.i("Admob", "onAdLoaded");
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        Log.i("Admob", loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });
        if (mInterstitialAd != null) {
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    Log.d("Admob", "Ad dismissed fullscreen content.");
                    mInterstitialAd = null;
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    Log.e("Admob", "Ad failed to show fullscreen content.");
                    mInterstitialAd = null;
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                    Log.d("Admob", "Ad showed fullscreen content.");
                }
            });
        }

    }



    // ------------------ BEST SCORE ------------------
    public void save(View view){
        sharedPreferences.edit().putInt("bestScore", score).apply();
    }



}
