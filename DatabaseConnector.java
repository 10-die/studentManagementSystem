import java.sql.*;

import javax.swing.JOptionPane;
public class DatabaseConnector 
{
    String url = "jdbc:mysql://localhost:3306/student_db";
    String username = "root";
    String password = "";
    String driver = "com.mysql.cj.jdbc.Driver";

    // default object constructor, create objects without parameters
    public DatabaseConnector()
    {
        
    }

    // 
    public void sqlSelectQuery()
    {
        try 
        {
            String query = "SELECT * FROM tblstudents";
            String output = "";
            //Establish connection 
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            
            //Place all records retrieved in a result get
            ResultSet rs = s.executeQuery(query);

            // Iterate through the result set and display the records on the screen
            while (rs.next()) 
            {
                System.out.println(rs.getString("ID") + " " + rs.getString("FullName") + " " + rs.getString("Mobile"));
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
    public void sqlAdd(String ID, String FullName, String Mobile) throws InstantiationException, IllegalAccessException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement("INSERT INTO tblstudents VALUES(?,?,?)");
            preparedStatement.setString(1, ID);
            preparedStatement.setString(2, FullName);
            preparedStatement.setString(3, Mobile);

            boolean b = preparedStatement.execute();
            if (b == true) 
                System.out.println("adding one row to tblstudents");
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
    public void sqlDelete(String ID) throws InstantiationException, IllegalAccessException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try 
        {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement("DELETE FROM tblstudents WHERE ID=?");
            preparedStatement.setString(1, ID);
            boolean b = preparedStatement.execute();
            if (b == true) 
                System.out.println("1 record deleted"); 
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
