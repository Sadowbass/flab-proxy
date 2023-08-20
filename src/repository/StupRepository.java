package repository;

import java.util.HashMap;
import java.util.Map;

public class StupRepository implements Repository<String> {

    private final Map<String, String> db;

    public StupRepository() {
        this.db = new HashMap<>();
    }

    @Override
    public String get(String key) {
        return db.get(key);
    }

    @Override
    public void add(String key, String value) {
        if (db.containsKey(key)) {
            throw new RuntimeException("duplicated key");
        }

        db.put(key, value);
    }
}
