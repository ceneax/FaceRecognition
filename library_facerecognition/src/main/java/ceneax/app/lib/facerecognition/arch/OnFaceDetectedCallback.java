package ceneax.app.lib.facerecognition.arch;

import com.google.mlkit.vision.face.Face;

import java.util.List;

public interface OnFaceDetectedCallback {
    void onFaceDetected(List<Face> faces);
}