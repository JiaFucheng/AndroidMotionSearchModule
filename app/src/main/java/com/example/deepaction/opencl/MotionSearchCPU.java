package com.example.deepaction.opencl;

public class MotionSearchCPU extends MotionSearch {

    private int threadCount;

    public MotionSearchCPU(int width, int height, int searchType, int blockSize, int threadCount) {
        super(width, height, searchType, blockSize);

        this.threadCount = threadCount;
        this.handle      = nativeInit(width, height, searchType, blockSize, threadCount);
    }

//    @Override
//    protected long initInternal(int width, int height, int searchType, int blockSize) {
//        return nativeInit(width, height, searchType, blockSize, threadCount);
//    }

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
         nativeShutdown(handle);
    }

    private static native long nativeInit(int width, int height, int searchType, int blockSize, int threadCount);
    private static native void nativeSearch(long handle, byte[] curFrameData, byte[] refFrameData, int[] mvArrayData, int lineOffset, int lineCount);
    private static native void nativeShutdown(long handle);

}
