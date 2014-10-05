/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author iain
 */
public class MainMenu extends javax.swing.JFrame {

    /**
     * 
     * @throws SQLException 
     */
    public MainMenu() throws SQLException {
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        topMenuPanel = new javax.swing.JPanel();
        leftMenuPanel = new javax.swing.JPanel();
        voluteerLabel = new javax.swing.JLabel();
        volunteersScrollPane = new javax.swing.JScrollPane();
        volunteersList = new javax.swing.JList();
        equipmentLabel = new javax.swing.JLabel();
        equipmentScrollPane = new javax.swing.JScrollPane();
        equipmentList = new javax.swing.JList();
        sceneLabel = new javax.swing.JLabel();
        sceneScrollPanel = new javax.swing.JScrollPane();
        sceneList = new javax.swing.JList();

        setPreferredSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        mainPanel.setBackground(new java.awt.Color(255, 153, 153));
        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        topMenuPanel.setBackground(new java.awt.Color(153, 153, 255));
        getContentPane().add(topMenuPanel, java.awt.BorderLayout.PAGE_START);

        leftMenuPanel.setLayout(new javax.swing.BoxLayout(leftMenuPanel, javax.swing.BoxLayout.PAGE_AXIS));

        voluteerLabel.setText("Volunteers");
        leftMenuPanel.add(voluteerLabel);

        volunteersList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        volunteersScrollPane.setViewportView(volunteersList);

        leftMenuPanel.add(volunteersScrollPane);

        equipmentLabel.setText("Equipment");
        leftMenuPanel.add(equipmentLabel);

        equipmentList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        equipmentScrollPane.setViewportView(equipmentList);

        leftMenuPanel.add(equipmentScrollPane);

        sceneLabel.setText("Scenes");
        leftMenuPanel.add(sceneLabel);

        sceneList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        sceneScrollPanel.setViewportView(sceneList);

        leftMenuPanel.add(sceneScrollPanel);

        getContentPane().add(leftMenuPanel, java.awt.BorderLayout.LINE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                new MainMenu().setVisible(true);
                } catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, "Could not load main" +
                            " menu.");
                }
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel equipmentLabel;
    private javax.swing.JList equipmentList;
    private javax.swing.JScrollPane equipmentScrollPane;
    private javax.swing.JPanel leftMenuPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel sceneLabel;
    private javax.swing.JList sceneList;
    private javax.swing.JScrollPane sceneScrollPanel;
    private javax.swing.JPanel topMenuPanel;
    private javax.swing.JList volunteersList;
    private javax.swing.JScrollPane volunteersScrollPane;
    private javax.swing.JLabel voluteerLabel;
    // End of variables declaration//GEN-END:variables
}
