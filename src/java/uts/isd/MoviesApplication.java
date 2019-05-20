package uts.isd;

import uts.isd.model.Movies;
import uts.isd.model.User;
import java.io.*;

public class MoviesApplication implements Serializable {
    
    private Movies movies;

    public MoviesApplication() {
    }
    
    public MoviesApplication(Movies movies) {
        super();
        this.movies = movies;
    }
    
    public Movies getMovies() {
        return movies;
    }
    
    public boolean emailAlreadyExists(String email) {
        // TODO: Query DB to see if email already exists
        return false;
    }
    
    public void registerUser(User user) {
        // TODO: Add User to DB
    }

    // NOTES:
    //
    // Create Customer superclass?
    // 1. User
    // 2. Admin
    
}
