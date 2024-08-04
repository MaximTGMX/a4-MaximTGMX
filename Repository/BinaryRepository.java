package Repository;

import Domain.Entitate;
import Domain.Inchiriere;
import Domain.Masina;
import Domain.MasinaFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryRepository<T extends Entitate> extends MemoryRepository<T> {
    private String fileName;
    public BinaryRepository(String fileName) {
        super();
        this.fileName = fileName;
        try {
            loadFile();
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e);
        }
    }
    @Override
    public void add(T o) throws IOException {
        super.add(o);
        saveFile();
    }

    @Override
    public void modify(T o) throws IOException {
        super.modify(o);
        saveFile();
    }

    @Override
    public void remove(int id) throws IOException {
        super.remove(id);
        saveFile();
    }

    private void loadFile() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.entitati = (List<T>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {

        }
    }

    private void saveFile() throws IOException {
        // try-with-resources
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this.entitati);
        }

        //ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));

        //oos.close();
    }
}
