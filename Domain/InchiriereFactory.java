package Domain;

import Domain.Inchiriere;
import Domain.Masina;

import java.io.IOException;

public class InchiriereFactory implements IEntitateFactory<Inchiriere> {
    @Override
    public Inchiriere createEntitate(String line) {
        int id = Integer.parseInt(line.split(" ")[0]);
        int id_m = Integer.parseInt(line.split(" ")[1]);
        String marca = line.split(" ")[2];
        String model = line.split(" ")[3];
        String b_date = line.split(" ")[4];
        String e_date = line.split(" ")[5];
        Masina masina = new Masina(id_m,marca,model);
        return new Inchiriere(id, masina, b_date, e_date);
    }
    public static StringBuilder EntString(Inchiriere i) {
        StringBuilder sb = new StringBuilder();
        sb.append(i.getID()).append(" ").append(i.getMasina().getID()).append(" ").append(i.getMasina().getMarca()).
                append(" ").append(i.getMasina().getModel()).append(" ").append(i.getBeginDate()).append(" ").append(i.getEndDate());
        return sb;
    }
}
