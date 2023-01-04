/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flowchart;

import java.awt.Graphics;
import java.io.PrintWriter;

/**
 *
 * @author sony
 */
public class Lozi extends Shape {

    public Lozi(int code, int length, int height, int Xcenter, int Ycenter, String text) {
        this.xcenter = Xcenter;
        this.ycenter = Ycenter;
        this.length = length;
        this.height = height;
        this.text = text;
        this.iscode = true;
        this.code = code;
        this.name = "Lozi";
    }

    public void draw(Graphics g) {
        int xPoints[] = {xcenter, xcenter - length / 2, xcenter, xcenter + length / 2};
        int yPoints[] = {ycenter - height / 2, ycenter, ycenter + height / 2, ycenter};
        g.drawPolygon(xPoints, yPoints, 4);
        g.drawString(text, xcenter - 26, ycenter);
    }

}
