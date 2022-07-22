package ceneax.app.lib.facerecognition.engine;

import android.annotation.SuppressLint;
import android.media.Image;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageProxy;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.google.mlkit.vision.face.FaceLandmark;

import org.tensorflow.lite.Interpreter;

import java.util.List;

import ceneax.app.lib.facerecognition.TFLite;
import ceneax.app.lib.facerecognition.arch.FRRunner;

public class FaceRecognitionRunner extends FRRunner<Object> {
    // 人脸检测组件
    private final FaceDetector mFaceDetector;
    // Tensorflow Lite
    private final TFLite mTFLite;

    public FaceRecognitionRunner() {
        mFaceDetector = FaceDetection.getClient(new FaceDetectorOptions.Builder().build());
        mTFLite = new TFLite();
    }

    @Override
    public void analyze(@NonNull ImageProxy image) {
        @SuppressLint("UnsafeOptInUsageError") Image mediaImage = image.getImage();
        if (mediaImage == null) {
            image.close();
            return;
        }

        InputImage inputImage = InputImage.fromMediaImage(mediaImage, image.getImageInfo().getRotationDegrees());
        mFaceDetector.process(inputImage)
                .addOnSuccessListener(faces -> {
                    // 未检测到人脸，继续接收下一帧图像去检测
                    if (faces.isEmpty()) {
                        return;
                    }

                    // 只取第一张人脸数据
                    Face face = faces.get(0);

                    // 开始计算128D脸部特征数据
                    mTFLite.run(this);
                })
                .addOnCompleteListener(task -> image.close());
    }

    @Override
    public Object run(@NonNull Interpreter interpreter) {
        return null;
    }

    @Override
    public void release() {
        mTFLite.release();
    }
}