package ceneax.app.lib.facerecognition.arch;

import androidx.camera.core.ImageAnalysis;

public abstract class FRRunner<T> implements ImageAnalysis.Analyzer, IRunner<T> {
    public abstract void release();
}