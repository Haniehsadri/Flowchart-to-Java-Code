/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flowchart.Forms.VirtualMachine;

import Flowchart.Forms.MainFrame;
import Flowchart.Lozi;
import Flowchart.Manager.ShapesManager;
import Flowchart.Motevaziyolazlah;
import Flowchart.Oval;
import Flowchart.Rectangle;
import Flowchart.Shape;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author sony
 */
public class VirtualMachine {

    Shape CurrentComponent = null;

    /**
     * this method run the flowchart
     *
     * @param frame
     */
    public void Run(MainFrame frame) {
        if (ShapesManager.shapemanager.IsCurrect()) {
            for (Shape shape : ShapesManager.shapemanager.getShapeList()) {
                if (shape instanceof Oval) {
                    if (shape.text.equals("start")) {
                        CurrentComponent = shape;
                    }
                }
            }
            CurrentComponent = CurrentComponent.out.next;
            while (CurrentComponent.out != null) {
                if (CurrentComponent instanceof Rectangle) {
                    Mohasebe(CurrentComponent, frame);
                    CurrentComponent = CurrentComponent.out.next;
                }
                if (CurrentComponent instanceof Motevaziyolazlah) {
                    if (CurrentComponent.in.previous.text.equals("start") || !CurrentComponent.out.next.text.equals("stop")) {
                        voroodi(CurrentComponent, frame);
                        CurrentComponent = CurrentComponent.out.next;
                    } else {
                        khorooji(CurrentComponent, frame);
                        CurrentComponent = CurrentComponent.out.next;
                    }
                }
                if (CurrentComponent instanceof Lozi) {
                    if (Sharti(CurrentComponent, frame)) {
                        if (CurrentComponent.out.text.equals("yes") || CurrentComponent.out.text.equals("true")) {
                            CurrentComponent = CurrentComponent.out.next;
                        }
                        if (CurrentComponent.out2 != null) {
                            if (CurrentComponent.out2.text.equals("yes") || CurrentComponent.out2.text.equals("true")) {
                                CurrentComponent = CurrentComponent.out2.next;
                            }
                        }
                    } else {
                        if (CurrentComponent.out.text.equals("no") || CurrentComponent.out.text.equals("false")) {
                            CurrentComponent = CurrentComponent.out.next;
                        }
                        if (CurrentComponent.out2 != null) {
                            if (CurrentComponent.out2.text.equals("no") || CurrentComponent.out2.text.equals("false")) {
                                CurrentComponent = CurrentComponent.out2.next;
                            }
                        }
                    }
                }

            }
            RAM.ram.refreshtable(frame);
        } else {
            JOptionPane.showMessageDialog(null, "Flowchart is not correct");
        }
    }

    /**
     * this method handle mohasebe component
     *
     * @param CurrentShape
     * @param Frame
     */
    public void Mohasebe(Shape CurrentShape, MainFrame Frame) {
        int i = 0;
        String text = CurrentShape.text;
        if (CurrentShape instanceof Rectangle) {
            while (text.charAt(i) != '=') {
                i++;
            }
            Variable Left = RAM.ram.EditVariable(text.substring(0, i));
            i++;
            int j = i;
            while (text.charAt(i) != '+' && text.charAt(i) != '-' && text.charAt(i) != '*' && text.charAt(i) != '/' && text.charAt(i) != '%') {
                i++;
            }
            Variable variable2;
            String temp = text.substring(j, i);
            int value1;
            int value2;
            if ((variable2 = RAM.ram.SearchVariable(temp)) != null) {
                value1 = variable2.getValue();
            } else {
                value1 = Integer.parseInt(temp);
            }
            char Amalgar = text.charAt(i);
            i++;
            String temp2 = (text.substring(i));
            if ((variable2 = RAM.ram.SearchVariable(temp2)) != null) {
                value2 = variable2.getValue();
            } else {
                value2 = Integer.parseInt(temp2);
            }
            int hasel = CPU.cpu.operation(value1, value2, Amalgar);
            Left.setValue(hasel);
        }
        RAM.ram.refreshtable(Frame);
    }

    /**
     * this method handle voroodi component
     *
     * @param Current
     * @param frame
     */
    public void voroodi(Shape Current, MainFrame frame) {
        String text = Current.text;
        Variable variable;
        String value;
        if (text.length() == 1) {
            variable = RAM.ram.EditVariable(text);
            value = JOptionPane.showInputDialog(null, "value for " + text + ":");
            if (value != null) {
                variable.setValue(Integer.parseInt(value));
            }
        } else {
            int i = 0;
            int j;
            int length = text.length();
            while (i < length) {
                j = i;

                while (text.charAt(i) != ',') {
                    i++;
                    if (i >= length) {
                        break;
                    }

                }

                String text2 = text.substring(j, i);
                variable = RAM.ram.EditVariable(text2);
                value = JOptionPane.showInputDialog(null, "value for " + text2 + ":");
                if (value != null) {
                    variable.setValue(Integer.parseInt(value));
                }
                RAM.ram.refreshtable(frame);
                i++;
            }
        }

    }

    /**
     * this method handle khorooji component
     *
     * @param Current
     * @param frame
     */
    public void khorooji(Shape Current, MainFrame frame) {
        String text = Current.text;
        Variable variable;
        String value;
        if (text.length() == 1) {
            variable = RAM.ram.SearchVariable(text);
            if (variable != null) {
                frame.result.setText("      " + variable.getName() + "=" + variable.getValue());
            }
        } else {
            String Final = "";
            int i = 0;
            int j;
            int length = text.length();
            while (i < length) {
                j = i;

                while (text.charAt(i) != ',') {
                    i++;
                    if (i >= length) {
                        break;
                    }

                }

                String text2 = text.substring(j, i);
                variable = RAM.ram.SearchVariable(text2);
                if (variable != null) {
                    Final = Final + (variable.getName() + "=" + variable.getValue() + "\n");
                }

                i++;
            }
            frame.result.setText(Final);
        }

    }

    /**
     * this method handle sharti component
     *
     * @param Current
     * @param frame
     * @return
     */
    public boolean Sharti(Shape Current, MainFrame frame) {
        int i = 0;
        String text = Current.text;
        while (text.charAt(i) != '>' && text.charAt(i) != '<' && text.charAt(i) != '=') {
            i++;
        }
        Variable variable2;
        String temp = text.substring(0, i);
        int value1;
        int value2;
        if ((variable2 = RAM.ram.SearchVariable(temp)) != null) {
            value1 = variable2.getValue();
        } else {
            value1 = Integer.parseInt(temp);
        }
        char Amalgar = text.charAt(i);
        i++;
        if (text.charAt(i) != '=') {
            String temp2 = (text.substring(i));
            if ((variable2 = RAM.ram.SearchVariable(temp2)) != null) {
                value2 = variable2.getValue();
            } else {
                value2 = Integer.parseInt(temp2);
            }
            boolean hasel = CPU.cpu.Compare(value1, Amalgar, value2);
            return hasel;
        } else {

            i++;
            String temp2 = (text.substring(i));
            if ((variable2 = RAM.ram.SearchVariable(temp2)) != null) {
                value2 = variable2.getValue();
            } else {
                value2 = Integer.parseInt(temp2);
            }
            boolean hasel = CPU.cpu.Compare(value1, Amalgar, value2);
            boolean hasel2 = CPU.cpu.Compare(value1, '=', value2);
            return hasel || hasel2;
        }
    }
}
