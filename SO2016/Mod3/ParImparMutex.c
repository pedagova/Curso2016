#include<stdio.h>
#include<string.h>
#include<pthread.h>
#include<stdlib.h>
#include<unistd.h>

int dato = 0;
int senial_impar = 0; 

pthread_mutex_t senial; 

void *Par(void *arg);
void *Impar(void *arg);

int main(){
	pthread_t par, impar;

	pthread_mutex_init(&senial, NULL);

	pthread_create(&par, NULL, Par, NULL);
   	pthread_create(&impar, NULL, Impar, NULL);

	pthread_join(par, NULL);    pthread_join(impar, NULL);

	pthread_mutex_destroy(&senial);
	exit(0);
}

void *Par(void *arg){
	int i = 0;
	while(i < 5){
		pthread_mutex_lock(&senial);
		if(senial_impar == 1){
			dato++;
			printf("%d par\n", dato);
	
			senial_impar = 0;

			i++;
		}
	pthread_mutex_unlock(&senial);
	}

}
void *Impar(void *arg){
	int i = 0;
	while(i < 5){
		pthread_mutex_lock(&senial);
		if(senial_impar == 0){
			
			dato++;
			printf("%d impar\n", dato);

			senial_impar = 1;

				

			i++;
		}
		pthread_mutex_unlock(&senial);
	}
}
