package sh.david.bouldertreeapi.datastore.interfaces;

public interface FuzzySearch<T> {
  boolean goodEnoughEquals(T obj);
}
