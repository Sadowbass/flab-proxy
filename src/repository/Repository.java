package repository;

public interface Repository<T> {

    T get(String key);

    void add(String key, String value);
}
