package Service;

import Domain.Inchiriere;
import Domain.Masina;
import Repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InchiriereService {
    Repository<Inchiriere> repository;
    Service serviceMasina;
    public InchiriereService(Repository<Inchiriere> repository, Service serviceMasina)
    {
        this.repository = repository;
        this.serviceMasina = serviceMasina;
    }
    public void add(int id, Masina masina, String begin_date, String end_date) throws IOException {
        repository.add(new Inchiriere(id, masina, begin_date, end_date));
    }
    public void modify(int id, Masina masina, String begin_date, String end_date) throws IOException {
        repository.modify(new Inchiriere(id, masina, begin_date, end_date));
    }
    public void remove(int id) throws IOException {
        repository.remove(id);
    }
    public Collection<Inchiriere> getAll()
    {
        return repository.getAll();
    }
    public Inchiriere getByID(int id)
    {
        return repository.find(id);
    }
    public void celeMaiInchiriateMasini()
    {
        List<Inchiriere> inchirieri = (ArrayList<Inchiriere>) getAll();
        Map<Integer, Long> countMasini = inchirieri.stream().map(inchiriere -> inchiriere.getMasina()).collect(Collectors.groupingBy(Masina::getID,Collectors.counting()));

        List<Map.Entry<Integer, Long>> sortedMasini = countMasini.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue())).collect(Collectors.toList());

        int size = sortedMasini.size();
        while (size>0)
        {
            Integer key = sortedMasini.getFirst().getKey();
            Long value = sortedMasini.getFirst().getValue();
            System.out.println(serviceMasina.getByID(key) + " cu " + value + " inchirieri.");
            sortedMasini.removeFirst();
            size = sortedMasini.size();
        }
    }
}
