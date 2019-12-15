package com.example.deepaction.opencl;

public class Transform {

    protected long handle;

    protected int representation;

    protected int width;
    protected int height;
    protected int argbSize;
    protected final int cropSize = 224;
    protected int cropDataSize;

    protected byte[] argbBuffer;

    public class Representation {
        public static final int REPRE_IFRAME = 0;
        public static final int REPRE_MV     = 1;
        public static final int REPRE_RES    = 2;
        public static final int REPRE_MV_RES = 3;
    }

    private int getDims(int representation) {
        if (representation == Representation.REPRE_MV)
            return 2;
        else if (representation == Representation.REPRE_IFRAME ||
                 representation == Representation.REPRE_RES)
            return 3;
        else
            return 0;
    }

    public int getRepresentation() { return this.representation; }

    public int getCropSize() { return this.cropSize; }

    public Transform() {}

    public Transform(int width, int height, int representation, String bnParamFilePath) {
        handle = init(width, height, representation, bnParamFilePath);
    }

    public long init(int width, int height, int representation, String bnParamFilePath) {
        this.width   = width;
        this.height  = height;
        this.argbSize = width * height * 4;
        this.representation = representation;
        this.cropDataSize = cropSize * cropSize * getDims(representation);

        if (representation == Representation.REPRE_IFRAME) {
            this.argbBuffer = new byte[argbSize];
        } else {
            this.argbBuffer = null;
        }

        return 0L;
    }

    public byte[] yuvToARGB(byte[] data) { return null; }

    public float[] processIFrame(byte[] data) { return null; }

    public float[] processPFrame(int[] data) { return null; }

    public void shutdown() {}
}
