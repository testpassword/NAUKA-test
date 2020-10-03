import argparse
import subprocess
from datetime import datetime
from os import path
import sys


# links:
# https://docs.blender.org/api/current/info_quickstart.html
# https://blender.stackexchange.com/questions/6817/how-to-pass-command-line-arguments-to-a-blender-python-script
# https://stackoverflow.com/questions/14982836/rendering-and-saving-images-through-blender-python
# https://stackoverflow.com/questions/48482513/render-blender-outside-of-blender-gui-with-python
# https://stackoverflow.com/questions/21406887/subprocess-changing-directory/21406995
# https://blender.stackexchange.com/questions/80/can-cycles-renders-be-done-from-the-command-line
# https://stackoverflow.com/questions/15535240/how-to-write-to-stdout-and-to-log-file-simultaneously-with-popen

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--input", required=True, type=str, help="Path to .blend file")
    args = parser.parse_args()
    extension = path.splitext(args.input)[-1].lower()
    if extension == ".blend":
        imageName = "//image" + datetime.now().strftime("%Y%m%d-%H-%M-%S_#")
        logFile = open(path.dirname(args.input) + "/log" + datetime.now().strftime("%Y%m%d-%H-%M-%S.txt"), "w")
        command = ["blender", "--background", args.input,
                   "--render-output", imageName,
                   "--render-frame", str(1),
                   "--engine", "RPR"]
        proc = subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, universal_newlines=True)
        for line in proc.stdout:
            sys.stdout.write(line)
            logFile.write(line)
    else: print("Extension should be .blend")


if __name__ == "__main__": main()