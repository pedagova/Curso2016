#include<stdio.h>
#include<string.h>
#include<pthread.h>
#include<stdlib.h>
#include<unistd.h>

int data = 0;

pthread_cond_t c_par;
pthread_cond_t c_impar;

pthread_mutex_t senial; 

void *Par(void *arg);
void *Impar(void *arg);

int main(){
	pthread_t par, impar;

	pthread_mutex_init(&senial, NULL);

	pthread_cond_init(&c_par, NULL);
    	pthread_cond_init(&c_impar, NULL);	
	
	pthread_create(&par, NULL, Par, NULL);
   	pthread_create(&impar, NULL, Impar, NULL);

	pthread_join(par, NULL);    pthread_join(impar, NULL);

	pthread_mutex_destroy(&senial);
	
	pthread_cond_destroy(&c_par);
    	pthread_cond_destroy(&c_impar);
	
	exit(0);
}

void *Par(void *arg){
	int i = 0;
		
	while (i < 2000){
		pthread_mutex_lock(&senial);
		pthread_cond_wait(&c_impar, &senial); //bloqueo hasta que acabe el impar
		data++;
		printf("%d par\n", data);
		i++;
		pthread_mutex_unlock(&senial);
		pthread_cond_signal(&c_par); //fin par
		
	}

}
void *Impar(void *arg){
	int i = 0;
	while(i < 2000){
		pthread_mutex_lock(&senial);
		if(i > 0)
			pthread_cond_wait(&c_par, &senial); //bloqueo hasta que acabe el par			
		data++;
		printf("%d impar\n", data);
		i++;
		pthread_mutex_unlock(&senial);
		pthread_cond_signal(&c_impar); //fin impar
				
	}
}
