package pl.hendzel.searchapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("search")
class SearchController {

    private static final int SIZE = 1000000;
    private InMemoryDatabase<String> database = new InMemoryStringDatabase(SIZE);

    @GetMapping
    SentencesResponse<String> search(@RequestParam String word) {
        Set<String> foundSentences = database.search(word);
        return new SentencesResponse<>(word, foundSentences);
    }

    @PutMapping("restart-db")
    void restartDb(@RequestParam int size) {
        this.database = new InMemoryStringDatabase(size);
    }

    static class SentencesResponse<T> implements Serializable {
        private final int size;
        private final String word;
        private final List<T> foundObjects;

        SentencesResponse(String word, Set<T> foundObjects) {
            this.word = word;
            this.foundObjects = new ArrayList<>(foundObjects);
            this.size = foundObjects.size();
        }

        public String getWord() {
            return word;
        }

        public int getSize() {
            return size;
        }

        public List<T> getFoundObjects() {
            return foundObjects;
        }
    }
}
