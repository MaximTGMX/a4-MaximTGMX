package Testing;
import Domain.Inchiriere;
import Domain.InchiriereFactory;
import Domain.Masina;
import Domain.MasinaFactory;
import Repository.MemoryRepository;
import Repository.Repository;
import Service.Service;
import Service.InchiriereService;
import Repository.TextRepository;
import Repository.BinaryRepository;
import UI.UI;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class Testing{
    @Test
    public void Testing() throws IOException {
        TestingMasina();
        TestingInchiriere();
        TestingService();
        TestingTextRepo();
        TestingBinRepo();
    }

    public void TestingMasina(){
        Masina masina = new Masina(1,"AWOOGA","UWU");
        assert masina.getID()==1;
        assert masina.getMarca().equals("AWOOGA");
        assert masina.getModel().equals("UWU");
    }

    public void TestingInchiriere(){
        Masina masina = new Masina(1,"AWOOGA","UWU");
        Inchiriere inchiriere = new Inchiriere(1,masina,"12/12/12", "13/12/12");
        assert inchiriere.getID()==1;
        assert inchiriere.getBeginDate().equals("12/12/12");
        assert inchiriere.getEndDate().equals("13/12/12");
    }

    public void TestingService() throws IOException {
        Repository<Masina> repositoryMasina = new MemoryRepository<Masina>();
        Repository<Inchiriere> repositoryInchiriere = new MemoryRepository<Inchiriere>();
        Service masinaService = new Service(repositoryMasina);
        InchiriereService inchiriereService = new InchiriereService(repositoryInchiriere,masinaService);

        masinaService.add(1,"AWOOGA","UWU");
        inchiriereService.add(1,new Masina(1,"AWOOGA","UWU"),"12/12/12", "13/12/12");

        List<Masina> m = (List<Masina>) masinaService.getAll();
        List<Inchiriere> i = (List<Inchiriere>) inchiriereService.getAll();
        //test add masina
        assert m.get(0).getID()==1;
        assert m.get(0).getMarca().equals("AWOOGA");
        assert m.get(0).getModel().equals("UWU");
        //test add inchiriere
        assert i.get(0).getID()==1;
        assert i.get(0).getBeginDate().equals("12/12/12");
        assert i.get(0).getEndDate().equals("13/12/12");

        masinaService.modify(1,"JETSTREAM","SAM");
        m = (List<Masina>) masinaService.getAll();
        //test update masina
        assert m.get(0).getID()==1;
        assert m.get(0).getMarca().equals("JETSTREAM");
        assert m.get(0).getModel().equals("SAM");

        inchiriereService.modify(1,new Masina(1,"JETSTREAM","SAM"),"13/12/12","14/12/12");
        i = (List<Inchiriere>) inchiriereService.getAll();
        //test update inchiriere
        assert i.get(0).getID()==1;
        assert i.get(0).getBeginDate().equals("13/12/12");
        assert i.get(0).getEndDate().equals("14/12/12");

        assert masinaService.getByID(1).getMarca().equals(m.get(0).getMarca());
        assert inchiriereService.getByID(1).getBeginDate().equals(i.get(0).getBeginDate());
        assert masinaService.getByID(1).getModel().equals(m.get(0).getModel());
        assert inchiriereService.getByID(1).getEndDate().equals(i.get(0).getEndDate());

        masinaService.remove(1);
        m = (List<Masina>) masinaService.getAll();
        //test remove masina
        assert m.size()==0;

        inchiriereService.remove(1);
        i = (List<Inchiriere>) inchiriereService.getAll();
        //test remove inchiriere
        assert i.size()==0;


    }

    public void TestingTextRepo() throws IOException {
        Repository<Masina> repositoryMasina = new TextRepository<>("C:\\Users\\Frosty Lumberjack\\Documents\\GitHub\\a2-MaximTGMX\\Testing\\test_masina.txt", new MasinaFactory());
        Repository<Inchiriere> repositoryInchiriere = new TextRepository<>("C:\\Users\\Frosty Lumberjack\\Documents\\GitHub\\a2-MaximTGMX\\Testing\\test_inchiriere.txt", new InchiriereFactory());

        List<Masina> m = (List<Masina>) repositoryMasina.getAll();
        List<Inchiriere> i = (List<Inchiriere>) repositoryInchiriere.getAll();
        //read
        assert m.get(0).getID()==1;
        assert m.get(0).getMarca().equals("Jaguar");
        assert m.get(0).getModel().equals("XE");

        assert i.get(0).getID()==1;
        assert i.get(0).getBeginDate().equals("12/12/12");
        assert i.get(0).getEndDate().equals("13/12/12");

        repositoryMasina.add(new Masina(10,"AWOOGA","UWU"));
        repositoryInchiriere.add(new Inchiriere(2,new Masina(11,"AWOOGA","UWU"),"16/4/20","17/4/20"));
        //add
        assert m.get(5).getID()==10;
        assert m.get(5).getMarca().equals("AWOOGA");
        assert m.get(5).getModel().equals("UWU");

        assert i.get(1).getID()==2;
        assert i.get(1).getBeginDate().equals("16/4/20");
        assert i.get(1).getEndDate().equals("17/4/20");

        repositoryMasina.modify(new Masina(1,"AWOOGA","UWU"));
        repositoryInchiriere.modify(new Inchiriere(1,new Masina(11,"AWOOGA","UWU"),"13/12/12","14/12/12"));
        m = (List<Masina>) repositoryMasina.getAll();
        i = (List<Inchiriere>) repositoryInchiriere.getAll();
        //update
        assert m.get(0).getMarca().equals("AWOOGA");
        assert m.get(0).getModel().equals("UWU");

        assert i.get(0).getBeginDate()=="13/12/12";
        assert i.get(0).getEndDate()=="14/12/12";

        repositoryMasina.remove(10);
        repositoryInchiriere.remove(2);
        m = (List<Masina>) repositoryMasina.getAll();
        i = (List<Inchiriere>) repositoryInchiriere.getAll();

        assert m.size()==5;
        assert i.size()==1;
    }

    public void TestingBinRepo() throws IOException {
        Repository<Masina> repositoryMasina = new BinaryRepository<Masina>("C:\\Users\\Frosty Lumberjack\\Documents\\GitHub\\a2-MaximTGMX\\Testing\\test_masina.bin");
        Repository<Inchiriere> repositoryInchiriere = new BinaryRepository<Inchiriere>("C:\\Users\\Frosty Lumberjack\\Documents\\GitHub\\a2-MaximTGMX\\Testing\\test_inchiriere.bin");

        List<Masina> m = (List<Masina>) repositoryMasina.getAll();
        List<Inchiriere> i = (List<Inchiriere>) repositoryInchiriere.getAll();
        //read
        assert m.size()==0;

        assert i.size()==0;

        repositoryMasina.add(new Masina(1,"AWOOGA","UWU"));
        repositoryInchiriere.add(new Inchiriere(1,new Masina(10,"AWOOGA","UWU"),"16/4/20","17/4/20"));
        //add
        assert m.get(0).getID()==2;
        assert m.get(0).getMarca().equals("AWOOGA");
        assert m.get(0).getModel().equals("UWU");

        assert i.get(0).getID()==1;
        assert i.get(0).getBeginDate().equals("16/4/20");
        assert i.get(0).getEndDate().equals("17/4/20");

        repositoryMasina.modify(new Masina(10,"AWOOGA","OWO"));
        repositoryInchiriere.modify(new Inchiriere(1,new Masina(10,"AWOOGA","UWU"),"13/12/12","14/12/12"));
        m = (List<Masina>) repositoryMasina.getAll();
        i = (List<Inchiriere>) repositoryInchiriere.getAll();
        //update
        assert m.get(0).getMarca().equals("AWOOGA");
        assert m.get(0).getModel().equals("OWO");

        assert i.get(0).getBeginDate()=="13/12/12";
        assert i.get(0).getEndDate()=="14/12/12";

        repositoryMasina.remove(1);
        repositoryInchiriere.remove(1);
        m = (List<Masina>) repositoryMasina.getAll();
        i = (List<Inchiriere>) repositoryInchiriere.getAll();

        assert m.size()==0;
        assert i.size()==0;
    }
}
