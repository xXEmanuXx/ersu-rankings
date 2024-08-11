#include <stdio.h>

#include "headers/fix_format.h"

int main(int argc, char** argv) {
    if (argc != 2) {
       printf("Usage: %s <file>\n", argv[0]);
       return 1;
    }

    FILE* f = fopen(argv[1], "r");
    FILE* newf = fopen("tmp.txt", "w");

    /* char row[MAX_LENGTH];
    fgets(row, MAX_LENGTH, f);
    printStr(row); */

    modifyFormat(f, newf);

    fclose(f);
    fclose(newf);
    
    remove(argv[1]);
    rename("tmp.txt", argv[1]);

    printf("Terminazione corretta\n");
    return 0;
}