/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flowchart.Manager;

import Flowchart.Drawable;
import Flowchart.Forms.Input;
import Flowchart.Forms.MainFrame;
import Flowchart.Link;
import Flowchart.Lozi;
import Flowchart.Motevaziyolazlah;
import Flowchart.Oval;
import Flowchart.Rectangle;
import Flowchart.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author sony
 */
public class ShapesManager {

    public static ShapesManager shapemanager = new ShapesManager();
    private ArrayList<Drawable> DrawableList = new ArrayList<Drawable>();
    private ArrayList<Shape> ShapeList = new ArrayList<Shape>();
    private ArrayList<Link> LinkList = new ArrayList<Link>();
    public Shape pre = null;
    public Shape next = null;
    String text;
    int x1, y1, x2, y2;
    Input input = new Input(null, true);

    private ShapesManager() {

    }

    public ArrayList<Drawable> getDrawableList() {
        return DrawableList;
    }

    public ArrayList<Shape> getShapeList() {
        return ShapeList;
    }

    public ArrayList<Link> getLinkList() {
        return LinkList;
    }

    public void DrawAll(Graphics g) {

        for (Drawable drawable : DrawableList) {
            g.setColor(Color.BLUE);
            drawable.draw(g);
        }
    }

    /**
     * link two components that user choose
     *
     * @param x
     * @param y
     * @param code
     * @return
     */
    public int AddLink(int x, int y, int code) {
        for (Shape shape : ShapeList) {
            if (shape.xcenter - shape.length / 2 - 10 < x && x < shape.xcenter + shape.length / 2 + 10 && y < shape.ycenter + shape.height / 2 + 10 && shape.ycenter - shape.height / 2 - 10 < y) {
                while (pre == null) {
                    x1 = x;
                    y1 = y;
                    text = null;
                    pre = shape;
                    if (shape instanceof Lozi) {
                        text = JOptionPane.showInputDialog("کامپوننت شرطی است لطفا متن رابط را تعیین کنید");
                    }
                    return 1;
                }
                next = null;
                while (next == null) {
                    x2 = x;
                    y2 = y;
                    next = shape;
                    Link link = new Link(code, text, x1, y1, x2, y2);
                    link.previous = pre;
                    link.next = next;
                    DrawableList.add(link);
                    LinkList.add(link);
                    if (pre.LinkOutCounter == 0) {
                        pre.out = link;

                    }
                    if (pre.LinkOutCounter == 1) {
                        pre.out2 = link;

                    }

                    if (pre.LinkOutCounter == 2) {
                        pre.out3 = link;

                    }
                    if (pre.LinkOutCounter == 3) {
                        pre.out4 = link;

                    }
                    pre.LinkOutCounter++;
                    pre = null;
                    if (next.LinkInCounter == 0) {
                        next.in = link;
                    }
                    if (next.LinkInCounter == 1) {
                        next.in2 = link;
                    }

                    if (next.LinkInCounter == 2) {
                        next.in3 = link;
                    }
                    if (next.LinkInCounter == 3) {
                        next.in4 = link;
                    }
                    next.LinkInCounter++;
                    return 2;
                }

            }
        }
        return 3;
    }

    public boolean PrevationOverlap(int x, int y) {
        for (Shape shape : ShapeList) {
            if (shape.xcenter - shape.length - 3 < x && x < shape.xcenter + shape.length + 3 && y < shape.ycenter + shape.height + 3 && shape.ycenter - shape.height - 3 < y) {
                JOptionPane.showMessageDialog(null, "امکان پذیر نیست همپوشانی رخ میدهد");
                return true;
            }
        }
        return false;
    }

    /**
     * edit the shape that user choose
     *
     * @param x
     * @param y
     * @return Shape
     */
    public void EditShape(int x, int y, Graphics g) {
        for (Shape shape : ShapeList) {

            if (shape.xcenter - shape.length / 2 < x && x < shape.xcenter + shape.length / 2 && y < shape.ycenter + shape.height / 2 && shape.ycenter - shape.height / 2 < y) {

                if (shape.text != null) {
                    input.code.setText(shape.text);
                } else {
                    input.code.setText("");
                }
                if (shape instanceof Lozi) {
                    input.label1.setText("height:");
                    input.x.setText(String.valueOf(shape.xcenter));
                    input.y.setText(String.valueOf(shape.ycenter));
                    input.width.setText(String.valueOf(90));
                    input.length.setText(String.valueOf(140));

                } else {
                    input.label1.setText("height:");
                    input.x.setText(String.valueOf(shape.xcenter));
                    input.y.setText(String.valueOf(shape.ycenter));
                    input.width.setText(String.valueOf(60));
                    input.length.setText(String.valueOf(130));
                }
                input.setVisible(true);
                if (MainFrame.flag1 == 1) {
                    if (shape.xcenter != Integer.parseInt(input.x.getText()) || shape.ycenter != Integer.parseInt(input.y.getText())) {
                        DrawableList.remove(shape.in);
                        DrawableList.remove(shape.out);
                    }
                    shape.xcenter = Integer.parseInt(input.x.getText());
                    shape.ycenter = Integer.parseInt(input.y.getText());
                    shape.basexcenter = Integer.parseInt(input.x.getText());
                    shape.baseycenter = Integer.parseInt(input.y.getText());
                    shape.length = Integer.parseInt(input.length.getText());
                    shape.height = Integer.parseInt(input.width.getText());
                    shape.text = input.code.getText();
                    g.setColor(Color.WHITE);
                    g.fillRect(10, 10, 800, 640);
                    DrawAll(g);

                }

            }

        }
    }

    /**
     * return the shape that user choose
     *
     * @param x
     * @param y
     * @return Shape
     */
    public Shape SearchShape(int x, int y) {
        for (Shape shape : ShapeList) {
            if (shape.xcenter - shape.length / 2 < x && x < shape.xcenter + shape.length / 2 && y < shape.ycenter + shape.height / 2 && shape.ycenter - shape.height / 2 < y) {
                return shape;
            }
        }
        return null;
    }

    /**
     * this method move the shape that user is dragging it
     *
     * @param x
     * @param y
     */
    public void MoveShape(Shape shape, int x, int y) {
        if (shape != null) {

            shape.xcenter = x;
            shape.ycenter = y;
            DrawableList.remove(shape.in);
            LinkList.remove(shape.in);
            DrawableList.remove(shape.out);
            LinkList.remove(shape.out);
            DrawableList.remove(shape.in2);
            LinkList.remove(shape.in2);
            DrawableList.remove(shape.out2);
            LinkList.remove(shape.out2);
            DrawableList.remove(shape.in3);
            LinkList.remove(shape.in3);
            DrawableList.remove(shape.out3);
            LinkList.remove(shape.out3);
            DrawableList.remove(shape.in4);
            LinkList.remove(shape.in4);
            DrawableList.remove(shape.out4);
            LinkList.remove(shape.out4);
            shape.LinkInCounter = 0;
            shape.LinkOutCounter = 0;
        }
    }

    /**
     * save the flowchart in a file that user enter
     *
     * @param path
     */
    public void SaveToFile(String path) {
        try {
            FileWriter fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (Drawable drawable : DrawableList) {
                drawable.Save(pw);
            }
            for (Drawable drawable : shapemanager.getDrawableList()) {
                drawable.ConvertIntToDrawable();
            }
            pw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ذخیره امکان پذیر نیست");
        }
    }

    /**
     * this method is for reading data from file
     *
     * @param path
     * @param g
     */
    public void Load(String path, Graphics g) {
        try {
            String Type;
            FileReader FR = new FileReader(path);
            BufferedReader BR = new BufferedReader(FR);
            while ((Type = BR.readLine()) != null) {
                if (Type.equals("Rectangle")) {
                    int code = Integer.parseInt(BR.readLine());
                    int length = Integer.parseInt(BR.readLine());
                    int heigth = Integer.parseInt(BR.readLine());
                    int xcenter = Integer.parseInt(BR.readLine());
                    int ycenter = Integer.parseInt(BR.readLine());
                    String text = BR.readLine();
                    boolean iscode;
                    if (BR.readLine().equals("true")) {
                        iscode = true;
                    } else {
                        iscode = false;
                    }
                    Rectangle rectangle = new Rectangle(code, length, heigth, xcenter, ycenter, text);
                    String number;
                    if ((number = BR.readLine()).equals("1")) {
                        rectangle.inn = Integer.parseInt(BR.readLine());
                        rectangle.LinkInCounter = 1;
                    }
                    if (number.equals("2")) {
                        rectangle.inn = Integer.parseInt(BR.readLine());
                        rectangle.inn2 = Integer.parseInt(BR.readLine());
                        rectangle.LinkInCounter = 2;
                    }
                    if (number.equals("3")) {
                        rectangle.inn = Integer.parseInt(BR.readLine());
                        rectangle.inn2 = Integer.parseInt(BR.readLine());
                        rectangle.inn3 = Integer.parseInt(BR.readLine());
                        rectangle.LinkInCounter = 3;
                    }
                    if (number.equals("4")) {
                        rectangle.inn = Integer.parseInt(BR.readLine());
                        rectangle.inn2 = Integer.parseInt(BR.readLine());
                        rectangle.inn3 = Integer.parseInt(BR.readLine());
                        rectangle.inn4 = Integer.parseInt(BR.readLine());
                        rectangle.LinkInCounter = 4;
                    }

                    if ((number = BR.readLine()).equals("1")) {
                        rectangle.outt = Integer.parseInt(BR.readLine());
                        rectangle.LinkOutCounter = 1;
                    }
                    if (number.equals("2")) {
                        rectangle.outt = Integer.parseInt(BR.readLine());
                        rectangle.outt2 = Integer.parseInt(BR.readLine());
                        rectangle.LinkOutCounter = 2;
                    }
                    if (number.equals("3")) {
                        rectangle.outt = Integer.parseInt(BR.readLine());
                        rectangle.outt2 = Integer.parseInt(BR.readLine());
                        rectangle.outt3 = Integer.parseInt(BR.readLine());
                        rectangle.LinkOutCounter = 3;
                    }
                    if (number.equals("4")) {
                        rectangle.outt = Integer.parseInt(BR.readLine());
                        rectangle.outt2 = Integer.parseInt(BR.readLine());
                        rectangle.outt3 = Integer.parseInt(BR.readLine());
                        rectangle.outt4 = Integer.parseInt(BR.readLine());
                        rectangle.LinkOutCounter = 4;
                    }
                    DrawableList.add(rectangle);
                    ShapeList.add(rectangle);

                }
                if (Type.equals("Oval")) {

                    int code = Integer.parseInt(BR.readLine());
                    int length = Integer.parseInt(BR.readLine());
                    int heigth = Integer.parseInt(BR.readLine());
                    int xcenter = Integer.parseInt(BR.readLine());
                    int ycenter = Integer.parseInt(BR.readLine());
                    String text = BR.readLine();
                    boolean iscode;
                    if (BR.readLine().equals("true")) {
                        iscode = true;
                    } else {
                        iscode = false;
                    }
                    Oval oval = new Oval(code, length, heigth, xcenter, ycenter, text);
                    String number;
                    if ((number = BR.readLine()).equals("1")) {
                        oval.inn = Integer.parseInt(BR.readLine());
                        oval.LinkInCounter = 1;
                    }
                    if (number.equals("2")) {
                        oval.inn = Integer.parseInt(BR.readLine());
                        oval.inn2 = Integer.parseInt(BR.readLine());
                        oval.LinkInCounter = 2;
                    }
                    if (number.equals("3")) {
                        oval.inn = Integer.parseInt(BR.readLine());
                        oval.inn2 = Integer.parseInt(BR.readLine());
                        oval.inn3 = Integer.parseInt(BR.readLine());
                        oval.LinkInCounter = 3;
                    }
                    if (number.equals("4")) {
                        oval.inn = Integer.parseInt(BR.readLine());
                        oval.inn2 = Integer.parseInt(BR.readLine());
                        oval.inn3 = Integer.parseInt(BR.readLine());
                        oval.inn4 = Integer.parseInt(BR.readLine());
                        oval.LinkInCounter = 4;
                    }
                    if ((number = BR.readLine()).equals("1")) {
                        oval.outt = Integer.parseInt(BR.readLine());
                        oval.LinkOutCounter = 1;
                    }
                    if (number.equals("2")) {
                        oval.outt = Integer.parseInt(BR.readLine());
                        oval.outt2 = Integer.parseInt(BR.readLine());
                        oval.LinkOutCounter = 2;
                    }
                    if (number.equals("3")) {
                        oval.outt = Integer.parseInt(BR.readLine());
                        oval.outt2 = Integer.parseInt(BR.readLine());
                        oval.outt3 = Integer.parseInt(BR.readLine());
                        oval.LinkOutCounter = 3;
                    }
                    if (number.equals("4")) {
                        oval.outt = Integer.parseInt(BR.readLine());
                        oval.outt2 = Integer.parseInt(BR.readLine());
                        oval.outt3 = Integer.parseInt(BR.readLine());
                        oval.outt4 = Integer.parseInt(BR.readLine());
                        oval.LinkOutCounter = 4;
                    }
                    DrawableList.add(oval);
                    ShapeList.add(oval);

                }
                if (Type.equals("Lozi")) {

                    int code = Integer.parseInt(BR.readLine());
                    int length = Integer.parseInt(BR.readLine());
                    int heigth = Integer.parseInt(BR.readLine());
                    int xcenter = Integer.parseInt(BR.readLine());
                    int ycenter = Integer.parseInt(BR.readLine());
                    String text = BR.readLine();
                    boolean iscode;
                    if (BR.readLine().equals("true")) {
                        iscode = true;
                    } else {
                        iscode = false;
                    }
                    Lozi lozi = new Lozi(code, length, heigth, xcenter, ycenter, text);
                    String number;
                    if ((number = BR.readLine()).equals("1")) {
                        lozi.inn = Integer.parseInt(BR.readLine());
                        lozi.LinkInCounter = 1;
                    }
                    if (number.equals("2")) {
                        lozi.inn = Integer.parseInt(BR.readLine());
                        lozi.inn2 = Integer.parseInt(BR.readLine());
                        lozi.LinkInCounter = 2;
                    }
                    if (number.equals("3")) {
                        lozi.inn = Integer.parseInt(BR.readLine());
                        lozi.inn2 = Integer.parseInt(BR.readLine());
                        lozi.inn3 = Integer.parseInt(BR.readLine());
                        lozi.LinkInCounter = 3;
                    }
                    if (number.equals("4")) {
                        lozi.inn = Integer.parseInt(BR.readLine());
                        lozi.inn2 = Integer.parseInt(BR.readLine());
                        lozi.inn3 = Integer.parseInt(BR.readLine());
                        lozi.inn4 = Integer.parseInt(BR.readLine());
                        lozi.LinkInCounter = 4;
                    }
                    if ((number = BR.readLine()).equals("1")) {
                        lozi.outt = Integer.parseInt(BR.readLine());
                        lozi.LinkOutCounter = 1;
                    }
                    if (number.equals("2")) {
                        lozi.outt = Integer.parseInt(BR.readLine());
                        lozi.outt2 = Integer.parseInt(BR.readLine());
                        lozi.LinkOutCounter = 2;
                    }
                    if (number.equals("3")) {
                        lozi.outt = Integer.parseInt(BR.readLine());
                        lozi.outt2 = Integer.parseInt(BR.readLine());
                        lozi.outt3 = Integer.parseInt(BR.readLine());
                        lozi.LinkOutCounter = 3;
                    }
                    if (number.equals("4")) {
                        lozi.outt = Integer.parseInt(BR.readLine());
                        lozi.outt2 = Integer.parseInt(BR.readLine());
                        lozi.outt3 = Integer.parseInt(BR.readLine());
                        lozi.outt4 = Integer.parseInt(BR.readLine());
                        lozi.LinkOutCounter = 4;
                    }
                    DrawableList.add(lozi);
                    ShapeList.add(lozi);

                }
                if (Type.equals("Motevaziyolazlah")) {

                    int code = Integer.parseInt(BR.readLine());
                    int length = Integer.parseInt(BR.readLine());
                    int heigth = Integer.parseInt(BR.readLine());
                    int xcenter = Integer.parseInt(BR.readLine());
                    int ycenter = Integer.parseInt(BR.readLine());
                    String text = BR.readLine();
                    boolean iscode;
                    if (BR.readLine().equals("true")) {
                        iscode = true;
                    } else {
                        iscode = false;
                    }
                    Motevaziyolazlah motevaziyolazlah = new Motevaziyolazlah(code, length, heigth, xcenter, ycenter, text);
                    String number;
                    if ((number = BR.readLine()).equals("1")) {
                        motevaziyolazlah.inn = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.LinkInCounter = 1;
                    }
                    if (number.equals("2")) {
                        motevaziyolazlah.inn = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.inn2 = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.LinkInCounter = 2;
                    }
                    if (number.equals("3")) {
                        motevaziyolazlah.inn = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.inn2 = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.inn3 = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.LinkInCounter = 3;
                    }
                    if (number.equals("4")) {
                        motevaziyolazlah.inn = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.inn2 = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.inn3 = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.inn4 = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.LinkInCounter = 4;
                    }
                    if ((number = BR.readLine()).equals("1")) {
                        motevaziyolazlah.outt = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.LinkOutCounter = 1;
                    }
                    if (number.equals("2")) {
                        motevaziyolazlah.outt = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.outt2 = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.LinkOutCounter = 2;
                    }
                    if (number.equals("3")) {
                        motevaziyolazlah.outt = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.outt2 = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.outt3 = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.LinkOutCounter = 3;
                    }
                    if (number.equals("4")) {
                        motevaziyolazlah.outt = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.outt2 = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.outt3 = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.outt4 = Integer.parseInt(BR.readLine());
                        motevaziyolazlah.LinkOutCounter = 4;
                    }
                    DrawableList.add(motevaziyolazlah);
                    ShapeList.add(motevaziyolazlah);

                }
                if (Type.equals("Link")) {

                    int code = Integer.parseInt(BR.readLine());
                    String text = BR.readLine();
                    int x1 = Integer.parseInt(BR.readLine());
                    int y1 = Integer.parseInt(BR.readLine());
                    int x2 = Integer.parseInt(BR.readLine());
                    int y2 = Integer.parseInt(BR.readLine());
                    Link link = new Link(code, text, x1, y1, x2, y2);
                    link.previouss = Integer.parseInt(BR.readLine());
                    link.nextt = Integer.parseInt(BR.readLine());
                    DrawableList.add(link);
                    LinkList.add(link);
                }
                for (Drawable drawable : shapemanager.getDrawableList()) {
                    drawable.ConvertIntToDrawable();
                }

                DrawAll(g);
            }
        } catch (IOException e) {

        }
    }

    /**
     * this method check flowchart is correct or not
     *
     * @return
     */
    public boolean IsCurrect() {
        int flag = 0;
        int flag2 = 0;
        for (Shape shape : ShapeList) {
            if (shape.in == null && shape.out == null) {
                return false;
            }

            if (!(shape instanceof Oval)) {
                if (shape.in == null || shape.out == null) {
                    return false;
                }

                if (shape.text.equals("start") || shape.text.equals("stop")) {
                    return false;
                }

            }

            if (shape instanceof Oval) {
                if (!(shape.text.equals("start")) && !(shape.text.equals("stop"))) {
                    return false;
                }

                if (shape.text.equals("start")) {
                    flag = 1;
                }
                if (shape.text.equals("stop")) {

                    flag2 = 1;
                }
            }
        }
        if (flag == 0 || flag2 == 0) {
            return false;
        }

        return true;
    }
}
