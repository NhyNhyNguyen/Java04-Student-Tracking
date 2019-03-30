/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import common.MenuEnum;
import java.util.Objects;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author NHI
 */
public class MenuDto {

    private MenuEnum name;
    private JPanel pnMenu;
    private JLabel lbMenu;

    public MenuDto(MenuEnum name, JPanel pnMenu, JLabel lbMenu) {
        this.name = name;
        this.pnMenu = pnMenu;
        this.lbMenu = lbMenu;
    }

    public MenuEnum getName() {
        return name;
    }

    public JPanel getPnMenu() {
        return pnMenu;
    }

    public JLabel getLbMenu() {
        return lbMenu;
    }

    public void setName(MenuEnum name) {
        this.name = name;
    }

    public void setPnMenu(JPanel pnMenu) {
        this.pnMenu = pnMenu;
    }

    public void setLbMenu(JLabel lbMenu) {
        this.lbMenu = lbMenu;
    }

    @Override
    public String toString() {
        return "MenuDto{" + "name=" + name + ", pnMenu=" + pnMenu + ", lbMenu=" + lbMenu + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof MenuDto)) {
            return false;
        }
        MenuDto dto = (MenuDto) o;
        return dto.getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName()); //To change body of generated methods, choose Tools | Templates.
    }

}
