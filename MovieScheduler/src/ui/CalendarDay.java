package ui;

import businessobjects.BusinessObjectList;
import businessobjects.Scene;
import businessobjects.SceneFilmingDate;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 * A ui class representing a single day in the schedule calendar. Displays the
 * day of the month, then a list of all the scenes scheduled on that day.
 */
public class CalendarDay extends javax.swing.JPanel {

    // Constructor
    /**
     * Creates a new instance of a Calendar Day. Set date must be called before
     * this can properly be displayed.
     */
    public CalendarDay() {
        initComponents();
        dateFormatter = new SimpleDateFormat("dd");
        filmingDates = new BusinessObjectList<>();
        filmingDateView = new BusinessObjectListView<>(filmingDates);
        filmingDateScrollPane.setViewportView(filmingDateView);
        filmingDateView.setCellRenderer(new CalendarCellRenderer());
        JPopupMenu filmingDateViewPopupMenu = new JPopupMenu();
        JMenuItem filmingDateMenuItem = new JMenuItem("Remove From Schedule");
        filmingDateViewPopupMenu.add(filmingDateMenuItem);
        
        filmingDateViewPopupMenu.addPopupMenuListener(new PopupMenuListener()
        {
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
                SceneFilmingDate selectedSceneFilmingDate
                        = filmingDateView.getSelectedValue();
                if (selectedSceneFilmingDate == null)
                {
                    return;
                }

                filmingDateMenuItem.setText("Remove "
                        + selectedSceneFilmingDate.toString()
                        + " from the schedule");
            }
        });
        
        filmingDateView.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if (SwingUtilities.isRightMouseButton(e))
                {

                    int indexUnderPointer
                            = filmingDateView.locationToIndex(e.getPoint());

                    if (!filmingDateView.getCellBounds(indexUnderPointer,
                            indexUnderPointer).contains(e.getPoint()))
                    {
                        filmingDateView.clearSelection();
                    } 
                    else
                    {

                        filmingDateView.setSelectedIndex(indexUnderPointer);

                        filmingDateViewPopupMenu.show(
                                filmingDateView, e.getX(), e.getY());
                    }
                }
                super.mousePressed(e);
            }
        });
        
        filmingDateMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }

        });
        
        
    }

    // Public Methods
    /**
     * Sets the Foreground Color of the panel.
     *
     * @param color
     */
    @Override
    public void setForeground(Color color) {
        if (dayOfMonthLabel != null) {
            dayOfMonthLabel.setForeground(color);
        }
        super.setForeground(color);
    }

    /**
     * Sets the date that this calendar displays
     *
     * @param date::GregorianCalendar ~ The date this CalendarDay displays
     */
    public void setDate(GregorianCalendar date) {
        this.date = (GregorianCalendar) date.clone();
        dayOfMonthLabel.setText(dateFormatter.format(date.getTime()));
        filmingDates.clear();
    }

    /**
     * Returns the date currently being displayed by the CalendarDay
     *
     * @return The date currently being displayed by the CalendarDay
     */
    public GregorianCalendar date() {
        return date;
    }

    /**
     * The filming dates to display in this CalendarDay
     *
     * @return The filming dates to display in this CalendarDay
     */
    public BusinessObjectList<SceneFilmingDate> filmingDates() {
        return filmingDates;
    }

    // Private Member Class
    private class CalendarCellRenderer extends DefaultListCellRenderer {

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            // Assumes the stuff in the list has a pretty toString
            SceneFilmingDate filmingDate = (SceneFilmingDate)value;
            if(filmingDate.hasConflict())
            {
                this.setBorder(new LineBorder(Color.RED));
                this.setBackground(Color.PINK);
            }
            else
            {
                this.setBorder(new LineBorder(Color.GRAY));
                this.setBackground(Color.GREEN);    
            }
            this.setText(value.toString());
            

            return this;
        }
    }

    // Private Methods
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        dayOfMonthLabel = new javax.swing.JLabel();
        filmingDateScrollPane = new javax.swing.JScrollPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221)));

        dayOfMonthLabel.setText("dayOfMonth");

        filmingDateScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        filmingDateScrollPane.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(dayOfMonthLabel)
                .addContainerGap(36, Short.MAX_VALUE))
            .addComponent(filmingDateScrollPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(dayOfMonthLabel)
                .addGap(2, 2, 2)
                .addComponent(filmingDateScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Private Member Variables
    private final BusinessObjectListView<SceneFilmingDate> filmingDateView;
    private final BusinessObjectList<SceneFilmingDate> filmingDates;
    private GregorianCalendar date;
    private final SimpleDateFormat dateFormatter;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dayOfMonthLabel;
    private javax.swing.JScrollPane filmingDateScrollPane;
    // End of variables declaration//GEN-END:variables
}
