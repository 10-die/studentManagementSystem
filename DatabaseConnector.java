import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javafx.scene.control.TextField;

import javax.swing.JFrame;
public class DatabaseConnector 
{
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER_JDBC = "com.mysql.cj.jdbc.Driver";
    String updateKey = "";

    // default object constructor, create objects without parameters
    public DatabaseConnector()
    {
        
    }

    // mutator method, create new table in database
    public void setTable()
    {
        Connection connection = null;
        Statement statement = null;
        try 
        {
            Class.forName(DRIVER_JDBC);
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            boolean b  = statement.execute("CREATE TABLE tblstudent(ID int primary key, fullName varchar(50), mobile varchar(50))");
            if (b == true)
                JOptionPane.showMessageDialog(null, "tblstudent created successfully");
        } 
        catch (SQLException sqlEx) 
        {
            sqlEx.printStackTrace();
            System.exit(1);
        }
        catch (ClassNotFoundException clsNotFoundEx)
        {
            clsNotFoundEx.printStackTrace();
            System.exit(1);
        }
        finally
        {
            try
            {
                statement.close();
                connection.close();
            }
            catch (Exception ex)
            {
                System.exit(1);
            }
        }
    }
    // 
    public void sqlSelectQuery()
    {
        try 
        {
            String query = "SELECT * FROM tblstudent";
            String output = "";
            //Establish connection 
            Class.forName(DRIVER_JDBC).newInstance();
            Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            Statement s = conn.createStatement();
            
            //Place all records retrieved in a result get
            ResultSet rs = s.executeQuery(query);

            // Iterate through the result set and display the records on the screen
            while (rs.next()) 
            {
                output = output + rs.getString("ID") + " " + rs.getString("FullName") + " " + rs.getString("Mobile") + "\n";
            }
            conn.close();

            JOptionPane.showMessageDialog(null, output);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) 
        {
            e.printStackTrace();
        }
    }

    //
    public void sqlAdd(String ID, String fullName, String mobile) throws InstantiationException, IllegalAccessException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            Class.forName(DRIVER_JDBC).newInstance();
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO tblstudent VALUES(?,?,?)");
            preparedStatement.setString(1, ID);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, mobile);

            boolean b = preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "student added to the table");
        }
        catch (SQLException sqlExeException)
        {
            sqlExeException.printStackTrace();
            System.exit(1);
        }
        catch (ClassNotFoundException classNotFoundException)
        {
            classNotFoundException.printStackTrace();
            System.exit(1);
        }
        finally
        {
            try
            {
                preparedStatement.close();
                connection.close();
            }
            catch (Exception e)
            {
                System.exit(1);
            }
        }
    }

    //
    public void sqlDelete(String ID, JTextField txfID) throws InstantiationException, IllegalAccessException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try 
        {
            Class.forName(DRIVER_JDBC).newInstance();
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM tblstudent WHERE ID=?");
            preparedStatement.setString(1, ID);
            boolean b = preparedStatement.execute();
            txfID.setText("");
            JOptionPane.showMessageDialog(null, "Deleted StudentID " + ID);
        } 
        catch (SQLException sqlEx) 
        {
            sqlEx.printStackTrace();
            System.exit(1);
        }
        catch (ClassNotFoundException clsNotFoundEx)
        {
            clsNotFoundEx.printStackTrace();
            System.exit(1);
        }
        finally
        {
            try 
            {
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                System.exit(1);
            }
        }
    }

    //

}
