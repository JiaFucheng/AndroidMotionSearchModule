package com.example.deepaction.opencl;

public class AccumulateCPU extends Accumulate {

    private long handle;

    private class AccumulateMode {
        public static final int MODE_BLOCK_LEVEL = 1;
        public static final int MODE_PIXEL_LEVEL = 2;
    }

    public AccumulateCPU(int width, int height, int block_size) {
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
        nativeAccumulateMV(this.handle, mvArrayData, AccumulateMode.MODE_BLOCK_LEVEL);
    }

    public int[] getAccumulatedMV() {
        int[] accuMVArrayData = new int[accuMVDataSize];
        nativeGetAccumulatedMV(this.handle, accuMVArrayData);
        return accuMVArrayData;
    }

    private static native long nativeInit(int width, int height, int block_size);
    private static native void nativeShutdown(long handle);

    private static native void nativeResetAccumulatedMV(long handle);
    private static native void nativeAccumulateMV(long handle, int[] mvArrayData, int mode);
    private static native void nativeGetAccumulatedMV(long handle, int[] accuMVArrayData);
}
