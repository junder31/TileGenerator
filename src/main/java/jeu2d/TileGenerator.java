package jeu2d;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by junderwood42 on 5/18/16.
 */
public class TileGenerator {
    private final int tileWidthPx;
    private final int tileHeightPx;
    private final int layers;

    public TileGenerator(int tileWidthPx, int layers) {
        //
        this.tileWidthPx = tileWidthPx;
        this.tileHeightPx = (tileWidthPx / 4) * (layers + 1);
        this.layers = layers;
    }

    @SuppressWarnings("unused")
    public int getTileWidthPx() {
        return tileWidthPx;
    }

    public static void main(String[] args) throws Exception {
        TileGenerator tileGenerator = new TileGenerator(64, 3);
        BufferedImage image = tileGenerator.createTiles();
        File file = new File("tiles.png");
        ImageIO.write(image, "png", file);
    }

    public BufferedImage createTiles() {
        final int width = tileWidthPx * 4;
        final int height = tileHeightPx * 4;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        //iterate all tiles
        Tile tile = new Tile(new TileVertex(0), new TileVertex(0), new TileVertex(0), new TileVertex(0));
        BufferedImage tileImage = drawTile(tile);

        image.getGraphics().drawImage(tileImage, 0, 0, null);

        return image;
    }

    private BufferedImage drawTile(Tile tile) {
        BufferedImage tileImage = new BufferedImage(tileWidthPx, tileHeightPx, BufferedImage.TYPE_INT_ARGB);

    }
}
