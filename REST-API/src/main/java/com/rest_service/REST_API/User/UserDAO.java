package com.rest_service.REST_API.User;

import com.rest_service.REST_API.Exceptions.NotFoundException;
import com.rest_service.REST_API.Posts.PostModel;
import com.rest_service.REST_API.Posts.PostRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserDAO {

    // Autowire the repositories without static
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // Default constructor (not required, but can be kept if needed)
    public UserDAO() {
    }

    // Constructor injection (alternative to field injection)
    @Autowired
    public UserDAO(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // Method to fetch all users
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    // Method to find user by ID
    public UserModel findUserID(Integer id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        if (userModel.isPresent()) {
            return userModel.get();
        } else {
            throw new RuntimeException("User with ID " + id + " does not exist.");
        }
    }

    // Method to save user
    public boolean saveUser(UserModel userModel) {
        try {
            userRepository.save(userModel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete user by ID
    public ResponseEntity<?> deleteById(Integer id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 No Content
            } else {
                throw new NotFoundException("ID not found.");
            }
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);  // 404 Not Found
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting the record.", HttpStatus.INTERNAL_SERVER_ERROR);  // 500 Internal Server Error
        }
    }

    // Method to create posts
    public ResponseEntity<?> createPosts(PostModel posts) {
        try {
            PostModel post = postRepository.save(posts);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(post.getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating post.", HttpStatus.INTERNAL_SERVER_ERROR);  // 500 Internal Server Error
        }
    }
}
