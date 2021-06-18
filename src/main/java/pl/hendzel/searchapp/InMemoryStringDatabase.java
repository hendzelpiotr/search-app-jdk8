package pl.hendzel.searchapp;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;

final class InMemoryStringDatabase implements InMemoryDatabase<String> {

    private final Set<String> sentences;

    InMemoryStringDatabase(int size) {
        this.sentences = init(size);
    }

    @Override
    public Set<String> init(int size) {
        Set<String> randomWords = new HashSet<>();
        for (int i = 0; i < size; i++) {
            String generatedString = RandomStringUtils.randomAlphanumeric(10);
            randomWords.add(generatedString);
        }
        return randomWords;
    }

    @Override
    public Set<String> search(String word) {
        return sentences.stream()
                .filter(sentence -> sentence.toLowerCase().contains(word))
                .collect(Collectors.toSet());
    }

}
