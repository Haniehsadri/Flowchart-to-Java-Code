/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flowchart;

import Flowchart.Manager.ShapesManager;
import java.io.PrintWriter;

/**
 *
 * @author sony
 */
public abstract class Shape implements Drawable {

    public int length;
    public int height;
    public int xcenter;
    public int ycenter;
    public int basexcenter;
    public int baseycenter;
    public int code;
    public String text = "";
    boolean iscode = true;
    public String name;
    public int LinkOutCounter = 0;
    public int LinkInCounter = 0;
    public Link in = null;
    public Link out = null;
    public Link out2 = null;
    public Link out3 = null;
    public Link out4 = null;
    public Link in2 = null;
    public Link in3 = null;
    public Link in4 = null;
    public int inn;
    public int inn2 = 0;
    public int inn3 = 0;
    public int inn4 = 0;
    public int outt = 0;
    public int outt2 = 0;
    public int outt3 = 0;
    public int outt4 = 0;

    /**
     * save the component in a file
     *
     * @param pw
     */
    public void Save(PrintWriter pw) {
        pw.println(name);
        pw.println(code);
        pw.println(length);
        pw.println(height);
        pw.println(xcenter);
        pw.println(ycenter);
        if (text.isEmpty()) {
            pw.println("");
        } else {
            pw.println(text);
        }

        pw.println(iscode);
        pw.println(LinkInCounter);

        if (LinkInCounter == 1) {
            pw.println(in.code);
        }
        if (LinkInCounter == 2) {
            pw.println(in.code);
            pw.println(in2.code);
        }
        if (LinkInCounter == 3) {
            pw.println(in.code);
            pw.println(in2.code);
            pw.println(in3.code);
        }
        if (LinkInCounter == 4) {
            pw.println(in.code);
            pw.println(in2.code);
            pw.println(in3.code);
            pw.println(in4.code);
        }
        pw.println(LinkOutCounter);
        if (LinkOutCounter == 1) {
            pw.println(out.code);
        }
        if (LinkOutCounter == 2) {
            pw.println(out.code);
            pw.println(out2.code);
        }
        if (LinkOutCounter == 3) {
            pw.println(out.code);
            pw.println(out2.code);
            pw.println(out3.code);
        }
        if (LinkOutCounter == 4) {
            pw.println(out.code);
            pw.println(out2.code);
            pw.println(out3.code);
            pw.println(out4.code);
        }

    }

    /**
     * convert a link code that is read from a file to a link
     */
    @Override
    public void ConvertIntToDrawable() {
        if (LinkInCounter == 1) {
            for (Link link : ShapesManager.shapemanager.getLinkList()) {
                if (link.code == inn) {
                    in = link;
                }
            }
        }
        if (LinkInCounter == 2) {
            for (Link link : ShapesManager.shapemanager.getLinkList()) {
                if (link.code == inn) {
                    in = link;
                }
                if (link.code == inn2) {
                    in2 = link;
                }
            }
        }
        if (LinkInCounter == 3) {
            for (Link link : ShapesManager.shapemanager.getLinkList()) {
                if (link.code == inn) {
                    in = link;
                }
                if (link.code == inn2) {
                    in2 = link;
                }
                if (link.code == inn3) {
                    in3 = link;
                }
            }
        }
        if (LinkInCounter == 4) {
            for (Link link : ShapesManager.shapemanager.getLinkList()) {
                if (link.code == inn) {
                    in = link;
                }
                if (link.code == inn2) {
                    in2 = link;
                }
                if (link.code == inn3) {
                    in3 = link;
                }
                if (link.code == inn4) {
                    in4 = link;
                }
            }
        }
        if (LinkOutCounter == 1) {
            for (Link link : ShapesManager.shapemanager.getLinkList()) {
                if (link.code == outt) {
                    out = link;
                }
            }
        }
        if (LinkOutCounter == 2) {
            for (Link link : ShapesManager.shapemanager.getLinkList()) {
                if (link.code == outt) {
                    out = link;
                }
                if (link.code == outt2) {
                    out2 = link;
                }
            }
        }
        if (LinkOutCounter == 3) {
            for (Link link : ShapesManager.shapemanager.getLinkList()) {
                if (link.code == outt) {
                    out = link;
                }
                if (link.code == outt2) {
                    out2 = link;
                }
                if (link.code == outt3) {
                    out3 = link;
                }
            }
        }
        if (LinkOutCounter == 4) {
            for (Link link : ShapesManager.shapemanager.getLinkList()) {
                if (link.code == outt) {
                    out = link;
                }
                if (link.code == outt2) {
                    out2 = link;
                }
                if (link.code == outt3) {
                    out3 = link;
                }
                if (link.code == outt4) {
                    out4 = link;
                }
            }
        }
    }
}
