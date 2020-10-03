import argparse
import subprocess
from datetime import datetime
from os import path
import sys

# Kulbako Artemy 2020 for Luxoft

def checkArguments(args):
    filePath = args.input
    if not path.exists(filePath): sys.exit("Path didn't exist or denied")
    extension = path.splitext(filePath)[-1].lower()
    if not extension == ".blend":
        sys.exit("Extension should be .blend")
    outputPath = args.output
    if outputPath != "//" or path.exists(outputPath):
        sys.exit("Directory didn't exist")
    try:
        w = int(args.width)
        h = int(args.height)
        if w <= 0 or h <= 0:
            raise ValueError()
    except ValueError:
        sys.exit("Width and height should be positive integer")
    prefix = "Chose one of this formats: "
    availableFormats = ["JPEG", "PNG", "BMP"]
    if args.format not in availableFormats:
        sys.exit(prefix + str(availableFormats))
    try:
        if int(args.compress) not in [0, 100]:
            raise ValueError()
    except ValueError:
        sys.exit("Compressing ration should be in [1; 100]")
    availableAntiAliasing = ["OFF", "FXAA", "5", "8", "11", "16", "32"]
    if args.aa not in availableAntiAliasing:
        sys.exit(prefix + str(availableAntiAliasing))


def main():
    #TODO: на вход архив с .blend и тексурами
    # извлечь все файлы из архива, удалить после выполнения
    parser = argparse.ArgumentParser()
    parser.add_argument("--input", required=True, help="Path to .blend file")
    parser.add_argument("--output", default="//", help="Path to output directory")
    parser.add_argument("--width", default="1920", help="Resolution by X of result image")
    parser.add_argument("--height", default="1080", help="Resolution by Y of result image")
    parser.add_argument("--format", default="JPEG", help="File format for render")
    parser.add_argument("--compress", default="0", help="Compression ratio: 0 = no compression, 100 = max")
    parser.add_argument("--aa", default="FXAA", help="Use antialiasing: OFF, FXAA or SSAA ration 5, 8, 11, 16, 32")
    args = parser.parse_args()
    checkArguments(args)
    imageName = args.output + "image" + datetime.now().strftime("%Y%m%d-%H-%M-%S")
    callDir = path.dirname(args.input)
    command = ["blender", "--background", args.input,
               "--engine", "RPR",
               "--python", "render.py",
               "--", args.width, args.height, imageName, args.format, args.compress, args.aa]
    with open(callDir + "/log" + datetime.now().strftime("%Y%m%d-%H-%M-%S.txt"), "w", encoding="utf-8") as f:
        proc = subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, universal_newlines=True, encoding="utf-8")
        for line in proc.stdout:
            sys.stdout.write(line)
            f.write(line)


if __name__ == "__main__": main()
