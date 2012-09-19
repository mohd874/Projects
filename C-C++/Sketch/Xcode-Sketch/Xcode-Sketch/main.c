//
//  main.c
//  Xcode-Sketch
//
//  Created by Mohamed Al-Habshi on ٩‏/٧‏/٢٠١١.
//  Copyright ٢٠١١ __MyCompanyName__. All rights reserved.
//

#include <stdio.h>

int main (int argc, const char * argv[])
{

    
    int num;
    printf("What is the size of the array u want: \n");
    scanf("%d", &num);
    fpurge(stdin);
    
    int myArrayInt[num];
    
    myArrayInt[0] = 90;
    printf("\n%d", myArrayInt[0]);
    
    return 0;
}

