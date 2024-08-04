package UI;

import Domain.Inchiriere;
import Domain.Masina;
import Service.Service;
import Service.InchiriereService;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public class UI {
    Service masinaService;
    InchiriereService inchiriereService;

    public UI(Service masinaService, InchiriereService inchiriereService)
    {
        this.masinaService = masinaService;
        this.inchiriereService = inchiriereService;
    }
    public void Menu() throws IOException {
        //Hardcoded();
        while (true)
        {
            printMenu();
            String option;
            Scanner scanner = new Scanner(System.in);
            option = scanner.next();

            switch (option) {
                case "1":
                {
                    try {
                        System.out.print("Dati ID-ul, marca si modelul masinii: ");
                        int id = scanner.nextInt();
                        String marca = scanner.next();
                        String model = scanner.next();
                        masinaService.add(id, marca, model);
                    }
                    catch (RuntimeException | IOException e)
                    {
                        System.out.println(e);
                    }
                    break;
                }
                case "2":
                {
                    try {
                        System.out.print("Dati ID-ul masinii cautate: ");
                        int id = scanner.nextInt();
                        System.out.print("Dati noua marca si noul model: ");
                        boolean exists = false;
                        Collection<Masina> masini = masinaService.getAll();
                        for (Masina m : masini) if (m.getID() == id) exists = true;
                        if (!exists) throw new RuntimeException("Masina nu exista!");
                        String marca = scanner.next();
                        String model = scanner.next();
                        masinaService.modify(id, marca, model);
                    }
                    catch (RuntimeException | IOException e)
                    {
                        System.out.println(e);
                    }
                    break;
                }
                case "3":
                {
                    try {
                        System.out.print("Dati ID-ul masinii cautate: ");
                        int id = scanner.nextInt();
                        boolean exists = false;
                        Collection<Masina> masini = masinaService.getAll();
                        for (Masina m : masini) if (m.getID() == id) exists = true;
                        if (!exists) throw new RuntimeException("Masina nu exista!");
                        masinaService.remove(id);
                    }
                    catch (RuntimeException | IOException e)
                    {
                        System.out.println(e);
                    }
                    break;
                }
                case "4": {
                    try {
                        System.out.print("Dati ID-ul inchirierii, ID-ul masinii inchiriate, data inceput si data sfarsit: ");
                        int id = scanner.nextInt();
                        int id2 = scanner.nextInt();
                        String begin_date = scanner.next();
                        String end_date = scanner.next();
                        boolean exists = false;
                        Collection<Masina> masini = masinaService.getAll();
                        for (Masina m : masini) if (m.getID() == id2) exists = true;
                        if (!exists) throw new RuntimeException("Masina nu exista!");
                        inchiriereService.add(id, masinaService.getByID(id2), begin_date, end_date);
                    } catch (RuntimeException e) {
                        System.out.println(e);
                    }
                    break;
                }
                case "5":
                {
                    try {
                        System.out.print("Dati ID-ul inchirierii cautate: ");
                        int id = scanner.nextInt();
                        boolean exists = false;
                        Collection<Inchiriere> inchirieri = inchiriereService.getAll();
                        for (Inchiriere i: inchirieri) if (i.getID()==id) exists = true;
                        if (!exists) throw new RuntimeException("Inchiriera nu exista!");
                        System.out.print("Dati noul ID al masinii, noua data de incepere si de final a inchirierii: ");
                        int id2 = scanner.nextInt();
                        exists = false;
                        Collection<Masina> masini = masinaService.getAll();
                        for (Masina m : masini) if (m.getID() == id2) exists = true;
                        if (!exists) throw new RuntimeException("Masina nu exista!");
                        String begin_date = scanner.next();
                        String end_date = scanner.next();
                        inchiriereService.modify(id, masinaService.getByID(id2), begin_date, end_date);
                    }
                    catch (RuntimeException e)
                    {
                        System.out.println(e);
                    }
                    break;
                }
                case "6":
                {
                    try {
                        System.out.print("Dati ID-ul inchirierii cautate: ");
                        int id = scanner.nextInt();
                        boolean exists = false;
                        Collection<Inchiriere> inchirieri = inchiriereService.getAll();
                        for (Inchiriere i: inchirieri) if (i.getID()==id) exists = true;
                        if (!exists) throw new RuntimeException("Inchiriera nu exista!");
                        inchiriereService.remove(id);
                    }
                    catch (RuntimeException e)
                    {
                        System.out.println(e);
                    }
                    break;
                }
                case "7":
                {
                    inchiriereService.celeMaiInchiriateMasini();
                    break;
                }
                case "a":
                {
                    Collection<Masina> masini = masinaService.getAll();
                    for (Masina m: masini) System.out.println(m);
                    break;
                }
                case "i":
                {
                    Collection<Inchiriere> inchirieri = inchiriereService.getAll();
                    for (Inchiriere i: inchirieri) System.out.println(i);
                    break;
                }
                case "x":
                {
                    return;
                }
                default:
                {
                    System.out.println("Optiune gresita!");
                }
            }
        }
    }
    private void printMenu()
    {
        System.out.println("\n\n --Lista optiuni--");
        System.out.println("1. Inregistreaza o masina");
        System.out.println("2. Modifica masina");
        System.out.println("3. Sterge masina");
        System.out.println("4. Adauga inchiriere");
        System.out.println("5. Modifica inchiriere");
        System.out.println("6. Sterge inchiriere");
        System.out.println("7. Afiseaza masinile dupa nr inchirieri");
        System.out.println("a. Afiseaza masinile");
        System.out.println("i. Afiseaza inchirierile");
        System.out.println("x. Iesire");
        System.out.print("\nOptiune: ");
    }
    private void Hardcoded() throws IOException {
        try{
            this.masinaService.add(1, "Jaguar", "XE");
            this.masinaService.add(2, "Buick", "Regal");
            this.masinaService.add(3, "Ford", "Explorer");
            this.masinaService.add(4, "Toyota", "Corrola");
            this.masinaService.add(5, "Porsche", "Panamera");
        }
        catch (RuntimeException e)
        {
            System.out.println(e);
        }
    }
}
