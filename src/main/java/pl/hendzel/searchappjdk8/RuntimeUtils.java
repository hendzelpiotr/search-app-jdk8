package pl.hendzel.searchappjdk8;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

final class RuntimeUtils {

    private RuntimeUtils() {
    }

    static long getCurrentlyUsedMemory() {
        return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()
                + ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
    }

    static long getGcCount() {
        long sum = 0;
        for (GarbageCollectorMXBean b : ManagementFactory.getGarbageCollectorMXBeans()) {
            long count = b.getCollectionCount();
            if (count != -1) {
                sum += count;
            }
        }
        return sum;
    }

    static long getReallyUsedMemory() {
        long before = getGcCount();
        System.gc();
        while (getGcCount() == before) {
        }
        return getCurrentlyUsedMemory();
    }

    static long getGarbageCollectionTime() {
        long collectionTime = 0;
        for (GarbageCollectorMXBean garbageCollectorMXBean : ManagementFactory.getGarbageCollectorMXBeans()) {
            collectionTime += garbageCollectorMXBean.getCollectionTime();
        }
        return collectionTime;
    }

    static long getProcessCpuTime() {
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return operatingSystemMXBean.getProcessCpuTime();
    }

}
