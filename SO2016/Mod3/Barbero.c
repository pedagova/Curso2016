#include <stdio.h>
#include <string.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <semaphore.h>

#define N 10

sem_t sem;
pthread_mutex_t elem;
pthread_mutex_t cortando;
pthread_mutex_t durmiendo;

pthread_cond_t despertar;
pthread_cond_t terminarCortar;

int nroClientes =0;


void *cortar_pelo(void *args);
void *recibir_corte_pelo(void *args);


int main(){
	printf("Estoy aqui arrancando");
	pthread_t cliente, barbero;	
	sem_init(&sem,0,N);
	pthread_mutex_init(&elem,NULL);
	pthread_mutex_init(&cortando,NULL);
	pthread_mutex_init(&durmiendo,NULL);
	pthread_cond_init(&despertar,NULL);
	pthread_cond_init(&terminarCortar,NULL);
	
	pthread_create(&barbero, NULL, cortar_pelo, NULL);
	pthread_create(&cliente, NULL, recibir_corte_pelo, NULL);
	


	pthread_join(cliente, NULL);
	pthread_join(barbero, NULL);
	sem_destroy(&sem);
	pthread_mutex_destroy(&elem);
	pthread_mutex_destroy(&cortando);
	pthread_mutex_destroy(&durmiendo);
	pthread_cond_destroy(&despertar);
	pthread_cond_destroy(&terminarCortar);
}

void *cortar_pelo( void *args){
	printf("Ha entrado");
	while(1){
		pthread_mutex_lock(&elem);
		if(nroClientes==0){
			pthread_mutex_lock(&durmiendo);	
		        pthread_cond_wait(&despertar,&durmiendo);
			pthread_mutex_unlock(&durmiendo);	
		}else{
			wait(9);
			printf("Le ha cortado el pelo BARBERO");
			pthread_cond_signal(&terminarCortar);		
		}
		printf("Estoy aqui");
		pthread_mutex_unlock(&elem);
	}
}

void *recibir_corte_pelo(void *args){

	printf("cliente estupido en ejecucion");
	pthread_mutex_lock(&elem);
	if(nroClientes==0){
		pthread_cond_signal(&despertar);	
	}

	if(nroClientes!=N){
		nroClientes++;
		sem_wait(&sem);		
			
		pthread_mutex_lock(&cortando);
		
		printf("Esperando a que le corte el pelo CLIENTE");
		pthread_cond_wait(&terminarCortar,&cortando);
		printf("Se le ha cortado el pelo CLIENTE");

		pthread_mutex_unlock(&cortando);

		sem_post(&sem);
		nroClientes--;
	}

	pthread_mutex_unlock(&elem);	
	printf("cliente estupido no acabaaaaaa");			
}
