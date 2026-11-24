package io.freemytv;

import android.content.Context;

/**
 * Neutralized (Phase 2, A1). The watchdog no longer records crashes or reverts
 * the home screen. All methods are kept for source compatibility but are no-ops.
 */
final class Watchdog {
    private Watchdog() {}

    /** No longer installs the uncaught-exception handler. */
    static synchronized void install(Context c) {}

    /** Always false: the home screen is never considered as having crashed too often. */
    static boolean crashedTooOften(Context c) {
        return false;
    }

    /** No longer deletes the crash file. */
    static void clear(Context c) {}
}
