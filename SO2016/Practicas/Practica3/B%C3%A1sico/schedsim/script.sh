
#/*--------------------SO: Grupo A-P3--------------------------------------*/
#     									   */
#/* Equipo:     Tuxedo Team                                                */
#/*-------------------------------------------------------------------------*/

# Script scheduler


clear


	nameScheduler=([1]=RR [2]=SJF [3]=PRIO [4]=FCFS)
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
			echo "Fichero no existente, introduzca otro"
		fi
		cd ..
	done

	clear

	# NUMERO CPUs
	while true;
	do
		echo "Numero maximo de CPUs (min:0,max:8): "
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
	fi
	mkdir resultados
	


	for (( s=1; s<=4; s++ )) 
	do  
		for (( c=1; c<= $maxCPUs; c++ )) 
		do  
			./schedsim -n $c -s ${nameScheduler[s]} -i examples/$fichero

			for (( i=0; i< $c; i++ )) 
			do  
	  			#mover CPU_$i.log a results/nameSched-CPU-$i.log
				mv CPU_$i.log resultados/CPU_${nameScheduler[s]}_$i$c.log
				#generar gráfica
				echo $value 
				cd ../gantt-gplot
				./generate_gantt_chart ../schedsim/resultados/CPU_${nameScheduler[s]}_$i$c.log
				cd ../schedsim
				pwd
			done
			
			
		done
	done
		

