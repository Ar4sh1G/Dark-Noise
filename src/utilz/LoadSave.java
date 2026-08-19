package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {

    
    public static final String LEVEL_ATLAS = "WALL.jpg";
    
    public static BufferedImage GetSpriteAtlas(String fileName) {
        InputStream is = LoadSave.class.getResourceAsStream("/res/" + fileName);

        
        if (is == null) {
            System.err.println("[LoadSave] Recurso no encontrado: " + fileName);
            return null;
        }

        BufferedImage img = null;
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("[LoadSave] Error leyendo: " + fileName);
            e.printStackTrace();
        } finally {
            try { is.close(); } catch (IOException ignored) {}
        }
        return img;
    }
}