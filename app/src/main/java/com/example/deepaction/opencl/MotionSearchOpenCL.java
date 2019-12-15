package com.example.deepaction.opencl;

public class MotionSearchOpenCL extends MotionSearch {

    public MotionSearchOpenCL(int width, int height, int searchType, int blockSize) {
        super(width, height, searchType, blockSize);
    }

    @Override
    protected long initInternal(int width, int height, int searchType, int blockSize) {
        return nativeInitOpenCLContext(width, height, searchType, blockSize);
    }

    @Override
    protected void searchInternal(long handle, byte[] curFrameData, byte[] refFrameData, int[] mvArrayData) {
        nativeSearch(handle, curFrameData, refFrameData, mvArrayData, 0, mbHeight);
    }

    @Override
    protected void searchInternal(long handle, byte[] curFrameData, byte[] refFrameData, int[] mvArrayData, int lineOffset, int lineCount) {
        nativeSearch(handle, curFrameData, refFrameData, mvArrayData, lineOffset, lineCount);
    }

    @Override
    protected void shutdownInternal(long handle) {
        nativeShutdownOpenCLContext(handle);
    }

    private static native long nativeInitOpenCLContext(int width, int height, int searchType, int blockSize);
    private static native void nativeSearch(long handle, byte[] curFrameData, byte[] refFrameData, int[] mvArrayData,  int lineOffset, int lineCount);
    private static native void nativeShutdownOpenCLContext(long handle);

}
