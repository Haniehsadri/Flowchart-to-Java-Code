/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flowchart;

import Flowchart.Manager.ShapesManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.PrintWriter;

/**
 *
 * @author sony
 */
public class Link implements Drawable {

    public String text;
    public Shape next = null;
    public Shape previous = null;
    public int nextt = 0;
    public int previouss = 0;
    int x1;
    int y1;
    int x2;
    int y2;
    public int code;
    private final int ArrowSize = 10;

    public Link(int code, String text, int x1, int y1, int x2, int y2) {
        this.text = text;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.code = code;

    }

    public void draw(Graphics g) {

        int flag5 = 0;
        g.setColor(Color.red);
        if (text != null) {
            if (x1 >= x2 && y1 >= y2) {
                g.drawString(text, x1 - 28, y1 - 17);
                flag5 = 1;
            }
            if (x1 < x2 && y1 < y2 && flag5 == 0) {
                g.drawString(text, x1 + 15, y1 + 19);
                flag5 = 1;
            }
            if (x1 < x2 && y1 > y2 && flag5 == 0) {
                g.drawString(text, x1 + 15, y1 - 17);
                flag5 = 1;
            }
            if (x1 > x2 && y1 < y2 && flag5 == 0) {
                g.drawString(text, x1 - 28, y1 + 19);
                flag5 = 1;
            }
        }
        g.drawLine(x1, y1, x1, y1 + (y2 - y1) / 2);
        g.drawLine(x1, y1 + (y2 - y1) / 2, x2, y1 + (y2 - y1) / 2);
        drawArrow(g, x2, y1 + (y2 - y1) / 2, x2, y2);
    }

    /**
     * draw a link with arrow
     *
     * @param g1
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);
        g.drawLine(0, 0, len, 0);
        g.fillPolygon(new int[]{len, len - ArrowSize, len - ArrowSize, len},
                new int[]{0, -ArrowSize, ArrowSize, 0}, 4);
    }

    /**
     * save the link in a file
     *
     * @param pw
     */
    public void Save(PrintWriter pw) {
        pw.println("Link");
        pw.println(code);
        if (text == null) {
            pw.println("");
        } else {
            pw.println(text);
        }
        pw.println(x1);
        pw.println(y1);
        pw.println(x2);
        pw.println(y2);
        pw.println(previous.code);
        pw.println(next.code);
    }

    /**
     * convert a shape code that is read from file to shape
     */
    @Override
    public void ConvertIntToDrawable() {
        for (Shape shape : ShapesManager.shapemanager.getShapeList()) {
            if (previouss == shape.code) {
                previous = shape;
            }
            if (nextt == shape.code) {
                next = shape;
            }
        }
    }
}
