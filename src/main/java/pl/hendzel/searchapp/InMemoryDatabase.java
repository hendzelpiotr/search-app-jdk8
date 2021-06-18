package pl.hendzel.searchapp;

import java.io.Serializable;
import java.util.Set;

public interface InMemoryDatabase<T extends Serializable> {
    Set<T> init(int size);
    Set<T> search(String word);
}
