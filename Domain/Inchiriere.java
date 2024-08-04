package Domain;

public class Inchiriere extends Entitate {
    private static final long serialVersionUID = 1000L;
    private String begin_date;
    private String end_date;
    private Masina masina;


    public Inchiriere(int ID, Masina masina, String begin_date, String end_date)
    {
        super(ID);
        this.masina = masina;
        this.begin_date = begin_date;
        this.end_date = end_date;
    }

    public Masina getMasina() {return masina;}
    public String getBeginDate() {return begin_date;}
    public String getEndDate() {return end_date;}
    @Override
    public String toString()
    {
        return "ID Inchiriere: " + id + ", Masina " + masina + ", Begin Date: " + begin_date + ", End Date: " + end_date;
    }
}
