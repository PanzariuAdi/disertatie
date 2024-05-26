package info.uaic.ro.sandbox.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MeasureUtils {

    private static final long MEGABYTE = 1024L * 1024L;

    public static long calculateMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
}
