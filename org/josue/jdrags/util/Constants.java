package org.josue.jdrags.util;


import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 4:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Constants {
    public static final int[] BLACK_DRAGONS = {4676, 54, 4674, 4675};
    public static final int[] LOOT = {536, 1747, 1315, 1123, 1303, 1185, 1373, 1319, 560, 565, 554, 561, 18778, 11286, 12160, 12163, 1249, 1215, 1216, 1201, 2366, 1149, 892, 9143, 9342, 1392, 574, 570, 7937, 372, 384, 533, 454, 450, 2362, 452, 2364, 2999, 270, 6686, 5315, 5316, 5289, 1516, 5304};
    public static final int[] CHICKENS = {41, 1017};
    public static final int[] PRAYER_POTIONS = {23253, 23251, 23249, 23247, 23245, 23243, 143, 141, 139, 2434};
    public static final int[] PRAYER_RENEWAL = {21630, 21632, 21634, 21636, 23609, 23611, 23613, 23615, 23617, 23619};
    public static final int ANTIFIRE_POTIONS[] = {15307, 15306, 15305, 15304};
    public static final int FOOD = 385;
    public static final int IMP = 27996;
    public static final int PRAYER_FLASK = 23243;
    public static final int PRAYER_RENEWAL_FLASK = 23609;
    public static final int SUPER_ANTIFIRE = 15304;
    public static final int RAW_CHICKEN = 2138;
    public static final int PORTAL = 12260;
    ////////////////AREAS/////////////////
    public static Area BANK_AREA = new Area(new Tile[]{
            new Tile(2378, 4463, 0), new Tile(2385, 4463, 0),
            new Tile(2385, 4455, 0), new Tile(2378, 4453, 0)
    });
    //////////////WALKING//////////////////
//    public static final Tile[] BANK_TO_PATH = (new Tile[]{
//    new Tile(2453, 4475, 0), new Tile(2451, 4463, 0), new Tile(2450, 4454, 0)
//    });
    public static final Tile[] BANK_TO_PATH = (new Tile[]{
            new Tile(2453, 4475, 0), new Tile(2451, 4463, 0), new Tile(2450, 4454, 0),
            new Tile(2444, 4553, 0), new Tile(2437, 4453, 0), new Tile(2430, 4458, 0),
            new Tile(2423, 4462, 0), new Tile(2417, 4456, 0), new Tile(2403, 4450, 0),
            new Tile(2400, 4450, 0), new Tile(2389, 4454, 0), new Tile(2382, 4458, 0)
    });
}
