package Service;

import Domain.Masina;
import Repository.Repository;

import java.io.IOException;
import java.util.Collection;

public class Service {
    Repository<Masina> repository;
    public Service(Repository<Masina> repository)
    {
        this.repository = repository;
    }
    public void add(int id, String marca, String model) throws IOException {
        repository.add(new Masina(id, marca, model));
    }
    public void modify(int id, String marca, String model) throws IOException {
        repository.modify(new Masina(id, marca, model));
    }
    public void remove(int id) throws IOException {
        repository.remove(id);
    }
    public Collection<Masina> getAll()
    {
        return repository.getAll();
    }
    public Masina getByID(int id)
    {
        return repository.find(id);
    }
}
