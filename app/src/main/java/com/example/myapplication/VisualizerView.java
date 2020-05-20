package com.example.myapplication;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;


/**
 * {@link VisualizerView} is responsible for setting up and drawing the visualization of the music.
 */
public class VisualizerView extends View {
    Drawable drawable;
    Drawable wrappedDrawable;



    public VisualizerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setColor(Context context, String newColorKey) {

        @ColorInt
        int shapeColor;


        if (newColorKey.equals(getContext().getString(R.string.pref_color_blue_value))) {
            shapeColor = ContextCompat.getColor(getContext(), R.color.pref_color_blue_value);
            System.out.println("get Blue");
        } else if (newColorKey.equals(getContext().getString(R.string.pref_color_green_value))) {
            shapeColor = ContextCompat.getColor(getContext(), R.color.pref_color_green_value);
            System.out.println("get Green");
        } else {
            shapeColor = ContextCompat.getColor(getContext(), R.color.pref_color_red_value);
            System.out.println("get Red");
        }

        drawable = AppCompatResources.getDrawable(context, R.drawable.circle);
        wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable,shapeColor);
    }
}
