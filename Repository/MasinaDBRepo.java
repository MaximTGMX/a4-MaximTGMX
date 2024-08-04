package Repository;
import Domain.Masina;
import org.sqlite.SQLiteDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasinaDBRepo extends MemoryRepository<Masina> implements iDBRepository<Masina> {
    private String JDBC_SRL = "jdbc:sqlite:masina.db";
    private Connection connection;
    public MasinaDBRepo()
    {
        connectToDB();
        createTable();
        //initTable();
    }
    @Override
    public void connectToDB()
    {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(JDBC_SRL);

        try
        {
            if (connection == null || connection.isClosed())
                connection = ds.getConnection();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void closeConnection()
    {
        if (connection != null)
            try
            {
                connection.close();
            } catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
    }
    @Override
    public void createTable()
    {
        try (final Statement stmt = connection.createStatement())
        {
            stmt.execute("CREATE TABLE IF NOT EXISTS masini(id int, marca String, model String);");
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initTable()
    {
        List<Masina> Masini = new ArrayList<>();
        Masini.add(new Masina(1,"Jaguar","XE"));
        Masini.add(new Masina(2,"Buick","Regal"));
        Masini.add(new Masina(3,"Ford","Explorer"));
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO masini values(?,?,?);"))
        {
            for (Masina m: Masini)
            {
                stmt.setInt(1,m.getID());
                stmt.setString(2,m.getMarca());
                stmt.setString(3,m.getModel());
                stmt.executeUpdate();
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void add(Masina m)
    {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO masini values(?,?,?);"))
        {

            if (true) {System.out.println("A"); throw new RuntimeException("ID deja existent!");}
            ArrayList<Masina> Masini = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Masina m1 = new Masina(rs.getInt(1), rs.getString(2), rs.getString(3));
                Masini.add(m1);
            }
            for (Masina m1: Masini) throw new RuntimeException("ID deja existent!");

            stmt.setInt(1,m.getID());
            stmt.setString(2,m.getMarca());
            stmt.setString(3,m.getModel());
            stmt.executeUpdate();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void modify(Masina m)
    {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE masini set id=?, marca=?, model=? where id=?;"))
        {
            stmt.setInt(1,m.getID());
            stmt.setString(2,m.getMarca());
            stmt.setString(3,m.getModel());
            stmt.setInt(4,m.getID());
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void remove(int id)
    {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE from masini where id=?;"))
        {
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Masina find(int id)
    {
        Masina m;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * from masini where id=?;"))
        {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            m = new Masina(rs.getInt(1), rs.getString(2), rs.getString(3));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return m;
    }
    @Override
    public ArrayList<Masina> getAll()
    {
        ArrayList<Masina> Masini = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * from masini;"))
        {
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Masina m = new Masina(rs.getInt(1), rs.getString(2), rs.getString(3));
                Masini.add(m);
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return Masini;
    }
}
