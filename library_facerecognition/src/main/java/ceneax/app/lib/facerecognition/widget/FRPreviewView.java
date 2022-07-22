package ceneax.app.lib.facerecognition.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.PreviewView;

public class FRPreviewView extends FrameLayout {
    // CameraX的预览组件
    private PreviewView mPreviewView;

    public FRPreviewView(@NonNull Context context) {
        this(context, null);
    }

    public FRPreviewView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FRPreviewView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPreviewView = new PreviewView(getContext());

        addView(mPreviewView);
    }

    public PreviewView getPreviewView() {
        return mPreviewView;
    }
}