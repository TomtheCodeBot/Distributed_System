package step3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
  public static Connection getConnection()
    throws SQLException
  {
    try
    {
      Class.forName("org.sqlite.JDBC");
      return DriverManager.getConnection("jdbc:sqlite:distrubuted_system.sqlite");
    }
    catch (ClassNotFoundException|SQLException ex)
    {
      ex.printStackTrace();
    }
    return null;
  }
}
