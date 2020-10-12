#links:

##1

1. https://docs.blender.org/api/current/info_quickstart
2. https://docs.blender.org/manual/en/latest/advanced/command_line/arguments.html
3. https://blender.stackexchange.com/questions/6817/how-to-pass-command-line-arguments-to-a-blender-python-script
4. https://stackoverflow.com/questions/14982836/rendering-and-saving-images-through-blender-python
5. https://stackoverflow.com/questions/48482513/render-blender-outside-of-blender-gui-with-python
6. https://stackoverflow.com/questions/21406887/subprocess-changing-directory/21406995
7. https://blender.stackexchange.com/questions/80/can-cycles-renders-be-done-from-the-command-line
8. https://stackoverflow.com/questions/15535240/how-to-write-to-stdout-and-to-log-file-simultaneously-with-popen
9. https://pythonz.net/references/named/file.close/
10. https://blender.stackexchange.com/questions/48584/how-to-add-a-texture-to-a-material-using-python

##2

1. https://www.jenkins.io/doc/book/pipeline/syntax/
2. https://stackoverflow.com/questions/43003510/can-jenkins-job-execute-shell-or-windows-command-conditionally-based-on-agent-os
3. https://stackoverflow.com/questions/43587964/jenkins-pipeline-if-else-not-working
4. https://stackoverflow.com/questions/43587964/jenkins-pipeline-if-else-not-working
5. https://stackoverflow.com/questions/51480440/file-parameter-in-declarative-pipeline
6. https://stackoverflow.com/questions/61057780/unable-to-stash-file-in-jenkins-pipeline
7. https://stackoverflow.com/questions/46448976/jenkins-declarative-pipeline-post-action-success-before-always
8. http://vgaidarji.me/blog/2018/07/30/working-with-jenkinsfile-in-intellij-idea/
9. https://ealebed.github.io/posts/2018/jenkins-%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5-shared-libraries/
10. https://stackoverflow.com/questions/21045061/git-clone-from-another-directory/21045596
11. https://issues.jenkins-ci.org/browse/JENKINS-62961

###Заметки

1. file в замыкании parameters хранить относительный от workspace-а путь к файлу, поэтому приходится использовать 
    строковый путь к нему и java-ские библиотеки, это же сломало render.py, так-как движок блендера понимает только абсолютные пути.
2. Странная логика stash (сначала dir, потом включить файла, просто путь нельзя указать).
3. Декларативный if-else через when сложен (даже в декларативных функциональных языках есть if-else).
4. Проблемы интерфейса: не красивый, не удобный, плагин не установился сразу (кнопки пропали), параметры пайплайна 
    обновляются только после перезапуска, некорректные описания ошибок (Library expected to contain at least one 
    of src or vars directories).
5. Выкачивает весь репозиторий если надо получить jenkinsfile из scm.
6. Задал пути для Gradle и Maven.