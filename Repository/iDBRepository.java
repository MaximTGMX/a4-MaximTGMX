package Repository;

import Domain.Entitate;
import Domain.Masina;

import java.util.ArrayList;

public interface iDBRepository<T extends Entitate> extends Repository<T> {
    void connectToDB();
    void closeConnection();
    void createTable();
    void initTable();
    void add(T t);
    void modify(T t);
    void remove(int id);
    T find(int id);
    ArrayList<T> getAll();

}
