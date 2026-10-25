#!/usr/bin/env bash
# Toma capturas de las pantallas principales en el emulador.
# Uso: capturar.sh <perfil> <apk> <carpeta_salida>
set -u
PERFIL="$1"; APK="$2"; OUT="$3"
PKG=com.hmusic.app
mkdir -p "$OUT"
n=0

shot() { n=$((n+1)); adb exec-out screencap -p > "$OUT/${PERFIL}-$(printf %02d $n)-$1.png"; echo "captura $PERFIL $n $1"; }
dump() { rm -f /tmp/ui.xml; timeout 40 adb shell uiautomator dump /sdcard/ui.xml >/dev/null 2>&1; adb pull /sdcard/ui.xml /tmp/ui.xml >/dev/null 2>&1; }
# Toca el primer nodo cuyo text o content-desc contenga $1. Devuelve 1 si no lo encuentra.
tap() {
  dump
  local xy
  xy=$(python3 - "$1" <<'PY'
import sys,re,xml.etree.ElementTree as ET
q=sys.argv[1].lower()
try: root=ET.parse('/tmp/ui.xml').getroot()
except Exception: sys.exit(1)
for n in root.iter('node'):
    t=(n.get('text') or '')+'|'+(n.get('content-desc') or '')
    if q in t.lower():
        a=list(map(int,re.findall(r'\d+',n.get('bounds'))))
        print((a[0]+a[2])//2,(a[1]+a[3])//2); sys.exit(0)
sys.exit(1)
PY
) || { echo "no encontrado: $1"; return 1; }
  adb shell input tap $xy; return 0
}
swipe_up() {
  local h; h=$(adb shell wm size | tail -n1 | sed -E 's/.*x([0-9]+).*/\1/')
  adb shell input swipe 300 $((h*3/4)) 300 $((h/4)) 400
}

adb uninstall $PKG >/dev/null 2>&1
adb install -r -g "$APK" || echo "fallo instalacion"
adb shell cmd locale set-app-locales $PKG --locales es-MX >/dev/null 2>&1
adb shell monkey -p $PKG -c android.intent.category.LAUNCHER 1 >/dev/null 2>&1
sleep 20
shot inicio
tap "invitado" && sleep 20
shot feed
tap "Entendido" && sleep 3
tap "Permitir" && sleep 2
tap "Allow" && sleep 2
shot feed-limpio
for i in 1 2 3 4; do swipe_up; sleep 3; shot feed-scroll$i; done
# reproducir algo desde un estante
for i in 1 2 3 4 5; do tap "Reproducir todo" && break; swipe_up; sleep 2; done
sleep 15; shot reproduciendo
for i in 1 2 3 4 5 6; do adb shell input swipe 300 400 300 1000 300; done; sleep 2; shot feed-arriba-con-mini
tap "Estadísticas" && sleep 8 && shot estadisticas
tap "Biblioteca" && sleep 5 && shot biblioteca
tap "Inicio" && sleep 4
# abrir reproductor completo tocando a la izquierda de "Siguiente" del mini reproductor
dump
python3 - <<'PY' > /tmp/xy || true
import re,xml.etree.ElementTree as ET
root=ET.parse('/tmp/ui.xml').getroot()
c=[n for n in root.iter('node') if (n.get('content-desc') or '')=='Siguiente']
if c:
    a=list(map(int,re.findall(r'\d+',c[-1].get('bounds'))))
    print(max(a[0]-300,50),(a[1]+a[3])//2)
PY
if [ -s /tmp/xy ]; then
  adb shell input tap $(cat /tmp/xy); sleep 6; shot reproductor
  tap "LETRAS" && sleep 6 && shot letras
  tap "COLA" && sleep 4 && shot cola
  tap "REPRODUCIENDO" && sleep 3
  tap "Controles de audio" && sleep 3 && shot controles-audio && adb shell input keyevent KEYCODE_BACK && sleep 2
  adb shell input keyevent KEYCODE_BACK; sleep 3
fi
tap "Ajustes" && sleep 5 && shot ajustes && swipe_up && sleep 2 && shot ajustes-scroll
tap "Audio y reproducción" && sleep 4 && shot ajustes-audio
adb shell input keyevent KEYCODE_BACK; sleep 2
adb shell input keyevent KEYCODE_BACK; sleep 3
tap "Buscar" && sleep 3 && adb shell input text "bad%sbunny" && adb shell input keyevent 66 && sleep 10 && shot buscar
echo "listo $PERFIL"
