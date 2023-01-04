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
public class CPU {

    private CPU() {
    }
    public static CPU cpu = new CPU();

    /**
     * do a math operation
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int operation(int a, int b, char c) {
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            case '%':
                return a % b;
        }
        return 0;
    }

    /**
     * compare to value
     *
     * @param a
     * @param c
     * @param b
     * @return
     */
    public boolean Compare(int a, char c, int b) {
        switch (c) {
            case '>':
                return a > b;
            case '<':
                return a < b;
            case '=':
                return a == b;

        }
        return true;
    }

}
