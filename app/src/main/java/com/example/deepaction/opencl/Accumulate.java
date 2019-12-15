package com.example.deepaction.opencl;

public class Accumulate {

    protected long handle;

    protected int width;
    protected int height;
    protected int accuMVDataSize;

    public Accumulate(int width, int height, int block_size) {
        init(width, height, block_size);
    }

    protected void init(int width, int height, int block_size) {
        accuMVDataSize = width * height * 2;
    }

    public void shutdown() {}

    public void resetAccumulatedMV() {}

    public void accumulateMV(int[] mvArrayData) {}

    public int[] getAccumulatedMV() {
        return null;
    }
}
