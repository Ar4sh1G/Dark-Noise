/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilz;

import levels.Wall;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import levels.Trap;

public class MapLoader {

    public static float spawnX, spawnY;
    public static float exitX,  exitY;
    public static List<float[]> enemyPositions = new ArrayList<>();
    public static List<Trap> traps = new ArrayList<>();
    

    public static List<Wall> loadWalls(String fileName) {
        traps = new ArrayList<>();
        List<Wall> walls = new ArrayList<>();
        enemyPositions = new ArrayList<>();  

        try {
            InputStream is = MapLoader.class.getResourceAsStream("/res/" + fileName);
            if (is == null) {
                System.err.println("[MapLoader] Archivo no encontrado: " + fileName);
                return walls;
            }

            JSONParser parser = new JSONParser();
            JSONObject root   = (JSONObject) parser.parse(new InputStreamReader(is));
            JSONArray  layers = (JSONArray)  root.get("layers");

            for (Object layerObj : layers) {
                JSONObject layer = (JSONObject) layerObj;

                String layerName = (String) layer.get("name");
                if (!"walls".equals(layerName) && !"wall".equals(layerName)) continue;

                float offX = layer.get("offsetx") != null
                    ? ((Number) layer.get("offsetx")).floatValue() : 0;
                float offY = layer.get("offsety") != null
                    ? ((Number) layer.get("offsety")).floatValue() : 0;

                JSONArray objects = (JSONArray) layer.get("objects");
                if (objects == null) continue;

                for (Object objRaw : objects) {
                    JSONObject obj = (JSONObject) objRaw;

                    String name  = (String) obj.get("name");
                    float  baseX = ((Number) obj.get("x")).floatValue() + offX;
                    float  baseY = ((Number) obj.get("y")).floatValue() + offY;

                    if ("spawn".equals(name)) {
                        spawnX = baseX;
                        spawnY = baseY;
                        System.out.println("[MapLoader] Spawn: (" + spawnX + ", " + spawnY + ")");
                        continue;
                    }

                    if ("exit".equals(name)) {
                        exitX = baseX;
                        exitY = baseY;
                        System.out.println("[MapLoader] Exit: (" + exitX + ", " + exitY + ")");
                        continue;
                    }

                    if ("enemy".equals(name)) {
                        enemyPositions.add(new float[]{baseX, baseY});
                        System.out.println("[MapLoader] Enemigo en: (" + baseX + ", " + baseY + ")");
                        continue;
                    }
                    
                    if ("trap".equals(name)) {
                        float w = ((Number) obj.get("width")).floatValue();
                        float h = ((Number) obj.get("height")).floatValue();
                        traps.add(new Trap(baseX, baseY, w, h));
                        System.out.println("[MapLoader] Trampa en: (" + baseX + ", " + baseY + ") " + w + "x" + h);
                        continue;
                    }

                    JSONArray polyline = obj.get("polyline") != null
                        ? (JSONArray) obj.get("polyline")
                        : (JSONArray) obj.get("polygon");

                    if (polyline == null) continue;

                    boolean isPolygon = obj.get("polygon") != null;
                    int limit = isPolygon ? polyline.size() : polyline.size() - 1;

                    for (int i = 0; i < limit; i++) {
                        JSONObject p1 = (JSONObject) polyline.get(i);
                        JSONObject p2 = (JSONObject) polyline.get((i + 1) % polyline.size());

                        float x1 = baseX + ((Number) p1.get("x")).floatValue();
                        float y1 = baseY + ((Number) p1.get("y")).floatValue();
                        float x2 = baseX + ((Number) p2.get("x")).floatValue();
                        float y2 = baseY + ((Number) p2.get("y")).floatValue();

                        walls.add(new Wall(x1, y1, x2, y2));
                    }
                }
            }

            System.out.println("[MapLoader] Total paredes: " + walls.size());

        } catch (Exception e) {
            System.err.println("[MapLoader] Error: " + e.getMessage());
        }

        return walls;
    }
}