package info.uaic.ro.sandbox.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MeasureUtils {

    private static final long MEGABYTE = 1024L * 1024L;
    private static final long KILOBYTE = 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

    public static long bytesToKilobytes(long bytes) {
        return bytes / KILOBYTE;
    }
}
