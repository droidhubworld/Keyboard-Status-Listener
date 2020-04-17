package com.ak961.keyboardstatuslistener;

import android.app.Activity;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.lifecycle.LifecycleObserver;

public class KeyboardEventListener implements LifecycleObserver {
    Activity activity;
    KeyboardListener callback;
    View rootView;
    private Boolean lastState;

    public KeyboardEventListener(Activity activity, View rootView, KeyboardListener callback) {
        this.activity = activity;
        this.rootView = rootView;
        this.callback = callback;
        lastState = isKeyboardOpen();
        dispatchKeyboardEvent(lastState);
        registerKeyboardListener();
    }

    private void registerKeyboardListener() {
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(listener);
    }

    private void dispatchKeyboardEvent(Boolean isOpen) {
        if (isOpen) {
            callback.onKeyboardStatusChange(true);
        } else {
            callback.onKeyboardStatusChange(false);
        }
    }

    private ViewTreeObserver.OnGlobalLayoutListener listener = new ViewTreeObserver.OnGlobalLayoutListener() {

        @Override
        public void onGlobalLayout() {
            Boolean isOpen = isKeyboardOpen();
            if (isOpen == lastState) {
                return;
            } else {
                dispatchKeyboardEvent(isOpen);
                lastState = isOpen;
            }
        }
    };

    public void unregisterKeyboardListener() {
        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
    }

    private Boolean isKeyboardOpen() {
        Rect visibleBounds = new Rect();
        rootView.getWindowVisibleDisplayFrame(visibleBounds);
        int heightDiff = rootView.getHeight() - visibleBounds.height();
        int marginOfError = Math.round(this.convertDpToPx(50F));
        return heightDiff > marginOfError;
    }

    private Float convertDpToPx(Float dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                activity.getResources().getDisplayMetrics()
        );
    }

    public Boolean isKeyboardClosed() {
        return !this.isKeyboardOpen();
    }
}
