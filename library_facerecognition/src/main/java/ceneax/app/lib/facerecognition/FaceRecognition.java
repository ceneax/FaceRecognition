package ceneax.app.lib.facerecognition;

import androidx.core.app.ComponentActivity;

import ceneax.app.lib.facerecognition.arch.FRAnimationView;
import ceneax.app.lib.facerecognition.arch.FRRunner;
import ceneax.app.lib.facerecognition.engine.FaceRecognitionRunner;
import ceneax.app.lib.facerecognition.widget.DefaultFRAnimationView;
import ceneax.app.lib.facerecognition.widget.FRPreviewView;

public class FaceRecognition {
    private final Builder mBuilder;

    // 相机
    private final FRCamera mCamera;

    private FaceRecognition(Builder builder) {
        mBuilder = builder;

        // 创建相机
        mCamera = new FRCamera.Builder(mBuilder.cameraBuilder).build();
        // 添加扫描动画View
        if (mBuilder.animationView != null) {
            mBuilder.frPreviewView.addView(mBuilder.animationView);
        }
    }

    /**
     * 获取相机
     * @return 相机
     */
    public FRCamera getFRCamera() {
        return mCamera;
    }

    /**
     * 获取当前解析引擎
     * @return Engine
     */
    public Engine getEngine() {
        return mBuilder.engine;
    }

    /**
     * 释放资源
     */
    public void release() {
        if (mBuilder.animationView != null) {
            mBuilder.animationView.release();
        }
        if (mCamera != null) {
            mCamera.release();
        }
        if (mBuilder.frRunner != null) {
            mBuilder.frRunner.release();
        }
    }

    public static class Builder {
        private final FRCamera.Builder cameraBuilder;
        // 预览图像View
        private FRPreviewView frPreviewView;
        // 解析识别引擎
        private Engine engine = Engine.DEFAULT;
        // 识别分析处理类
        private FRRunner<?> frRunner = new FaceRecognitionRunner();
        // 扫描动画View
        private FRAnimationView animationView;

        public Builder(ComponentActivity activity) {
            cameraBuilder = new FRCamera.Builder(activity);
            // 赋值一个默认的Analyzer
            cameraBuilder.setAnalyzer(frRunner);
            // 赋值一个默认的扫描动画View
            animationView = new DefaultFRAnimationView(activity);
        }

        public Builder setFRPreviewView(FRPreviewView frPreviewView) {
            this.frPreviewView = frPreviewView;
            cameraBuilder.setPreviewView(this.frPreviewView.getPreviewView());
            return this;
        }

        public Builder setEngine(Engine engine) {
            this.engine = engine;
            setFRRunner(EngineFactory.create(engine));
            return this;
        }

        public Builder setCameraId(int cameraId) {
            cameraBuilder.setCameraId(cameraId);
            return this;
        }

        public Builder setAnimationView(FRAnimationView animationView) {
            this.animationView = animationView;
            return this;
        }

        private void setFRRunner(FRRunner<?> frRunner) {
            this.frRunner = frRunner;
            cameraBuilder.setAnalyzer(this.frRunner);
        }

        public FaceRecognition build() {
            return new FaceRecognition(this);
        }
    }

    public enum Engine {
        DEFAULT
    }
}