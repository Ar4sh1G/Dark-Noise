package utilz;

public class Constants {

    
    public static class Screen {
        public static final int GAME_WIDTH  = 1280;
        public static final int GAME_HEIGHT = 800;
    }

    
    public static class PlayerConstants {

        // Acciones
        public static final int IDLE    = 0;
        public static final int WALKING = 1;

        // Direcciones
        public static final int DIR_DOWN  = 0;
        public static final int DIR_UP    = 1;
        public static final int DIR_RIGHT = 2;
        public static final int DIR_LEFT  = 3;

        // Tamaño del sprite original (cada PNG individual)
        public static final int SPRITE_WIDTH  = 64;
        public static final int SPRITE_HEIGHT = 40;

        // Sub-imagen a recortar del PNG (zona útil del personaje)
        // Ajustá SUB_X/SUB_Y si el PNG tiene márgenes
        public static final int SUB_X      = 0;
        public static final int SUB_Y      = 0;
        public static final int SUB_WIDTH  = SPRITE_WIDTH;   // 64
        public static final int SUB_HEIGHT = SPRITE_HEIGHT;  // 40

        // Tamaño en pantalla
        public static final int DRAW_WIDTH  = 30;
        public static final int DRAW_HEIGHT = 30;

        // Velocidad de animación (ticks por fotograma)
        public static final int ANIMATION_SPEED = 120;

        // Velocidad de movimiento
        public static final float MOVE_SPEED = 1.0f;

        public static int GetSpriteAmount(int playerAction) {
            switch (playerAction) {
                case IDLE:    return 1;
                case WALKING: return 2;
                default:      return 1;
            }
        }
    }

    
    // Cada constante es el nombre del PNG dentro de /res/
    public static class PlayerSprites {

        // Idle (posición neutral - los archivos "2")
        public static final String IDLE_DOWN  = "pixel_art_large - 2 abajo.png";
        public static final String IDLE_UP    = "pixel_art_large - 2 arriba.png";
        public static final String IDLE_RIGHT = "pixel_art_large - 2 derecha.png";
        public static final String IDLE_LEFT  = "pixel_art_large - 2 izquierda.png";

        // Walking – pie derecho adelante
        public static final String WALK_RIGHT_FOOT_DOWN  = "pixel_art_large - 1 pie derecho abajo.png";
        public static final String WALK_RIGHT_FOOT_UP    = "pixel_art_large - 1 pie derecho arriba.png";
        public static final String WALK_RIGHT_FOOT_RIGHT = "pixel_art_large - 1 pie derecho derecha.png";
        public static final String WALK_RIGHT_FOOT_LEFT  = "pixel_art_large - 1 pie derecho izquierda.png";

        // Walking – pie izquierdo adelante
        public static final String WALK_LEFT_FOOT_DOWN  = "pixel_art_large - 1 pie izquierdo abajo.png";
        public static final String WALK_LEFT_FOOT_UP    = "pixel_art_large - 1 pie izquierdo arriba.png";
        public static final String WALK_LEFT_FOOT_RIGHT = "pixel_art_large - 1 pie izquierdo derecha.png";
        public static final String WALK_LEFT_FOOT_LEFT  = "pixel_art_large - 1 pie izquierdo izquierda.png";
    }
}