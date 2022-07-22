package ceneax.app.lib.facerecognition.arch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public abstract class FRAnimationView extends View {
    public FRAnimationView(Context context) {
        super(context);
    }

    public FRAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FRAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FRAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public abstract void release();
}