/*
  StackOverrun.c
  This program shows an example of how a stack-based
  buffer overrun can be used to execute abritary code.
  Its objective is to find an input string that executes the function bar..
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void foo(const char* input){
  char buf[10];        
  
  printf("My stack looks like:\n%p\n%p\n%p\n%p\n%p\n%p\n\n");
  
  strcpy(buf, input);
  printf("%s\n", buf);
  
  printf("Now the stack looks like:\n%p\n%p\n%p\n%p\n%p\n%p\n\n");
}

void bar(void){
     printf("Augh! I've been hacked!\n");
}

int main(int argc, char *argv[]){
    printf("address of foo = %p\n",foo);
    printf("address of bar = %p\n",bar);
    
    if(argc != 2){
            printf("Please supply a string as an argument!\n");
            
            getchar();
            return -1;        
    }
    foo(argv[1]);
    getchar();
    return 0;
}
