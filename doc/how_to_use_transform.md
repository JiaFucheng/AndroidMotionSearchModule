## Prepare BN Parameter File
1. Create directory **SDCard/coviar_opencl/bn_params**.
2. Put **BN_parameters_ucf101_mv.bin** and **BN_parameters_ucf101_residual.bin** in **SDCard/coviar_opencl/bn_params**.

## How to Use Transform?
```java
// BN paramters file path
String sdcardPath = Environment.getExternalStorageDirectory().getPath();
String bnParamPath = sdcardPath + "coviar_opencl/bn_params/BN_parameters_ucf101_mv.bin";

// Create transform with BN paramters file path
Transform mvTransform = TransformCPU(width, height, Representation.REPRE_MV, bnParamPath);

// Process
mvTransform.processPFrame(accuMVData);

// Shut down
mvTransform.shutdown();
```