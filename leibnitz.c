//include libraries
#include<stdio.h>
#include<pthread.h> // for threads
#include<unistd.h>  // fork
#include<math.h>
#include<stdlib.h> // atoi ...


//Count Threads
#define NUM_THREADS 2

//struktur für thread
typedef struct {
	int rank;
    double sum;
} thread_data;


//array aus structs erstellen
thread_data td[NUM_THREADS];


void* help_pi(void *rank) {

    //thread rank gets assigned the value of whats is pointed to by the int pointer rank
	int thread_rank = *(int *)rank;
	double incr = 0;
    int sign;
    
    int k = thread_rank;

	if (k % 2) {
		sign = -1;
	} else {
		sign = 1;
	}

	td[thread_rank].sum = 4*((double)sign / (2 * thread_rank + 1));

    //laufe solange bis increment < 0.000001
	do {

		k += NUM_THREADS;

		if (k % 2) {
			sign = -1;
		} else {
			sign = 1;
		}

		incr = (double)sign / (2 * k + 1);
		td[thread_rank].sum += 4 * incr;
	}
    while ( fabs(incr) > 1e-6); //fabs -> absolutwert -1,5 -> 1,5 ...

	return NULL;
}

int main(){

    //init of variables
	int rank = 0;
	int err;
	double pi = 0;

    //array mit thread_ids 
    pthread_t thread_ids[NUM_THREADS];

    //erstellen der threads
	while(rank < NUM_THREADS) {

        //Zuweisung rank im struct entspricht dem gegebenen rank 0, 1, 2, ..
		td[rank].rank = rank;


		err = pthread_create(&(thread_ids[rank]), NULL, help_pi, (void*)&td[rank].rank);
		
        //Error handling
        if (err != 0) {
			printf("Can't create thread error =%d\n", err);
			return 1;
		}

        //rank counter erhöhen
		rank++;
	}

    //wiederverwenden von rank um zu counten    
	rank = 0;

    //warten auf die Beendigung der Threads -> null kein rückgabewert
	while(rank < NUM_THREADS) {
		pthread_join(thread_ids[rank], NULL);
		rank++;
	}


    //wiederverwenden von rank um zu counten
	rank = 0;

    //PI aus den einzelnen threads zusammenaddieren
	while(rank < NUM_THREADS) {
        pi += td[rank].sum;
		rank++;
	}

  //print der Pi-Approximation
  printf("%f\n",pi);

  return 0;
}