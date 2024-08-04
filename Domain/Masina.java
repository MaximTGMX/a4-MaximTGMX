package Domain;

public class Masina extends Entitate {
    private static final long serialVersionUID = 1000L;
    private String marca;
    private String model;

    public Masina(int ID, String marca, String model)
    {
        super(ID);
        this.marca = marca;
        this.model = model;
    }

    public String getMarca() {return marca;}
    public String getModel() {return model;}
    @Override
    public String toString()
    {
        return "ID: " + id + ", Marca: " + marca + ", Model: " + model;
    }
}
