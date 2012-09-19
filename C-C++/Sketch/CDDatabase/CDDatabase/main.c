//
//  main.c
//  CDDatabase
//
//  Created by Mohamed Al-Habshi on ٢٠‏/٧‏/٢٠١١.
//  Copyright ٢٠١١ __MyCompanyName__. All rights reserved.
//

#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
;
struct CDDataEntry{
    char artist[40];
    char composer[40];
    char albumName[40];
    int trackCount;
    bool isSampler;
};

int gNumDatabaseEntries = 0;
struct CDDataEntry* gDatabase = NULL;

void doNewCommand(){
    char yesOrNo;
    
    if(gDatabase == NULL){
        gDatabase = malloc(sizeof(struct CDDataEntry));
        if(gDatabase == NULL){
            printf("ERROR: couldn't create new entry!\n");
            return;
        }
    }else{
        struct CDDataEntry* newPtr = NULL;
        newPtr = realloc(gDatabase, gNumDatabaseEntries+1);
        if(newPtr == NULL){
            printf("ERROR: could not create new entry");
            return;
        }
        gDatabase = newPtr;
    }
    
    gNumDatabaseEntries += 1;
    
    printf("Artist Name: ");
    scanf("%39s", gDatabase[gNumDatabaseEntries-1].artist);
    fpurge(stdin);
    
    printf("Composer Name: ");
    scanf("%39s", gDatabase[gNumDatabaseEntries-1].composer);
    fpurge(stdin);
    
    printf("Album Name: ");
    scanf("%39s", gDatabase[gNumDatabaseEntries-1].albumName);
    fpurge(stdin);
    
    printf("No. of Tracks: ");
    scanf("%d", gDatabase[gNumDatabaseEntries-1].trackCount);
    fpurge(stdin);
    
    printf("Sampler? (y/n) ");
    scanf("%c", &yesOrNo);
    fpurge(stdin);
    
    gDatabase[gNumDatabaseEntries-1].isSampler = (yesOrNo == 'y' || yesOrNo == 'Y');
    
}

int main (int argc, const char * argv[])
{
    struct CDDataEntry myEntries[10];
    bool keepRunning = true;
    char userInput[11];
    
    while(keepRunning == true){
        printf("\nType NEW, LIST, or QUIT:\n ");
        scanf("%10s",userInput);
        fpurge(stdin);
        
        if(strcmp(userInput, "NEW") == 0){
            printf("NEW");
        }else if(strcmp(userInput, "LIST") == 0){
            printf("LIST");
        }else if (strcmp(userInput, "QUIT") == 0){
            printf("QUIT");
            keepRunning = false;
        }else{
            printf("Invalid Command! \n");
        }
    }
    
    return 0;
}

