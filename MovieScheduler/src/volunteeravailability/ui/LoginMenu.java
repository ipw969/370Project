/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volunteeravailability.ui;

import actions.LoadVolunteerAction;
import businessobjects.BusinessObjectList;
import businessobjects.TimeInterval;
import businessobjects.Volunteer;
import database.Database;
import database.JdbcDatabase;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import volunteeravailability.login.Login;
import ui.ErrorDisplay;

/**
 * This class is the login screen shownwhen the Volunteer Availabaility application is launched.
 * @author matthewgalbraith
 * 
 */
public class LoginMenu extends javax.swing.JFrame {

    private final Database database;
    
    public LoginMenu(Database database) {
        this.initComponents();
        this.database = database;
    }
    public LoginMenu() {
        this.initComponents();
        database = null;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginPanel = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        pswdField = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        loginLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        loginLabel.setText("Volunteer Login");

        usernameLabel.setText("Username (email):");

        passwordLabel.setText("Password:");

        nameField.setText("abc@xyz.com");
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        pswdField.setText("jPasswordField1");

        jButton1.setText("Forgot password?");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameLabel)
                            .addComponent(passwordLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(nameField)
                                .addComponent(pswdField)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loginLabel)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameLabel)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordLabel)
                            .addComponent(pswdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(okButton)
                            .addComponent(cancelButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        //Notify user if there are missing fields
        if (nameField.getText().isEmpty() || 
                new String(pswdField.getPassword()).isEmpty()) {
            ErrorDisplay missingInfoError = new ErrorDisplay(this, "Username and password"
                    + " fields can not be left blank! Please make sure you have"
                    + "entered a valid username (e-mail) and password.");
            missingInfoError.setVisible(true);
        }
        else if (nameField.getText().equals("Passme")) {
            /*FOR DEMONSTRATION AND TESTING:
             *Passme is a dummy username that skips authentication, and loads 
             *hard coded volunteer information from the client.
             */
            Volunteer testVolunteer = new Volunteer("TestPerson", "LoggedinSucessfully", "theTest@test.test", "867-5309");
            GregorianCalendar testIntervalStart = new GregorianCalendar(2014, 11, 01, 12, 0);
            GregorianCalendar testIntervalEnd = new GregorianCalendar(2014, 11, 01, 15, 0);
            TimeInterval testTimeInterval = new TimeInterval(testIntervalStart, testIntervalEnd);
            testVolunteer.addAvailability(testTimeInterval);
            VolunteerMainMenu testMainMenu = new VolunteerMainMenu(testVolunteer, database);
            testMainMenu.setVisible(true);
            this.setVisible(false);
            this.repaint();
        }
        else {
            //initialize login 
            Login login = new Login(database, nameField.getText(), new String(pswdField.getPassword()));
            try {
                login.sendUsernamePassword();
            } catch (SQLException ex) {
                Logger.getLogger(LoginMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            //create main menu with volunteer information if user validated
            if (login.userValidated()) {
                LoadVolunteerAction loadVolunteer = new LoadVolunteerAction(nameField.getText(), database);
                loadVolunteer.run();
                Volunteer volunteer = (Volunteer) loadVolunteer.getBusinessObject();
                
                VolunteerMainMenu mainMenu = new VolunteerMainMenu(volunteer, database);
                mainMenu.setVisible(true);
                this.dispose();
                this.setVisible(false);
            } 
            else { /*invalid username or password*/
                ErrorDisplay invalidCredentialsError = new ErrorDisplay(this, "Sorry, you have entered"
                        + "and invalid username and password combination."
                        + "\nPlease try again!");
                invalidCredentialsError.setVisible(true);
            }
        }
    
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ForgotPasswordDialog forgotPassword = new ForgotPasswordDialog(this, true);
        forgotPassword.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JTextField nameField;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField pswdField;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
