package com.example.appbanhang.service;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.appbanhang.R;

public class AnimationUtil {
    private static final int ANIMATION_DURUTION = 1000;
    private static boolean isAnimationStart;
    public static void translateAnimation(final ImageView viewAnimation
            , @NonNull final View startView, View endView
            , final Animation.AnimationListener animationListener){
        Resources resources = startView.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.shopping_cart);
        viewAnimation.setImageBitmap(bitmap);


        float startviewWidthCenter = startView.getWidth() / 2;
        float startViewHeightCenter = startView.getHeight()/2;

        float endviewWidthCenter = endView.getWidth() / 0.75f;
        float endViewHeightCenter = endView.getHeight()/2f;
        final int[] startPos = new int[2];
        startView.getLocationOnScreen(startPos);

        final int[] endPos = new int[2];
        endView.getLocationOnScreen(endPos);

        float fromX = startPos[0];
        float toX = endPos[0]+endviewWidthCenter - startviewWidthCenter;
        float fromY = startPos[1] -startViewHeightCenter;
        float toY = endPos[1] - endViewHeightCenter + startViewHeightCenter;

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new AccelerateInterpolator());

        int animationDuration = 200;

        ScaleAnimation startScaleAnimation = new ScaleAnimation(1.0f,1.5f,1.0f,1.5f,startviewWidthCenter,startViewHeightCenter);
        startScaleAnimation.setDuration(animationDuration);

        TranslateAnimation translateAnimation = new TranslateAnimation(fromX,toX,fromY,toY);
        translateAnimation.setStartOffset(animationDuration);
        translateAnimation.setDuration(ANIMATION_DURUTION);
        
        ScaleAnimation translateScaleAnimation = new ScaleAnimation(1.0f,0.0f,1.0f,0.0f,toX,toY);
        translateScaleAnimation.setStartOffset(animationDuration);
        translateScaleAnimation.setDuration(ANIMATION_DURUTION);

        animationSet.addAnimation(startScaleAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(translateScaleAnimation);

        if(isAnimationStart){
            viewAnimation.clearAnimation();
            if(animationListener != null){
                animationListener.onAnimationEnd(null);
            }
        }

        viewAnimation.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimationStart = true;

                viewAnimation.setVisibility(View.VISIBLE);

                if(animationListener != null){
                    animationListener.onAnimationStart(animation);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewAnimation.setVisibility(View.GONE);

                if(animationListener != null){
                    animationListener.onAnimationEnd(animation);
                }
                isAnimationStart = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if(animationListener != null){
                    animationListener.onAnimationRepeat(animation);
                }
            }
        });
        


    }
}
