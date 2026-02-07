package com.devmob.languagepicker.managers;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;



public class DialogManager {

    private final Dialog dialog;
    private final Window window;

    // Default values
    private static final float DEFAULT_DIM = 0.6f;
    private static final int DEFAULT_BLUR_RADIUS = 20;
    // Optional overrides
    private Integer width = null;
    private Integer height = null;
    private Integer gravity = Gravity.CENTER;
    private Integer softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
    private Boolean cancelable = null;
    private Integer blurRadius = 20;
    private Float dimAmount = 0.6f;
    private Float elevation = 0f;

    private DialogManager(Dialog dialog) {
        this.dialog = dialog;
        this.window = dialog.getWindow();


    }

    // ENTRY POINT
    public static DialogManager with(Dialog dialog) {
        return new DialogManager(dialog);
    }

    // ---- Fluent setters ----

    public DialogManager setElevation(Float elevation) {
        this.elevation = elevation;
        return this;
    }

    public DialogManager setBlurRadius(Integer blurRadius) {
        this.blurRadius = blurRadius;
        return this;
    }

    public DialogManager setDimAmount(Float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    public DialogManager setCancelable(boolean value) {
        this.cancelable = value;
        return this;
    }

    public DialogManager setGravity(int value) {
        this.gravity = value;
        return this;
    }

    public DialogManager setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public DialogManager setSoftInputMode(int mode) {
        this.softInputMode = mode;
        return this;
    }




    // ---- APPLY ----
    public void apply() {
        if (dialog == null || window == null) return;

        // --- DEFAULTS ---


        if (blurRadius != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                window.addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                window.getAttributes().setBlurBehindRadius(blurRadius);
            }
        }

//        // --- OVERRIDES ---
        if (dimAmount != null) window.setElevation(dimAmount);
        if (elevation != null) window.setElevation(elevation);
        if (dimAmount != null) window.setDimAmount(dimAmount);
        if (cancelable != null) dialog.setCancelable(cancelable);
        if (gravity != null) window.setGravity(gravity);
        if (width != null && height != null) window.setLayout(width, height);
        if (softInputMode != null) window.setSoftInputMode(softInputMode);
    }

    public DialogManager setKeyboardListener(Activity activity, final KeyboardVisibilityListener listener) {
        if (activity == null) return this;
        final FrameLayout rootLayout = activity.findViewById(android.R.id.content);
        rootLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            boolean wasOpened = false;

            @Override
            public boolean onPreDraw() {
                Rect rect = new Rect();
                rootLayout.getWindowVisibleDisplayFrame(rect);
                int screenHeight = rootLayout.getHeight();
                int keypadHeight = screenHeight - rect.bottom;

                boolean isKeyboardOpen = keypadHeight > screenHeight * 0.15;
                if (isKeyboardOpen != wasOpened) {
                    wasOpened = isKeyboardOpen;
                    listener.onKeyboardVisibilityChanged(isKeyboardOpen);
                }
                return true;
            }
        });
        return this;
    }
    public interface KeyboardVisibilityListener {
        void onKeyboardVisibilityChanged(boolean isOpen);
    }


}
