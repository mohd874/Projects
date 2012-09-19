#include <stdio.h>
#include <string.h>

const char tracks[][80] = {
	  "I left my heart in Harvard Med School",
	  "Newark, Newark - a wonderful town",
	  "Dancing with a Dork",
	  "From here to maternity",
	  "The girl from Iwo Jima",
	};

void findTrack(char msg[]){
	int i=0;
	for(i=0;i<5;i++){
		if(strstr(tracks[i], msg)){
			printf("Track %i: - %s\n", i, tracks[i]);
		}
	}
}

int main(){
	
	char search[80];
	puts("What music are you looking for: ");
	//fgets(search, 80, stdin);
	scanf("%79s", search);
	findTrack(search);
	printf("You are seachring for: %s",search);
	puts(strstr(tracks[0], search));
	return 0;
}