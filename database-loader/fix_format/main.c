#include <stdio.h>

#include "headers/fix_format.h"

int main(int argc, char** argv) {
    // Fixes format for all files in one execution
    if (argc < 2) {
        printf("Usage: %s <file-1> <file-2> ... <file-n>\n", argv[0]);
        return 1;
    }

    for (int i = 1; i <= argc - 1; i++) {
        char filename[MAX_FILENAME];

        snprintf(filename, MAX_FILENAME, "../../data/initial/%s", argv[i]);
        FILE* f = fopen(filename, "r");

        snprintf(filename, MAX_FILENAME, "../../data/%s", argv[i]);
        FILE* newf = fopen(filename, "w");

        /* char row[MAX_LENGTH];
        fgets(row, MAX_LENGTH, f);
        printStr(row); */

        modifyFormat(f, newf);

        fclose(f);
        fclose(newf);
    }

    printf("Correct termination\n");
    return 0;
}