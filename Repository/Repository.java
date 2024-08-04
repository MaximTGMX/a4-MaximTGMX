package Repository;
import Domain.Entitate;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public interface Repository<T extends Entitate> extends Iterable<T> {
    public void add(T entitate) throws IOException;
    public void modify(T entitate) throws IOException;
    public void remove(int id) throws IOException;
    public T find(int id);
    public Collection<T> getAll();
    public Iterator<T> iterator();
}
