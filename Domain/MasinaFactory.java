package Domain;

import Domain.Masina;

public class MasinaFactory implements IEntitateFactory<Masina> {
    @Override
    public Masina createEntitate(String line) {
        int id = Integer.parseInt(line.split(" ")[0]);
        String marca = line.split(" ")[1];
        String model = line.split(" ")[2];

        return new Masina(id, marca, model);
    }
    public static StringBuilder EntString(Masina m) {
        StringBuilder sb = new StringBuilder();
        sb.append(m.getID()).append(" ").append(m.getMarca()).append(" ").append(m.getModel());
        return sb;
    }
}
