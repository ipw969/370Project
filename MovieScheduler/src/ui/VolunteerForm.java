/**
 *
 * @author johnmason
 */
package ui;

import actions.SaveVolunteerAction;
import businessobjects.Volunteer;
import businessobjects.*;
import database.Database;
import database.JdbcDatabase;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import moviescheduler.MovieSchedulerController;

/**
 * A ui class for editing or adding Volunteers
 *
 * @author johnmason
 */
public class VolunteerForm extends javax.swing.JFrame
        implements BusinessObjectListener {

    BusinessObjectList<TimeInterval> availabilityList = new BusinessObjectList();
    private final MovieSchedulerController controller;
    private final Volunteer originalVolunteer;
    private final Volunteer volunteerToEdit;
   
    /**
     * Creates a volunteer form and populates it with previous volunteer info
     */
    public VolunteerForm(Volunteer originalVolunteer, Volunteer volunteerToEdit, MovieSchedulerController controller) {
        

        this.originalVolunteer = originalVolunteer;
        this.volunteerToEdit = volunteerToEdit;
        this.controller = controller;
        initComponents();
        BusinessObjectListView<TimeInterval> availabilitiesListView
                = new BusinessObjectListView<>(volunteerToEdit.getAvailabilities());
        this.availabilitiesScrollPane.setViewportView(availabilitiesListView);
        PopulateFormForEdit(volunteerToEdit);
        this.firstNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateFirstName();
            }

            public void removeUpdate(DocumentEvent e) {
                updateFirstName();
            }

            public void insertUpdate(DocumentEvent e) {
                updateFirstName();
            }

            private void updateFirstName() {
                volunteerToEdit.setFirstName(firstNameTextField.getText());

            }
        });

        this.lastNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateLastName();
            }

            public void removeUpdate(DocumentEvent e) {
                updateLastName();
            }

            public void insertUpdate(DocumentEvent e) {
                updateLastName();
            }

            private void updateLastName() {
                volunteerToEdit.setLastName(lastNameTextField.getText());
            }
        });

        this.emailTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateEmail();
            }

            public void removeUpdate(DocumentEvent e) {
                updateEmail();
            }

            public void insertUpdate(DocumentEvent e) {
                updateEmail();
            }

            private void updateEmail() {
                volunteerToEdit.setEmail(emailTextField.getText());
            }
        });

        this.phoneNumberTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updatePhoneNumber();
            }

            public void removeUpdate(DocumentEvent e) {
                updatePhoneNumber();
            }

            public void insertUpdate(DocumentEvent e) {
                updatePhoneNumber();
            }

            private void updatePhoneNumber() {
                volunteerToEdit.setPhoneNumber(phoneNumberTextField.getText());
            }
        });
    }

    // Private Methods
    @Override
    public void validStateAltered(boolean newState, BaseBusinessObject sender) {
        okayButton.setEnabled(newState);
        errorIcon.setVisible(!newState);

        if (!newState) {
            errorIcon.setToolTipText(sender.getErrorMessage());
        } else {
            errorIcon.setToolTipText("");
        }
    }

    @Override
    public void changedStateAltered(boolean newState, BaseBusinessObject sender) {
        okayButton.setEnabled(sender.isValid() && newState);
    }

    private void save() 
    {
        controller.saveBusinessObject(originalVolunteer, volunteerToEdit);
    }

    private boolean confirmClose() {
        if (volunteerToEdit.hasChanged()) {
            if (volunteerToEdit.isValid()) {
                int confirm = JOptionPane.showOptionDialog(this,
                        "Changes have been made, do you wish to save?",
                        "Save Changes?",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, null, null);

                if (confirm == JOptionPane.YES_OPTION) {
                    save();
                    return true;
                } else if (confirm == JOptionPane.NO_OPTION) {
                    return true;
                }
            } else {
                int confirm = JOptionPane.showOptionDialog(
                        this,
                        "Changes have been made, but cannot be saved because "
                        + "they are not valid. Do you wish to return to editing",
                        "Return to editing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null, null, null);

                if (confirm == JOptionPane.NO_OPTION) {
                    return true;
                }
            }
        } else {
            return true;
        }

        return false;
    }

    //method to populate the form with previously entered information
    private void PopulateFormForEdit(Volunteer volunteerToEdit) {
        if(volunteerToEdit.isNew())
        {
            firstNameTextField.setText(volunteerToEdit.getFirstName());
            lastNameTextField.setText(volunteerToEdit.getLastName());
            emailTextField.setText(volunteerToEdit.getEmail());
            phoneNumberTextField.setText(volunteerToEdit.getPhoneNumber());
        }
        
        errorIcon.setVisible(!volunteerToEdit.isValid());

        validStateAltered(volunteerToEdit.isValid(), volunteerToEdit);
        okayButton.setEnabled(false);

        volunteerToEdit.addListener(this);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        firstNameTextField = new javax.swing.JTextField();
        lastNameTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        phoneNumberTextField = new javax.swing.JTextField();
        addAvailabilityLabel = new javax.swing.JLabel();
        startAvailabilityLabel = new javax.swing.JLabel();
        endAvailabilityLabel = new javax.swing.JLabel();
        addAvailabilityButton = new javax.swing.JButton();
        currentAvailabilitiesLabel = new javax.swing.JLabel();
        startAvailabilitySpinner = new javax.swing.JSpinner();
        endAvailabilitySpinner = new javax.swing.JSpinner();
        firstNameLabel = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        phoneNumberLabel = new javax.swing.JLabel();
        availabilitiesScrollPane = new javax.swing.JScrollPane();
        topPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        errorIcon = new javax.swing.JLabel();
        bottomPanel = new javax.swing.JPanel();
        okayButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Volunteer");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        firstNameTextField.setText("First Name");
        firstNameTextField.setName(""); // NOI18N

        lastNameTextField.setText("Last Name ");
        lastNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameTextFieldActionPerformed(evt);
            }
        });

        emailTextField.setText("Email Address");

        phoneNumberTextField.setText("Phone Number");

        addAvailabilityLabel.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        addAvailabilityLabel.setText("Add an Availability:");
        addAvailabilityLabel.setToolTipText("Add a start time and an endtime to indicate your availability.\nClick add once you have indicated a time and it will be added to your current availabilities.");

        startAvailabilityLabel.setText("Start:");

        endAvailabilityLabel.setText("End:");

        addAvailabilityButton.setText("Add Availability");
        addAvailabilityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAvailabilityButtonActionPerformed(evt);
            }
        });

        currentAvailabilitiesLabel.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        currentAvailabilitiesLabel.setText("Current Availabilities:");

        startAvailabilitySpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR));

        endAvailabilitySpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR));

        firstNameLabel.setText("First Name:");

        lastNameLabel.setText("Last Name:");

        emailLabel.setText("Email Address:");

        phoneNumberLabel.setText("Phone Number:");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstNameLabel)
                            .addComponent(lastNameLabel)
                            .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneNumberLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(phoneNumberTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(lastNameTextField)
                            .addComponent(emailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(firstNameTextField)))
                    .addComponent(addAvailabilityLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(availabilitiesScrollPane)
                    .addComponent(currentAvailabilitiesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addAvailabilityButton)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startAvailabilityLabel)
                            .addComponent(endAvailabilityLabel))
                        .addGap(99, 99, 99)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startAvailabilitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(endAvailabilitySpinner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameLabel)
                    .addComponent(currentAvailabilitiesLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lastNameLabel)
                            .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailLabel)
                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phoneNumberLabel)
                            .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(addAvailabilityLabel)
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startAvailabilitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startAvailabilityLabel))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(endAvailabilitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(endAvailabilityLabel))
                        .addGap(18, 18, 18)
                        .addComponent(addAvailabilityButton))
                    .addComponent(availabilitiesScrollPane))
                .addGap(10, 10, 10))
        );

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        titleLabel.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        titleLabel.setText("Volunteer");

        errorIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Error.png"))); // NOI18N

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 416, Short.MAX_VALUE)
                .addComponent(errorIcon)
                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(errorIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(topPanel, java.awt.BorderLayout.PAGE_START);

        okayButton.setText("Okay");
        okayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okayButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addContainerGap(390, Short.MAX_VALUE)
                .addComponent(okayButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap())
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okayButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        getContentPane().add(bottomPanel, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okayButtonActionPerformed
        this.save();
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_okayButtonActionPerformed

    /**
     * upon clicking this button the text in both the start and end dates are
     * added to the drop down list of availabilities
     */
    private void addAvailabilityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAvailabilityButtonActionPerformed

        //parse the values to date values
        Date sDate, eDate;
        sDate = (Date) startAvailabilitySpinner.getValue();
        eDate = (Date) endAvailabilitySpinner.getValue();

        //initialize the Gregorian dates
        GregorianCalendar startDate = new GregorianCalendar();
        GregorianCalendar endDate = new GregorianCalendar();

        //give the gregorian calendars the times obtained from the date values obtained from the gui
        startDate.setTime(sDate);
        endDate.setTime(eDate);
        TimeInterval timeInterval = new TimeInterval(startDate, endDate);

        volunteerToEdit.getAvailabilities().add(timeInterval);

    }//GEN-LAST:event_addAvailabilityButtonActionPerformed

    private void lastNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameTextFieldActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (confirmClose()) {
            volunteerToEdit.removeListener(this);
            this.dispose();
        } 
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VolunteerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VolunteerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VolunteerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VolunteerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Could not load database driver with "
                            + "message: " + ex.toString());
                    return;
                }

                JdbcDatabase testDatabase = null;
                try {
                    testDatabase = new JdbcDatabase(
                            "jdbc:postgresql://edjo.usask.ca/cmpt370_group06",
                            "cmpt370_group06",
                            "Raptorjesusisawesome55775");
                } catch (SQLException ex) {
                    System.out.println("Failed to connection to db with message: "
                            + ex.getMessage());
                    return;
                }
                //new VolunteerForm(theScript, testDatabase).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAvailabilityButton;
    private javax.swing.JLabel addAvailabilityLabel;
    private javax.swing.JScrollPane availabilitiesScrollPane;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel currentAvailabilitiesLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel endAvailabilityLabel;
    private javax.swing.JSpinner endAvailabilitySpinner;
    private javax.swing.JLabel errorIcon;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton okayButton;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JTextField phoneNumberTextField;
    private javax.swing.JLabel startAvailabilityLabel;
    private javax.swing.JSpinner startAvailabilitySpinner;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

}
