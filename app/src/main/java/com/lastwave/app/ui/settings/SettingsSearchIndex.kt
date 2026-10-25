package com.lastwave.app.ui.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.BubbleChart
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.CloudSync
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.HighQuality
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lyrics
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.material.icons.filled.SwitchAccount
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Usb
import androidx.compose.material.icons.filled.SpeakerPhone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.Waves
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Categorization of different setting entry interaction types.
 */
enum class EntryType {
    ACTION,
    TOGGLE,
    SLIDER,
    THEME_SELECTOR,
    ACCENT_PICKER,
    CUSTOM,
}

/**
 * A searchable settings entry indexed for fuzzy search.
 *
 * @property id Unique stable identifier for this setting (e.g. "audio.streaming_quality")
 * @property title Primary human-readable title of the setting
 * @property subtitle Descriptive explanation or default state of the setting
 * @property keywords List of curated keywords, synonyms, and technical terms to match against
 * @property icon The icon vector representing this setting
 * @property iconContainer Composable color provider for the icon background
 * @property iconTint Composable color provider for the icon foreground
 * @property parentTab The [SettingsTab] where this setting is located
 * @property section The section header within the parent tab (e.g. "Salida y volumen")
 * @property type The interaction type of the setting
 */
data class SettingsEntry(
    val id: String,
    val title: String,
    val subtitle: String,
    val keywords: List<String>,
    val icon: ImageVector,
    val iconContainer: @Composable () -> Color,
    val iconTint: @Composable () -> Color,
    val parentTab: SettingsTab,
    val section: String,
    val type: EntryType = EntryType.ACTION,
)

/**
 * Static registry of all searchable settings entries across all tabs.
 */
object SettingsSearchIndex {

    val allEntries: List<SettingsEntry> by lazy {
        buildList {
            // ==========================================
            // TAB: AUDIO & PLAYBACK
            // ==========================================
            add(
                SettingsEntry(
                    id = "audio.streaming_quality",
                    title = "Calidad de transmisión",
                    subtitle = "Sin pérdida, Hi-Res, FLAC, AAC, MP3, 24 bits / 192 kHz, profundidad y frecuencia de muestreo",
                    keywords = listOf(
                        "quality", "bitrate", "flac", "lossless", "hires", "hi-res", "dolby", "atmos",
                        "mp3", "aac", "opus", "streaming", "audio quality", "sound quality", "192khz",
                        "24-bit", "stream", "format"
                    ),
                    icon = Icons.Filled.HighQuality,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Audio y reproducción",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.download_quality",
                    title = "Calidad de descarga",
                    subtitle = "Formato y tasa de bits de descarga: FLAC sin pérdida o audio de YouTube",
                    keywords = listOf(
                        "download", "offline", "storage", "bitrate", "flac", "lossless", "cache",
                        "download quality", "save songs", "music downloads"
                    ),
                    icon = Icons.Filled.CloudDownload,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Audio y reproducción",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.bit_perfect",
                    title = "Modo Bit-Perfect",
                    subtitle = "Omite el mezclador y DSP de Android; envía audio bit a bit al DAC USB",
                    keywords = listOf(
                        "bit-perfect", "bitperfect", "direct output", "bypass mixer", "dac", "usb dac",
                        "audiophile", "lossless", "exclusive", "sample rate match", "native playback",
                        "dsp bypass", "hi-res direct"
                    ),
                    icon = Icons.Filled.Tune,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Audio y reproducción",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.crossfade",
                    title = "Fundido cruzado",
                    subtitle = "Mezcla suavemente el final de una canción con la siguiente",
                    keywords = listOf(
                        "crossfade", "fade", "transition", "overlap", "gapless", "mix tracks",
                        "smooth playback", "dj fade"
                    ),
                    icon = Icons.Filled.GraphicEq,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Audio y reproducción",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.crossfade_duration",
                    title = "Duración del fundido",
                    subtitle = "Ajusta en segundos la duración del fundido entre canciones",
                    keywords = listOf(
                        "crossfade seconds", "crossfade duration", "fade length", "crossfade time",
                        "slider", "seconds", "transition time"
                    ),
                    icon = Icons.Filled.Timer,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Audio y reproducción",
                    type = EntryType.SLIDER,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.keep_lyrics_screen_awake",
                    title = "Mantener pantalla encendida en letras",
                    subtitle = "Evita que la pantalla se apague mientras ves las letras",
                    keywords = listOf(
                        "keep screen awake", "keep screen on", "lyrics sleep", "screen timeout",
                        "display awake", "lyrics screen"
                    ),
                    icon = Icons.Filled.Lyrics,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Audio y reproducción",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.download_lyrics",
                    title = "Descargar letras",
                    subtitle = "Guarda archivos .lrc e incrusta las letras en las canciones descargadas",
                    keywords = listOf(
                        "lyrics", "lrc", "download lyrics", "embed lyrics", "karaoke offline",
                        "save lyrics", "text", "song words"
                    ),
                    icon = Icons.Filled.Lyrics,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Audio y reproducción",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.battery_optimization",
                    title = "Reproducción en segundo plano (optimización de batería)",
                    subtitle = "Excluye la app de las restricciones de batería, apps en suspensión y Device Care de Samsung",
                    keywords = listOf(
                        "battery", "background", "sleep", "sleeping apps", "device care", "power saving",
                        "keep alive", "doze", "stop playing", "playback killed"
                    ),
                    icon = Icons.Filled.Bolt,
                    iconContainer = { MaterialTheme.colorScheme.errorContainer },
                    iconTint = { MaterialTheme.colorScheme.onErrorContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Audio y reproducción",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.usb_exclusive",
                    title = "Salida USB exclusiva",
                    subtitle = "Comunicación directa usbdevfs con DAC USB externos (Android 10+)",
                    keywords = listOf(
                        "usb", "dac", "exclusive", "usb exclusive", "usbdevfs", "external dac",
                        "hardware volume", "headphone amp", "dongle dac"
                    ),
                    icon = Icons.Filled.Usb,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Salida y volumen",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.internal_direct",
                    title = "Salida directa interna",
                    subtitle = "Omite el mezclador de Android en altavoz / jack sin DAC USB",
                    keywords = listOf(
                        "direct", "mixer", "bypass", "hires direct", "uapp", "aaudio", "mmap",
                        "exclusive", "sortie directe", "interne", "contourner", "speaker", "jack",
                    ),
                    icon = Icons.Filled.SpeakerPhone,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Salida y volumen",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.force_software_volume",
                    title = "Volumen por software (DAC USB)",
                    subtitle = "Ajusta el volumen en la app en lugar del volumen de hardware del DAC",
                    keywords = listOf(
                        "volume", "software volume", "hardware volume", "dac", "logiciel", "matériel",
                    ),
                    icon = Icons.Filled.VolumeUp,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Salida y volumen",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.loudness_normalization",
                    title = "Normalización de volumen",
                    subtitle = "Nivelación de volumen ReplayGain: Canción (-14 LUFS), Álbum o Desactivado",
                    keywords = listOf(
                        "loudness", "normalization", "volume", "replaygain", "lufs", "track volume",
                        "leveling", "equal volume", "album loudness", "gain"
                    ),
                    icon = Icons.Filled.VolumeUp,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Salida y volumen",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.autoeq",
                    title = "AutoEQ",
                    subtitle = "Corrección de audífonos con AutoEQ, ecualizador paramétrico y perfiles por dispositivo",
                    keywords = listOf(
                        "autoeq", "auto eq", "headphone", "casque", "iem", "harman", "parametric",
                        "peq", "correction", "oratory1990", "crinacle", "target",
                    ),
                    icon = Icons.Filled.GraphicEq,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Salida y volumen",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.equalizer",
                    title = "Ecualizador",
                    subtitle = "Moldea el sonido en 15 bandas con curvas personalizadas y preajustes",
                    keywords = listOf(
                        "equalizer", "eq", "15-band", "presets", "bass", "treble", "midrange",
                        "bass boost", "sound profile", "frequencies", "audio shape"
                    ),
                    icon = Icons.Filled.GraphicEq,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Salida y volumen",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.system_audio_effects",
                    title = "Efectos de audio del sistema",
                    subtitle = "Permite que ecualizadores externos (Dolby Atmos, Wavelet) procesen el audio",
                    keywords = listOf(
                        "system audio effects", "wavelet", "dolby system", "external eq", "audio fx",
                        "dsp apps", "system equalizer", "sound enhancer"
                    ),
                    icon = Icons.Filled.VolumeUp,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Salida y volumen",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.studio_clarity",
                    title = "Clarity Studio Master",
                    subtitle = "Sonido abierto y nítido, detalle amplio y separación estéreo limpia",
                    keywords = listOf(
                        "clarity", "studio master", "master clarity", "separation", "detail", "crisp",
                        "highs", "vocal clarity", "soundstage", "dsp clarity"
                    ),
                    icon = Icons.Filled.Waves,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Salida y volumen",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.clarity_preset",
                    title = "Preajuste de salida Clarity",
                    subtitle = "Elige la firma de sonido Clarity: Audiófilo, Cálido, Brillante o Vocal",
                    keywords = listOf(
                        "clarity preset", "preset", "sound signature", "warm", "bright", "audiophile",
                        "tuning", "vocal preset"
                    ),
                    icon = Icons.Filled.Tune,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Salida y volumen",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "audio.clarity_spatial_bypass",
                    title = "Omitir Clarity en audio espacial",
                    subtitle = "Omitir automáticamente Clarity en fuentes multicanal y de audio espacial",
                    keywords = listOf(
                        "clarity bypass", "spatial bypass", "atmos bypass", "multichannel bypass",
                        "dsp bypass", "disable clarity on spatial"
                    ),
                    icon = Icons.Filled.GraphicEq,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.AUDIO,
                    section = "Salida y volumen",
                    type = EntryType.TOGGLE,
                )
            )

            // ==========================================
            // TAB: APPEARANCE & VISUALS
            // ==========================================
            add(
                SettingsEntry(
                    id = "appearance.theme_mode",
                    title = "Modo de tema",
                    subtitle = "Elige predeterminado del sistema, tema claro o tema oscuro",
                    keywords = listOf(
                        "theme", "dark mode", "light mode", "system theme", "night mode", "day mode",
                        "appearance", "color scheme", "style"
                    ),
                    icon = Icons.Filled.Contrast,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Apariencia",
                    type = EntryType.THEME_SELECTOR,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.lastwave_theme",
                    title = "Tema exclusivo de LastWave",
                    subtitle = "Paleta del logotipo con lima eléctrico y verde azulado obsidiana",
                    keywords = listOf(
                        "lastwave theme", "brand theme", "special theme", "logo theme", "lime",
                        "teal", "obsidian", "electric lime", "iconic theme", "brand colors"
                    ),
                    icon = Icons.Filled.AutoAwesome,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Apariencia",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.amoled",
                    title = "Negro puro AMOLED",
                    subtitle = "Superficies negro puro para pantallas OLED y AMOLED que ahorran batería",
                    keywords = listOf(
                        "amoled", "oled", "pure black", "pitch black", "true black", "battery saver",
                        "dark mode", "contrast"
                    ),
                    icon = Icons.Filled.Contrast,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Apariencia",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.dynamic_color",
                    title = "Color dinámico (Material You)",
                    subtitle = "Ajusta los colores de acento al fondo de pantalla y la paleta del sistema",
                    keywords = listOf(
                        "dynamic color", "material you", "monet", "wallpaper color", "system colors",
                        "adaptive color", "android 12 color"
                    ),
                    icon = Icons.Filled.Palette,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Apariencia",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.liquid_glass",
                    title = "Vidrio líquido",
                    subtitle = "Fondos translúcidos de vidrio esmerilado y efectos de desenfoque",
                    keywords = listOf(
                        "liquid glass", "glassmorphism", "blur", "frosted glass", "translucent",
                        "acrylic", "transparency", "visual effect"
                    ),
                    icon = Icons.Filled.BubbleChart,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Apariencia",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.md3_nav_bar",
                    title = "Barra de navegación Material 3",
                    subtitle = "Barra de navegación inferior clásica en lugar del dock flotante",
                    keywords = listOf(
                        "md3", "material 3", "navigation bar", "bottom bar", "nav bar",
                        "dock", "floating dock", "full width nav"
                    ),
                    icon = Icons.Filled.Apps,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Apariencia",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.dynamic_now_playing",
                    title = "Pantalla de reproducción dinámica",
                    subtitle = "Fondo de color adaptable en la pantalla de reproducción según la portada",
                    keywords = listOf(
                        "dynamic now playing", "player screen", "adaptive background", "album art color",
                        "gradient player", "now playing theme", "cover art color"
                    ),
                    icon = Icons.Filled.Album,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Apariencia",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.app_font",
                    title = "Fuente de la app",
                    subtitle = "Alterna entre la fuente del sistema y la tipografía personalizada",
                    keywords = listOf(
                        "font", "typeface", "typography", "text style", "custom font", "app font",
                        "lettering", "outfit font"
                    ),
                    icon = Icons.Filled.TextFields,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Apariencia",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.home_sections",
                    title = "Secciones de la pantalla de Inicio",
                    subtitle = "Reordena, muestra u oculta las secciones de tu pantalla de Inicio",
                    keywords = listOf(
                        "home", "home sections", "feed", "customize home", "reorder home",
                        "layout", "hide sections", "shelves", "front page"
                    ),
                    icon = Icons.Filled.Dashboard,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Apariencia",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.accent_preset",
                    title = "Color de acento y paleta",
                    subtitle = "Elige preajustes, paleta monocromática o rueda de color personalizada",
                    keywords = listOf(
                        "accent", "color", "palette", "monochrome", "color wheel", "hex",
                        "custom color", "theme color", "tint"
                    ),
                    icon = Icons.Filled.Palette,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Color de acento",
                    type = EntryType.ACCENT_PICKER,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.lyrics_animation",
                    title = "Estilo de animación de letras",
                    subtitle = "Brillo karaoke, deslizamiento, desvanecido, rebote o interfaz moderna a pantalla completa",
                    keywords = listOf(
                        "lyrics animation", "karaoke", "lyrics style", "modern lyrics", "lyrics ui",
                        "smooth scroll", "glow lyrics", "lyrics presentation"
                    ),
                    icon = Icons.Filled.Lyrics,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Funciones experimentales",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.wavy_seekbar",
                    title = "Barra de progreso ondulada",
                    subtitle = "Barra de progreso ondulada multicapa para la reproducción",
                    keywords = listOf(
                        "wavy seekbar", "wave slider", "progress bar", "fluid seekbar", "waveform",
                        "scrubber", "timeline", "squiggly line"
                    ),
                    icon = Icons.Filled.Waves,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Funciones experimentales",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.music_video",
                    title = "Videoclip",
                    subtitle = "Mira el videoclip sincronizado de la canción actual desde el menú del reproductor",
                    keywords = listOf(
                        "video", "music video", "watch video", "mv", "synced video", "youtube video",
                        "fullscreen video", "exact ms sync", "now playing video"
                    ),
                    icon = Icons.Filled.SmartDisplay,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Funciones experimentales",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.rotating_background",
                    title = "Fondo giratorio",
                    subtitle = "Anima la portada giratoria en el reproductor y las letras (desactívalo para un fondo estático)",
                    keywords = listOf(
                        "rotating background", "rotating artwork", "fluid background", "static background",
                        "disable rotation", "android 11", "spinning artwork", "player background",
                        "lyrics background", "album rotation", "static artwork"
                    ),
                    icon = Icons.Filled.RestartAlt,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Funciones experimentales",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.lyrics_rotating_background",
                    title = "Movimiento del fondo de letras",
                    subtitle = "Anima la portada giratoria en la vista de letras (desactívalo para un fondo estático)",
                    keywords = listOf(
                        "lyrics background motion", "lyrics rotating background", "lyrics fluid background",
                        "lyrics motion", "lyrics background"
                    ),
                    icon = Icons.Filled.RestartAlt,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Funciones experimentales",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.lyrics_provider",
                    title = "Proveedor de letras",
                    subtitle = "Elige el proveedor de letras sincronizadas (LRCLIB, Musixmatch, etc.)",
                    keywords = listOf(
                        "lyrics provider", "lrclib", "musixmatch", "synced lyrics source",
                        "lyrics engine", "lyrics fetcher"
                    ),
                    icon = Icons.Filled.Lyrics,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Funciones experimentales",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.lyrics_offset",
                    title = "Desfase de sincronización de letras",
                    subtitle = "Ajusta el desfase en milisegundos para que las letras coincidan con la voz",
                    keywords = listOf(
                        "lyrics offset", "lyrics sync", "delay lyrics", "early lyrics", "ms offset",
                        "timing", "sync adjustment", "vocals match"
                    ),
                    icon = Icons.Filled.Timer,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Funciones experimentales",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.lyrics_size",
                    title = "Tamaño del texto de letras",
                    subtitle = "Ajusta el tamaño de las letras en el reproductor y en pantalla completa",
                    keywords = listOf(
                        "lyrics size", "lyrics font size", "font size", "text size", "resize lyrics",
                        "bigger lyrics", "smaller lyrics", "lyrics scale", "lyrics zoom"
                    ),
                    icon = Icons.Filled.FormatSize,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Funciones experimentales",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.lyrics_autohide",
                    title = "Ocultar botones de letras",
                    subtitle = "Tiempo de inactividad antes de ocultar los botones flotantes de letras (2-10 s o Nunca)",
                    keywords = listOf(
                        "hide lyrics buttons", "auto hide", "idle", "fade out", "lyrics controls",
                        "font size button", "fullscreen button", "never", "seconds", "timeout"
                    ),
                    icon = Icons.Filled.VisibilityOff,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Funciones experimentales",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.translation_language",
                    title = "Idioma de traducción",
                    subtitle = "Idioma al que traduce el botón de traducir letras (vacío = idioma de la app)",
                    keywords = listOf(
                        "translation language", "translate lyrics", "lyrics language", "romanization",
                        "romanize", "language of lyrics", "translate to", "lyrics translate"
                    ),
                    icon = Icons.Filled.Translate,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Funciones experimentales",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.canvas_enabled",
                    title = "Fondos de video Canvas",
                    subtitle = "Videos visuales en bucle durante la reproducción, como Spotify Canvas",
                    keywords = listOf(
                        "canvas", "video", "looping video", "spotify canvas", "video background",
                        "visualizer", "moving artwork"
                    ),
                    icon = Icons.Filled.SmartDisplay,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Canvas",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.canvas_full_bleed",
                    title = "Canvas a pantalla completa",
                    subtitle = "Expande el video Canvas a toda la pantalla detrás de los controles",
                    keywords = listOf(
                        "canvas full bleed", "full screen canvas", "edge to edge", "immersion",
                        "canvas size", "fit screen"
                    ),
                    icon = Icons.Filled.Visibility,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Canvas",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "appearance.canvas_cellular",
                    title = "Canvas con datos móviles",
                    subtitle = "Permite reproducir videos Canvas con datos móviles",
                    keywords = listOf(
                        "canvas cellular", "mobile data", "canvas data saver", "lte", "5g",
                        "stream canvas mobile", "data usage"
                    ),
                    icon = Icons.Filled.CloudDownload,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.APPEARANCE,
                    section = "Canvas",
                    type = EntryType.TOGGLE,
                )
            )

            // ==========================================
            // TAB: YOUTUBE & SYNC
            // ==========================================
            add(
                SettingsEntry(
                    id = "youtube.account",
                    title = "Conexión de cuenta de YouTube",
                    subtitle = "Conecta o administra tu cuenta de Google / YouTube Music",
                    keywords = listOf(
                        "youtube", "google account", "yt music", "login", "sign in", "oauth",
                        "connect youtube", "disconnect youtube", "account"
                    ),
                    icon = Icons.Filled.CloudSync,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.YOUTUBE,
                    section = "YouTube y sincronización",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "youtube.sync",
                    title = "Sincronización de biblioteca 24/7",
                    subtitle = "Mantén playlists y favoritos sincronizados con YouTube Music",
                    keywords = listOf(
                        "youtube sync", "library sync", "cloud sync", "auto sync", "mirror playlists",
                        "24/7 sync", "background sync", "sync interval"
                    ),
                    icon = Icons.Filled.CloudSync,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.YOUTUBE,
                    section = "YouTube y sincronización",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "youtube.channel",
                    title = "Canal de YouTube",
                    subtitle = "Cambia entre canales personales y de marca vinculados a tu cuenta",
                    keywords = listOf(
                        "youtube channel", "brand account", "channel selector", "switch channel",
                        "yt profile", "sub channel"
                    ),
                    icon = Icons.Filled.SwitchAccount,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.YOUTUBE,
                    section = "YouTube y sincronización",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "youtube.select_playlists",
                    title = "Seleccionar playlists para sincronizar",
                    subtitle = "Elige qué playlists sincronizar con tu cuenta de YouTube Music",
                    keywords = listOf(
                        "sync playlists", "playlist selection", "choose playlists", "mirror playlists",
                        "synced playlists", "filter sync"
                    ),
                    icon = Icons.Filled.FormatListBulleted,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.YOUTUBE,
                    section = "YouTube y sincronización",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "youtube.shown_playlists",
                    title = "Mostrar / ocultar playlists de la cuenta",
                    subtitle = "Controla qué playlists de tu cuenta de YouTube aparecen en LastWave",
                    keywords = listOf(
                        "show playlists", "hide playlists", "visibility", "account playlists",
                        "library visibility", "filter playlists"
                    ),
                    icon = Icons.Filled.Visibility,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.YOUTUBE,
                    section = "YouTube y sincronización",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "youtube.import",
                    title = "Importar desde YouTube Music",
                    subtitle = "Busca, explora o pega enlaces e IDs de playlists para importarlas",
                    keywords = listOf(
                        "import youtube", "import playlist", "paste link", "yt import", "copy playlist",
                        "transfer youtube", "make local"
                    ),
                    icon = Icons.Filled.QueueMusic,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.YOUTUBE,
                    section = "YouTube y sincronización",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "youtube.history",
                    title = "Historial de escucha de YouTube Music",
                    subtitle = "Registra reproducciones en tu historial oficial de YouTube Music",
                    keywords = listOf(
                        "youtube history", "watch history", "yt history sync", "scrobble to youtube",
                        "listening history", "sync plays"
                    ),
                    icon = Icons.Filled.History,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.YOUTUBE,
                    section = "YouTube y sincronización",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "spotify.account",
                    title = "Conectar cuenta de Spotify",
                    subtitle = "Inicia sesión para activar Spotify Canvas y sincronizar tu biblioteca",
                    keywords = listOf(
                        "spotify", "spotify account", "connect spotify", "sp_dc", "spotify login",
                        "canvas login", "disconnect spotify", "logout spotify", "sign out"
                    ),
                    icon = Icons.Filled.PlayCircle,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.primary },
                    parentTab = SettingsTab.SPOTIFY,
                    section = "Spotify",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "spotify.import",
                    title = "Tus playlists de Spotify",
                    subtitle = "Importa tus playlists y canciones favoritas de Spotify a la biblioteca",
                    keywords = listOf(
                        "spotify playlist", "import playlist", "my playlists", "liked songs",
                        "spotify library", "import from spotify"
                    ),
                    icon = Icons.Filled.QueueMusic,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.SPOTIFY,
                    section = "Spotify",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "spotify.canvas",
                    title = "Spotify Canvas (videos de fondo en bucle)",
                    subtitle = "Obtén y reproduce de fondo los videos en bucle de Spotify",
                    keywords = listOf(
                        "spotify canvas", "canvas", "video loop", "looping video", "moving background"
                    ),
                    icon = Icons.Filled.SmartDisplay,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.SPOTIFY,
                    section = "Spotify",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "spotify.prefer_canvas",
                    title = "Preferir Spotify Canvas",
                    subtitle = "Consultar primero Spotify, antes que Apple Music, Tidal y portadas de la comunidad",
                    keywords = listOf(
                        "spotify canvas", "prefer", "priority", "first", "apple music", "tidal"
                    ),
                    icon = Icons.Filled.AutoAwesome,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.SPOTIFY,
                    section = "Spotify",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "spotify.token",
                    title = "Importar token sp_dc",
                    subtitle = "Pega una cookie de sesión de tu navegador en lugar de iniciar sesión aquí",
                    keywords = listOf(
                        "sp_dc", "cookie", "token", "paste", "import", "session", "spotify login"
                    ),
                    icon = Icons.Filled.Key,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.SPOTIFY,
                    section = "Token de sesión",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "liked.local",
                    title = "Guardar en canciones que te gustan",
                    subtitle = "Guarda una lista de canciones que te gustan en la app",
                    keywords = listOf(
                        "liked songs", "like", "heart", "favorite", "favourites", "local playlist"
                    ),
                    icon = Icons.Filled.Favorite,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.YOUTUBE,
                    section = "Canciones que te gustan",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "liked.youtube",
                    title = "Sincronizar canciones favoritas con YouTube Music",
                    subtitle = "Marcar también como “Me gusta” en YouTube Music",
                    keywords = listOf(
                        "liked songs", "sync", "youtube music", "like", "heart", "favorite"
                    ),
                    icon = Icons.Filled.CloudSync,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.YOUTUBE,
                    section = "Canciones que te gustan",
                    type = EntryType.TOGGLE,
                )
            )

            // ==========================================
            // TAB: LAST.FM
            // ==========================================
            add(
                SettingsEntry(
                    id = "lastfm.connect",
                    title = "Conexión de cuenta de Last.fm",
                    subtitle = "Conecta tu cuenta de Last.fm para scrobbling, favoritos y estadísticas",
                    keywords = listOf(
                        "lastfm", "last.fm", "scrobble", "scrobbling", "login lastfm", "connect lastfm",
                        "auth", "stats", "loved tracks", "profile"
                    ),
                    icon = Icons.Filled.Album,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.LAST_FM,
                    section = "Integraciones / Scrobbling",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "lastfm.api_credentials",
                    title = "Credenciales de API de Last.fm personalizadas",
                    subtitle = "Configura tu propia clave y secreto de API de Last.fm para evitar límites compartidos",
                    keywords = listOf(
                        "lastfm api key", "custom api", "api secret", "developer key", "credentials",
                        "rate limit", "last.fm token"
                    ),
                    icon = Icons.Filled.Code,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.LAST_FM,
                    section = "Integraciones / Scrobbling",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "discord.presence",
                    title = "Discord Rich Presence",
                    subtitle = "Muestra la canción, calidad y portada en tu perfil de Discord",
                    keywords = listOf(
                        "discord", "rich presence", "discord rpc", "now playing discord",
                        "discord status", "listening to", "presence", "show what im playing"
                    ),
                    icon = Icons.Filled.Public,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.LAST_FM,
                    section = "Integraciones / Scrobbling",
                    type = EntryType.TOGGLE,
                )
            )

            // ==========================================
            // TAB: NOTIFICATION SCROBBLER
            // ==========================================


            // ==========================================
            // TAB: LIBRARY & CONTENT
            // ==========================================
            add(
                SettingsEntry(
                    id = "library.home_sections",
                    title = "Diseño y secciones de Inicio",
                    subtitle = "Configura las secciones y accesos rápidos visibles en Inicio",
                    keywords = listOf(
                        "home layout", "home sections", "feed", "organize home", "shelves",
                        "customize library", "home screen layout"
                    ),
                    icon = Icons.Filled.Dashboard,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.LIBRARY,
                    section = "Inicio y organización",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "library.excluded_songs",
                    title = "Exclusiones de recomendaciones",
                    subtitle = "Administra las canciones excluidas de recomendaciones y mezclas",
                    keywords = listOf(
                        "excluded songs", "recommendations", "blocked tracks", "hidden songs",
                        "don't recommend", "exclude from mixes", "reset exclusions"
                    ),
                    icon = Icons.Filled.RestartAlt,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.LIBRARY,
                    section = "Inicio y organización",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "library.podcasts",
                    title = "Pódcasts",
                    subtitle = "Muestra u oculta secciones de pódcasts, programas y búsqueda de episodios",
                    keywords = listOf(
                        "podcast", "podcasts", "shows", "episodes", "hide podcasts",
                        "youtube music podcasts", "episode search"
                    ),
                    icon = Icons.Filled.Mic,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.LIBRARY,
                    section = "Contenido",
                    type = EntryType.TOGGLE,
                )
            )
            add(
                SettingsEntry(
                    id = "library.import_yt",
                    title = "Importar desde YouTube Music",
                    subtitle = "Busca, explora o pega enlaces e IDs de playlists para importarlas",
                    keywords = listOf(
                        "import youtube", "import playlist", "yt music import", "paste playlist url",
                        "transfer youtube playlists"
                    ),
                    icon = Icons.Filled.QueueMusic,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.LIBRARY,
                    section = "Importaciones y complementos",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "library.import_external",
                    title = "Importar desde Spotify / Apple Music",
                    subtitle = "Pega la URL de una playlist pública de Spotify o Apple Music para transferirla",
                    keywords = listOf(
                        "import spotify", "import apple music", "transfer playlist", "spotify to lastwave",
                        "apple to lastwave", "playlist converter", "public link"
                    ),
                    icon = Icons.Filled.QueueMusic,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.LIBRARY,
                    section = "Importaciones y complementos",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "library.import_file",
                    title = "Importar playlists desde archivo (CSV / M3U)",
                    subtitle = "Importa archivos de playlists, historiales CSV o playlists M3U/M3U8",
                    keywords = listOf(
                        "import file", "csv", "m3u", "m3u8", "import local", "playlist file",
                        "local file import", "open playlist"
                    ),
                    icon = Icons.Filled.FileDownload,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.LIBRARY,
                    section = "Importaciones y complementos",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "library.modules",
                    title = "Extensiones y módulos",
                    subtitle = "Explora y configura complementos y funciones adicionales",
                    keywords = listOf(
                        "modules", "extensions", "plugins", "addons", "modularity", "extra features"
                    ),
                    icon = Icons.Filled.Extension,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.LIBRARY,
                    section = "Importaciones y complementos",
                    type = EntryType.ACTION,
                )
            )

            // ==========================================
            // TAB: DATA & STORAGE
            // ==========================================
            add(
                SettingsEntry(
                    id = "backup.create",
                    title = "Respaldar ajustes y playlists",
                    subtitle = "Exporta tus playlists, credenciales y preferencias a un archivo JSON",
                    keywords = listOf(
                        "backup", "export data", "save backup", "export json", "backup playlists",
                        "backup settings", "save configuration"
                    ),
                    icon = Icons.Filled.Backup,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.DATA_BACKUP,
                    section = "Copia de seguridad y restauración",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "backup.restore",
                    title = "Restaurar desde copia de seguridad",
                    subtitle = "Importa una copia de seguridad de LastWave para restaurar ajustes y playlists",
                    keywords = listOf(
                        "restore", "import backup", "recover data", "load backup", "restore playlists",
                        "restore settings", "json restore"
                    ),
                    icon = Icons.Filled.CloudDownload,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.DATA_BACKUP,
                    section = "Copia de seguridad y restauración",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "backup.clear_all",
                    title = "Restablecer app y borrar todos los datos",
                    subtitle = "Elimina credenciales, playlists, audio en caché y restablece todas las preferencias",
                    keywords = listOf(
                        "clear all", "reset", "erase", "wipe data", "delete everything", "factory reset",
                        "danger", "remove data", "clear cache"
                    ),
                    icon = Icons.Filled.Delete,
                    iconContainer = { MaterialTheme.colorScheme.errorContainer },
                    iconTint = { MaterialTheme.colorScheme.onErrorContainer },
                    parentTab = SettingsTab.DATA_BACKUP,
                    section = "Gestión de datos",
                    type = EntryType.ACTION,
                )
            )

            // ==========================================
            // TAB: ABOUT & SYSTEM
            // ==========================================
            add(
                SettingsEntry(
                    id = "about.language",
                    title = "Idioma de la app",
                    subtitle = "Cambia el idioma de la app (español, inglés, ruso y más)",
                    keywords = listOf(
                        "language", "locale", "translation", "english", "spanish", "russian",
                        "idioma", "sprache", "change language"
                    ),
                    icon = Icons.Filled.Language,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.ABOUT,
                    section = "Idioma",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "about.telegram_support",
                    title = "Novedades y soporte en Telegram",
                    subtitle = "Únete a @clashprojects en Telegram para noticias, versiones beta y soporte",
                    keywords = listOf(
                        "telegram", "support", "community", "chat", "clashprojects", "help",
                        "updates channel", "news"
                    ),
                    icon = Icons.AutoMirrored.Filled.Send,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.ABOUT,
                    section = "Acerca de y comunidad",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "about.discord_support",
                    title = "Soporte en Discord",
                    subtitle = "Únete a nuestro servidor de Discord para soporte y comentarios",
                    keywords = listOf(
                        "discord", "community", "chat", "support", "feedback", "server", "discussion"
                    ),
                    icon = Icons.Filled.Group,
                    iconContainer = { MaterialTheme.colorScheme.tertiaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onTertiaryContainer },
                    parentTab = SettingsTab.ABOUT,
                    section = "Acerca de y comunidad",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "about.more_apps",
                    title = "Más apps de Clash Projects",
                    subtitle = "Únete a @MaterialYouApp en Telegram para descubrir otras apps",
                    keywords = listOf(
                        "more apps", "material you app", "other apps", "clash projects", "developer"
                    ),
                    icon = Icons.Filled.AutoAwesome,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.ABOUT,
                    section = "Acerca de y comunidad",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "about.diagnostics",
                    title = "Exportar diagnóstico y registros",
                    subtitle = "Exporta el informe de diagnóstico, registros de fallos e info del dispositivo",
                    keywords = listOf(
                        "diagnostics", "logs", "crash logs", "debug info", "report issue", "bug report",
                        "troubleshooting", "system info"
                    ),
                    icon = Icons.Filled.Code,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.ABOUT,
                    section = "Acerca de y comunidad",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "about.check_updates",
                    title = "Buscar actualizaciones",
                    subtitle = "Busca en GitHub la versión más reciente y sus novedades",
                    keywords = listOf(
                        "update", "check updates", "new version", "github releases", "changelog",
                        "download update", "latest release", "upgrade"
                    ),
                    icon = Icons.Filled.CloudDownload,
                    iconContainer = { MaterialTheme.colorScheme.primaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onPrimaryContainer },
                    parentTab = SettingsTab.ABOUT,
                    section = "Acerca de y comunidad",
                    type = EntryType.ACTION,
                )
            )
            add(
                SettingsEntry(
                    id = "about.source_code",
                    title = "Código abierto",
                    subtitle = "Ver el repositorio en github.com/Clash-Projects/LastWave-native",
                    keywords = listOf(
                        "source code", "github", "open source", "repository", "gpl", "license",
                        "contribute", "git"
                    ),
                    icon = Icons.Filled.Code,
                    iconContainer = { MaterialTheme.colorScheme.secondaryContainer },
                    iconTint = { MaterialTheme.colorScheme.onSecondaryContainer },
                    parentTab = SettingsTab.ABOUT,
                    section = "Acerca de y comunidad",
                    type = EntryType.ACTION,
                )
            )
        }
    }
}
