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
public class Motevaziyolazlah extends Shape {

    public Motevaziyolazlah(int code, int length, int height, int Xcenter, int Ycenter, String text) {
        this.xcenter = Xcenter;
        this.ycenter = Ycenter;
        this.length = length;
        this.height = height;
        this.text = text;
        this.iscode = true;
        this.code = code;
        this.name = "Motevaziyolazlah";
    }

    public void draw(Graphics g) {
        int xPoints[] = {xcenter - length / 2, xcenter - length / 2 - 18, xcenter + length / 2 - 18, xcenter + length / 2};
        int yPoints[] = {ycenter - height / 2, ycenter + height / 2, ycenter + height / 2, ycenter - height / 2};
        g.drawPolygon(xPoints, yPoints, 4);
        g.drawString(text, xcenter - 26, ycenter);
    }

}
