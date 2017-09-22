/*
Read4K Facebook Interview Question

This isn't tested, but I'm pretty confident this works. read4k() is not
implemented
*/

#include <stdlib.h>
#include <string.h>
#define BLOCK_SIZE 4000

int read4k(char* buf) {
	return 0;	// dummy val
}

int min(int a, int b) {
	if (a < b)
		return a;
	else
		return b;
}

int readn(char* buf, int n) {
	char * temp = (char *) malloc (BLOCK_SIZE * sizeof(char));
	int total = 0;
	int bytesRead;

	do {
		bytesRead = read4k(temp);			// this will take max BLOCK_SIZE
		int bytesToWrite = min(bytesRead, n);	// this will be min 0, max BLOCK_SIZE
		memcpy(temp, buf, bytesToWrite);
		n -= bytesToWrite;			// decrease number of bytes remaining to read
		buf += bytesToWrite;		// increase buffer by bytes read
		total += bytesToWrite;		// keep track of total bytes read
	} while (n >= 0 && bytesRead != 0);

	return total;
}


int main() {

	return 0;
}