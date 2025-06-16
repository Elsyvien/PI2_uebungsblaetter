package info2.blob.gui;


import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

import info2.blob.Blob;
import info2.blob.Maze;

public class MazePanel extends JPanel implements MazeListener {
    private static final long serialVersionUID = 100806644814083864L;

    public static final int MARGIN = 20;
    
    public static final int TILE_WIDTH  = 48;
    public static final int TILE_HEIGHT = 48;
    
    private int width;
    private int height;
    private int rows;
    private int cols;
    private int sleep;
    
    private Maze maze;
    private Vector2d[][] anchors;
    private Vector2d[][] centers;
    
    private BufferedImage mazeLayerBuffer;
    private BufferedImage blobLayerBuffer;
    private BufferedImage renderBuffer;
    
    private Graphics2D mazeLayerBufferContext;
    private Graphics2D blobLayerBufferContext;
    private Graphics2D renderBufferContext;
    
    public MazePanel(final Maze maze, final int sleep) {
        //
        this.maze = maze;
        this.rows = maze.getRows();
        this.cols = maze.getCols();
        this.sleep = sleep;
        this.height = this.rows * TILE_HEIGHT + 2 * MARGIN;
        this.width = this.cols * TILE_WIDTH + 2 * MARGIN;
        //
        final Dimension d = new Dimension(this.width, this.height);
        this.setPreferredSize(d);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
        this.setSize(d);
        //
        final Random rnd = new Random(1234);
        //
        this.centers = new Vector2d[this.rows][this.cols];
        this.anchors = new Vector2d[this.rows][this.cols];
        int yoffset = MARGIN;
        for (int i = 0; i < rows; i++) {
            int xoffset = MARGIN;
            for (int j = 0; j < cols; j++) {
                final int x = xoffset + (TILE_WIDTH / 2);
                final int y = yoffset + (TILE_HEIGHT / 2);
                this.centers[i][j] = new Vector2d(x, y);
                this.anchors[i][j] = new Vector2d(
                    x + rnd.nextInt(15) - 7,
                    y + rnd.nextInt(15) - 7
                );
                xoffset += TILE_WIDTH;
            }
            yoffset += TILE_HEIGHT;
        }
        //
        // initialize render buffers.
        //
        this.mazeLayerBuffer = new BufferedImage(
            this.width, this.height,
            BufferedImage.TYPE_INT_ARGB
        );
        this.mazeLayerBufferContext = this.mazeLayerBuffer.createGraphics();
        this.mazeLayerBufferContext.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        this.mazeLayerBufferContext.setRenderingHint(
            RenderingHints.KEY_ALPHA_INTERPOLATION,
            RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY
        );
        this.mazeLayerBufferContext.setRenderingHint(
            RenderingHints.KEY_COLOR_RENDERING,
            RenderingHints.VALUE_COLOR_RENDER_QUALITY
        );
        this.mazeLayerBufferContext.setRenderingHint(
            RenderingHints.KEY_DITHERING,
            RenderingHints.VALUE_DITHER_ENABLE
        );
        this.mazeLayerBufferContext.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BICUBIC
        );
        this.mazeLayerBufferContext.setRenderingHint(
            RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY
        );
        //
        this.blobLayerBuffer = new BufferedImage(
            this.width, this.height,
            BufferedImage.TYPE_INT_ARGB
        );
        this.blobLayerBufferContext = this.blobLayerBuffer.createGraphics();
        //
        this.renderBuffer = new BufferedImage(
            this.width, this.height,
            BufferedImage.TYPE_INT_ARGB
        );
        this.renderBufferContext = this.renderBuffer.createGraphics();
        
        //
        // we need to draw maze only once.
        //
        this.repaintMazeLayerBuffer();
    }
    
    
    private final void repaintMazeLayerBuffer() {
        final Graphics2D g = this.mazeLayerBufferContext;
        final Random rnd = new Random(1234);
        //
        // Draw background.
        //
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.width, this.height);
        //
        // Draw floor tiles.
        //
        int yoffset = MARGIN;
        //
        final int mazeHeight = this.rows * TILE_HEIGHT;
        final int mazeWidth = this.cols * TILE_WIDTH;
        //
        final int patchWidth = BlobGUIResources.IMG_SOIL.getWidth();
        final int patchHeight = BlobGUIResources.IMG_SOIL.getHeight();
        //
        final int patchRows = (mazeHeight / patchHeight) + (
            ((mazeHeight % patchHeight) > 0)?(1):(0)
        );
        final int patchCols = (mazeWidth / patchWidth) + (
            ((mazeWidth % patchWidth) > 0)?(1):(0)
        );
        //
        for (int i = 0; i < patchRows; i++ ) {
            
            int xoffset = MARGIN;
            
            final int patchRestHeight = Math.min(patchHeight, (mazeHeight - (i * patchHeight)));
            
            for (int j = 0; j < patchCols; j++) {
            
                final int patchRestWidth = Math.min(patchWidth, (mazeWidth - (j * patchWidth)));
                
                g.drawImage(
                    BlobGUIResources.IMG_SOIL,
                    xoffset, yoffset, 
                    xoffset + patchRestWidth, yoffset + patchRestHeight,
                    0, 0,
                    patchRestWidth,
                    patchRestHeight,
                    null
                );
                
                xoffset += patchWidth;
            }
            yoffset += patchHeight;
        }
        //
        // Draw cells.
        //
        //
        yoffset = MARGIN;
        //
        for (int i = 0; i < this.rows; i++) {
            //
            int xoffset = MARGIN;
            //
            for (int j = 0; j < this.cols; j++) {
                
                if (this.maze.isGoal(j, i)) {
                    g.drawImage(
                        BlobGUIResources.IMG_GOAL,
                        xoffset + (TILE_WIDTH / 2) - BlobGUIResources.IMG_GOAL.getWidth() / 2,
                        yoffset + (TILE_HEIGHT / 2) - BlobGUIResources.IMG_GOAL.getHeight() / 2,
                        null
                    );
                }
                
                xoffset += TILE_WIDTH;
            }
            yoffset += TILE_HEIGHT;
        }
        //
        // Draw walls.
        //
        //
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (this.maze.isWallAbove(j, i)) {
                    drawWallAbove(g, rnd, MARGIN, MARGIN, i, j, 4, 4);
                }
                if (this.maze.isWallLeft(j, i)) {
                    drawWallLeft(g, rnd, MARGIN, MARGIN, i, j, 4, 4);
                }
                if (i == (this.rows - 1) && this.maze.isWallBelow(j, i)) {
                    drawWallAbove(g, rnd, MARGIN, MARGIN, i + 1, j, 4, 4);
                }
                if (j == (this.cols - 1) && this.maze.isWallRight(j, i)) {
                    drawWallLeft(g, rnd, MARGIN, MARGIN, i, j + 1, 4, 4);
                }
            }
        }
    }
    
    private static void drawWallAbove(
        final Graphics2D g, final Random rnd, 
        final int xoffset, final int yoffset, 
        final int i, final int j, final int margin1, final int margin2
    ) {
        final int y = yoffset + (i * TILE_HEIGHT);
        final int x1 = (xoffset + (j * TILE_WIDTH)) + margin1;
        final int x2 = x1 + TILE_WIDTH - margin2;
        
        final int hw = 8;
        final int hh = 8;
        
        final int n = 5;
        
        final double step = ((double)(x2 - x1)) / (n - 1); 
        
        for (int k = 0; k < n; k++) {
            final int sx = (int)(x1 + (step * k) + ((rnd.nextDouble() - 0.5) * 2.0)); 
            final int sy = y + (int)((rnd.nextDouble() - 0.5) * 4.0);
            final int r = rnd.nextInt(BlobGUIResources.IMG_STONES.length);
            g.drawImage(BlobGUIResources.IMG_STONES[r], sx - hw, sy - hh, null);
        }
    }
    
    private static void drawWallLeft(
        final Graphics2D g, final Random rnd, 
        final int xoffset, final int yoffset,
        final int i, final int j, final int margin1, final int margin2
    ) {
        final int x = xoffset + (j * TILE_WIDTH);
        final int y1 = yoffset + (i * TILE_HEIGHT) + margin1;
        final int y2 = y1 + TILE_HEIGHT - margin2;
        
        final int hw = 8;
        final int hh = 8;
       
        final int n = 6;
        
        final double step = ((double)(y2 - y1)) / (n - 1); 
        
        for (int k = 0; k < n; k++) {
            final int sx = x + (int)((rnd.nextDouble() - 0.5) * 4.0);
            final int sy = (int)(y1 + (step * k) + ((rnd.nextDouble() - 0.5) * 2.0));
            final int r = rnd.nextInt(BlobGUIResources.IMG_STONES.length);
            g.drawImage(BlobGUIResources.IMG_STONES[r], sx - hw, sy - hh, null);
        }
    }
    
    
    private boolean isBlob(final int x, final int y) {
        return this.maze.getCellState(x, y) > Blob.CELL_STATE_FREE;
    }
    
    private boolean isValid(final int x, final int y) {
        return (
            ((x >= 0) && (x < this.cols)) &&
            ((y >= 0) && (y < this.rows))
        );
    }
    
    private boolean isEndPoint(final int x, final int y) {
        
        int ctr = 0;
        
        if (!this.maze.isWallLeft(x, y) && isValid(x - 1, y) && isBlob(x - 1, y)) ctr++;
        if (!this.maze.isWallRight(x, y) && isValid(x + 1, y) && isBlob(x + 1, y)) ctr++;
        if (!this.maze.isWallBelow(x, y) && isValid(x, y + 1) && isBlob(x, y + 1)) ctr++;
        if (!this.maze.isWallAbove(x, y) && isValid(x, y - 1) && isBlob(x, y - 1)) ctr++;

        return ctr == 1;
    }
    
    private void drawBlobEndPoint(
        final Graphics2D g, 
        final int x, final int y,
        final Random rnd
    ) {
        final int state = this.maze.getCellState(x, y);
        
        final Vector2d c = this.centers[y][x];
        
        int rad = 10;
        
        if (state == Blob.CELL_STATE_BLOB_FRESH) {
            //
            // Alive.
            //
            rnd.setSeed(System.currentTimeMillis());
            g.setColor(new Color(1.0f, 1.0f, 0.0f, 0.6f));
            
            final double pulse = (this.ctr * 0.5);
            if (this.maze.isGoal(x,  y)) { 
                rad += (int)(7.0 * Math.sin(pulse));
            } else {
                rad += (int)(2.0 * Math.sin(pulse));
            }
        } else if (state == Blob.CELL_STATE_BLOB_DEAD) {
            //
            // Dead.
            //
            rnd.setSeed(x * y);
            g.setColor(new Color(0.7f, 0.7f, 0.4f, 0.4f));
        }
        
        g.fillOval((int)(c.x - rad), (int)(c.y - rad), rad * 2, rad * 2);
        
        g.setStroke(this.stroke_fibre);
        
        final int steps = 25;
        final Vector2d p = c.copy();
        
        for (int i = 0; i < steps; i++) {
            p.x = rnd.nextDouble() - 0.5;
            p.y = rnd.nextDouble() - 0.5;
            Vector2d.normalize(p, p);
            Vector2d.mul(p, rad + rnd.nextInt(9), p);
                    
            final int px = (int)c.x;
            final int py = (int)c.y;
            final int px2 = (int)(c.x + p.x);
            final int py2 = (int)(c.y + p.y);
            
            g.drawLine(px, py, px2, py2);
            g.drawLine(px2, py2, px2 + rnd.nextInt(9) - 4, py2 + rnd.nextInt(11) - 4);
            g.drawLine(px2, py2, px2 + rnd.nextInt(9) - 4, py2 + rnd.nextInt(11) - 4);

        }
    }
    
    private Stroke stroke_channel_dead  = new BasicStroke(6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    private Stroke stroke_channel_alive = new BasicStroke(6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    private Stroke stroke_fibre = new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

    
    private void drawBlobChannel(
        final Graphics2D g, 
        final int x, final int y, 
        final int x2, final int y2, 
        final Random rnd
    ) {
        
        final int state = this.maze.getCellState(x2, y2);
        if (state == Blob.CELL_STATE_BLOB_FRESH) {
            //
            // Alive.
            //
            rnd.setSeed(System.currentTimeMillis());
            g.setStroke(this.stroke_channel_alive);
            g.setColor(new Color(1.0f, 1.0f, 0.0f, 0.6f));
        } else if (state == Blob.CELL_STATE_BLOB_DEAD) {
            //
            // Dead.
            //
            g.setStroke(this.stroke_channel_dead);
            rnd.setSeed(x * y);
            g.setColor(new Color(0.7f, 0.7f, 0.4f, 0.4f));
        }
        
        final Vector2d p1 = this.anchors[y][x];
        final Vector2d p2 = this.anchors[y2][x2];
        
        final Vector2d dir = Vector2d.sub(p2, p1);
        final Vector2d orth = new Vector2d(-dir.y, dir.x);
        Vector2d.normalize(orth, orth);
        
        g.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
        
        g.setStroke(this.stroke_fibre);
        
        final int steps = 25;
        final Vector2d step = Vector2d.mul(dir, 1.0 / (steps));
        
        final Vector2d p = p1.copy();
        for (int i = 0; i < steps; i++) {
            Vector2d.add(p, step, p);
            final int px = (int)p.x;
            final int py = (int)p.y;
            final int px2 = (int)(p.x + (rnd.nextDouble() - 0.5) * 20.0 * orth.x);
            final int py2 = (int)(p.y + (rnd.nextDouble() - 0.5) * 20.0 * orth.y);
            g.drawLine(px, py, px2, py2);
            g.drawLine(px2, py2, px2 + rnd.nextInt(9) - 4, py2 + rnd.nextInt(9) - 4);
            g.drawLine(px2, py2, px2 + rnd.nextInt(9) - 4, py2 + rnd.nextInt(9) - 4);
        }
    }
    
    private boolean isSucc(final int x, final int y, final int x2, final int y2) {
        return (
            isValid(x2, y2) && 
            isBlob(x2, y2) && 
            (this.maze.getAccessValue(x2, y2) > this.maze.getAccessValue(x, y))
        );
    }
    
    
    private void drawBlobChannels(final Graphics2D g, final int x, final int y, Random rnd) {
        if (!this.maze.isWallLeft(x, y) && isSucc(x, y, x - 1, y)) {
            drawBlobChannel(g, x, y, x - 1, y, rnd);
        }
        if (!this.maze.isWallRight(x, y) && isSucc(x, y, x + 1, y)) {
            drawBlobChannel(g, x, y, x + 1, y, rnd);
        }
        if (!this.maze.isWallAbove(x, y) && isSucc(x, y, x, y - 1)) {
            drawBlobChannel(g, x, y, x, y - 1, rnd);
        }
        if (!this.maze.isWallBelow(x, y) && isSucc(x, y, x, y + 1)) {
            drawBlobChannel(g, x, y, x, y + 1, rnd);
        }
    }
    
    private final void repaintBlobRenderBuffer() {
        final Graphics2D g = this.blobLayerBufferContext;
        //
        final double pulse = (this.ctr) * 0.5;
        this.stroke_channel_alive = new BasicStroke(
            8f + (float)Math.sin(pulse) * 3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND
        );
        //
        g.setBackground(new Color(0f, 0f, 0f, 0f));
        g.clearRect(0, 0, this.blobLayerBuffer.getWidth(), this.blobLayerBuffer.getHeight());
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
        //
        // Draw cells.
        //
        for (int i = 0; i < this.rows; i++) {
            //
            for (int j = 0; j < this.cols; j++) {
                if (isBlob(j, i)) {
                    Random rnd = new Random(1234);
                    if (isEndPoint(j, i)) {
                        drawBlobEndPoint(g, j, i, rnd);
                    }
                    drawBlobChannels(g, j, i, rnd);
                }
            }
        }
    }
    
    private long ctr = 0;
    
    private final void repaintRenderBuffer() {
        final Graphics2D g = this.renderBufferContext;
        g.drawImage(this.mazeLayerBuffer, 0, 0, null);
        this.repaintBlobRenderBuffer();
        g.drawImage(this.blobLayerBuffer, 0, 0, null);
        this.ctr++;
    }
    
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.repaintRenderBuffer();
        g.drawImage(this.renderBuffer, 0, 0, null);
    }

    @Override
    public void onUpdate(final Maze maze) {
        if (this.maze == maze) {
            //this.repaint();
            if (this.sleep > 0) {
                try {
                    Thread.sleep(this.sleep);
                } catch (InterruptedException e) {}
            }
        }
    }

}