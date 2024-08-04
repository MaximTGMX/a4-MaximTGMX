package Repository;
import Domain.Entitate;
import Domain.Masina;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MemoryRepository<T extends Entitate> implements Repository<T> {
    List<T> entitati = new ArrayList<>();
    @Override
    public void add(T Entity) throws IOException {
        Collection<T> masini = this.getAll();
        for (T m: masini) if (m.getID()==Entity.getID()) throw new RuntimeException("ID deja existent!");
        entitati.add(Entity);
    }
    @Override
    public void modify(T Entity) throws IOException {
        for (int i = 0; i < entitati.size(); i++)
            if (entitati.get(i).getID()==Entity.getID())
            {
                entitati.set(i,Entity);
                return;
            }
    }
    @Override
    public void remove(int id) throws IOException {
        for (T entitate: entitati)
            if (entitate.getID()==id)
            {
                entitati.remove(entitate);
                return;
            }
    }
    @Override
    public T find(int id)
    {
        for (T entitate: entitati)
            if (entitate.getID()==id)
                return entitate;
        return null;
    }
    @Override
    public Collection<T> getAll()
    {
        return new ArrayList<T>(entitati);
    }
    @Override
    public Iterator<T> iterator()
    {
        return new ArrayList<T>(entitati).iterator();
    }
}
