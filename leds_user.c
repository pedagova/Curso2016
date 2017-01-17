#include <stdio.h>
#include <stdlib.h>
#include <termios.h>
#include <unistd.h>
#include <fcntl.h>
#include <time.h>
#define PATH "/dev/leds"
#define NUMCICLOS 100000
static int fileDesc;
int kbhit(void)
{
	struct termios oldt, newt;
	int ch;
	int oldf;
	tcgetattr(STDIN_FILENO, &oldt);
	newt = oldt;
	newt.c_lflag &= ~(ICANON | ECHO);
	tcsetattr(STDIN_FILENO, TCSANOW, &newt);
	oldf = fcntl(STDIN_FILENO, F_GETFL, 0);
	fcntl(STDIN_FILENO, F_SETFL, oldf | O_NONBLOCK);
	ch = getchar();
	tcsetattr(STDIN_FILENO, TCSANOW, &oldt);
	fcntl(STDIN_FILENO, F_SETFL, oldf);
	if(ch != EOF)
	{
		ungetc(ch, stdin);
		return 1;
	}
	return 0;
}
char myread(void){
	if (kbhit()) return getchar();
	else return ' ';
}
int main(void)
{
	int contPlayer1 = 0;
	int contPlayer2 = 0;
	int pause = 0;
	int tickCont = 1;
	int on = 0;
	char ch;
	int i;
	int perdidoPlayer1 = 0;
	int perdidoPlayer2 = 0;

	if((fileDesc = open(PATH, O_RDWR)) < 0)
	{
		puts("Error");
	}
	srand(time(NULL));
	while(!perdidoPlayer1 || !perdidoPlayer2){
		ch = myread();
		if(!pause){
			if(ch == 's' && !perdidoPlayer1){
				system("clear");
				if(on) {contPlayer1++;}
				else {puts("Has perdido Player 1\n"); perdidoPlayer1 =1;}
			}
			if(ch == 'g' && !perdidoPlayer2){
				system("clear");
				if(on) {contPlayer2++;}
				else {puts("Has perdido Player 2"); perdidoPlayer2 =1;}
			}

			if(tickCont == NUMCICLOS){
				
				if(rand() % 100 < 80){
					on = 1;
					if(write(fileDesc, "1", 1) < 0) {
						puts("Can not write to file!\n");
					}
				}else{
					on = 0;
					if(write(fileDesc, "\n", 1) < 0) {
						puts("Can not write to file!\n");
					}
				}
				tickCont = 0;
				system("clear");
				printf("La puntuacion es: \nPlayer 1: %d \nPlayer 2: %d\n", contPlayer1, contPlayer2);
				
			}
			else tickCont++;
		}
		if(ch == 'p') {pause = !pause; puts("pausado");}
	}
	puts("Calculando puntuación final");
	for (i = 0; i < 4; i++){
		if(i == 0)
			write(fileDesc, "1", 1);
		else if(i == 1)
			write(fileDesc, "2", 1);
		else if (i == 2)
			write(fileDesc, "3", 1) ;
		else
			write(fileDesc, "123", 3);
		sleep(1);
	}
	printf("La puntuacion final es: \nPlayer 1: %d \nPlayer 2: %d\n", contPlayer1, contPlayer2);
	if((fileDesc = close(fileDesc)) < 0)
	{
		puts("Error");
	}
	return 0;
}
