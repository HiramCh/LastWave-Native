#!/usr/bin/env bash
mkdir -p capturas
{
  echo "== dispositivos"; adb devices
  bash tools/capturas/capturar.sh normal app-debug.apk capturas
  adb shell wm size 720x1440; adb shell wm density 360; sleep 3
  bash tools/capturas/capturar.sh pequeno app-debug.apk capturas
  adb shell wm size reset; adb shell wm density reset
} > capturas/log.txt 2>&1
exit 0
