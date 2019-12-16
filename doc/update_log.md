# 19/12/16 21:00
** Updated Files **
1. AccumulateCPU.java
2. libmotion_search_jni.so

** Update Content **
1. TransformCPU 中处理 I-Frame 时，错误地使用了 BGR 格式（下标 012），应该调换为 RGB 格式（下标012）。
2. AccumulateCPU 调用 nativeAccumulateMV 方法时，应传入 MODE_BLOCK_LEVEL=1 值作为参数，否则内部不会执行真正的 Accumulate MV 函数。
