package info2.pendigit.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import info2.pendigit.resources.SequenceClassifier;


/**
 * This is the main window of the PenDigitRecognition demo. 
 * 
 * @author Sebastian Otte
 */
public class PenDigitRecognitionFrame extends JFrame implements KeyListener, PenBoardListener, ActionListener {
	private static final long serialVersionUID = 451106717250647418L;
	
	private PenBoardPanel panel    = null;
	private JTextField	  tbdata   = null;
	private OnlineDiagram diagram  = null;
	
	private boolean		  training = false;

	private SequenceClassifier predictor = null;
	
    public static final int green1 = 0x146614;
    public static final int green3 = 0x50b350;
    public static final int red1 = 0x660000;
    public static final int red3 = 0xb33e3e;
    public static final int blue1 = 0x143066;
    public static final int blue3 = 0x5079b3;
    public static final int orange1 = 0xb34e0b;
    public static final int orange3 = 0xd99a3d;
    public static final int magenta1 = 0x581466;
    public static final int magenta3 = 0x9650b3;
    public static final int cyan3 = 0x20d3d3;
    public static final int gray1 = 0x4d4d4d;
    public static final int gray3 = 0x8c8c8c;
    
    public static final int[] COLORS = {
	    green1,
	    green3,
	    red1,
	    red3,
	    blue1,
	    blue3,
	    gray3,
	    orange3,
	    cyan3,
	    magenta3
	};
	
	public PenDigitRecognitionFrame(final SequenceClassifier pdi) {
		super("Praktische Informatik 2 - Handwriting Recognition");
		
		this.predictor = pdi;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.panel   = new PenBoardPanel(this);
		this.diagram = new OnlineDiagram(200, -0.1, 1.1, 10, 0.0, 0.5, 1.0) {
            private static final long serialVersionUID = 1L;
            private Font font = new Font("Verdana", Font.BOLD, 20); 
		   
	        @Override
	        protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        g.setFont(font);
		        final int y = 20;
		        final int cellwidth = this.getWidth() / 10;
		        //
		        for (int i = 0; i < 10; i++) {
		            final int x = (i * cellwidth) + (cellwidth / 2);
		            g.setColor(new Color(COLORS[i]));
		            g.drawString("" + i, x, y);
		        }
		    }
		};
		this.diagram.setBackground(Color.BLACK);
		//
		for (int i = 0; i < COLORS.length; i++) {
		    this.diagram.assignColor(i, new Color(COLORS[i]));
		}
		//
		//
		final JPanel mainpanel = new JPanel();
		mainpanel.setLayout(new BorderLayout(0, 0));
		
		final JPanel leftpanel  = new JPanel();
		final JPanel rightpanel = new JPanel();
		
		leftpanel.setLayout(new BorderLayout(5, 5));
		this.tbdata = new JTextField();
		//
		final JPanel cont1 = new JPanel();
		final JPanel cont11 = new JPanel();
		final JPanel cont2 = new JPanel();
		final JPanel cont22 = new JPanel();
		//
		cont11.setBorder(BorderFactory.createEtchedBorder());
		cont22.setBorder(BorderFactory.createEtchedBorder());
		//
		//cont1.setBorder(new EmptyBorder(0, 0, 0, 0));
		//cont2.setBorder(new EmptyBorder(0, 0, 0, 0));
		//
		cont1.add(cont11);
		cont11.add(this.panel);
		//
		final Dimension d = new Dimension(PenBoardPanel.WIDTH, 50);
		this.tbdata.setPreferredSize(d);
		this.tbdata.setMinimumSize(d);
		this.tbdata.setMaximumSize(d);
		this.tbdata.setSize(d);
		
		this.tbdata.setFont(new Font(
		    this.tbdata.getFont().getName(),
		    this.tbdata.getFont().getStyle(),
		    (int)(this.tbdata.getFont().getSize() * 2)
		));
		
		cont2.add(cont22);
		cont22.add(this.tbdata);
		
		leftpanel.add(cont1, BorderLayout.NORTH);
		leftpanel.add(cont2, BorderLayout.SOUTH);
		
		rightpanel.setLayout(new BorderLayout(5, 5));
		rightpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		final JPanel rightpanelcont = new JPanel();
		rightpanelcont.setLayout(new BorderLayout(5, 5));
		rightpanelcont.setBorder(BorderFactory.createEtchedBorder());
		final JPanel rightpanelcontin = new JPanel();
        rightpanelcontin.setLayout(new BorderLayout(5, 5));
        rightpanelcontin.setBorder(new EmptyBorder(5, 5, 5, 5));
        rightpanelcontin.add(diagram, BorderLayout.CENTER);
        rightpanelcont.add(rightpanelcontin, BorderLayout.CENTER);
        rightpanel.add(rightpanelcont, BorderLayout.CENTER);
		
		mainpanel.add(leftpanel, BorderLayout.WEST);
		
		final Dimension rightpaneldim = new Dimension(600, leftpanel.getHeight());
		rightpanel.setPreferredSize(rightpaneldim);
		rightpanel.setSize(rightpaneldim);
		mainpanel.add(rightpanel, BorderLayout.EAST);
		this.add(mainpanel);
		
		this.panel.addKeyListener(this);
		this.addKeyListener(this);
		
		this.pack();
		this.setResizable(false);
		
		this.panel.setFocusTraversalKeysEnabled(false);
		this.setFocusTraversalKeysEnabled(false);
	}
	
	
	@Override
	public void paint(final Graphics g) {
		super.paint(g);
	}
	

	@Override
	public void keyPressed(final KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (this.training) {
				this.training = false;
			}
		}
	}

	@Override
	public void keyReleased(final KeyEvent e) {
	    //
	}
	
	private double[] classification = null;
	
	private void useClassification() {
	    
		if (classification == null) return;
        //
		// print result.
		//
		for (int i = 0; i < classification.length; i++) {
			System.out.print("" + i + ":" + classification[i] + "\t");
		}
		System.out.println();
		//
		// take the best value. 
		//
		int    idx = -1;
		double max = -Double.MIN_VALUE;
		for (int i = 0; i < classification.length; i++) {
	    	if (classification[i] > max) {
				max = classification[i];
				idx = i;
			}
		}
		final String usemsg = "" + idx;
		System.out.println("candidate '" + idx + "' wins with value " + max + ".");
		this.tbdata.setText(this.tbdata.getText() + idx);
		this.panel.setMsgColor(Color.RED);
		this.panel.setMsg(usemsg);
	}
	
	@Override
	public void onPenDraw(final double x, final double y, final boolean pendown) {
		//
        // feed the function with the input and grab output.
	    // perform "normalization" to match training statistics.
        //
	    final double nx = (x - 0.5) / 0.25;
	    final double ny = (y - 0.5) / 0.25;
	    //
	    this.classification = this.predictor.predict(new double[]{nx, ny, (pendown)?(1.0):(0.0)});
	    this.diagram.record(this.classification);
	    this.diagram.repaint();
	}

	@Override
	public void onFinished() {
	    this.useClassification();
	    this.predictor.reset();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	    //
	}

}