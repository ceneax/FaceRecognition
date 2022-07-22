package ceneax.app.lib.facerecognition.arch;

import androidx.annotation.NonNull;

import org.tensorflow.lite.Interpreter;

public interface IRunner<T> {
    T run(@NonNull Interpreter interpreter);
}