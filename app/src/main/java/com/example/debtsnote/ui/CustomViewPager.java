package com.example.debtsnote.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {

    private boolean isEnabled;

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.isEnabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isEnabled && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isEnabled && super.onInterceptTouchEvent(ev);
    }

    public boolean isPagingEnabled() {
        return isEnabled;
    }

    public void setPagingEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
