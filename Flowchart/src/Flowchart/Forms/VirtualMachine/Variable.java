/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flowchart.Forms.VirtualMachine;

/**
 *
 * @author sony
 */
public class Variable {

    private String Name;
    private int value;

    public String getName() {
        return Name;
    }

    public int getValue() {
        return value;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Variable(String Name, int value) {
        this.Name = Name;
        this.value = value;
    }

}
