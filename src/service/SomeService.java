package service;

import customannotation.Transactional;
import repository.Repository;

public class SomeService implements Service {

    private final Repository<String> repository;

    public SomeService(Repository<String> repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void add(String key, String value) {
        repository.add(key, value);
    }

    @Override
    public String find(String key) {
        return repository.get(key);
    }
}
