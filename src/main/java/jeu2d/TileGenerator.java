package jeu2d;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by junderwood42 on 5/18/16.
 */
public class TileGenerator {
    private static final int[][] vertexHeightCombinations = {
            { 0, 0, 0, 0 },
            { 1, 0, 0, 0 },
            { 1, 1, 0, 0 },
            { 1, 0, 1, 0 },
            { 1, 1, 1, 0 }
    };
    private final int tileWidthPx;
    private final int tileHeightPx;
    private final int blockSize;

    public TileGenerator(int tileWidthPx) {
        this.tileWidthPx = tileWidthPx;
        this.tileHeightPx = tileWidthPx;
        this.blockSize = tileWidthPx / 4;
    }

    @SuppressWarnings("unused")
    public int getTileWidthPx() {
        return tileWidthPx;
    }

    public static void main(String[] args) throws Exception {
        TileGenerator tileGenerator = new TileGenerator(64);
        BufferedImage image = tileGenerator.createTiles();
        File file = new File("tiles.png");
        ImageIO.write(image, "png", file);
    }

    public BufferedImage createTiles() {
        final int width = tileWidthPx * 4;
        final int height = tileHeightPx * 5;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.getGraphics();
        //iterate all tiles
        int y = 0;
        for(int[] vertexHeights : vertexHeightCombinations) {
            Tile tile = new Tile(new TileVertex(vertexHeights[0]),
                                 new TileVertex(vertexHeights[1]),
                                 new TileVertex(vertexHeights[2]),
                                 new TileVertex(vertexHeights[3]));
            for(int x = 0; x < 4; x++) {
                graphics.drawImage(drawTile(tile), x * tileWidthPx, y * tileHeightPx, null);
                tile = tile.rotateClockwise();
            }
            y++;
        }

        return image;
    }

    private BufferedImage drawTile(Tile tile) {
        BufferedImage tileImage = new BufferedImage(tileWidthPx, tileHeightPx, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = (Graphics2D) tileImage.getGraphics();
        drawLeftSide(graphics2D, tile);
        drawRightSide(graphics2D, tile);
        drawTop(graphics2D, tile);
        return tileImage;
    }

    private void drawTop(Graphics2D graphics2D, Tile tile) {
        int[] topXPoints = { 0, blockSize * 2, tileWidthPx};
        int[] topYPoints = {
                ((tile.getLeftVertex().getHeight() * 2 + 1) * blockSize),
                ((tile.getTopVertex().getHeight() * 2 + 0) * blockSize),
                ((tile.getRightVertex().getHeight() * 2 + 1) * blockSize) };

        Polygon topHalfTriangle = new Polygon(topXPoints, topYPoints, topXPoints.length);
        graphics2D.setColor(new Color(50, 225, 50));
        graphics2D.fillPolygon(topHalfTriangle);

        int[] bottomXPoints = { 0, blockSize * 2, tileWidthPx};
        int[] bottomYPoints = {
                ((tile.getLeftVertex().getHeight() * 2 + 1) * blockSize),
                ((tile.getBottomVertex().getHeight() * 2 + 2) * blockSize),
                ((tile.getRightVertex().getHeight() * 2 + 1) * blockSize) };

        Polygon bottomHalfTriangle = new Polygon(bottomXPoints, bottomYPoints, bottomXPoints.length);
        graphics2D.setColor(new Color(25, 205, 25));
        graphics2D.fillPolygon(bottomHalfTriangle);

        drawTopBorder(graphics2D, tile);
    }

    private void drawTopBorder(Graphics2D graphics2D, Tile tile) {
        int[] xPoints = { 0, blockSize * 2, tileWidthPx, blockSize * 2};
        int[] yPoints = {
                ((tile.getLeftVertex().getHeight() * 2 + 1) * blockSize),
                ((tile.getTopVertex().getHeight() * 2 + 0) * blockSize),
                ((tile.getRightVertex().getHeight() * 2 + 1) * blockSize),
                ((tile.getBottomVertex().getHeight() * 2 + 2) * blockSize)};
        Polygon topBorder = new Polygon(xPoints, yPoints, xPoints.length);
        graphics2D.setColor(new Color(0, 175, 0));
        graphics2D.drawPolygon(topBorder);
    }

    private void drawLeftSide(Graphics2D graphics2D, Tile tile) {
        int[] xPoints = { 0, tileWidthPx / 2, tileWidthPx / 2, 0};
        int[] yPoints = {
                ((tile.getLeftVertex().getHeight() * 2 + 1) * blockSize),
                ((tile.getBottomVertex().getHeight() * 2 + 2) * blockSize),
                tileHeightPx,
                tileHeightPx - blockSize };
        Polygon left = new Polygon(xPoints, yPoints, xPoints.length);

        graphics2D.setColor(new Color(102, 51, 0));
        graphics2D.fillPolygon(left);
        graphics2D.setColor(new Color(153, 76, 0));
        graphics2D.drawPolygon(left);
    }

    private void drawRightSide(Graphics2D graphics2D, Tile tile) {
        int[] xPoints = { tileWidthPx / 2, tileWidthPx, tileWidthPx, tileWidthPx / 2 };
        int[] yPoints = {
                ((tile.getBottomVertex().getHeight() * 2 + 2) * blockSize),
                ((tile.getRightVertex().getHeight() * 2 + 1) * blockSize),
                tileHeightPx - blockSize,
                tileHeightPx };
        Polygon right = new Polygon(xPoints, yPoints, xPoints.length);

        graphics2D.setColor(new Color(102, 51, 0));
        graphics2D.fillPolygon(right);
        graphics2D.setColor(new Color(153, 76, 0));
        graphics2D.drawPolygon(right);
    }
}
