/*******************************************************************************
 * JANNLab Neural Network Framework for Java
 * Copyright (C) 2012-2013 Sebastian Otte
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package info2.pendigit.gui; 

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

/**
 *  A simple swing based online diagram for visualization of
 *  data sequences.
 * <br></br> 
 * @author Sebastian Otte
 */
public class OnlineDiagram extends JPanel {
    private static final long serialVersionUID = 5369278568426927464L;
    //
    private double  lbd        = -1.0;
    private double  ubd        = 1.0;
    private double  range      = 2.0;
    private int     length     = 1;
    private int     curves     = 1;
    private int     drawoffset = 0;
    private int     drawlength = 0;
    private int     thickness  = 2;
    private Stroke  stroke     = null;
    private Stroke  dotted     = new BasicStroke(
        1f, 
        BasicStroke.CAP_ROUND, 
        BasicStroke.JOIN_ROUND, 
        1f, 
        new float[] {4f}, 
        0f
    );
    //
    private double[][] data      = null;
    private boolean[]  visible   = null;
    private Color[]    color     = null; 
    private boolean    drawlines = true;
    
    private double[]   hlines    = null; 
    
    public boolean getDrawLines() {
        return this.drawlines;
    }
    
    public void setDrawLines(final boolean drawlines) {
        this.drawlines = drawlines;
    }
    
    public void reset() {
        this.drawoffset = 0;
        this.drawlength = 0;
        this.repaint();
    }
    
    public void record(final double ...values) {
        //
        // determine last idx.
        //
        final int idx = (this.drawoffset + this.drawlength) % this.length;
        //
        // add values.
        //
        final int min = Math.min(this.curves, values.length);
        for (int i = 0; i < min; i++) {
            this.data[i][idx] = values[i];
        }
        //
        // shift values.
        //
        if (this.drawlength < this.length) {
            this.drawlength++;
        } else {
            this.drawoffset++;
        }
    }
    
    private void setup() {
        this.data    = new double[this.curves][this.length];
        this.color   = new Color[this.curves];
        this.visible = new boolean[this.curves];
        for (int i = 0; i < this.visible.length; i++) {
            this.visible[i] = true;
        }
        //
        this.drawoffset = 0;
        this.drawlength = 0;
        //
        this.stroke = new BasicStroke(this.thickness);
    }
    
    public void assignColor(final int curve, final Color color) {
        this.color[curve] = color;
    }
    
    public boolean getVisible(final int curve) {
        return this.visible[curve];
    }
    
    public void setVisible(final int curve, final boolean visible) {
        this.visible[curve] = visible;
    }
    
    public OnlineDiagram(
            final int length,
            final double lbd,
            final double ubd,
            final int curves,
            final double... hlines
    ) {
        this.lbd = Math.min(lbd, ubd);
        this.ubd = Math.max(lbd, ubd);
        this.range = this.ubd - this.lbd;
        //
        this.length = Math.max(1, length);
        this.curves  = Math.max(1, curves);
        //
        this.hlines = hlines;
        //
        this.setup();
        this.setBackground(new Color(0, 30, 0));
    }
    
    private int xValue(final int x) {
        final int width = this.getWidth();
        final int xx    = (x * width) / this.length;
        //
        return xx;
    }
    
    private int yValue(final double y) {
        final double height = (this.getHeight());
        final double yy     = ((y - this.lbd) * height / this.range);
        //
        return (int)yy;
    }
    
    
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        //
        final double range = this.ubd - this.lbd;
        //
        super.paintComponent(g);
        if (this.drawlines) {
            g2.setColor(Color.GRAY);
            g2.setStroke(this.dotted);
            //
            final int x1 = 0;
            final int x2 = this.getWidth() - 1;
            //
            for (double line : hlines) {
                //
                final int y = this.getHeight() - (int)(((line - lbd) / range) * this.getHeight());
                g2.drawLine(x1, y, x2, y);
            }
        }
        
        g2.setStroke(this.stroke);
        //
        for (int l = 0; l < this.curves; l++) {
            if (!this.visible[l]) continue;
            final double[] line = data[l];
            //
            if (this.color[l] == null) {
                g2.setColor(Color.BLACK);
            } else {
                g2.setColor(this.color[l]);
            }
            //
            int hi  = this.getHeight() - 1;
            int xx1 = this.xValue(0);
            int yy1 = hi - this.yValue(line[this.drawoffset % this.length]);
            //
            for (int i = 1; i < this.drawlength; i++) {
                final int xx2 = xValue(i);
                final int yy2 = hi - yValue(
                    line[(this.drawoffset + i) % this.length]
                );
                //
                g2.drawLine(xx1, yy1, xx2, yy2);
                //
                xx1 = xx2;
                yy1 = yy2;
            }
        }
    }
    
    public int getLines() {
        return this.curves;
    }
    
    public double getRange() {
        return this.range;
    }
    
    public double getLbd() {
        return this.lbd;
    }
    
    public double getUbd() {
        return this.ubd;
    }
      
}
