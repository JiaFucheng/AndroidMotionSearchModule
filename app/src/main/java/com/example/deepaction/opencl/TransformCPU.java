package com.example.deepaction.opencl;

import android.util.Log;
import java.util.Locale;

public class TransformCPU extends Transform {

    private final static String TAG = "TransformCPU";

    public TransformCPU(int width, int height, int representation, String bnParamFilePath) {
        handle = init(width, height, representation, bnParamFilePath);
    }

    @Override
    public long init(int width, int height, int representation, String bnParamFilePath) {
        super.init(width, height, representation, bnParamFilePath);

        return nativeInit(width, height, representation, bnParamFilePath);
    }

    @Override
    public byte[] yuvToARGB(byte[] data) {
        if (representation != Representation.REPRE_IFRAME) {
            Log.w(TAG, String.format(Locale.CHINA,
                    "Transform with representation %d can not call yuvToARGB",
                    representation));
            return null;
        }

        // byte[] outputData = new byte[argbSize];
        nativeYUVToARGB(handle, data, argbBuffer);
        return argbBuffer;
    }

    @Override
    public float[] processIFrame(byte[] data) {
        if (representation != Representation.REPRE_IFRAME) {
            Log.e(TAG, String.format(Locale.CHINA,
                    "Transform with representation %d can not call processIFrame",
                    representation));
            return null;
        }

        float[] outputData = new float[cropDataSize];
        nativeProcessIFrame(handle, data, outputData);
        return outputData;
    }

    @Override
    public float[] processPFrame(int[] data) {
        if (representation != Representation.REPRE_MV &&
            representation != Representation.REPRE_RES) {
            Log.e(TAG, String.format(Locale.CHINA,
                    "Transform with representation %d can not call processPFrame",
                    representation));
            return null;
        }

        float[] outputData = new float[cropDataSize];
        nativeProcessPFrame(handle, data, outputData);
        return outputData;
    }

    @Override
    public void shutdown() {
        if (handle != 0)
            nativeShutdown(handle);
    }

    private static native long nativeInit(int width, int height, int representation, String bnParamFilePath);
    private static native void nativeShutdown(long handle);

    private static native void nativeYUVToARGB(long handle, byte[] data, byte[] outputData);

    private static native void nativeProcessIFrame(long handle, byte[] data, float[] outputData);
    private static native void nativeProcessPFrame(long handle, int[] data, float[] outputData);
}
