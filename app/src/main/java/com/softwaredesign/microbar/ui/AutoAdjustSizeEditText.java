package com.softwaredesign.microbar.ui;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.EditText;

/**
 * Created by mac on 16/6/6.
 */
/* 正在测试中... */
public class AutoAdjustSizeEditText extends EditText {

    private static final float DEFAULT_MIN_TEXT_SIZE = 8.0f;
    private static final float DEFAULT_MAX_TEXT_SIZE = 16.0f;

    private Paint textPaint;
    private float minTextSize = DEFAULT_MIN_TEXT_SIZE;
    private float maxTestSize = DEFAULT_MAX_TEXT_SIZE;

    public AutoAdjustSizeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void initialise() {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        if (this.textPaint == null) {
            this.textPaint = new Paint();
            this.textPaint.set(this.getPaint());
        }
        this.maxTestSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.maxTestSize, displayMetrics);
        if (DEFAULT_MIN_TEXT_SIZE >= maxTestSize) {
            this.maxTestSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.maxTestSize, displayMetrics);
        }
        this.maxTestSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.maxTestSize, displayMetrics);
        this.minTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.minTextSize, displayMetrics);
    }

    private void fitText(String text, int textWidth) {
        if (textWidth > 0) {
            int availableWidth = textWidth-this.getPaddingLeft()-this.getPaddingRight();
            float trySize = maxTestSize;
            textPaint.setTextSize(trySize);
            while((trySize > minTextSize) && (textPaint.measureText(text) > availableWidth)) {
                trySize -= 1;
                textPaint.setTextSize(trySize);
            }
            this.setTextSize(trySize);
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        this.initialise();
        String textString = text.toString();
        float trySize = maxTestSize;
        if (this.textPaint == null) {
            this.textPaint = new Paint();
            this.textPaint.set(this.getPaint());
        }
        this.textPaint.setTextSize(trySize);
        int textWidth = (int) this.textPaint.measureText(textString);
        this.fitText(textString, textWidth);
        Log.d("AutoAdjustSizeEditText", ""+this.getTextSize());
        super.setText(text, type);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        this.fitText(text.toString(), this.getWidth());
    }

}
