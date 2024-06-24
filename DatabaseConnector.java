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
        JOptionPane.showMessageDialog(null, "accessing tblstudent");
    }

    /*// mutator method, create new table in database
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
    } */

    // 
    public void sqlSelectAll(JFrame frameMain)
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

            JOptionPane.showMessageDialog(frameMain, "List Of Students\n\n" + output, "Student Information", 1);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // 
    public void sqlSearch(String ID, JFrame frameMain)
    {
        Connection connection = null;
        Statement statement = null;
        String output = "";
        try 
        {
            Class.forName(DRIVER_JDBC);
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tblstudent WHERE ID=" + ID);
            
            while (resultSet.next()) 
            {
                output = output + resultSet.getString("ID") + " " + resultSet.getString("FullName") + " " + resultSet.getString("Mobile");
            }

            JOptionPane.showMessageDialog(frameMain, "Student Information\n\n" + output, "Student Found", 1);

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
            } catch (Exception e) 
            {
                System.exit(1);
            }
        }
    }

    //
    public void sqlAdd(String ID, String Name, String Mobile, JTextField txfID, JTextField txfName, JTextField txfMobile,  JFrame frameMain) throws InstantiationException, IllegalAccessException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            Class.forName(DRIVER_JDBC).newInstance();
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO tblstudent VALUES(?,?,?)");
            preparedStatement.setString(1, ID);
            preparedStatement.setString(2, Name);
            preparedStatement.setString(3, Mobile);

            boolean b = preparedStatement.execute();

            txfID.setText("");
            txfName.setText("");
            txfMobile.setText("");

            JOptionPane.showMessageDialog(frameMain, "student added to the table" + "\nID: " + ID + "\nName: " + Name + "\nMobile: " + Mobile, "Success", 1);
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
    public void sqlDelete(String ID, JTextField txfID, JTextField txfName, JTextField txfMobile, JFrame frameMain) throws InstantiationException, IllegalAccessException
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
            txfName.setText("");
            txfMobile.setText("");
            JOptionPane.showMessageDialog(frameMain, "Student with ID " + ID + " deleted successfully", "Student Deleted", 1);
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
}