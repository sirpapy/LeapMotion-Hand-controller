echo off

set fichier = LeapMouse

IF "%PROCESSOR_ARCHITECTURE%"=="x86" (set LIB=x86) else (set LIB=x64)

java -classpath ".;lib/LeapJava.jar" -Djava.library.path=lib/%LIB% %fichier%
