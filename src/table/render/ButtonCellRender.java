/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.render;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import utils.ImageUtils;

/**
 *
 * @author NHI
 */
public class ButtonCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        JButton button = new JButton();
        button.setFocusPainted(false);
        button.setBorder(noFocusBorder);

        button.setIcon(ImageUtils.createIcon(ImageUtils.BKCIT_ICON_EDIT));
        return button;
    }

}
