package io.freemytv;

/**
 * Estilo centralizado del launcher (Fase 5).
 *
 * Todos los colores, tamaños, radios de esquina, tipografías y tiempos de animación
 * viven aquí. Para cambiar la apariencia, edite este archivo (o {@link #theme}).
 * El aspecto visual no cambia respecto a la versión original: solo se movieron
 * los valores repartidos en {@link HomeView} y {@link SettingsActivity} a constantes.
 *
 * Documentación de qué controla cada constante: {@link ESTILO.txt}.
 */
public final class Style {
    private Style() {}

    // ---------------------------------------------------------------- Colores base
    /** Fondo oscuro principal. */
    public static final int COLOR_SMOKE = 0xFF111113;
    /** Tinta (texto principal, fondos oscuros). */
    public static final int COLOR_INK = 0xFF1C1C1E;
    /** Blanco puro. */
    public static final int COLOR_WHITE = 0xFFF5F5F7;
    /** Color de subida (arriba). */
    public static final int COLOR_UP = 0xFF30D158;
    /** Color de bajada (abajo). */
    public static final int COLOR_DOWN = 0xFFFF453A;

    // ---------------------------------------------------------------- Tamaños y medidas
    /** Alto de la cápsula de la esquina superior derecha. */
    public static final float CAPSULE_H = 68;
    /** Offset superior de la cápsula. */
    public static final float CAPSULE_TOP = 46;
    /** Tamaño de botón redondo. */
    public static final float BUTTON = 56;
    /** Desplazamiento del cajón inferior cuando se abre. */
    public static final float SHIFT = 560;
    /** Fila de título del cajón. */
    public static final float ROW_TITLE = 518;
    /** Tope de fila del cajón. */
    public static final float ROW_TOP = 540;
    /** Alto de fila del cajón. */
    public static final float ROW_H = 126;
    /** Tope de las tarjetas del cajón. */
    public static final float CARDS_TOP = 708;
    /** Alto de las tarjetas del cajón. */
    public static final float CARDS_H = 300;
    /** Columnas de la cuadrícula "All Apps". */
    public static final int COLUMNS = 5;
    /** Filas visibles de la cuadrícula. */
    public static final int VISIBLE_ROWS = 3;
    /** Ancho de celda de la cuadrícula. */
    public static final int GRID_W = 300;
    /** Alto de celda de la cuadrícula. */
    public static final int GRID_H = 169;
    /** Separación entre celdas. */
    public static final int GRID_GAP = 40;
    /** Fila de la cuadrícula. */
    public static final int GRID_ROW = 239;
    /** Tope de la cuadrícula. */
    public static final int GRID_TOP = 196;
    /** Radio de esquina de la cuadrícula. */
    public static final int GRID_RADIUS = 22;

    // ---------------------------------------------------------------- Tiempos de animación
    /** Duración del toast en ms. */
    public static final long TOAST_MS = 6000;
    /** Duración del fade en ms. */
    public static final long FADE_MS = 800;
    /** Duración de la transición noche/día en ms. */
    public static final long NIGHT_MS = 2000;
    /** Tiempo de hold para reordenar en ms. */
    public static final long HOLD_MS = 500;
    /** Tiempo del movimiento (wiggle) en ms. */
    public static final long WIGGLE_MS = 300;

    // ---------------------------------------------------------------- Sombras
    /** Radio de blur de la sombra. */
    public static final float SHADOW_BLUR = 26;
    /** Radio de blur de la sombra profunda. */
    public static final float DEEP_BLUR = 30;

    // ---------------------------------------------------------------- Diseño claro (L_): barra de pestañas,
    // tres tarjetas de recomendación y estante.
    /** Base del título en diseño claro. */
    public static final float L_TITLE_BASE = 130;
    /** Tope de la barra de pestañas en diseño claro. */
    public static final float L_TABBAR_TOP = 158;
    /** Alto de la barra de pestañas en diseño claro. */
    public static final float L_TABBAR_H = 68;
    /** Tope de las tarjetas en diseño claro. */
    public static final float L_CARDS_TOP = 262;
    /** Alto de tarjeta en diseño claro. */
    public static final float L_CARD_H = 442;
    /** Ancho de tarjeta alta en diseño claro. */
    public static final float L_TALL_W = 320;
    /** Separación entre tarjetas en diseño claro. */
    public static final float L_CARD_GAP = 40;
    /** Botón en diseño claro. */
    public static final float L_BUTTON = 68;
    /** Separación entre botones en diseño claro. */
    public static final float L_BUTTON_GAP = 20;
    /** Centro Y de botones en diseño claro. */
    public static final float L_BUTTON_CY = 103;
    /** Tope del platillo en diseño claro. */
    public static final float L_PLATTER_TOP = 814;
    /** Alto de tile en diseño claro. */
    public static final float L_TILE_H = 126;
    /** Base de fecha en diseño claro. */
    public static final float L_DATE_BASE = 330;
    /** Base del reloj en diseño claro. */
    public static final float L_CLOCK_BASE = 540;
    /** Base del clima en diseño claro. */
    public static final float L_WEATHER_BASE = 626;
    /** Base de citas en diseño claro. */
    public static final float L_QUOTES_BASE = 694;

    // ---------------------------------------------------------------- Colores diseño claro
    /** Tinta en diseño claro. */
    public static final int COLOR_L_INK = 0xFF1D1D1F;
    /** Dimensión en diseño claro. */
    public static final int COLOR_L_DIM = 0xFF6E6E73;
    /** Pálido en diseño claro. */
    public static final int COLOR_L_FAINT = 0xFF8E8E93;

    // ---------------------------------------------------------------- Crecimiento / movimiento
    /** Crecimiento del tile al levantarse. */
    public static final float LIFT_GROW = 0.08f;
    /** Grados de movimiento del wiggle. */
    public static final float WIGGLE_DEG = 1.5f;

    // ---------------------------------------------------------------- Wallpaper / fondo
    /**
     * Tipo de fondo de pantalla.
     * {@link #WALLPAPER_NONE}: sin fondo (gradiente, máximo ahorro de RAM).
     * {@link #WALLPAPER_STATIC}: imagen estática (elige una imagen del usuario).
     * {@link #WALLPAPER_VIDEO}: video en bucle (consume mucha RAM; no recomendado en TV con 1 GB).
     */
    public static final int WALLPAPER_NONE = 0;
    public static final int WALLPAPER_STATIC = 1;
    public static final int WALLPAPER_VIDEO = 2;

    /** Tipo de wallpaper por defecto (sin video para ahorrar RAM en TVs limitados). */
    public static final int DEFAULT_WALLPAPER_TYPE = WALLPAPER_NONE;

    /** Ruta del archivo de imagen estática (vacío = usar gradiente por defecto). */
    public static String WALLPAPER_IMAGE_PATH = "";

    /** Si se reproduce video en bucle como fondo (desactivado por defecto por consumo de RAM). */
    public static boolean WALLPAPER_VIDEO_ENABLED = false;

    /** Ruta del archivo de video de fondo (vacío = ninguno). */
    public static String WALLPAPER_VIDEO_PATH = "";
}
