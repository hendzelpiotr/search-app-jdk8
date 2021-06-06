package pl.hendzel.searchappjdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("search")
class SearchController {

    private static final int SIZE = 100000;
    private InMemoryDatabase database = new InMemoryDatabase(SIZE);

    @GetMapping
    SentencesResponse search(@RequestParam String word) {
        Set<String> foundSentences = database.searchSentencesContain(word);
        return new SentencesResponse(foundSentences);
    }

    @PutMapping("restart-db")
    void restartDb(@RequestParam int size) {
        this.database = new InMemoryDatabase(size);
    }

    static class SentencesResponse {
        private final int size;
        private final List<String> foundSentences;

        SentencesResponse(Set<String> foundSentences) {
            this.foundSentences = new ArrayList<>(foundSentences);
            this.size = foundSentences.size();
        }

        public int getSize() {
            return size;
        }

        public List<String> getFoundSentences() {
            return foundSentences;
        }
    }
}
