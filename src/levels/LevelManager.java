package levels;

import main.Game;
import java.awt.Graphics;
import entities.Enemy;
import javax.swing.SwingUtilities;
import main.LogroManager;
import main.Sesion;

public class LevelManager {

    private Game game;
    private Level currentLevel;
    private String[] levelFiles = {"nivel1.json", "nivel2.json","nivel3.json","nivel4.json","nivel5.json",
                      "nivel6.json","nivel7.json","nivel8.json","nivel9.json","nivel10.json"};
    private int currentIndex = 0;
    private boolean exitSonando  = false;
    private boolean enemyMatando = false;

    public LevelManager(Game game) {
        this.game = game;
        loadLevel(currentIndex);
    }

    public void loadLevel(int index) {
        if (currentIndex == 1) {
            LogroManager.completoTutorial();
        }
        LogroManager.terminarNivel(currentIndex, levelFiles.length);
        exitSonando  = false;  // ← agregá esta
        enemyMatando = false;
        currentIndex = index;
        currentLevel = new Level(levelFiles[index]);
        game.getPlayer().setRectPos(
            (int) currentLevel.getSpawnX(),
            (int) currentLevel.getSpawnY()
        );
        game.getPlayer().setWalls(currentLevel.getWalls());
        game.getPlayer().setTraps(currentLevel.getTraps());
        currentLevel.getExitBeacon().setTraps(currentLevel.getTraps());
    }

    public void nextLevel() {
        if (currentIndex < levelFiles.length - 1) {
            currentIndex++;
            loadLevel(currentIndex);
        } else {
            float tiempoTotal = game.getTiempoJugado();
            persistencia.Usuario u = main.Sesion.getUsuario();
            if (u != null) {
                persistencia.Ranking r = new persistencia.Ranking();
                r.setUsuarioId(u.getUsuarioId());
                r.setTiempoMax(tiempoTotal);
                r.setFecha(new java.sql.Date(System.currentTimeMillis()));
                new persistencia.RankingDAO().InsertarDato(r);
            }
            game.stopGameLoop();
            persistencia.PartidaGuardadaDAO dao = new persistencia.PartidaGuardadaDAO();
            dao.EliminarDato(Sesion.getUsuario().getUsuarioId());
            SwingUtilities.invokeLater(() -> main.MainClass.mostrarCreditos(tiempoTotal));
        }
    }

    public void update() {
        currentLevel.update(
            game.getPlayer().getX() + utilz.Constants.PlayerConstants.DRAW_WIDTH  / 2f,
            game.getPlayer().getY() + utilz.Constants.PlayerConstants.DRAW_HEIGHT / 2f,
            game.getPlayer().getWaves()
        );

        
        for (Enemy e : currentLevel.getEnemies()) {
            if (e.isTouchingPlayer(
                game.getPlayer().getX() + utilz.Constants.PlayerConstants.DRAW_WIDTH  / 2f,
                game.getPlayer().getY() + utilz.Constants.PlayerConstants.DRAW_HEIGHT / 2f)
                && !enemyMatando) {
                enemyMatando = true;
                utilz.SoundManager.playEnemyKill();
                game.getGamePanel().triggerDeath();
            }
        }

        
        for (Trap t : currentLevel.getTraps()) {
            if (t.contains(
                game.getPlayer().getX() + utilz.Constants.PlayerConstants.DRAW_WIDTH  / 2f,
                game.getPlayer().getY() + utilz.Constants.PlayerConstants.DRAW_HEIGHT / 2f)) {
                game.getGamePanel().triggerDeath();
            }
        }

        
        float px = game.getPlayer().getX();
        float py = game.getPlayer().getY();
        float ex = currentLevel.getExitX();
        float ey = currentLevel.getExitY();
        float dist = (float) Math.sqrt((px - ex) * (px - ex) + (py - ey) * (py - ey));
        if (dist < 40f && !exitSonando) {
            exitSonando = true;
            utilz.SoundManager.playExit();
            nextLevel();
        }
    }

    public void draw(Graphics g) {
        currentLevel.draw(g);
    }

    public Level getCurrentLevel() { return currentLevel; }
    public int getCurrentIndex()   { return currentIndex; }

    public void resetCurrentLevel() {
        game.getPlayer().clearWaves();
        exitSonando  = false;
        enemyMatando = false;
        loadLevel(currentIndex);
    }
}
