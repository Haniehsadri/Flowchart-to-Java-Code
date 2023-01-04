/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flowchart.Forms.VirtualMachine;

import Flowchart.Forms.MainFrame;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sony
 */
public class RAM {

    private RAM() {
    }

    public static RAM ram = new RAM();
    private ArrayList<Variable> VariableList = new ArrayList<>();

    public ArrayList<Variable> getVariableList() {
        return VariableList;
    }

    /**
     * edit a variable
     *
     * @param name
     * @return
     */
    public Variable EditVariable(String name) {
        for (Variable variable : VariableList) {
            if (variable.getName().equals(name)) {
                return variable;
            }
        }
        Variable variable = new Variable(name, 0);
        VariableList.add(variable);
        return variable;
    }

    /**
     * this method find a variable whit it,s name
     *
     * @param name
     * @return
     */
    public Variable SearchVariable(String name) {
        for (Variable variable : VariableList) {
            if (variable.getName().equals(name)) {
                return variable;
            }
        }
        return null;
    }

    /**
     * this method update variables table
     *
     * @param Frame
     */
    public void refreshtable(MainFrame Frame) {
        DefaultTableModel model1 = (DefaultTableModel) Frame.table.getModel();
        while (model1.getRowCount() != 0) {
            model1.removeRow(0);
        }
        for (Variable variable : VariableList) {
            if (variable == null) {
                continue;
            }
            model1.addRow(new Object[]{variable.getName(), variable.getValue()});
        }
    }
}
