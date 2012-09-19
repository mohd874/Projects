#include <stdio.h>
#include <limits.h>
#include <float.h>

int main(){
	printf("The value of INT_MAX is %i\n", INT_MAX);
	printf("The value of INT_MIN is %i\n", INT_MIN);
	printf("The size of an int in bytes is %d \n", sizeof(int));
	
	printf("The value of FLT_MAX is %f\n", FLT_MAX);
	printf("The value of FLT_MIN is %.50f\n", FLT_MIN);
	printf("The size of an float in bytes is %d \n", sizeof(float));
	
	printf("The value of DBL_MAX is %i\n", DBL_MAX);
	printf("The value of DBL_MIN is %i\n", DBL_MIN);
	printf("The size of an int in bytes is %d \n", sizeof(double));
	
	printf("The value of SHRT_MAX is %f\n", SHRT_MAX);
	printf("The value of SHRT_MIN is %.50f\n", SHRT_MIN);
	printf("The size of an float in bytes is %d \n", sizeof(short));
	
	printf("The value of LNG_MAX is %i\n", LONG_MAX);
	printf("The value of LNG_MIN is %i\n", LONG_MIN);
	printf("The size of an int in bytes is %d \n", sizeof(long));
	
	return 0;
}