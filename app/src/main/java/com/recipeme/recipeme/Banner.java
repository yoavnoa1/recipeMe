package com.recipeme.recipeme;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class Banner extends View
{
    private final Drawable logo;

    public Banner(Context context, Drawable logo)
    {
        super(context);
        this.logo = logo;
        setBackgroundDrawable(logo);
    }

    public Banner(Context context, AttributeSet attrs, Drawable logo)
    {
        super(context, attrs);
        this.logo = logo;
        setBackgroundDrawable(logo);
    }

    public Banner(Context context, AttributeSet attrs, int defStyle, Drawable logo)
    {
        super(context, attrs, defStyle);
        this.logo = logo;
        setBackgroundDrawable(logo);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec)
    {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width * logo.getIntrinsicHeight() / logo.getIntrinsicWidth();
        setMeasuredDimension(width, height);
    }
}