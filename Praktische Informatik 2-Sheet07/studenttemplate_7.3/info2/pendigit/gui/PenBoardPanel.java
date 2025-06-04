package info2.pendigit.gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class represents the pen board on which one can write stuff with the mouse.
 * 
 * @author Sebastian Otte
 */
public class PenBoardPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

	private static final long serialVersionUID = -7350969441863931358L;

	public final static Color COLOR_BACK  = Color.BLACK;
	public final static Color COLOR_FRONT = Color.WHITE;
	
	public final static int FLUSH_TIME  = 700;
	
	public final static int WIDTH  = 400;
	public final static int HEIGHT = 400;
	public final static int SIZE   = WIDTH * HEIGHT;
	
	public final static int DRAWWIDTH  = 30;
	public final static int HDRAWWIDTH = DRAWWIDTH / 2;
	
	public final static int OUTPUT_WIDTH  = 12;
	public final static int OUTPUT_HEIGHT = 12;
	public final static int OUTPUT_SIZE   = OUTPUT_WIDTH * OUTPUT_HEIGHT;
	
	private Timer		  timer  = new Timer(FLUSH_TIME, this);
	
	private double[]	  output = new double[OUTPUT_SIZE];
	private BufferedImage img    = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB); 
	private BufferedImage map    = new BufferedImage(OUTPUT_WIDTH, OUTPUT_HEIGHT,
								   BufferedImage.TYPE_INT_ARGB);
	
	private PenBoardListener listener   = null;
	
	private String		  msg      = "";
	private Color	      msgcolor = Color.BLACK;
	
	private boolean		  changed  = true;
	private boolean       draw     = false;
	private int			  lastx    = 0;
	private int			  lasty    = 0;
	
	public double[] getOutput() {
		return this.output;		
	}
	
	public String getMsg() {
		return this.msg;
	}
	
	public void setMsg(final String msg) {
		this.msg = msg;
		this.repaint();
	}
	
	public Color getMsgColor() {
		return this.msgcolor;
	}
	
	public void setMsgColor(final Color pcol) {
		this.msgcolor = pcol;
	}
	
	public void refreshTimer() {
		this.timer.stop();
		this.timer.setDelay(FLUSH_TIME);
		this.timer.start();
	}
	
	private void updateMap() {
		Graphics2D g = (Graphics2D)this.map.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(this.img, 0, 0, OUTPUT_WIDTH, OUTPUT_HEIGHT, null);
	}
	
	public void flush() {
		if (this.changed) {
			this.updateMap();
			
			int i = 0;
			
			for (int y = 0; y < OUTPUT_HEIGHT; y++) {
				for (int x = 0; x < OUTPUT_WIDTH; x++) {
					int value = this.map.getRGB(x, y);
					this.output[i] = (255.0 - (value & 0xFF)) / 255.0;
					i++;
				}
			}
		}
		if (this.listener != null) {
		    this.listener.onFinished();
		}
		this.clear();
	}
	
	public PenBoardPanel(final PenBoardListener listener) {
		Dimension dim = new Dimension(WIDTH, HEIGHT);
		
		this.timer.stop();
		this.listener = listener;
		
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setSize(dim);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.clear();
	}
	
	public void clear() {
		final Graphics g = this.img.getGraphics();
		
		g.setColor(COLOR_BACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	
		this.repaint();
		this.changed = true;
	}
	
	public void drawDot(final int px, final int py) {
		final Graphics g = this.img.getGraphics();

		final int x = px - HDRAWWIDTH;
		final int y = py - HDRAWWIDTH;

		g.setColor(COLOR_FRONT);
		g.fillOval(x, y, DRAWWIDTH, DRAWWIDTH);

		this.repaint();
		this.changed = true;
	}
	
	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.img, 0, 0, WIDTH, HEIGHT, null);
		
		this.updateMap();
	
		final int owidth  = 50;
		final int oheight = 50;
		
		final int offy = 1;
		final int offx = 1;
		
		final Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setColor(Color.RED);
		g2d.drawRect(WIDTH - (owidth + 2 + offx), HEIGHT - (oheight + 2 + offy), owidth + 2, oheight + 2);
		g2d.drawImage(this.map, WIDTH - (owidth + 1 + offx), HEIGHT - (oheight + 1 + offy), owidth, oheight, null);
	
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
	    //
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	    //
	}

	@Override
	public void mouseExited(MouseEvent e) {
	    //
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.draw = true;
			this.drawDot(e.getX(), e.getY());
			this.lastx = e.getX();
			this.lasty = e.getY();
			this.pendown = true;
			this.timer.stop();
		}
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.draw = false;
			this.pendown = false;
			this.refreshTimer();
		}
	}

	private boolean pendown = false;
	
	@Override
	public void mouseDragged(final MouseEvent e) {
		if (this.draw) { 
			
			final double x1 = this.lastx;
			final double y1 = this.lasty;
			
			final double x2 = e.getX();
			final double y2 = e.getY();
			
			final double lx = x2 - x1;
			final double ly = y2 - y1;
			
			final double ll = Math.sqrt((lx * lx) + (ly * ly)); 
			
			final double incx = lx / ll; 
			final double incy = ly / ll;
			
			final int max = (int)ll;
			
			double x = x1;
			double y = y1;
			
			for (int i = 0; i <= max; i++) {
				this.drawDot((int)x, (int)y);
				x += incx;
				y += incy;
			}
			
			if (this.listener != null) {
			    this.listener.onPenDraw(x2 / (double)(this.getWidth()), 1.0 - (y2 / (double)(this.getHeight())), this.pendown);
			}
		
			this.pendown = false;
			
			this.lastx = e.getX();
			this.lasty = e.getY();
		}
	}

	@Override
	public void mouseMoved(final MouseEvent e) {
		//
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == this.timer) {
			this.flush();
			this.timer.stop();
		}
		
	}
}