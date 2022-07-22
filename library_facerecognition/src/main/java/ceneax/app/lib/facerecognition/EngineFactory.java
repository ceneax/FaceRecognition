package ceneax.app.lib.facerecognition;

import ceneax.app.lib.facerecognition.arch.FRRunner;
import ceneax.app.lib.facerecognition.engine.FaceRecognitionRunner;

public class EngineFactory {
    public static FRRunner<?> create(FaceRecognition.Engine engine) {
        switch (engine) {
            default:
                return new FaceRecognitionRunner();
        }
    }
}
