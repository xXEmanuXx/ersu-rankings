#!/bin/bash

# Compile 'fix_format' .c files
echo "Compiling and running 'fix_format' .c files"
cd fix_format
gcc main.c fix_format.c -o fix_format
./fix_format first_years.txt subsequent_years.txt
if [ $? -ne 0 ]; then
    echo "Error on fix_format script execution"
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

touch /data/db_loader_done

echo "Database loaded"