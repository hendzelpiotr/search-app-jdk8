package pl.hendzel.searchappjdk8;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("runtime")
class RuntimeController {

    @GetMapping
    void runtimeCheck() {
        RuntimeUtils.waitUntilGcRun();
    }

}
