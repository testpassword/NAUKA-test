# Kulbako Artemy 2020 for Luxoft

import argparse
import subprocess
from datetime import datetime
import os
from os import path
import sys
import zipfile
from shutil import rmtree
import json


# Проверяет на корректность те аргументы, которые не может проверить парсер
def checkArguments(args):
    fp = args.input
    if not path.exists(fp):
        sys.exit("Path didn't exist or denied")
    if not zipfile.is_zipfile(fp):
        sys.exit("Extension should be .zip")
    if args.output is None:
        args.output = path.dirname(fp)
    if not path.exists(args.output):
        sys.exit("Directory didn't exist")


# Возвращает первую найденную blend-сцену в директории или выбрасывает исключение при их отсутствии
def findScene(dir):
    sceneFile = None
    for file in os.listdir(dir):
        if file.endswith(".blend"):
            sceneFile = os.path.join(dir, file)
            return sceneFile
    if sceneFile is None:
        raise FileNotFoundError()


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--input", required=True, help="Path to .zip file")
    parser.add_argument("--output", help="Path to output directory")
    parser.add_argument("--width", type=int, default=1920, help="Resolution by X of result image",
                        choices=range(2, 15360))
    parser.add_argument("--height", type=int, default=1080, help="Resolution by Y of result image",
                        choices=range(2, 15360))
    parser.add_argument("--format", default="JPEG", help="File format for render", choices=["JPEG", "PNG", "BMP"])
    parser.add_argument("--compress", type=int, default=0, choices=range(0, 101),
                        help="Compression ratio: 0 = no compression, 100 = max")
    parser.add_argument("--aa", default="FXAA", help="Use antialiasing: OFF, FXAA or SSAA ration 5, 8, 11, 16, 32",
                        choices=["OFF", "FXAA", "5", "8", "11", "16", "32"])
    args = parser.parse_args()
    checkArguments(args)
    callDir = path.dirname(args.input)  # получаем директорию с архивом
    tempDir = callDir + "/render_tmp"
    try:
        with zipfile.ZipFile(args.input, "r") as rawData:  # распаковывает архив в временную директорию
            rawData.extractall(tempDir)
        sceneFile = findScene(tempDir)  # проверяем наличие blend-сцены
        imageName = args.output + "/image" + datetime.now().strftime("%Y%m%d-%H-%M-%S")  # задаём имя выходному файлу
        params = vars(args)  # формируем карту с параметрами рендера
        params["output"] = imageName
        params["textures"] = tempDir
        command = ["blender", "--background", sceneFile,
                   "--engine", "RPR",
                   "--python", "render.py",
                   "--", json.dumps(params)]
        # Вызываем процесс blender в фоне, передав ему сцену, параметры рендера и скрипт.
        # Лог одновременно выводится в консоль и файл.
        with open(callDir + "/log" + datetime.now().strftime("%Y%m%d-%H-%M-%S.txt"), "w", encoding="utf-8") as f:
            proc = subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, universal_newlines=True,
                                    encoding="utf-8")
            for line in proc.stdout:
                sys.stdout.write(line)
                f.write(line)
    except FileNotFoundError:
        sys.exit("Can't find .blend file in archive")
    finally:
        rmtree(tempDir)  # удаляем за собой временные файлы


if __name__ == "__main__": main()
