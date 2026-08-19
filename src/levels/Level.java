package levels;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;   
import entities.ExitBeacon;
import entities.Enemy;
import entities.SoundWave;
import utilz.MapLoader;

public class Level {

    private List<Wall> walls;
    private float spawnX, spawnY;
    private float exitX,  exitY;
    private ExitBeacon exitBeacon;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Trap> traps = new ArrayList<>();

    public Level(String fileName) {
        walls      = MapLoader.loadWalls(fileName);
        spawnX     = MapLoader.spawnX;
        spawnY     = MapLoader.spawnY;
        exitX      = MapLoader.exitX;
        exitY      = MapLoader.exitY;
        exitBeacon = new ExitBeacon(exitX, exitY, walls);
        traps      = MapLoader.traps;  

        for (float[] pos : MapLoader.enemyPositions) {
            Enemy e = new Enemy(pos[0], pos[1]);
            e.setWalls(walls);
            enemies.add(e);
        }
    }

    public float getSpawnX() { return spawnX; }
    public float getSpawnY() { return spawnY; }
    public float getExitX()  { return exitX;  }
    public float getExitY()  { return exitY;  }

    public List<Wall> getWalls() { return walls; }

    public void draw(Graphics g) {
        exitBeacon.render(g);
        for (Enemy e : enemies) e.render(g);
    }

    public void update(float playerX, float playerY, List<SoundWave> waves) {
        exitBeacon.update();
        for (Enemy e : enemies) {
            e.update(playerX, playerY, waves);
        }
    }
    
    public List<Enemy> getEnemies() { return enemies; }
    public List<Trap> getTraps() { return traps; }
    public ExitBeacon getExitBeacon() { return exitBeacon; }
}