
#/*--------------------SO: Grupo A-P3--------------------------------------*/
#     									   */
#/* Equipo:     Tuxedo Team                                                */
#/*-------------------------------------------------------------------------*/

# Script scheduler

clear

	# FICHERO
	while true;
	do
		echo "¿ Qué fichero desea simular ?(ejemplo.txt): "
		read fichero
		
		cd examples

		if [ -e $fichero ]; then

			echo "Fichero existe"

			if [ -f $fichero ]; then
				echo "Fichero de texto regular"
				cd ..
				break

			else 
				echo "Fichero no regular, introduzca uno valido"
			fi

		else 
			echo "Fichero no existente, introduzca uno"
		fi
		cd ..
	done

	clear

	# NUMERO CPUs
	while true;
	do
		echo "Numero maximo de CPUs ( >0 y <8): "
		read maxCPUs

		if [ $maxCPUs -gt 0 ]; then

			if [  $maxCPUs -le 8 ]; then
				echo "Numero valido"
				break
			else 
				echo "Numero invalido de CPUs ( No puede ser mayor que 8)"
			fi

		else 
			echo "Numero invalido de CPUs (No puede ser menor o igual que 0)"
		fi
    		
	done

	#Crea directorio resultados
	if [ -d resultados ] ; then
		rm -r resultados
		echo "eliminado directorio resultados"
	else
		mkdir resultados
	fi

	#foreach nameSched in listaDeSchedulersDisponibles
	for (( s=0; s<4; s++ )) 
	do  
		for (( c=1; c<= $maxCPUs; c++ )) 
		do  
			./schedsim -n c -i examples/$fichero

			for (( i=1; i<= c; i++ )) 
			do  
	  			#mover CPU_$i.log a results/nameSched-CPU-$i.log
				cp CPU$i.log resultados/nameSched-CPU-$i.log
				#generar gráfica
				cd ../gantt-gplot
				./generate_gantt_chart ../schedsim/resultados/CPU_$i.log

			done
			cd ../schedsim
			
		done
	done
		

