/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flowchart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.PrintWriter;
import static javax.swing.text.StyleConstants.Bold;

/**
 *
 * @author sony
 */
public class Rectangle extends Shape {

    public Rectangle(int code, int length, int height, int xcenter, int ycenter, String text) {
        this.length = length;
        this.height = height;
        this.xcenter = xcenter;
        this.ycenter = ycenter;
        this.text = text;
        this.iscode = true;
        this.code = code;
        this.name = "Rectangle";
    }

    public void draw(Graphics g) {
        g.drawRect(xcenter - length / 2, ycenter - height / 2, length, height);
        g.drawString(text, xcenter - 26, ycenter);

    }

}
