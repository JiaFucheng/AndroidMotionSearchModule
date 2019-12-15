package com.example.deepaction.opencl;

public class MotionSearch {

    protected long handle;

    protected int mbWidth;
    protected int mbHeight;
    protected int mbStride;
    protected int mvArrSize;

    private int[] mvArrayData;

    public MotionSearch(int width, int height, int search_type, int block_size) {
        handle = init(width, height, search_type, block_size);
    }

    public int getMBWidth() {
        return this.mbWidth;
    }

    public int getMBHeight() {
        return this.mbHeight;
    }

    protected long init(int width, int height, int search_type, int block_size) {
        mbWidth     = width  / block_size;
        mbHeight    = height / block_size;
        mbStride    = (mbWidth + 1);
        mvArrSize   = mbStride * mbHeight * 2;
        mvArrayData = new int[mvArrSize];

        return initInternal(width, height, search_type, block_size);
    }

    public int[] search(byte[] curFrameData, byte[] refFrameData) {
        searchInternal(handle, curFrameData, refFrameData, mvArrayData);
        return mvArrayData;
    }

    public int[] search(byte[] curFrameData, byte[] refFrameData, int lineOffset, int lineCount) {
        searchInternal(handle, curFrameData, refFrameData, mvArrayData, lineOffset, lineCount);
        return mvArrayData;
    }

    public void shutdown() {
        shutdownInternal(handle);
    }

    protected long initInternal(int width, int height, int search_type, int block_size) { return 0L; }
    protected void searchInternal(long handle, byte[] curFrameData, byte[] refFrameData, int[] mvArrayData) {}
    protected void searchInternal(long handle, byte[] curFrameData, byte[] refFrameData, int[] mvArrayData, int lineOffset, int lineCount) {}
    protected void shutdownInternal(long handle) {}

}
