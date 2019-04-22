/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.render;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author NHI
 */
public class MiddleCellRender extends DefaultTableCellRenderer{
    private static MiddleCellRender instance = null;

    private MiddleCellRender() {
    }
    public static synchronized MiddleCellRender getInstance(){
        if(instance == null){
            instance = new MiddleCellRender();
        }
        return instance;
    }
    
    @Override
    public int getHorizontalAlignment() {
        return SwingConstants.CENTER;
    }
    
}
