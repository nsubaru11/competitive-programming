#include <stdio.h>

#include <math.h>

#include <time.h>

#include <string.h>

#include <stdlib.h>


#define LOOP_NUM 5000000


int digit_calc_log10(int);

int digit_calc_division10(int);

int digit_calc_strlen(int);

void function_loop(int (*p_function)(int));


int main() {

	// 処理 Log10 ()

	function_loop(digit_calc_log10);


	// 処理 /10

	function_loop(digit_calc_division10);


	// 処理 strlen

	function_loop(digit_calc_strlen);


	return 0;
}


// 桁数をLog10で計算

int digit_calc_log10(int value) { return log10(value) + 1; }


// 桁数を10の除算で計算

int digit_calc_division10(int value) {

	int digit = 0;


	while (value) {

		value /= 10;

		digit++;
	}


	return digit;
}


// 桁数をcharの長さで計算

int digit_calc_strlen(int value) {

	char c_value[100];

	sprintf(c_value, "%d", value);

	return strlen(c_value);
}


// LOOP_NUM回p_function関数を繰り返す

void function_loop(int (*p_function)(int)) {

	clock_t start, end;


	start = clock();

	int digit;

	int value;

	for (value = 1; value < LOOP_NUM; value++) {

		digit = p_function(value);

		//      printf("%d:%d\n", value, digit);
	}

	end = clock();


	printf("処理時間:%d[ms]\n", end - start);
}
