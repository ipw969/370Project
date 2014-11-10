package ui;

import businessobjects.Scene;
import businessobjects.Script;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 * A class representing a ui panel for managing the filming Schedule
 */
public class SchedulePanel extends JPanel{
    
    // Constructor
    /**
     * Creates an instance of a SchedulePanel which displays information for the
     * provided Script's Schedule
     * @param script::Script ~ The Script whose Schedule is to be displayed in
     * the SchedulePanel
     */
    public SchedulePanel(Script script)
    {
        if(script == null)
            throw new RuntimeException(
                    "Cannot pass null Script to ScehdulePanel");
        this.script = script;
        setLayout(new BorderLayout());
        
        initializeSceneList();
        
        initializeScheduleCalendar();
        
    }
    // Public Methods
    
    // Protected Methods
    
    // Private Methods
    /**
     * Initializes the list of Scenes which are present in the Script.
     */
    private void initializeSceneList()
    {
        JPanel sideScenePanel = new JPanel();
        sideScenePanel.setLayout(new BorderLayout());
        sideScenePanel.setBorder(new EmptyBorder(20,20,20,20));
        
        BusinessObjectListView<Scene> sceneListView = 
                new BusinessObjectListView<>(script.scenes());
        
        JScrollPane sceneListViewScrollPane = new JScrollPane();
        sceneListViewScrollPane.setViewportView(sceneListView);
        sideScenePanel.add(sceneListViewScrollPane, BorderLayout.CENTER);
        
        JPopupMenu sceneListViewPopupMenu = new JPopupMenu();
        JMenuItem scheduleSceneMenuItem = new JMenuItem("Schedule Scene...");
        
        sceneListView.addMouseListener( new MouseAdapter()
        {
                public void mousePressed(MouseEvent e)
                {
                        if ( SwingUtilities.isRightMouseButton(e) )
                        {
                            System.out.println("Pointer at (" + e.getPoint().x + ", " + e.getPoint().y + ")");
                            int indexUnderPointer =
                                    sceneListView.locationToIndex(e.getPoint());
                            System.out.println("Selected index is " + indexUnderPointer);                            
                            if(indexUnderPointer == -1 || sceneListView.getCellBounds(indexUnderPointer, 
                                    indexUnderPointer).contains(e.getPoint()))
                            {
                                sceneListView.clearSelection();                                
                            }
                            else
                            {
                                sceneListView.setSelectedIndex(indexUnderPointer);

                                sceneListViewPopupMenu.show(
                                        sceneListView, e.getX() , e.getY());
                            }
                        }
                        super.mousePressed(e);
                }
        });
        scheduleSceneMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                // Stub for user clicking "Schedule Scene..." in context menu
            }
        });
        sceneListViewPopupMenu.add(scheduleSceneMenuItem);
        //sceneListView.setComponentPopupMenu(sceneListViewPopupMenu);
        
        sceneListViewPopupMenu.addPopupMenuListener(new PopupMenuListener(){
            public void popupMenuCanceled(PopupMenuEvent e)
            {
                //No action taken when menu is canceled
            }
            
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
            {
                //No action taken before menu becomes invisible
            }
            
            public void popupMenuWillBecomeVisible(PopupMenuEvent e)
            {
                Scene selectedScene = sceneListView.getSelectedValue();
                if(selectedScene == null)
                    return;
                           
                scheduleSceneMenuItem.setText("Schedule " + 
                        sceneListView.getSelectedValue().toString());
            }
        });
        add(sideScenePanel, BorderLayout.LINE_START);
    }
    
    /**
     * Initializes the ScheduleCalendar with the Schedule present in the Script.
     */
    private void initializeScheduleCalendar()
    {
        JPanel mainScenePanel = new JPanel();
        mainScenePanel.setLayout(new BorderLayout());
        mainScenePanel.setBorder(new EmptyBorder(20,20,20,20));
        
        ScheduleCalendar scheduleCalendar = 
                new ScheduleCalendar(script.schedule());
        mainScenePanel.add(scheduleCalendar, BorderLayout.CENTER);
        add(mainScenePanel, BorderLayout.CENTER);
    }
    
    // Private Member Variables
    /**
     * The Script whose Schedule is to be displayed
     */
    private Script script;
    
    // Static Methods
    /**
     * Simple demonstration method which displays the SchedulePanel. Used for
     * design alterations, not Unit Testing
     * @param args 
     */
    public static void main(String[] args)
    {
        JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        SchedulePanel testPanel = new SchedulePanel(new Script("Test"));
        testFrame.add(testPanel);
        
        testFrame.setVisible(true);
    }
}
