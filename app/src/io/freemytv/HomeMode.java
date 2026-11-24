package io.freemytv;

import android.content.Context;
import android.os.Build;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Neutralized (Phase 2, A1). This class no longer executes any system commands
 * (no pm, no settings put/delete, no cmd package set-home-activity, no Adb).
 * All methods are kept for source compatibility but are no-ops.
 */
final class HomeMode {
    private HomeMode() {}

    static final String PKG = "io.freemytv";
    static final String HOME_ACTIVITY = PKG + "/" + PKG + ".HomeActivity";
    static final String SETTINGS_ACTIVITY = PKG + "/" + PKG + ".SettingsActivity";
    static final String FREE = "free", ORIGINAL = "original";

    /**
     * Turned off in Free My TV mode: intentionally empty. No app is ever disabled.
     */
    static final String[] DEBLOAT = {};

    /** The voice assistant: intentionally empty. */
    static final String[] ASSISTANT = {};

    /** Never turned off: wide list of brand and Android system prefixes. */
    static final Set<String> PROTECTED = new HashSet<>(Arrays.asList(
        "com.hisilicon.", "com.cultraview.", "com.zeasn.", "com.ctv.",
        "com.ecloud.", "com.tvfactory", "com.google.android.", "com.android.",
        "com.mediatek.", "org.droidtv.", "com.mstar.", "com.tcl.", "com.tvos.",
        PKG
    ));

    // MARK: - Requests (from any process) — all no-ops

    /** No longer schedules anything. */
    static void request(Context c, String mode) {}

    /** No longer reverts anything. */
    static void requestRevertHome(Context c) {}

    /** No longer runs a boot check. */
    static void bootCheck(Context c) {}

    /** No longer verifies anything after an update. */
    static void verify(Context c) {}

    /** No longer ensures the remote accessibility service. */
    static void ensureRemoteService(Context c, long delayMs) {}

    /** No longer ensures the remote service now. */
    static void ensureRemoteServiceNow(Context app) {}

    // MARK: - Switches (main process) — all no-ops

    static void applyNow(Context c, String mode) {}

    // MARK: - Safety net — no-op

    /** No longer clears the crash count. */
    static void clear(Context c) {}

    /** No longer reverts anything. */
    static void revertHomeNow(Context c) {}

    /** No longer verifies anything after an update. */
    static void verifyNow(Context c) {}

    /** No longer measures memory. */
    static void measureMemoryNow(Context c, String mode) {}

    // MARK: - State (shared file) — returns an empty object, never persists

    static JSONObject state(Context c) {
        return new JSONObject();
    }

    static void save(Context c, JSONObject s) {}
}
