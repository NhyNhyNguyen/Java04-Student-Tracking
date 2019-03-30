/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.MenuEnum;
import dto.MenuDto;
import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utils.ColorUtils;
import view.pnClassMan;
import view.pnCourseMan;
import view.pnStatistical;
import view.pnHomePage;
import view.pnStudentMan;

/**
 *
 * @author NHI
 */
public class Navigator {
    private MenuEnum pressedMenu;
    private MenuEnum name;
    private JPanel pnMain;
    private List<MenuDto> menus;
    public Navigator(JPanel pnMainView) {
        this.pnMain = pnMainView;
    }
    public void setStartPage(){
        pnMain.removeAll();
        pressedMenu = MenuEnum.HOMEPAGE;
        pnMain.setLayout(new BorderLayout());
        pnMain.add(new pnHomePage());
        pnMain.validate();
        pnMain.repaint();
    }

    public void setEvent(List<MenuDto> menus) {
        this.menus = menus;
        menus.forEach(menu -> 
        {
            menu.getLbMenu().addMouseListener(new LableMenuEven(menu));
        });
        
    }
    public class LableMenuEven extends MouseAdapter{
        private JPanel selectedPanel;
        private final MenuEnum menuName;
        private final JLabel curLable;
        private final JPanel curPanel;
        private LableMenuEven(MenuDto menu) {
            this.curLable = menu.getLbMenu();
            this.curPanel = menu.getPnMenu();
            this.menuName = menu.getName();
        }

        @Override
        public void mousePressed(MouseEvent me) {
            //hover 
            pressedMenu = menuName;
            for(MenuDto menu : menus){
                menu.getPnMenu().setBackground(ColorUtils.normalMenu);
            }
            curPanel.setBackground(ColorUtils.hoverMenu);
            switch(menuName){
                case HOMEPAGE:
                    selectedPanel = new pnHomePage();
                    break;
                case COURSE_MANAGER:
                    selectedPanel = new pnCourseMan();
                    break;
                case STUDENT_MANAGER:
                    selectedPanel = new pnStudentMan();
                    break;
                case CLASS_MANAGER:
                    selectedPanel = new pnClassMan();
                    break;
                case STATICTIS:
                    selectedPanel = new pnStatistical();
                    break;
                    
            }
             pnMain.removeAll();
            pnMain.setLayout(new BorderLayout());
            pnMain.add(selectedPanel);
            pnMain.validate();
            pnMain.repaint();
            
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            curPanel.setBackground(ColorUtils.hoverMenu);
        }

        @Override
        public void mouseExited(MouseEvent me) {
            if(!pressedMenu.equals(menuName)){
                curPanel.setBackground(ColorUtils.normalMenu);
            }
        }
        
        
        
}
    
}
