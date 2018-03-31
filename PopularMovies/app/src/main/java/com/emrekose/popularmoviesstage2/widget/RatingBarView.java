package com.emrekose.popularmoviesstage2.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.emrekose.popularmoviesstage2.R;

public class RatingBarView extends View {

    private Paint roundPaint, roundProgressPaint, textPaint;
    private RectF ovalRect;
    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float textSize;
    private float roundWidth;
    private float progress;
    private int max;
    private String text;


    public RatingBarView(Context context) {
        super(context);
        init(context, null);
    }

    public RatingBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RatingBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RatingBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RatingBarView);
        roundColor = a.getColor(R.styleable.RatingBarView_roundColor, Color.GRAY);
        roundProgressColor = a.getColor(R.styleable.RatingBarView_roundProgressColor, Color.BLACK);
        roundWidth = a.getDimension(R.styleable.RatingBarView_roundWidth, 10);
        text = a.getString(R.styleable.RatingBarView_text);
        textColor = a.getColor(R.styleable.RatingBarView_textColor, Color.BLACK);
        textSize = a.getDimension(R.styleable.RatingBarView_textSize, 18);
        max = a.getInt(R.styleable.RatingBarView_max, 100);

        a.recycle();

        ovalRect = new RectF();

        roundPaint = new Paint();
        roundPaint.setColor(roundColor);
        roundPaint.setStyle(Paint.Style.STROKE);
        roundPaint.setStrokeWidth(roundWidth);
        roundPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setStrokeWidth(0);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);

        roundProgressPaint = new Paint();
        roundProgressPaint.setColor(roundProgressColor);
        roundProgressPaint.setStyle(Paint.Style.STROKE);
        roundProgressPaint.setStrokeWidth(roundWidth);
        roundProgressPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        int radius = (int) (cx - roundWidth / 2);

        canvas.drawCircle(cx, cy, radius, roundPaint);

        float textWidth = textPaint.measureText(text);
        canvas.drawText(text, cx - textWidth / 2, cy + textSize / 2, textPaint);

        ovalRect.set(cx - radius, cy - radius, cx + radius, cy + radius);
        canvas.drawArc(ovalRect, 270, 360 * progress / max, false, roundProgressPaint);
    }

    public void setText(String text) {
        this.text = text;

        postInvalidate();
    }

    public void setProgress(float progress) {
        this.progress = progress * 10;

        postInvalidate();
    }
}
