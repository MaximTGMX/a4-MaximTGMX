package Repository;

import Domain.Inchiriere;
import Domain.Masina;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InchiriereDBRepo extends MemoryRepository<Inchiriere> implements iDBRepository<Inchiriere> {
    private String JDBC_SRL = "jdbc:sqlite:inchiriere.db";
    private Connection connection;
    public InchiriereDBRepo()
    {
        connectToDB();
        createTable();
        initTable();
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
            stmt.execute("CREATE TABLE IF NOT EXISTS inchirieri(id int, id_masina int, marca String, model String, begin_data String, end_date String);");
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initTable()
    {
        List<Inchiriere> Inchirieri = new ArrayList<>();
        Inchirieri.add(new Inchiriere(1,new Masina(1,"Jaguar","XE"),"12/12/2023","13/12/2023"));
        Inchirieri.add(new Inchiriere(2,new Masina(1,"Jaguar","XE"),"13/12/2023","14/12/2023"));
        Inchirieri.add(new Inchiriere(3,new Masina(1,"Jaguar","XE"),"14/12/2023","15/12/2023"));
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO inchirieri values(?,?,?,?,?,?);"))
        {
            for (Inchiriere i: Inchirieri)
            {
                stmt.setInt(1,i.getID());
                stmt.setInt(2,i.getMasina().getID());
                stmt.setString(3,i.getMasina().getMarca());
                stmt.setString(4,i.getMasina().getModel());
                stmt.setString(5,i.getBeginDate());
                stmt.setString(6,i.getEndDate());
                stmt.executeUpdate();
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void add(Inchiriere i)
    {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO inchirieri values(?,?,?,?,?,?);"))
        {
            stmt.setInt(1,i.getID());
            stmt.setInt(2,i.getMasina().getID());
            stmt.setString(3,i.getMasina().getMarca());
            stmt.setString(4,i.getMasina().getModel());
            stmt.setString(5,i.getBeginDate());
            stmt.setString(6,i.getEndDate());
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void modify(Inchiriere i)
    {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE inchirieri set id=?, id_masina=?, marca=?, model=?, begin_date=?, end_date=? where id=?;"))
        {
            stmt.setInt(1,i.getID());
            stmt.setInt(2,i.getMasina().getID());
            stmt.setString(3,i.getMasina().getMarca());
            stmt.setString(4,i.getMasina().getModel());
            stmt.setString(5,i.getBeginDate());
            stmt.setString(6,i.getEndDate());
            stmt.setInt(7,i.getID());
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void remove(int id)
    {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE from inchirieri where id=?;"))
        {
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Inchiriere find(int id)
    {
        Inchiriere i;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * from inchirieri where id=?;"))
        {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            i = new Inchiriere(rs.getInt(1), new Masina(rs.getInt(2), rs.getString(3), rs.getString(4)), rs.getString(5), rs.getString(6));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }
    @Override
    public ArrayList<Inchiriere> getAll()
    {
        ArrayList<Inchiriere> Inchirieri = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * from inchirieri;"))
        {
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Inchiriere i = new Inchiriere(rs.getInt(1), new Masina(rs.getInt(2),rs.getString(3),rs.getString(4)), rs.getString(5), rs.getString(6));
                Inchirieri.add(i);
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return Inchirieri;
    }
}
