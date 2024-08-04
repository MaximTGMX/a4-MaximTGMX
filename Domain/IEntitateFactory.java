package Domain;

public interface IEntitateFactory<T extends Entitate> {
    public T createEntitate(String line);
}
