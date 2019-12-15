package com.example.deepaction.opencl;

public class AccumulateOpenCL extends Accumulate {

    public AccumulateOpenCL(int width, int height, int block_size) {
        super(width, height, block_size);

        this.handle = nativeInit(width, height, block_size);
    }

    public void shutdown() {
        super.shutdown();
        nativeShutdown(this.handle);
    }

    public void resetAccumulatedMV() {
        nativeResetAccumulatedMV(this.handle);
    }

    public void accumulateMV(int[] mvArrayData) {
        nativeAccumulateMV(this.handle, mvArrayData);
    }

    public int[] getAccumulatedMV() {
        int[] accuMVArrayData = new int[accuMVDataSize];
        nativeGetAccumulatedMV(this.handle, accuMVArrayData);
        return accuMVArrayData;
    }

    private static native long nativeInit(int width, int height, int block_size);
    private static native void nativeShutdown(long handle);

    private static native void nativeResetAccumulatedMV(long handle);
    private static native void nativeAccumulateMV(long handle, int[] mvArrayData);
    private static native void nativeGetAccumulatedMV(long handle, int[] accuMVArrayData);
}
