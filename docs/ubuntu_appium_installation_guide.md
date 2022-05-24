### Инструкция по установке appium на Ubuntu
Данный способ является альтернативой установке GUI компонент. Подробнее [тут.](./gui_appium_installation_guide.md)
1. Установить npm nodejs.\
   [Подробнее тут (вреси и ос)](https://github.com/nodesource/distributions/blob/master/README.md).
    ```shell
    curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E shell -
    sudo apt-get install -y nodejs nmp
    ```

2. Установить appium и doctor.
    ```shell
    nmp install -g appium appium-doctor
    ```

3. Проверяем версию java.\
   Рекомендуется 8, но не является обязательным требованием._
   ```shell
    java --version
   ```
   - Дальше возможно три пути развития событий:
        - Если java не установлена, то устанавливаем из списка предложенных _(напоминание про рекомендованную версию -> java8)_
        - Если java установлена, то проверяем, что она есть в env как JAVA_HOME.
            ```shell
            echo $JAVA_HOME
            ```
        - Если java установлена, но ее нет $JAVA_HOME, то находим ее расположение. _(использовать будем в дальнейших шагах инструкции)_
            ```shell
            type -p java|xargs readlink -f|xargs dirname|xargs dirname
            ```
            Ожидаемый результат:
            ```shell
            /usr/lib/jvm/*ваша версия java*
            ```
    
4. Устанавливаем JAVA SDK.
    - **Предпочтительный способ.**\
      Android Studio. Путь по умолчанию -> /home/*user*/Android/Sdk 
      
    - Альтернативыне способы:
        - Через пакетный мененджер.
          ```shell
          sudo apt update && sudo apt install android-sdk
          # Может оказаться в любом из нижеприведенных путей
          # /home/*user*/Android/Sdk
          # /usr/lib/android-sdk
          # /Library/Android/sdk/
          # /Users/*user*/Library/Android/sdk
          ```
        - Скачать с [оффициального сайта](https://developer.android.com/studio/#downloads) и распаковать в удобную директорию.

5. Добавить переменные окружения.
    - Открыть на редактирование ~/.shellrc _(в домашней директории пользователя, а не root'a)_ и дописать нижеприведенные строки:
        ```shell
        ### APPIUM SETAPP ###
        # Подставляем собственный путь из шага 4.
        export ANDROID_HOME="/home/*user*/Android/Sdk"
        
        # Подставяем собсвтенный путь к JAVA_HOME из шага 3.
        export JAVA_HOME="/usr/lib/jvm/default-java"
        export ADB="$ANDROID_HOME/platform-tools/adb"
        
        export PATH="$PATH:$ANDROID_HOME/tools/bin/
        
        ```

6. Проверить корректность установки.
    ```shell
    appium-doctor
    ```
    Пример выводы:
    ```shell
    nfo AppiumDoctor Appium Doctor v.1.16.0
    info AppiumDoctor ### Diagnostic for necessary dependencies starting ###
    info AppiumDoctor  ✔ The Node.js binary was found at: /usr/bin/node
    info AppiumDoctor  ✔ Node version is 18.0.0
    info AppiumDoctor  ✔ ANDROID_HOME is set to: /home/*user*/Android/Sdk
    info AppiumDoctor  ✔ JAVA_HOME is set to: /usr/lib/jvm/default-java
    info AppiumDoctor    Checking adb, android, emulator
    info AppiumDoctor      'adb' is in /home/*user*/Android/Sdk/platform-tools/adb
    info AppiumDoctor      'android' is in /home/*user*/Android/Sdk/tools/android
    info AppiumDoctor      'emulator' is in /home/*user*/Android/Sdk/emulator/emulator
    info AppiumDoctor  ✔ adb, android, emulator exist: /home/*user*/Android/Sdk
    info AppiumDoctor  ✔ 'bin' subfolder exists under '/usr/lib/jvm/default-java'
    info AppiumDoctor ### Diagnostic for necessary dependencies completed, no fix needed. ###
    info AppiumDoctor
    info AppiumDoctor ### Diagnostic for optional dependencies starting ###
    WARN AppiumDoctor  ✖ opencv4nodejs cannot be found.
    WARN AppiumDoctor  ✖ ffmpeg cannot be found
    WARN AppiumDoctor  ✖ mjpeg-consumer cannot be found.
    WARN AppiumDoctor  ✖ bundletool.jar cannot be found
    info AppiumDoctor  ✔ gst-launch-1.0 and gst-inspect-1.0 are installed at: /usr/bin/gst-launch-1.0 and /usr/bin/gst-inspect-1.0
    info AppiumDoctor ### Diagnostic for optional dependencies completed, 4 fixes possible. ###
    info AppiumDoctor
    info AppiumDoctor ### Optional Manual Fixes ###
    info AppiumDoctor The configuration can install optionally. Please do the following manually:
    WARN AppiumDoctor  ➜ Why opencv4nodejs is needed and how to install it: https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/image-comparison.md
    WARN AppiumDoctor  ➜ ffmpeg is needed to record screen features. Please read https://www.ffmpeg.org/ to install it
    WARN AppiumDoctor  ➜ mjpeg-consumer module is required to use MJPEG-over-HTTP features. Please install it with 'npm i -g mjpeg-consumer'.
    WARN AppiumDoctor  ➜ bundletool.jar is used to handle Android App Bundle. Please read http://appium.io/docs/en/writing-running-appium/android/android-appbundle/ to install it
    info AppiumDoctor
    info AppiumDoctor ###
    info AppiumDoctor
    info AppiumDoctor Bye! Run appium-doctor again when all manual fixes have been applied!
    info AppiumDoctor

    ```
    Наличие warning'ов допускается (не все зависимости нам сейчас нужны).

### Возможные проблемы.
1. Не запускается uiautomatorviewer.
   Это может быть связано с установленной версией java.
   Eсли ошибка выглядит так:
    ```shell
    -Djava.ext.dirs=/home/*user*/Android/Sdk/tools/lib/x86_64:/home/*user*/Android/Sdk/tools/lib is not supported.  Use -classpath instead.
    Error: Could not create the Java Virtual Machine.
    Error: A fatal exception has occurred. Program will exit.
    ```
    То меняем в $ANDROID_HOME/tools/bin/uiautomatorviewer строчку:
    ```shell
    exec "${javaCmd}" $javaOpts -Djava.ext.dirs="$frameworkdir" -Dcom.android.uiautomator.bindir="$progdir" -jar "$jarpath" "$@"
    ```
    На:
    ```shell
    exec "${javaCmd}" $javaOpts \
      -Dcom.android.uiautomator.bindir="$progdir" \
      -cp $swtpath/swt.jar:$frameworkdir/* com.android.uiautomator.UiAutomatorViewer "$@"
    ```