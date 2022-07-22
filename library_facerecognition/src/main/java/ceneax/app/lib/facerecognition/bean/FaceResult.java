package ceneax.app.lib.facerecognition.bean;

public class FaceResult {
    private final float confidence;
    private final float[][] feature;

    public FaceResult(float confidence, float[][] feature) {
        this.confidence = confidence;
        this.feature = feature;
    }

    public float getConfidence() {
        return confidence;
    }

    public float[][] getFeature() {
        return feature;
    }
}