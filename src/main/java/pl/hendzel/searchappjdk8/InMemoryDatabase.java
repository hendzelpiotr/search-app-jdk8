package pl.hendzel.searchappjdk8;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;

final class InMemoryDatabase {

    private final Set<String> sentences;

    InMemoryDatabase(int size) {
        this.sentences = init(size);
    }

    Set<String> init(int size) {
        Set<String> randomWords = new HashSet<>();
        for (int i = 0; i < size; i++) {
            String generatedString = RandomStringUtils.randomAlphabetic(10);
            randomWords.add(generatedString);
        }
        return randomWords;
    }

    Set<String> searchSentencesContain(String word) {
        return sentences.stream()
                .filter(sentence -> sentence.toLowerCase().contains(word))
                .collect(Collectors.toSet());
    }

}
