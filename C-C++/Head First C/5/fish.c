#include <stdio.h>

struct preferences {
	const char *food;
	int exercise_hours;
};
struct fish{
	const char *name;
	const char *species;
	int teeth;
	int age;
	struct preferences care;
};

void catalog (struct fish f){
	printf("%s is a %s with %i teeth. He is %i and likes %s. %s exercises %i a day \n",f.name,f.species,f.teeth,f.age,f.care.food,f.name,f.care.exercise_hours);
}

void label(struct fish f){
	printf("Name: %s \nSpecies: %s \nTheeth: %i \nAge: %i \nFav. Food: %s \n",f.name,f.species,f.teeth,f.age, f.care.food);
}


int main (char *args){
	struct fish snappy = {"Snappy","Piranah",34,4, {"Meat", 3}};
	catalog (snappy);
	label(snappy);
}

