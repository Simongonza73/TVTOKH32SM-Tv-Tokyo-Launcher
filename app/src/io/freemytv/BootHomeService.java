package io.freemytv;

import android.accessibilityservice.AccessibilityService;
import android.os.SystemClock;
import android.view.accessibility.AccessibilityEvent;

/**
 * Brings Free My TV's home screen up as early as possible after the TV is powered on.
 *
 * BOOT_COMPLETED only arrives once the factory launcher has finished starting (about 1 min 45 s on this TV), but an
 * enabled accessibility service is started by the system much earlier. So, during the first BootGate.WINDOW_MS after boot (and at most BootGate.MAX_OPENS times in total):
 *  - the moment this service connects, it opens our home screen;
 *  - and whenever the factory launcher's window (FACTORY_LAUNCHER) appears, it opens ours again, on top of it.
 * Outside that window it does nothing, so the factory launcher can still be opened later (e.g. from ADB).
 *
 * It reads no window content, no text and no keys: the config (res/xml/boot_home_service.xml) only delivers the
 * "window changed" events of the factory launcher's package. It only starts an activity of this app.
 * Enable it with ADB (see LEEME_REVERSA.txt); log: adb logcat -s FreeMyTV.
 */
public class BootHomeService extends AccessibilityService {
    /** The TV's factory launcher (confirmed with ADB on the Tokyo TV). */
    private static final String FACTORY_LAUNCHER = "com.cultraview.launcher.technical.hs351";
    /** Don't open the home screen more than once in this time (so it can never loop). */
    private static final long MIN_GAP_MS = 1500;

    private long lastStart;

    @Override protected void onServiceConnected() {
        long sinceBoot = SystemClock.elapsedRealtime();
        AppLog.w("boot-home: accessibility service connected, " + (sinceBoot / 1000) + " s after boot");
        open("service connected");
    }

    @Override public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null || event.getEventType() != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return;
        CharSequence pkg = event.getPackageName();
        if (pkg == null || !FACTORY_LAUNCHER.contentEquals(pkg)) return;
        if (BootGate.finished()) return;
        open("factory launcher on screen");
    }

    private void open(String why) {
        long now = SystemClock.elapsedRealtime();
        if (lastStart != 0 && now - lastStart < MIN_GAP_MS) return;
        if (!BootGate.tryOpen(why)) return;
        lastStart = now;
        HomeReceiver.startHome(getApplicationContext(), why);
    }

    @Override public void onInterrupt() {}
}
