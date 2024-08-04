package Repository;

import Domain.*;
import org.w3c.dom.Entity;
import org.w3c.dom.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

public class TextRepository <T extends Entitate> extends MemoryRepository<T> {
    private String fileName;
    private IEntitateFactory<T> entitateFactory;
    public TextRepository(String fileName, IEntitateFactory<T> entitateFactory) throws FileNotFoundException {
        this.fileName = fileName;
        this.entitateFactory = entitateFactory;
        readFromFile();
    }
    private void readFromFile() throws FileNotFoundException, RuntimeException
    {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            T entitate = entitateFactory.createEntitate(line);
            try {
                add(entitate);
            }
            catch (RuntimeException | IOException e)
            {
                System.out.println(e);
            }
        }
    }
    public void WriteToFile() throws IOException {
        File file = new File(fileName);
        if (fileName.equals("masina.txt") | fileName.equals("C:\\Users\\Frosty Lumberjack\\Documents\\GitHub\\a2-MaximTGMX\\Testing\\test_masina.txt")) {
            ArrayList<Masina> lista = (ArrayList<Masina>) getAll();
            DeleteFile();
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            for (Masina m : lista) {
                br.write((MasinaFactory.EntString(m)).toString());
                br.newLine();
            }
            br.close();
        }
        else
        {
            ArrayList<Inchiriere> lista = (ArrayList<Inchiriere>) getAll();
            DeleteFile();
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            for (Inchiriere i : lista) {
                br.write((InchiriereFactory.EntString(i)).toString());
                br.newLine();
            }
            br.close();
        }
    }

    public void DeleteFile() throws IOException {
        FileWriter fr = new FileWriter(fileName);
        fr.write("");
        fr.close();
    }

    @Override
    public void add(T Entity) throws IOException {
        super.add(Entity);
        WriteToFile();
    }
    @Override
    public void modify(T Entity) throws IOException {
        super.modify(Entity);
        WriteToFile();
    }
    @Override
    public void remove(int id) throws IOException {
        super.remove(id);
        WriteToFile();
    }
    @Override
    public T find(int id)
    {
        return (T) super.find(id);
    }
    @Override
    public Collection<T> getAll()
    {
        return super.getAll();
    }
}
