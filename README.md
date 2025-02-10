# Movie Review Application

This Movie Review application provides functionality to browse movies, view their details, watch trailers, and read or write reviews through a user-friendly interface.

## Table of Contents

- [Movie Review Application](#movie-review-application)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
  - [Tech-stack Used](#tech-stack-used)
    - [Backend](#backend)
    - [Frontend](#frontend)
  - [Database Design](#database-design)
    - [`Movie`](#movie)
    - [`Review`](#review)
  - [Application Architecture](#application-architecture)
  - [Future Enhancements](#future-enhancements)

---

## Features

- **Movie Browsing**: View a list of movies with details such as title, release date, poster, and genres.
- **Trailer Playback**: Watch movie trailers directly within the application.
- **Review Management**: Read and write reviews for movies.
- **Data Storage**: Persist movie and review data using MongoDB.
- **Modern UI**: Responsive frontend design using React and Bootstrap.
- **RESTful API**: Efficient and scalable API endpoints for movie and review management.

---

## Tech-stack Used

### Backend
- **Java/Spring Boot**: For API and business logic implementation.
- **MongoDB**: NoSQL database for storing application data.
- **MongoTemplate**: For complex MongoDB queries and updates.

### Frontend
- **React.js**: For building the user interface.
- **Bootstrap**: For responsive and styled components.
- **Axios**: For making API requests.
- **React Player**: For embedding and playing YouTube trailers.

---

## Database Design

The database consists of the following collections:

### `Movie`
- **id** (Primary Key): Unique identifier for each movie.
- **imdbId**: Unique identifier from IMDb.
- **title**: Movie title.
- **releaseDate**: Release date of the movie.
- **trailerLink**: Link to the movie trailer.
- **poster**: URL to the movie poster.
- **genres**: List of genres associated with the movie.
- **backdrops**: List of backdrop images for the movie.
- **reviewIds**: List of references to reviews.

### `Review`
- **id** (Primary Key): Unique identifier for each review.
- **body**: The content of the review.
- **imdbId**: Reference to the movie being reviewed.

---

## Application Architecture

The application is divided into three layers:
1. **Data Layer**: Handles database operations using MongoDB and Spring Data.
2. **Business Layer**: Contains logic for managing movies and reviews.
3. **UI Layer**: Provides an intuitive interface for interacting with the application.

---

## Future Enhancements

- **User Authentication**: Implement user authentication and role-based access control.
- **Watchlist**: Allow users to add movies to a personal watchlist.
- **Advanced Filtering**: Add advanced filtering options for movies (e.g., by genre, release year).
- **Enhanced UI**: Improve the user interface for better accessibility and user experience.
- **Rating System**: Implement a rating system for movies.   