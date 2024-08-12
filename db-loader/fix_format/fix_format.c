#include <stdio.h>
#include <string.h>

#include "headers/fix_format.h"

void printStr(char* str) {
    for (int i = 0; i < strlen(str); i++) {
        if (str[i] == '\t') {
            printf("\\t");
        }
        else if (str[i] == '\n') {
            printf("\\n");
        }
        else {
            printf("%c", str[i]);
        }
    }
    printf("\n");
}

void modifyFormat(FILE* f, FILE* newf) {
    char string[MAX_LENGTH];

    while (fgets(string, MAX_LENGTH, f) != NULL) {
        if (string[0] == '2') {
            // checks if the string is a participant if it starts with a '2' which indicates the request number
            for (int i = 0; i < strlen(string); i++) {
                if (string[i] == '.') {
                    // removes the dot by moving the remaining part of the string one byte to the left overlapping it
                    memmove(&string[i], &string[i+1], strlen(string) - i);
                    i--;
                }
                else if (string[i] == ',') {
                    // simply replaces comma with a dot for decimal numbers
                    string[i] = '.';
                }
            }
            fprintf(newf, "%s", string);
        }
    }
}