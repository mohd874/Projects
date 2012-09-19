//Tutorial from Masters-of-the-void. Lessons 1-7.

#include <stdio.h>
#include <stdbool.h>

int main (int argc, const char * argv[])
{

    int num1, num2;
    char operation;
    bool finished;
    
    finished = false;
    
    void getArgument(bool isLeft, int * num);
    
    while (finished != true){
        printf("What operation do you want to do? (+ , - , * , /)\n");
        scanf("%c", &operation);
        fpurge(stdin);

        switch(operation){
            case '+':
                getArgument(true, &num1);
                getArgument(false, &num2);
                printf("%d %c %d = %d\n", num1, operation, num2, num1+num2);
            break;
            case '-':
                getArgument(true, &num1);
                getArgument(false, &num2);
                printf("%d %c %d = %d\n", num1, operation, num2, num1-num2);
            break;
            case '*':
                getArgument(true, &num1);
                getArgument(false, &num2);
                printf("%d %c %d = %d\n", num1, operation, num2, num1*num2);
            break;
            case '/':                
                getArgument(true, &num1);
                getArgument(false, &num2);
                if(num2 == 0){
                    printf("Cannot divide by Zero\n");
                    break;
                }
                printf("%d %c %d = %d\n", num1, operation, num2, num1/num2);
            break;
            default:
                finished = true;
            break;
        }
    
    }
    printf("Finished.\n");
    
    return 0;
}

void getArgument(bool isLeft, int * num){
    if(isLeft){
        printf("Enter left argument: ");
    }else{
        printf("Enter right argument: ");
    }
    
    scanf("%d", num);
    fpurge(stdin);
    
}


