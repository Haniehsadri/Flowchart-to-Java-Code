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
public interface Drawable {

    public abstract void draw(Graphics g);

    public abstract void Save(PrintWriter pw);

    public abstract void ConvertIntToDrawable();
}
