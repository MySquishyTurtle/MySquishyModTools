package le.mysquishyturt.mysquishymodtools.modTools;

import le.mysquishyturt.mysquishymodtools.modTools.toolKit.ToolKit;

public class GiveItemRunnable implements Runnable {
    public void run() {
        try {
            Thread.sleep(500);
            ToolKit.getInstance().giveInventory();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
