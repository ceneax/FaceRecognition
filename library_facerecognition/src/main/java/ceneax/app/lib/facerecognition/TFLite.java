package ceneax.app.lib.facerecognition;

import android.content.res.AssetManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.tensorflow.lite.Interpreter;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;

import ceneax.app.lib.facerecognition.arch.IRunner;
import ceneax.app.lib.facerecognition.util.ModelUtil;

public class TFLite {
//    private static final class InstanceHolder {
//        // 单例
//        static final TFLite instance = new TFLite();
//    }
//
//    public static TFLite getInstance() {
//        return InstanceHolder.instance;
//    }

    // TensorFLow 解释器
    private Interpreter interpreter;

//    private TFLite() {}

    // --------------------- 加载模型 ---------------------
    public void loadModel(File modelFile) {
        release();
        interpreter = new Interpreter(modelFile);
    }

    public void loadModel(File modelFile, Interpreter.Options options) {
        release();
        interpreter = new Interpreter(modelFile, options);
    }

    public void loadModel(ByteBuffer byteBuffer) {
        release();
        interpreter = new Interpreter(byteBuffer);
    }

    public void loadModel(ByteBuffer byteBuffer, Interpreter.Options options) {
        release();
        interpreter = new Interpreter(byteBuffer, options);
    }

    public void loadModel(MappedByteBuffer mappedByteBuffer) {
        release();
        interpreter = new Interpreter(mappedByteBuffer);
    }

    public void loadModel(AssetManager assetManager, String fileName) throws IOException {
        loadModel(ModelUtil.loadModelFile(assetManager, fileName));
    }
    // --------------------- 加载模型 ---------------------

    /**
     * 获取 TensorFLow 解释器
     */
    @Nullable
    public Interpreter getInterpreter() {
        return interpreter;
    }

    /**
     * 释放资源
     */
    public void release() {
        if (interpreter != null) {
            interpreter.close();
            interpreter = null;
        }
    }

    /**
     * 开始执行
     */
    public <T> T run(@NonNull IRunner<T> runner) {
        if (interpreter == null) {
            throw new RuntimeException("请先加载模型");
        }
        return runner.run(interpreter);
    }
}