package org.josue.dragonfiend.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/12/13
 * Time: 11:57 PM
 * To change this template use File | Settings | File Templates.
 */

    public enum Potion {
        PRAYER_POTION(2434, new int[]{23253, 23251, 23249, 23247, 23245, 23243, 143, 141, 139, 2434}),
        PRAYER_RENEWAL(21630, new int[]{21630, 21632, 21634, 21636, 23609, 23611, 23613, 23615, 23617, 23619});
        private final int WITHDRAW_POT;
        private final int[] DRINK_IDS;

        Potion(int withdrawPrayer, int[] drinkPrayer) {
            WITHDRAW_POT = withdrawPrayer;
            DRINK_IDS = drinkPrayer;
        }

        public int withdrawPrayer() {
            return WITHDRAW_POT;

        }

        public int[] drinkPrayer() {
            return DRINK_IDS;
        }
    }
