#include <stdio.h>
#include <stdlib.h>

int main()
{
	char card_name[3];
	
	int val = 0;
	int count = 0;
	
	while(card_name[0] != 'E'){
		
		puts("Enter the card name: \n");
		scanf("%2s", card_name);
		
		switch(card_name[0]){
			case 'K':
			case 'Q':
			case 'J':
				val = 10;
				break;
			case 'A':
				val = 11;
				break;
			case 'E':
				continue;
			default:
				val = atoi(card_name);
				if(val < 1 || val > 10){
					puts("Your input in invalid!\n");
					continue;
				}
		}
		
		printf("The card value is: %i\n", val);
		
		if(val >= 3 && val <= 6){
			count++;
		}else if(val == 10){
			count--;
		}
		
		printf("Current count: %i\n", count);
	}
	
	printf("Cards count: %i\n", count);
	
	return 0;
}