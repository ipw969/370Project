package ui;

import actions.DeleteEquipmentAction;
import actions.DeleteSceneAction;
import actions.DeleteVolunteerAction;
import businessobjects.BusinessObjectList;
import businessobjects.Equipment;
import businessobjects.Scene;
import businessobjects.Script;
import businessobjects.Volunteer;
import database.Database;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import moviescheduler.MovieSchedulerController;

/**
 * Ui class representing a panel of information for the different resources for
 * the system, including Scenes, Equipment and Volunteers.
 *
 * @author Iain Workman
 */
public class ResourcePanel extends javax.swing.JPanel {

    /**
     * Creates new form ResourcePanel
     *
     * @param controller::MovieSchedulerController ~ The controller for the
     * system
     */
    public ResourcePanel(MovieSchedulerController controller) {
        initComponents();
        this.controller = controller;
        volunteerList = new EditableBusinessObjectListView<>(controller.getVolunteers());
        registerVolunteerActionListeners();
        equipmentList = new EditableBusinessObjectListView<>(controller.getEquipment());
        registerEquipmentActionListeners();
        sceneList = new EditableBusinessObjectListView<>(controller.getScenes());
        registerSceneActionListeners();
        volunteerListPanel.add(volunteerList);
        equipmentListPanel.add(equipmentList);
        sceneListPanel.add(sceneList);
    }

    /**
     * Registers all the action listeners to respond to Add, Edit and Delete for
     * the VolunteerList
     */
    private void registerVolunteerActionListeners() {
        volunteerList.addAddActionListener(
                new BusinessObjectListViewAddActionListener<Volunteer>() {

                    @Override
                    public void onAddActionPerformed(BusinessObjectListViewAddEvent<Volunteer> event) {
                        controller.displayVolunteerMenu(null);
                    }

                });

        volunteerList.addEditActionListener(
                new BusinessObjectListViewEditActionListener() {

                    @Override
                    public void onEditActionPerformed(BusinessObjectListViewEditEvent event) {
                        controller.displayVolunteerMenu((Volunteer) event.getObjectBeingEdited());
                    }

                });

        volunteerList.addDeleteActionListener(
                new BusinessObjectListViewDeleteActionListener<Volunteer>() {

                    @Override
                    public void onDeleteActionPerformed(BusinessObjectListViewDeleteEvent<Volunteer> event) {

                        if (!(event.getObjectBeingDeleted() instanceof Volunteer)) {
                            return;
                        }

                        controller.deleteBusinessObject(event.getObjectBeingDeleted());

                    }

                });

        volunteerList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (volunteerList.getSelectedValue() == null) {
                    return;
                }

                Volunteer selectedVolunteer = volunteerList.getSelectedValue();

                itemBriefTextField.setText(selectedVolunteer.toString());
                itemDescriptionTextArea.setText(selectedVolunteer.toDescriptiveString());
            }

        });
    }

    /**
     * Registers all the action listeners to respond to Add, Edit and Delete for
     * the EquipmentList
     */
    private void registerEquipmentActionListeners() {
        equipmentList.addAddActionListener(
                new BusinessObjectListViewAddActionListener<Equipment>() {

                    @Override
                    public void onAddActionPerformed(BusinessObjectListViewAddEvent<Equipment> event) {
                        controller.displayEquipmentMenu(null);
                    }

                });

        equipmentList.addEditActionListener(
                new BusinessObjectListViewEditActionListener() {

                    @Override
                    public void onEditActionPerformed(BusinessObjectListViewEditEvent event) {
                        controller.displayEquipmentMenu((Equipment) event.getObjectBeingEdited());
                    }

                });

        equipmentList.addDeleteActionListener(
                new BusinessObjectListViewDeleteActionListener<Equipment>() {

                    @Override
                    public void onDeleteActionPerformed(BusinessObjectListViewDeleteEvent<Equipment> event) {

                        if (!(event.getObjectBeingDeleted() instanceof Equipment)) {
                            return;
                        }

                        controller.deleteBusinessObject(event.getObjectBeingDeleted());

                    }

                });

        equipmentList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (equipmentList.getSelectedValue() == null) {
                    return;
                }

                Equipment selectedEquipment = equipmentList.getSelectedValue();

                itemBriefTextField.setText(selectedEquipment.toString());
                itemDescriptionTextArea.setText(selectedEquipment.toDescriptiveString());
            }

        });
    }

    /**
     * Registers all the action listeners to respond to Add, Edit and Delete for
     * the SceneList
     */
    private void registerSceneActionListeners() {
        sceneList.addAddActionListener(
                new BusinessObjectListViewAddActionListener<Scene>() {

                    @Override
                    public void onAddActionPerformed(BusinessObjectListViewAddEvent<Scene> event) {
                        controller.displaySceneMenu(null);
                    }

                });

        sceneList.addEditActionListener(
                new BusinessObjectListViewEditActionListener() {

                    @Override
                    public void onEditActionPerformed(BusinessObjectListViewEditEvent event) {
                        controller.displaySceneMenu((Scene) event.getObjectBeingEdited());
                    }

                });

        sceneList.addDeleteActionListener(
                new BusinessObjectListViewDeleteActionListener<Scene>() {

                    @Override
                    public void onDeleteActionPerformed(BusinessObjectListViewDeleteEvent<Scene> event) {

                        if (!(event.getObjectBeingDeleted() instanceof Scene)) {
                            return;
                        }

                        controller.deleteBusinessObject(event.getObjectBeingDeleted());

                    }

                });

        sceneList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (sceneList.getSelectedValue() == null) {
                    return;
                }

                Scene selectedScene = sceneList.getSelectedValue();

                itemBriefTextField.setText(selectedScene.toString());
                itemDescriptionTextArea.setText(selectedScene.toDescriptiveString());
            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        sidePanel = new javax.swing.JPanel();
        volunteersPanel = new javax.swing.JPanel();
        volunteersLabel = new javax.swing.JLabel();
        volunteerListPanel = new javax.swing.JPanel();
        equipmentPanel = new javax.swing.JPanel();
        equipmentLabel = new javax.swing.JLabel();
        equipmentListPanel = new javax.swing.JPanel();
        scenesPanel = new javax.swing.JPanel();
        scenesLabel = new javax.swing.JLabel();
        sceneListPanel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        descriptionScrollPane = new javax.swing.JScrollPane();
        itemDescriptionTextArea = new javax.swing.JTextArea();
        itemBriefTextField = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );

        setLayout(new java.awt.BorderLayout());

        sidePanel.setMaximumSize(new java.awt.Dimension(176, 32767));
        sidePanel.setLayout(new java.awt.GridLayout(3, 0));

        volunteersLabel.setText("Volunteers:");

        volunteerListPanel.setBackground(new java.awt.Color(255, 255, 255));
        volunteerListPanel.setMaximumSize(new java.awt.Dimension(250, 2147483647));
        volunteerListPanel.setPreferredSize(new java.awt.Dimension(250, 83));
        volunteerListPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout volunteersPanelLayout = new javax.swing.GroupLayout(volunteersPanel);
        volunteersPanel.setLayout(volunteersPanelLayout);
        volunteersPanelLayout.setHorizontalGroup(
            volunteersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(volunteersPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(volunteersLabel)
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(volunteersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(volunteerListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        volunteersPanelLayout.setVerticalGroup(
            volunteersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(volunteersPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(volunteersLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(volunteerListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        sidePanel.add(volunteersPanel);

        equipmentLabel.setText("Equipment:");

        equipmentListPanel.setBackground(new java.awt.Color(255, 255, 255));
        equipmentListPanel.setMaximumSize(new java.awt.Dimension(190, 2147483647));
        equipmentListPanel.setPreferredSize(new java.awt.Dimension(190, 83));
        equipmentListPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout equipmentPanelLayout = new javax.swing.GroupLayout(equipmentPanel);
        equipmentPanel.setLayout(equipmentPanelLayout);
        equipmentPanelLayout.setHorizontalGroup(
            equipmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(equipmentPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(equipmentLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, equipmentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(equipmentListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addContainerGap())
        );
        equipmentPanelLayout.setVerticalGroup(
            equipmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(equipmentPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(equipmentLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipmentListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        sidePanel.add(equipmentPanel);

        scenesLabel.setText("Scenes:");

        sceneListPanel.setBackground(new java.awt.Color(255, 255, 255));
        sceneListPanel.setMaximumSize(new java.awt.Dimension(190, 2147483647));
        sceneListPanel.setPreferredSize(new java.awt.Dimension(190, 76));
        sceneListPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout scenesPanelLayout = new javax.swing.GroupLayout(scenesPanel);
        scenesPanel.setLayout(scenesPanelLayout);
        scenesPanelLayout.setHorizontalGroup(
            scenesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scenesPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(scenesLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(scenesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sceneListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addContainerGap())
        );
        scenesPanelLayout.setVerticalGroup(
            scenesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scenesPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(scenesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sceneListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        sidePanel.add(scenesPanel);

        add(sidePanel, java.awt.BorderLayout.LINE_START);

        itemDescriptionTextArea.setEditable(false);
        itemDescriptionTextArea.setColumns(20);
        itemDescriptionTextArea.setRows(5);
        descriptionScrollPane.setViewportView(itemDescriptionTextArea);

        itemBriefTextField.setEditable(false);
        itemBriefTextField.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        itemBriefTextField.setToolTipText("");
        itemBriefTextField.setBorder(null);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(itemBriefTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(itemBriefTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(mainPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JLabel equipmentLabel;
    private javax.swing.JPanel equipmentListPanel;
    private javax.swing.JPanel equipmentPanel;
    private javax.swing.JTextField itemBriefTextField;
    private javax.swing.JTextArea itemDescriptionTextArea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel sceneListPanel;
    private javax.swing.JLabel scenesLabel;
    private javax.swing.JPanel scenesPanel;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JPanel volunteerListPanel;
    private javax.swing.JLabel volunteersLabel;
    private javax.swing.JPanel volunteersPanel;
    // End of variables declaration//GEN-END:variables
    private final MovieSchedulerController controller;
    private final EditableBusinessObjectListView<Volunteer> volunteerList;
    private final EditableBusinessObjectListView<Equipment> equipmentList;
    private final EditableBusinessObjectListView<Scene> sceneList;
}
