package pl.hendzel.searchappjdk8;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("runtime")
class RuntimeController {

    @GetMapping
    RuntimeResponse fetchRuntimeStats() {
        return new RuntimeResponse(
                RuntimeUtils.getReallyUsedMemory(),
                RuntimeUtils.getGarbageCollectionTime(),
                RuntimeUtils.getGcCount(),
                RuntimeUtils.getProcessCpuTime()
        );
    }

    static class RuntimeResponse {
        private final long usedMemory;
        private final long gcTimeInMillis;
        private final long gcCounter;
        private final long processCpuTime;

        RuntimeResponse(long usedMemory, long gcTimeInMillis, long gcCounter, long processCpuTime) {
            this.usedMemory = usedMemory;
            this.gcTimeInMillis = gcTimeInMillis;
            this.gcCounter = gcCounter;
            this.processCpuTime = processCpuTime;
        }

        public long getUsedMemory() {
            return usedMemory;
        }

        public long getGcTimeInMillis() {
            return gcTimeInMillis;
        }

        public long getGcCounter() {
            return gcCounter;
        }

        public long getProcessCpuTime() {
            return processCpuTime;
        }
    }
}
