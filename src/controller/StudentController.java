/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.toedter.calendar.JDateChooser;
import common.CrudEnum;
import entities.Student;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import model.StudentTableModel;
import service.StudentService;
import service.impl.StudentSeviceImpl;
import table.render.ButtonCellRender;
import table.render.MiddleCellRender;
import view.search.FrStudentInfo;

/**
 *
 * @author NHI
 */
public class StudentController {

    private final JTable tbStudent;
    private final JTextField tfSearch;
    private final JButton btAdd;
    private final JButton btExport;
    private final StudentTableModel model;
    private final StudentService studentService;
    private final JLabel lbStudentEventInfo;

    public StudentController(JTable tbStudent, JTextField tfSearch, JButton btAdd, JButton btExport, JLabel lbStudentEventInfo) {
        this.tbStudent = tbStudent;
        this.tfSearch = tfSearch;
        this.btAdd = btAdd;
        this.btExport = btExport;
        model = new StudentTableModel();
        studentService = new StudentSeviceImpl();
        this.lbStudentEventInfo = lbStudentEventInfo;
    }

    public StudentController(JButton btStore, JTextField tfStudentId, JTextField tfStudentName, JDateChooser birth, JTextField tfPhoneNumber, ButtonGroup buttonGroup, JTextField tfAccount, JScrollPane taAddress, JScrollPane taDescrible, JTextField tfWorkPlace) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void loadData() {
        tbStudent.setModel(model);
    }

    public void cssWithTable() {
        //render edit button
        final ButtonCellRender buttonCellRender = new ButtonCellRender();
        rerenderAlignmentColModel(7, buttonCellRender);
        Dimension dim = new Dimension(0, 32);
        JTableHeader tableHeader = tbStudent.getTableHeader();
        //độ cao header
        tableHeader.setPreferredSize(dim);
        tableHeader.setBackground(Color.blue);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(new Font("tahoma", Font.PLAIN, 14) {
        });
        //chinh do cao cac dong
        tbStudent.setRowHeight(26);
        //
        final MiddleCellRender middle = MiddleCellRender.getInstance();
        rerenderAlignmentColModel(0, middle);
        rerenderAlignmentColModel(2, middle);
        rerenderAlignmentColModel(4, middle);
        rerenderAlignmentColModel(6, middle);
        rerenderAlignmentColModel(1, middle);
        // độ dài cho mỗi col
        rerenderWidthColModel(6, 130);
        rerenderWidthColModel(5, 400);
        rerenderWidthColModel(4, 130);
        rerenderWidthColModel(3, 65);
        rerenderWidthColModel(2, 100);
        rerenderWidthColModel(1, 150);
        rerenderWidthColModel(0, 65);

        //prevent ordering columns
        tbStudent.getTableHeader().setReorderingAllowed(false);

    }

    private void rerenderAlignmentColModel(final int colIdx, final TableCellRenderer render) {
        tbStudent.getColumnModel().getColumn(colIdx).setCellRenderer(render);

    }

    private void rerenderWidthColModel(final int colIdx, final int width) {
        tbStudent.getColumnModel().getColumn(colIdx).setPreferredWidth(width);

    }

    public void setEvent() {
        tbActionListener();
        tfSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                final String fSearch = tfSearch.getText();
                TableRowSorter<AbstractTableModel> rowSorter = new TableRowSorter<>(model);
                //filtering 
                final RowFilter<AbstractTableModel, Object> filter = RowFilter.regexFilter(fSearch);
                rowSorter.setRowFilter(filter);
                tbStudent.setRowSorter(rowSorter);
            }

        });
        btAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                FrStudentInfo frStudentInfo = new FrStudentInfo(lbStudentEventInfo, model, tbStudent, CrudEnum.ADD, null,0);
                frStudentInfo.setVisible(true);
                frStudentInfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }

        });
    }

    private void tbActionListener() {
        tbStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                int selectedRow = tbStudent.getSelectedRow();
                int selectedColumn = tbStudent.getSelectedColumn();
                if (isSelectedButton(selectedRow, selectedColumn)) {
                    int id = Integer.parseInt(tbStudent.getValueAt(selectedRow, 0).toString());
                    Student studentInfo = studentService.getStudent(id);
                    FrStudentInfo frStudentInfo = new FrStudentInfo(lbStudentEventInfo, model, tbStudent, CrudEnum.EDIT, studentInfo,selectedRow);
                    frStudentInfo.setVisible(true);
                    frStudentInfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            }

        });
    }

    private boolean isSelectedButton(int selectedRow, int selectedColumn) {
        Object object = tbStudent.getValueAt(selectedRow, selectedColumn);
        return selectedColumn == 7;
    }

}
