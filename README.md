# ERSU Rankings Overview

This project is focused on creating unofficial rankings for the scholarship and accommodation contests provided by ERSU Catania, using the list of participants in the contests.

The goal of this web application is to allow students to get an idea of their position in the ranking so that they can make various considerations. These considerations are especially relevant to accommodation, allowing students to evaluate the possibility of renting a house before the start of the academic year, considering that the list of participants is usually released about a month before the official rankings.

## Application Structure

This application uses Docker containers and is divided into three main parts:

- **`database-loader`**: responsible for reading the initial data present in a shared volume, formatting it, and loading it into the database by creating appropriate `.csv` files.
- **`database`**: responsible for managing the creation of the database with MariaDB and its data once they have been loaded.
- **`website`**: responsible for managing the frontend and backend of the website, which will display the data to the user based on their requests.
