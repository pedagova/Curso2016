#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<unistd.h>

#define N = 10

int nC = 0;
int b_duerme = 1;

pthread_cond_c c_dormir;
pthread_cond_c c_corte;

pthread_mutex_t m_activo;
pthread_mutex_t m_corte;
pthread_mutex_t m_nC;

void *Barbero(void *arg);

void *Cliente(void *arg);

void cortaPelo();
void abandona();
void despierta();
void corte();
void duerme();

int main(){

}

void *Barbero(void *arg){
	while(1){pthread_mutex_lock(&m_nC);
		
		
		if(nC == 0) duerme();
		else cortaPelo();
		pthread_mutex_unlock(&m_nC);
	}
}

void *Cliente(void *arg){
	pthread_mutex_lock(&m_nC);
	if(nC == N) abandona();
	else {
		pthread_mutex_lock(&m_nC);
		nC++;
		pthread_mutex_unlock(&m_nC);
		if(b_duerme == 1) despierta();	
		else corte();
	}
	pthread_mutex_unlock(&m_nC);
}

void cortaPelo(){
	wait(9);
	pthread_cond_signal(&c_cortar);
}
void abandona(){
	printf("Adios, esto esta lleno\n");
}
void despierta(){
	pthread_cond_signal(&c_dormir);
	recibirCorte();
}
void recibirCorte(){
	pthread_cond_wait(&c_corte, m_corte);
	pritnf("Gracias por el corte");
}

void duerme(){
	pthread_mutex_lock(&m_activo, NULL);
	b_duerme = 1;
	pthread_cond_wait(&c_dormir, m_activo);
	b_duerme = 0;
	pthread_mutex_unlock(&m_activo, NULL);

}
