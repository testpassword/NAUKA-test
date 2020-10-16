from flask import Flask, request
import json
import JenkinsImpl as JI
from werkzeug.utils import secure_filename
from os import path


def uploadFile(req):
    rawFile = req.files["scene"]
    filename = secure_filename(rawFile.filename)
    absPath = path.join(app.config["UPLOAD_FOLDER"], filename)
    rawFile.save(absPath)
    return absPath


with open("../jen_config.json") as f:
    jenConf = json.load(f)
app = Flask(__name__)
app.config["UPLOAD_FOLDER"] = jenConf["UPLOAD_FOLDER"]
jenImpl = JI.JenkinsImpl(jenConf["url"], jenConf["username"], jenConf["password"])


@app.route("/render", methods=["POST"])
def launchPipeline():
    absPath = uploadFile(request)
    # получить файл и сохранить его в workspace,
    # запустить pipiline передав путь к файлу и параметры,
    # вернуть фронту код, фронт выведет ожидание
    PARAMETERS = {
        "INPUT_FILE": absPath,
        "WIDTH": request.form["width"],
        "HEIGHT": request.form["height"],
        "FORMAT": request.form["format"],
        "COMPRESSION": request.form["compression"],
        "ANTIALIASING_ALGORITHM": request.form["aa"]
    }
    res = jenImpl.launchPipeline(jenConf["pipeline"], jenConf["token"], PARAMETERS)
    print("Jenkins Build URL: {}".format(res['url']))
    return "You got it!"


if __name__ == "__main__":
    app.run()

# TODO: страница для 404
