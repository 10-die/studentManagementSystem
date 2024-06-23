import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;

public class GUI 
{
    // dbConn object built to provide the GUI class with access to the DatabaseConnector classes methods
    private DatabaseConnector dbConn = new DatabaseConnector();

    // void method, when called, build and display Student Manager GUI,
    public void setMainGUI()
    {
        // frame containing the panel upon which the GUI components sit
        JFrame frameMain = new JFrame("Student Manager");
        JPanel panelMain = new JPanel();

        panelMain.setLayout(null);
        frameMain.setSize(500, 200);
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // text fields and labels for student ID, FullName & Mobile
        JLabel lblID = new JLabel("ID:");
        lblID.setBounds(0, 0, 100, 30);
        panelMain.add(lblID);

        JTextField txfID = new JTextField();
        txfID.setBounds(50, 0, 200, 30);
        panelMain.add(txfID);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(0, 35, 100, 30);
        panelMain.add(lblName);

        JTextField txfName = new JTextField();
        txfName.setBounds(50, 35, 200, 30);
        panelMain.add(txfName);

        JLabel lblMobile = new JLabel("Mobile:");
        lblMobile.setBounds(0, 70, 100, 30);
        panelMain.add(lblMobile);

        JTextField txfMobile = new JTextField();
        txfMobile.setBounds(50, 70, 200, 30);
        panelMain.add(txfMobile);

        // Buttons, used to access and or mutate the table via methods within the database connection class
        JButton buttonAdd = new JButton("ADD");
        buttonAdd.setBounds(0, 120, 70, 30);
        buttonAdd.addActionListener(new AbstractAction("Add entry to table")
        {
            public void actionPerformed(ActionEvent add)
            {
                try 
                {

                    if (txfMobile.getText().length() == 10 && txfMobile.getText().charAt(0) == '0') 
                    {
                            dbConn.sqlAdd(txfID.getText(), txfName.getText(), txfMobile.getText(), txfID, txfName, txfMobile, frameMain);
                    }    
                    else
                    {
                            JOptionPane.showMessageDialog(frameMain, "invalid mobile number format\n\nIt should start with 0 and have 10 digits", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (InstantiationException | IllegalAccessException e) 
                {
                    e.printStackTrace();
                }
            }
        });
        panelMain.add(buttonAdd);

        JButton buttonViewStudent = new JButton("VIEW STUDENT");
        buttonViewStudent.setBounds(90, 120, 120, 30);
        buttonViewStudent.addActionListener(new AbstractAction("Add entry to table")
        {
            public void actionPerformed(ActionEvent view)
            {
                dbConn.sqlSelectQuery(frameMain);
            }
        });
        panelMain.add(buttonViewStudent);

        JButton buttonSearch = new JButton("SEARCH");
        buttonSearch.setBounds(230, 120, 100, 30);
        panelMain.add(buttonSearch);

        JButton buttonDelete = new JButton("DELETE");
        buttonDelete.setBounds(350, 120, 100, 30);
        buttonDelete.addActionListener(new AbstractAction("remove entry from the table")
        {
            public void actionPerformed(ActionEvent remove)
            {
                try 
                {
                    dbConn.sqlDelete(txfID.getText(), txfID, txfName, txfMobile, frameMain);
                } 
                catch (InstantiationException | IllegalAccessException e) 
                {
                    e.printStackTrace();
                }
            }
        });
        panelMain.add(buttonDelete);

        frameMain.add(panelMain);
        frameMain.setVisible(true);
    }
}
