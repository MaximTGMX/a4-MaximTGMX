import Domain.InchiriereFactory;
import Domain.MasinaFactory;
import Domain.Inchiriere;
import Domain.Masina;
import Repository.Repository;
import Repository.MemoryRepository;
import Repository.TextRepository;
import Repository.BinaryRepository;
import Repository.MasinaDBRepo;
import Repository.InchiriereDBRepo;
import Settings.Settings;
import Testing.Testing;
import Service.Service;
import Service.InchiriereService;
import UI.UI;

import java.io.FileNotFoundException;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        Testing t = new Testing();
        t.Testing();

        Settings setari = Settings.getInstance();

        Repository<Masina> repositoryMasina;
        Repository<Inchiriere> repositoryInchiriere;
        if (setari.getSettings().equals("memory"))
        {
            repositoryMasina = new MemoryRepository<Masina>();
            repositoryInchiriere = new MemoryRepository<Inchiriere>();
        }
        else if (setari.getSettings().equals("database"))
        {
            repositoryMasina = new MasinaDBRepo();
            repositoryInchiriere = new InchiriereDBRepo();
        }
        else if (setari.getSettings().equals("text")) {
            repositoryMasina = new TextRepository<>(setari.getMasinaFile(), new MasinaFactory());
            repositoryInchiriere = new TextRepository<>(setari.getInchiriereFile(), new InchiriereFactory());
        }
        else
        {
            repositoryMasina = new BinaryRepository<Masina>(setari.getMasinaFile());
            repositoryInchiriere = new BinaryRepository<Inchiriere>(setari.getInchiriereFile());
        }

        Service masinaService = new Service(repositoryMasina);
        InchiriereService inchiriereService = new InchiriereService(repositoryInchiriere,masinaService);
        UI ui = new UI(masinaService,inchiriereService);

        ui.Menu();
    }

    public static void ReadSettings() throws FileNotFoundException {

    }
}
