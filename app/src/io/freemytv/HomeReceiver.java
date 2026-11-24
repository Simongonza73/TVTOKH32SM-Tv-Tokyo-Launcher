package io.freemytv;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import java.util.List;

/**
 * On boot, opens Free My TV's home screen the way the system does when you press HOME on the remote (measured with
 * adb: the system starts io.freemytv/.HomeActivity, category HOME, flags NEW_TASK | RESET_TASK_IF_NEEDED).
 * The TV's factory launcher comes up on its own a few seconds after boot and would cover ours, so we try a few times
 * (see DELAYS_MS) and skip a try when our home screen is already in front. Each try is a short alarm: nothing sleeps
 * inside the receiver. It only starts an activity of this app (no system commands, no settings changed).
 * Everything is logged: adb logcat -s FreeMyTV. To turn it off, set android:enabled="false" on this receiver.
 */
public class HomeReceiver extends BroadcastReceiver {
    /** Moments after boot (ms) at which to bring the home screen up, if it isn't already in front. */
    private static final long[] DELAYS_MS = {6000, 14000, 24000, 36000};
    private static final String ACTION_TRY = "io.freemytv.action.BOOT_HOME_TRY";
    private static final String HOME_PROCESS = "io.freemytv:home";

    @Override public void onReceive(Context context, Intent intent) {
        String action = intent == null ? null : intent.getAction();
        if (action == null) return;
        Context app = context.getApplicationContext();
        if (Intent.ACTION_BOOT_COMPLETED.equals(action) || "android.intent.action.QUICKBOOT_POWERON".equals(action)) {
            AppLog.w("boot-home: received " + action + ", scheduling " + DELAYS_MS.length + " tries");
            AlarmManager am = (AlarmManager) app.getSystemService(Context.ALARM_SERVICE);
            if (am == null) {
                AppLog.w("boot-home: no AlarmManager, trying right now");
                bringHome(app, 0);
                return;
            }
            for (int i = 0; i < DELAYS_MS.length; i++) {
                Intent t = new Intent(app, HomeReceiver.class).setAction(ACTION_TRY).putExtra("try", i + 1);
                PendingIntent pi = PendingIntent.getBroadcast(app, i + 1, t, PendingIntent.FLAG_UPDATE_CURRENT);
                am.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + DELAYS_MS[i], pi);
            }
        } else if (ACTION_TRY.equals(action)) {
            bringHome(app, intent.getIntExtra("try", 0));
        }
    }

    private static void bringHome(Context app, int attempt) {
        if (homeInFront(app)) {
            AppLog.w("boot-home: try " + attempt + ": our home screen is already in front, nothing to do");
            return;
        }
        if (!BootGate.tryOpen("try " + attempt)) return;
        startHome(app, "try " + attempt);
    }

    /** Opens the home screen right now (what the system does when HOME is pressed). */
    static void startHome(Context app, String why) {
        try {
            Intent home = new Intent(app, HomeActivity.class);
            home.setAction(Intent.ACTION_MAIN);
            home.addCategory(Intent.CATEGORY_HOME);
            home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            app.startActivity(home);
            AppLog.w("boot-home: " + why + ": started HomeActivity");
        } catch (Exception e) {
            AppLog.w("boot-home: " + why + " failed: " + e);
        }
    }

    /** True when the home screen's own process is the one in the foreground. */
    private static boolean homeInFront(Context app) {
        ActivityManager am = (ActivityManager) app.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = am == null ? null : am.getRunningAppProcesses();
        if (list == null) return false;
        for (ActivityManager.RunningAppProcessInfo p : list) {
            if (HOME_PROCESS.equals(p.processName)) {
                return p.importance <= ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
            }
        }
        return false;
    }
}
