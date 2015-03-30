FICHIER=LeapMouse 
ARCH=$(uname -m)

if [ $ARCH="x86_64" ] 
then LIB="x64"
else LIB="x86"
fi

#sudo start service leapd
java -classpath ".:lib/LeapJava.jar" -Djava.library.path=lib/$LIB $FICHIER
