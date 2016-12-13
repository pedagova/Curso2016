#!/bin/bash

BASE_DIR="src" # directorio con los ficheros base
TEMP_DIR="temp" # directorio para archivos temporales
RESL_DIR="mount-point" # directorio resultado

MY_FS_H="myFS.h"
FUSE_LIB_C="fuseLib.c"
AUX_FILE_C="hi.c"

# restauramos el directorio resultado
fusermount -u $RESL_DIR
rmdir $RESL_DIR	
mkdir $RESL_DIR

# a:
echo "PARTE A"
# copiamos uno de los archivos del dir base al dir. temporal y resultado
cp $BASE_DIR/$MY_FS_H $TEMP_DIR/$MY_FS_H	
cp $BASE_DIR/$MY_FS_H $RESL_DIR/$MY_FS_H

# copiamos el otro archivo del dir base al dir. temporal y resultado
cp $BASE_DIR/$FUSE_LIB_C $TEMP_DIR/$FUSE_LIB_C	
cp $BASE_DIR/$FUSE_LIB_C $RESL_DIR/$FUSE_LIB_C

echo "FIN PARTE A"

# b:
echo "PARTE B"
# montamos un sistema de ficheros
./my-fsck virtual-disk
# hacemos diff de ambos ficheros sobre los directorios donde fueron copiados (si tenemos como resultado cadena vacia todo bien, mirar en internet si dudas)
DIFF_MY_FS_H=$(diff $RESL_DIR/$MY_FS_H $TEMP_DIR/$MY_FS_H)
DIFF_FUSE_LIB_C=$(diff $RESL_DIR/$FUSE_LIB_C $TEMP_DIR/$FUSE_LIB_C)
if [ "$DIFF_MY_FS_H" == "" ];
then echo " <MY_FS_H> Los ficheros son iguales en ambos directorios"
else echo " <MY_FS_H> Los ficheros son distintos"
fi
if [ "$DIFF_FUSE_LIB_C" == "" ];
then echo " <FUSE_LIB_C> Los ficheros son iguales en ambos directorios"
else echo " <FUSE_LIB_C> Los ficheros son distintos"
fi
# truncamos el mismo fichero en los dos directorios
truncate --size=-4096 $TEMP_DIR/$MY_FS_H
truncate --size=-4096 $RESL_DIR/$MY_FS_H
echo " <MY_FS_H> Fichero truncado, ocupa un bloque menos (4096)"

echo "FIN PARTE B"

# c:
echo "PARTE C"
# montamos un sistema de ficheros
./my-fsck virtual-disk
DIFF_TRUNCATE=$(diff $BASE_DIR/$MY_FS_H $RESL_DIR/$MY_FS_H)
if [ "$DIFF_TRUNCATE" == "" ];
then echo " <MY_FS_H> El archivo es identico al original"
else echo " <MY_FS_H> El archivo y la copia son distintos"
fi
echo "FIN PARTE C"

# d:
echo "PARTE D"
# copiamos un archivo cualquiera del dir base al dir resultado
cp $BASE_DIR/$AUX_FILE_C $RESL_DIR/$AUX_FILE_C
echo "FIN PARTE D"

# e:
echo "PARTE E"
# montamos un sistema de ficheros
./my-fsck virtual-disk
DIFF_COPY=$(diff $BASE_DIR/$AUX_FILE_C $RESL_DIR/$AUX_FILE_C)
if [ "$DIFF_COPY" == "" ];
then echo " <AUX_FILE_C> La copia es identica al original."
else echo " <AUX_FILE_C> La copia es distinta."
fi
echo "FIN PARTE E"

# f:
echo "PARTE F"
# truncamos el mismo fichero en los dos directorios
truncate --size=+4096 $TEMP_DIR/$FUSE_LIB_C
truncate --size=+4096 $RESL_DIR/$FUSE_LIB_C
echo " <FUSE_LIB_C> Fichero truncado, ocupa un bloque mas(4096)"
echo "FIN PARTE F"

# g:
echo "PARTE G"
# montamos un sistema de ficheros
./my-fsck virtual-disk
DIFF_TEMP_DIR_TRUNCATE_FUSE_LIB_C=$(diff $BASE_DIR/$FUSE_LIB_C $TEMP_DIR/$FUSE_LIB_C) # See Link1 at bottom | Make diff
DIFF_RESL_DIR_TRUNCATE_FUSE_LIB_C=$(diff $BASE_DIR/$FUSE_LIB_C $RESL_DIR/$FUSE_LIB_C) # See Link1 at bottom | Make diff
if [ "$DIFF_TEMP_DIR_TRUNCATE_FUSE_LIB_C" == "" ];
then echo "[FUSE_LIB_C -- TEMP_DIR] El fichero truncado es igual al original"
else echo "[FUSE_LIB_C -- TEMP_DIR] Los ficheros son distintos"
fi
if [ "$DIFF_RESL_DIR_TRUNCATE_FUSE_LIB_C" == "" ];
then echo "[FUSE_LIB_C -- RESL_DIR] El fichero truncado es igual al original"
else echo "[FUSE_LIB_C -- RESL_DIR] Los ficheros son distintos"
fi
echo "FIN PARTE G"
