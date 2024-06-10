import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;

public class GUI 
{
    DatabaseConnector dbConnector = new DatabaseConnector();
    //
    public void setMainGUI()
    {
        //
        JFrame frameMain = new JFrame();
        JPanel panelMain = new JPanel();

        panelMain.setLayout(null);
        frameMain.setSize(600,400);
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblID = new JLabel("ID:");
        lblID.setBounds(0, 0, 100, 30);
        panelMain.add(lblID);

        JTextField txfID = new JTextField();
        txfID.setBounds(100, 0, 300, 30);
        panelMain.add(txfID);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(0, 50, 100, 30);
        panelMain.add(lblName);

        JTextField txfName = new JTextField();
        txfName.setBounds(100, 50, 300, 30);
        panelMain.add(txfName);

        JLabel lblMobile = new JLabel("Mobile:");
        lblMobile.setBounds(0, 100, 100, 30);
        panelMain.add(lblMobile);

        JTextField txfMobile = new JTextField();
        txfMobile.setBounds(100, 100, 300, 30);
        panelMain.add(txfMobile);

        JButton buttonAdd = new JButton("ADD");
        buttonAdd.setBounds(0, 300, 120, 30);
        buttonAdd.addActionListener(new AbstractAction("Add entry to table")
        {
            public void actionPerformed(ActionEvent add)
            {
                try 
                {
                    dbConnector.sqlAdd(txfID.getText(), txfName.getText(), txfMobile.getText());
                } 
                catch (InstantiationException | IllegalAccessException e) 
                {
                    e.printStackTrace();
                }
            }
        });
        panelMain.add(buttonAdd);

        JButton buttonViewStudent = new JButton("VIEW STUDENT");
        buttonViewStudent.setBounds(150, 300, 120, 30);
        buttonViewStudent.addActionListener(new AbstractAction("Add entry to table")
        {
            public void actionPerformed(ActionEvent view)
            {
                dbConnector.sqlSelectQuery();
            }
        });
        panelMain.add(buttonViewStudent);

        JButton buttonSearch = new JButton("SEARCH");
        buttonSearch.setBounds(300, 300, 120, 30);
        panelMain.add(buttonSearch);

        JButton buttonDelete = new JButton("DELETE");
        buttonDelete.setBounds(450, 300, 120, 30);
        panelMain.add(buttonDelete);

        frameMain.add(panelMain);
        frameMain.setVisible(true);
    }
}
