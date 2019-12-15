package com.example.deepaction.opencl;

public class ResidualOpenCL extends Residual {

    public ResidualOpenCL(int width, int height, int blockSize) {
        super(width, height, blockSize);

        this.handle = nativeInit(width, height, blockSize);
    }

    public void shutdown() {
        nativeShutdown(this.handle);
    }

    public int[] getYUVResidual(byte[] curYUVData, byte[] refYUVData, int[] mvArrayData) {
        resArrayData = new int[resYUVArraySize];
        nativeGetYUVResidual(this.handle, curYUVData, refYUVData, mvArrayData, resArrayData);
        return resArrayData;
    }

    public int[] getResidual(byte[] curYUVData, byte[] refYUVData, int[] accuMVArrayData) {
        // BUG: If not resize resArrayData, it is wrong YUV array size.
        resArrayData = new int[resArraySize];
        nativeGetResidual(this.handle, curYUVData, refYUVData, accuMVArrayData, resArrayData);
        return resArrayData;
    }

    private static native long nativeInit(int width, int height, int blockSize);
    private static native void nativeShutdown(long handle);

    private static native void nativeGetYUVResidual(long handle,
                                                    byte[] curYUVData,  byte[] refYUVData,
                                                    int[]  mvArrayData, int[]  resArrayData);
    private static native void nativeGetResidual(long handle,
                                                 byte[] curYUVData,      byte[] refYUVData,
                                                 int[]  accuMVArrayData, int[]  resArrayData);

}
