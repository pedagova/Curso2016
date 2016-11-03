#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<unistd.h>
#include <sys/types.h>

int nC = 0;
int b_duerme = 1;

pthread_cond_t c_dormir;
pthread_cond_t c_corte;

pthread_mutex_t m_activo;
pthread_mutex_t m_corte;
pthread_mutex_t m_nC;

void *Barbero(void *arg);

void *Cliente(void *arg);

void cortaPelo();
void abandona();
void despierta();
void recibirCorte();
void duerme();

int main(){

	pthread_t barbero, cliente1, cliente2,cliente3, cliente4,cliente5, cliente6,cliente7, cliente8,cliente9, cliente10;

	pthread_mutex_init(&m_activo, NULL);
	pthread_mutex_init(&m_corte, NULL);
	pthread_mutex_init(&m_nC, NULL);

	pthread_cond_init(&c_dormir, NULL);
	pthread_cond_init(&c_corte, NULL);

	
	pthread_create(&barbero, NULL, Barbero, NULL);

	pthread_create(&cliente1, NULL, Cliente, NULL);
	sleep(1);
	pthread_create(&cliente2, NULL, Cliente, NULL);
	sleep(1);
	pthread_create(&cliente3, NULL, Cliente, NULL);
	sleep(1);
	pthread_create(&cliente4, NULL, Cliente, NULL);
	sleep(1);
	pthread_create(&cliente5, NULL, Cliente, NULL);
	sleep(1);
	pthread_create(&cliente6, NULL, Cliente, NULL);
	sleep(1);
	pthread_create(&cliente7, NULL, Cliente, NULL);
	sleep(1);
	pthread_create(&cliente8, NULL, Cliente, NULL);
	sleep(1);
	pthread_create(&cliente9, NULL, Cliente, NULL);
	sleep(1);
	pthread_create(&cliente10, NULL, Cliente, NULL);

	pthread_join(barbero, NULL);

	pthread_join(cliente1, NULL); 
	pthread_join(cliente2, NULL);
	pthread_join(cliente3, NULL); 
	pthread_join(cliente4, NULL);
	pthread_join(cliente5, NULL); 
	pthread_join(cliente6, NULL);
	pthread_join(cliente7, NULL); 
	pthread_join(cliente8, NULL);	
	pthread_join(cliente9, NULL); 
	pthread_join(cliente10, NULL);

	pthread_cond_destroy(&c_dormir);	
	pthread_cond_destroy(&c_corte);

	pthread_mutex_destroy(&m_activo);
	pthread_mutex_destroy(&m_corte);
	pthread_mutex_destroy(&m_nC);
	exit(0);
}

void *Barbero(void *arg){
	while(1){
		pthread_mutex_lock(&m_nC);		
		if(nC == -1) {
			pthread_mutex_unlock(&m_nC);
			duerme();	
		}
		else{
			
			pthread_mutex_unlock(&m_nC);
			cortaPelo();
			pthread_mutex_lock(&m_nC);
			nC--;
			pthread_mutex_unlock(&m_nC);
		}
	}
}

void *Cliente(void *arg){
	pthread_mutex_lock(&m_nC);
	if(nC == 3) {
		abandona();
		pthread_mutex_unlock(&m_nC);
	}
	else {
		nC++;
		if(nC == 1){ 
			pthread_mutex_unlock(&m_nC);
			despierta();
			
		}	
		else {
			pthread_mutex_unlock(&m_nC);	
			recibirCorte();
		}
	}
}

void cortaPelo(){
	sleep(2);
	pthread_cond_signal(&c_corte);
}

void abandona(){
	printf("Cliente: Adios, esto esta lleno\n");
}

void despierta(){
	printf("/ cliente grita desesperado por que le atiendan / \n");
	pthread_cond_signal(&c_dormir);
	recibirCorte();
}

void recibirCorte(){
	pthread_mutex_lock(&m_corte);
	pthread_cond_wait(&c_corte, &m_corte);
	pthread_mutex_unlock(&m_corte);
	printf("Gracias por el corte\n");
}

void duerme(){
	printf("Barbero: A dormir estoy molido\n");
	pthread_mutex_lock(&m_activo);
	pthread_cond_wait(&c_dormir, &m_activo);
	pthread_mutex_unlock(&m_activo);
	printf("Barbero: Quien me haya despertado se quedará sin patillas\n");
}
