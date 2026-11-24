package io.freemytv;

import android.os.SystemClock;

/**
 * Limits how many times the boot code may pull the user back to our home screen.
 * After the TV starts, the boot code (BootHomeService + HomeReceiver) may open our home at most MAX_OPENS times and
 * only during the first WINDOW_MS after boot. After that it is "done" and never opens it again until the next boot,
 * so apps opened later are left alone. (The counter lives in memory: a new boot always starts from zero.)
 */
final class BootGate {
    private BootGate() {}

    /** Most times the boot code may open our home screen after each power on. */
    static final int MAX_OPENS = 1;
    /** Only act during this time after boot (SystemClock.elapsedRealtime counts from boot). */
    static final long WINDOW_MS = 3 * 60_000;

    private static int opens;

    /** Asks permission to open the home screen; counts it when allowed. */
    static synchronized boolean tryOpen(String why) {
        long since = SystemClock.elapsedRealtime();
        if (since >= WINDOW_MS) {
            AppLog.w("boot-home: skipped (" + why + "): boot window over, " + (since / 1000) + " s after boot");
            return false;
        }
        if (opens >= MAX_OPENS) {
            AppLog.w("boot-home: skipped (" + why + "): already opened " + opens + " times, done for this boot");
            return false;
        }
        opens++;
        AppLog.w("boot-home: allowed (" + why + "), open " + opens + " of " + MAX_OPENS);
        return true;
    }

    /** True when the boot code has nothing left to do (limit reached or window over). */
    static synchronized boolean finished() {
        return opens >= MAX_OPENS || SystemClock.elapsedRealtime() >= WINDOW_MS;
    }
}
