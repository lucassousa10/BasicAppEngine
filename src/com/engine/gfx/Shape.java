package com.engine.gfx;

import com.engine.Input;

import java.awt.geom.Path2D;
import java.util.ArrayList;

/**
 * Basic wrapper class for Path2D.Float. This contains all usability
 * of Path2D with the addition of this holds info from a conversion of
 * a relative to absolute SVG path.
 *
 * To be used, this must receive a String that contains a RELATIVE SVG path.
 * This string is used to get absolute coordinates for the passed SVG vector,
 * then a basic conversor can turn it into a list of Operations, that holds
 * all need info to feed the super Path2D of this one.
 *
 * After that, all other transformations can be done using a AffineTransform
 * instance, who will do all the heavy work for us.
 *
 * @author Lucas Sousa
 *
 * @see SvgPathParsing
 * @see java.awt.geom.AffineTransform
 */
public class Shape extends Path2D.Float {

    public Shape(String d, float offX, float offY, float scale) {
        final ArrayList<SvgPathParsing.Operation> operations = SvgPathParsing.toAbsolutesOperations(d);

        //feeds the path with the content of SVG absolute path
        //(pre compiled into a operations list.
        //todo: implement remaining operations of a SVG path.
        for (SvgPathParsing.Operation o : operations) {
            switch (o.command) {
                case "M":
                    this.moveTo(
                            (o.args.get(0) + offX) * scale,
                            (o.args.get(1) + offY) * scale);
                    break;
                case "C":
                    this.curveTo(
                            (o.args.get(0) + offX) * scale,
                            (o.args.get(1) + offY) * scale,
                            (o.args.get(2) + offX) * scale,
                            (o.args.get(3) + offY) * scale,
                            (o.args.get(4) + offX) * scale,
                            (o.args.get(5) + offY) * scale);
                    break;
                case "Q":
                    this.quadTo(
                            (o.args.get(0) + offX) * scale,
                            (o.args.get(1) + offY) * scale,
                            (o.args.get(2) + offX) * scale,
                            (o.args.get(3) + offY) * scale);
                    break;
                case "Z":
                    this.closePath();
            }
        }
    }

    public boolean beingClicked(int button) {
        return Input.isMouseButton(button) && this.contains(Input.mousePoint);
    }

    public boolean clickUp(int button) {
        return Input.isMouseButtonUp(button) && this.contains(Input.mousePoint);
    }

    public boolean clickDown(int button) {
        return Input.isMouseButtonDown(button) && this.contains(Input.mousePoint);
    }


}
