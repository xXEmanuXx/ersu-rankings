#!/bin/bash

# Compile 'fix_format' .c files
echo "Compiling and running 'fix_format' .c files"
cd fix_format
gcc main.c fix_format.c -o fix_format
if [ $? -eq 0 ]; then
    echo "Running fix_format"
    ./fix_format first_years.txt subsequent_years.txt
else
    echo "Compilation of main.c and fix_format.c failed"
    exit 1
fi

# Run 'first_years.php' file
echo "Running 'first_years.php' file"
cd ../first_years
php first_years.php
if [ $? -ne 0 ]; then
    echo "Error on php script execution"
    exit 1;
fi

# Run 'subsequent_years.php' file
echo "Running 'subsequent_years.php' file"
cd ../subsequent_years
php subsequent_years.php
if [ $? -ne 0 ]; then
    echo "Error on php script execution"
    exit 1;
fi

echo "Database loaded"