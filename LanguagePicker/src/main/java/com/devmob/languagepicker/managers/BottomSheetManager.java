package com.devmob.languagepicker.managers;

import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.shape.MaterialShapeDrawable;

public class BottomSheetManager {

    private BottomSheetDialog dialog;
    private Fragment fragment;
    private InsetsListener insetsListener;
    private KeyboardVisibilityListener keyboardListener;

    // Defaults
    private static final float DEFAULT_DIM = 0.3f;
    private static final int DEFAULT_GRAVITY = Gravity.BOTTOM;
    private static final int DEFAULT_BLUR_RADIUS = 20;
    // Optional overrides
    private Boolean cancelable;
    private Boolean draggable;
    private Boolean hideable;
    private Boolean skipCollapsed;
    private Integer peekHeight;
    private Integer height;
    private Integer state;
    private Float dimAmount;
    private Integer softInputMode;

    private Integer blurRadius = 20;
    private Integer expandedOffset;
    private Float halfExpandedRatio; // 0-1f

    private Boolean fitToConstraints;
    private @ColorInt Integer navigationBarColor = null;


    private BottomSheetManager(@Nullable BottomSheetDialog dialog) {
        this.dialog = dialog;
    }
    private BottomSheetManager(@Nullable Fragment fragment) {
        this.fragment = fragment;
    }

    // ENTRY POINT
    public static BottomSheetManager with(@Nullable BottomSheetDialog dialog) {
        return new BottomSheetManager(dialog);
    }
    public static BottomSheetManager with(@Nullable Fragment fragment) {
        return new BottomSheetManager(fragment);
    }

    // ---- Fluent setters ----
    public BottomSheetManager setFitToConstraints(@NonNull boolean fitToConstraints) {
        this.fitToConstraints = fitToConstraints;
        return this;
    }
    public BottomSheetManager setExpandOffset(@NonNull int expandedOffset) {
        // leaves the px on top when expanded
        this.expandedOffset = expandedOffset;
        return this;
    }

    public BottomSheetManager setHalfExpandRatio(float halfExpandedRatio) {
        this.halfExpandedRatio = halfExpandedRatio;
        return this;
    }

    public BottomSheetManager attachFragment(@NonNull Fragment fragment) {
        this.fragment = fragment;
        return this;
    }

    public BottomSheetManager setCancelable(boolean value) {
        this.cancelable = value;
        return this;
    }


    public BottomSheetManager setDraggable(boolean value) {
        this.draggable = value;
        return this;
    }
    public BottomSheetManager setHideable(boolean value) {
        this.hideable = value;
        return this;
    }
    public BottomSheetManager setSkipCollapsed(boolean value) {
        this.skipCollapsed = value;
        return this;
    }

    public BottomSheetManager setPeekHeight(int heightPx) {
        this.peekHeight = heightPx;
        return this;
    }

    public BottomSheetManager setHeight(int heightPx) {
        this.height = heightPx;
        return this;
    }

    public BottomSheetManager setState(int state) {
        this.state = state;
        return this;
    }

    public BottomSheetManager setDimAmount(float amount) {
        this.dimAmount = amount;
        return this;
    }

    public BottomSheetManager setSoftInputMode(int mode) {
        this.softInputMode = mode;
        return this;
    }
    public BottomSheetManager setNavigationBarColor(@ColorInt int color) {
        this.navigationBarColor = color;
        return this;
    }
    public BottomSheetManager setOnApplyInsetsListener(InsetsListener listener) {
        this.insetsListener = listener;
        return this;
    }
    public BottomSheetManager setKeyboardListener(final KeyboardVisibilityListener listener) {
        this.keyboardListener = listener;
        return this;
    }

    public BottomSheetManager setBlurRadius(Integer blurRadius) {
        this.blurRadius = blurRadius;
        return this;
    }

    // ---- APPLY ----
    public void apply() {
        if (dialog == null) return;
        Window window = dialog.getWindow();
        if (window == null) return;

        // Window defaults
        window.setGravity(DEFAULT_GRAVITY);
        window.setDimAmount(DEFAULT_DIM);

        if (blurRadius != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                window.addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                window.getAttributes().setBlurBehindRadius(blurRadius);
            }
        }



        if (dimAmount != null) window.setDimAmount(dimAmount);
        if (softInputMode != null) window.setSoftInputMode(softInputMode);
        if (cancelable != null) dialog.setCancelable(cancelable);



        dialog.setOnShowListener(d -> {
            View bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet == null) return;


            // --- INSETS LISTENER ---
            if (insetsListener != null) {
                ViewCompat.setOnApplyWindowInsetsListener(bottomSheet, (v, insets) -> {
                    insetsListener.onApply(insets);
//                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//                    v.setPadding(
//                            v.getPaddingLeft(),
//                            v.getPaddingTop(),
//                            v.getPaddingRight(),
//                            systemBars.bottom // padding intern
//                    );

                    return insets;
                });
            }
            if (keyboardListener != null) {
                if (dialog.getOwnerActivity() != null) {
                    final FrameLayout rootLayout = dialog.getOwnerActivity().findViewById(android.R.id.content);
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
                                keyboardListener.onKeyboardVisibilityChanged(isKeyboardOpen);
                            }
                            return true;
                        }
                    });
                }
            }

//            bottomSheet.setBackgroundResource(R.drawable.background_bottom_sheet);
            bottomSheet.setClipToOutline(true);

            if (navigationBarColor != null) applyNavigationBarColor(navigationBarColor);

            BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);


            if (peekHeight != null) behavior.setPeekHeight(peekHeight);
            if (skipCollapsed != null) behavior.setSkipCollapsed(skipCollapsed);
            if (draggable != null) behavior.setDraggable(draggable);
            if (hideable != null) behavior.setHideable(hideable);
            if (state != null) behavior.setState(state);
            if (halfExpandedRatio != null) behavior.setHalfExpandedRatio(halfExpandedRatio);
            if (expandedOffset != null) behavior.setExpandedOffset(expandedOffset);
            if (fitToConstraints != null) behavior.setFitToContents(fitToConstraints);


            if (height != null) {
                ViewGroup.LayoutParams params = bottomSheet.getLayoutParams();
                params.height = height;
                bottomSheet.setLayoutParams(params);
            }
        });
    }

    // region Apply

    public void applyNavigationBarColor(@ColorInt int color) {
        if (dialog == null) return;

        View bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (bottomSheet == null) return;

        Drawable background = bottomSheet.getBackground();
        if (background instanceof MaterialShapeDrawable) {
            ((MaterialShapeDrawable) background).setFillColor(ColorStateList.valueOf(color));
        } else {
            bottomSheet.setBackgroundTintList(ColorStateList.valueOf(color));
        }

        if (dialog.getWindow() != null) dialog.getWindow().setNavigationBarColor(color);
    }
    // endregion

    // region Getters
    public Integer getInt(@NonNull String key) {
        if (fragment == null) {
            throw new IllegalStateException("Fragment not attached to BottomSheetManager");
        }

        Bundle args = fragment.getArguments();
        if (args == null || !args.containsKey(key)) {
            return null;
        }

        int value = args.getInt(key, 0);
        if (value == 0) {
            return null;
        }

        return value;
    }
    // endregion
    public interface InsetsListener {
        void onApply(WindowInsetsCompat insets);
    }
    public interface KeyboardVisibilityListener {
        void onKeyboardVisibilityChanged(boolean isOpen);
    }

}
