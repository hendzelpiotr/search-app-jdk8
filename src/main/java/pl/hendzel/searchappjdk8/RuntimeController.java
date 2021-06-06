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
                RuntimeUtils.getCurrentlyUsedMemory(),
                RuntimeUtils.getGarbageCollectionTime(),
                RuntimeUtils.getGcCount());
    }

    static class RuntimeResponse {
        private final long usedMemory;
        private final long gcTimeInMillis;
        private final long gcCounter;

        RuntimeResponse(long usedMemory, long gcTimeInMillis, long gcCounter) {
            this.usedMemory = usedMemory;
            this.gcTimeInMillis = gcTimeInMillis;
            this.gcCounter = gcCounter;
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
    }
}
