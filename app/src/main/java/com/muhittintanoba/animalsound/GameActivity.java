package com.muhittintanoba.animalsound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

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
    String[] animalNames = {
            "dog",
            "cat",
            "bird",
            "bear",
            "cow",
            "chick",
            "donkey",
            "duck",
            "elephant",
            "frog",
            "horse",
            "lion",
            "pig",
            "rooster",
            "sheep",
            "tiger",
            "alligator",
            "amazonmacaw",
            "bee",
            "bison",
            "bull",
            "canarybird",
            "chipmunks",
            "cows",
            "coyote",
            "cricket",
            "crickets",
            "crows",
            "elephant",
            "elk",
            "finch",
            "fox",
            "geese",
            "geier",
            "goat",
            "gorilla",
            "grasshopper",
            "jay",
            "kapuzineraffe",
            "kingcobra",
            "lamb",
            "lapwing",
            "leopard",
            "loewen",
            "mockingbird",
            "moewe",
            "mosquito",
            "nightingale",
            "osprey",
            "owl",
            "peacock",
            "pigeons",
            "pony",
            "puma",
            "rattlesnake",
            "raven",
            "redlori",
            "redparrot",
            "rhinozerus",
            "rooster",
            "schimpanse",
            "snowyowl",
            "sparrow",
            "squirrel",
            "swallow",
            "tawnyfrogmouth",
            "turkey",
            "wolf",
            "woodpecker",
            "yellowrumpedwarbler"
    };
    ImageView image1, image2, image3, image4, heartImageView, correctImage, cross1, cross2, cross3, cross4, scoreImage;
    MediaPlayer music, correctSound, wrongSound;
    TextView scoreText, bestScoreText, healthText, englishNames;
    private InterstitialAd mInterstitialAd;
    List<Integer> playedSounds = new ArrayList<Integer>();
    int randomVoice, randomForFirst, randomForSecond, randomForThird, randomForFourth,
            bestScore, health = 10, score = 0, clueLife = 3;
    SharedPreferences sharedPreferences;
    Animation heartBeatAnimation, marksAnimation;
    Handler handler;
    private RewardedAd mRewardedAd, mClueRewardAd;
    boolean isWrongAnswer = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initAdmob();
        loadIntersititial();
        loadRewardedAd();
        loadClueRewardedAd();

        heartImageView = findViewById(R.id.heartImageView);
        heartBeatAnimation = AnimationUtils.loadAnimation(this, R.anim.heart_beat);
        scoreText = (TextView) findViewById(R.id.scoreText);
        bestScoreText = (TextView) findViewById(R.id.bestScoreText);
        healthText = (TextView) findViewById(R.id.healthText);
        healthText.setText(Integer.toString(health));
        scoreImage = findViewById(R.id.scoreImageView);
        englishNames = (TextView) findViewById(R.id.englishNames);
        englishNames.setVisibility(View.INVISIBLE);

        correctSound = MediaPlayer.create(GameActivity.this, R.raw.positive_sound);
        wrongSound = MediaPlayer.create(GameActivity.this, R.raw.negative_beeps);
        marksAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        sharedPreferences= this.getSharedPreferences("com.muhittintanoba.animalsound", Context.MODE_PRIVATE);
        bestScore = sharedPreferences.getInt("bestScore", 0);
        bestScoreText.setText("Best: " + bestScore);


        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        cross1 = (ImageView) findViewById(R.id.cross1);
        cross2 = (ImageView) findViewById(R.id.cross2);
        cross3 = (ImageView) findViewById(R.id.cross3);
        cross4 = (ImageView) findViewById(R.id.cross4);

        changeScoreImage();
        cross4.bringToFront();
        cross3.bringToFront();
        cross2.bringToFront();
        cross1.bringToFront();
        changeImage();
        changeSound();

        heartBeatAnim(600,300, heartImageView);
        heartBeatAnim(600,300, healthText);

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
            isWrongAnswer = false;
            score += 1;
            changeScoreImage();
            scoreText.setText("Score: "+ score);
            if(bestScore<=score) {
                bestScore = score;
                sharedPreferences.edit().putInt("bestScore", bestScore).apply();
                bestScoreText.setText("Best: " + bestScore);
            };

            if(playedSounds.size() < 10){
                playedSounds.add(index);
            }else{
                playedSounds.remove(0);
                playedSounds.add(index);
            }
            correctSound.start();
            correctMark();
            changeEnglishNames();


            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            Handler handlerCorrectAnswer = new Handler();
            handlerCorrectAnswer.postDelayed(new Runnable() {
                public void run() {
                    music.pause();
                    correctSound.pause();
                    changeImage();
                    changeSound();
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    isWrongAnswer = true;
                    englishNames.setVisibility(View.INVISIBLE);
                }
            }, 3000);

        }else{
            health -= 1 ;
            if(health == 0 ) score = 0;
            healthText.setText(Integer.toString(health));
            wrongSound.start();
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
            heartBeatAnim(300, 150, heartImageView);
            heartBeatAnim(300, 150, healthText);
        }
        if(health <= 0 & mInterstitialAd != null){
            mInterstitialAd.show(GameActivity.this);
            health += 3;
            healthText.setText(Integer.toString(health));
            mInterstitialAd = null;
            loadIntersititial();
        }else if(mInterstitialAd == null) {
            Log.i("Admob", "Ad dismiss");
        }
    }
    public void showAnswer(View view) {
        if (mClueRewardAd != null & clueLife == 0) {
            Activity activityContext = GameActivity.this;
            mClueRewardAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    Log.d("AdmobR", "The user earned the reward.");
                    int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();
                    clueLife += rewardAmount;
                    mClueRewardAd = null;
                    loadClueRewardedAd();
                }
            });
        } else if(mClueRewardAd == null) {
            Log.d("AdmobR", "The rewarded ad wasn't ready yet.");
        }

        if(clueLife > 0){
            if(randomVoice == randomForFirst) clueAnimation(image1);
            else if(randomVoice == randomForSecond) clueAnimation(image2);
            else if(randomVoice == randomForThird) clueAnimation(image3);
            else if(randomVoice == randomForFourth) clueAnimation(image4);
            clueLife -= 1;
        }
    }
    public void playSound(View view){
        music.start();
    }

    public void changeScoreImage(){
        if(score < 10) {
            scoreImage.setImageResource(R.drawable.red_star_icon_original);
        } else if (score < 20 & score >= 10) {
            scoreImage.setImageResource(R.drawable.white_star_icon_original);
        } else if (score >= 20) {
            scoreImage.setImageResource(R.drawable.yellow_star_icon_original);
        }
    }

    public void changeEnglishNames(){
        englishNames.setVisibility(View.VISIBLE);
        String nameOfAnimal = animalNames[randomVoice].toUpperCase();
        englishNames.setText(nameOfAnimal);
    }



    public void clickedFirstImage(View view){
        checkVoice(randomForFirst);
        if(isWrongAnswer) {
            cross1.setImageResource(R.drawable.cross_out);
            cross1.startAnimation(marksAnimation);
            cross1.setVisibility(View.VISIBLE);
        }
    }
    public void clickedSecondImage(View view){
        checkVoice(randomForSecond);
        if(isWrongAnswer) {
            cross2.setImageResource(R.drawable.cross_out);
            cross2.startAnimation(marksAnimation);
            cross2.setVisibility(View.VISIBLE);
        }    }
    public void clickedThirdImage(View view){
        checkVoice(randomForThird);
        if(isWrongAnswer) {
            cross3.setImageResource(R.drawable.cross_out);
            cross3.startAnimation(marksAnimation);
            cross3.setVisibility(View.VISIBLE);
        }    }
    public void clickedFourthImage(View view){
        checkVoice(randomForFourth);
        if(isWrongAnswer) {
            cross4.setImageResource(R.drawable.cross_out);
            cross4.startAnimation(marksAnimation);
            cross4.setVisibility(View.VISIBLE);
        }    }
    public void correctMark(){
        if(randomVoice == randomForFirst) {
            cross1.setImageResource(R.drawable.check_mark);
            cross1.startAnimation(marksAnimation);
            cross1.setVisibility(View.VISIBLE);

        }
        else if(randomVoice == randomForSecond) {
            cross2.setImageResource(R.drawable.check_mark);
            cross2.startAnimation(marksAnimation);
            cross2.setVisibility(View.VISIBLE);
        }
        else if(randomVoice == randomForThird) {
            cross3.setImageResource(R.drawable.check_mark);
            cross3.startAnimation(marksAnimation);
            cross3.setVisibility(View.VISIBLE);
        }
        else if(randomVoice == randomForFourth) {
            cross4.setImageResource(R.drawable.check_mark);
            cross4.startAnimation(marksAnimation);
            cross4.setVisibility(View.VISIBLE);
        }
        Handler setVisibilityHandler = new Handler();
        setVisibilityHandler.postDelayed(new Runnable() {
            public void run() {
                cross1.setVisibility(View.INVISIBLE);
                cross2.setVisibility(View.INVISIBLE);
                cross3.setVisibility(View.INVISIBLE);
                cross4.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }
    public void gameOver(){
        if(health <= 0){
            score = 0;
            scoreText.setText("Score: "+ score);


        }
    }



    // ------------------ ANIMATION ------------------
    public void heartBeatAnim(int beatSpeed, int durationTime, View viewAnim){
        int newDuration = durationTime;
        heartBeatAnimation.setDuration(newDuration);

        // Animasyonu görünüme uygula
        viewAnim.startAnimation(heartBeatAnimation);

        handler = new Handler();
        final int delay = beatSpeed;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewAnim.startAnimation(heartBeatAnimation);
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    public void clueAnimation(ImageView imageView){
        correctImage = imageView;
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_rotate);
        correctImage.startAnimation(animation);
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
    public void loadRewardedAd(){
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(getApplicationContext(), "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        Log.d("AdmobR", loadAdError.toString());
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        super.onAdLoaded(rewardedAd);
                        mRewardedAd = rewardedAd;
                        Log.i("AdmobR", "Ad was loaded");
                    }
                });

        if(mRewardedAd != null){
            mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                    Log.i("AdmobR", "Ad was clicked");
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    Log.d("AdmobR", "Ad dissmissed");
                    mRewardedAd = null;
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    Log.d("AdmobR", "Ad failed to show");
                    mRewardedAd = null;
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                    Log.d("AdmobR", "ad recorded an impression");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                    Log.d("AdmobR", "ad showed fullscreen content");
                }
            });
        }
    }
    public void showRewardedAd(View view){
        if (mRewardedAd != null) {
            Activity activityContext = GameActivity.this;
            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    Log.d("AdmobR", "The user earned the reward.");
                    int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();
                    health += rewardAmount;
                    healthText.setText(Integer.toString(health));
                    handler.removeCallbacksAndMessages(null);
                    heartBeatAnim(600,300, heartImageView);
                    heartBeatAnim(600,300, healthText);
                    mRewardedAd = null;
                    loadRewardedAd();
                }
            });
        } else {
            Log.d("AdmobR", "The rewarded ad wasn't ready yet.");
        }
    }
    public void loadClueRewardedAd(){
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(getApplicationContext(), "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        Log.d("AdmobR", loadAdError.toString());
                        mClueRewardAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        super.onAdLoaded(rewardedAd);
                        mClueRewardAd = rewardedAd;
                        Log.i("AdmobR", "Ad was loaded");
                    }
                });

        if(mClueRewardAd != null){
            mClueRewardAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                    Log.i("AdmobR", "Ad was clicked");
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    Log.d("AdmobR", "Ad dissmissed");
                    mClueRewardAd = null;
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    Log.d("AdmobR", "Ad failed to show");
                    mClueRewardAd = null;
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                    Log.d("AdmobR", "ad recorded an impression");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                    Log.d("AdmobR", "ad showed fullscreen content");
                }
            });
        }
    }


    // ------------------ BEST SCORE ------------------
    public void save(View view){
        sharedPreferences.edit().putInt("bestScore", score).apply();
    }



}
