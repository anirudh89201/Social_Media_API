package com.rest_service.REST_API.Posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PostCommandRunner implements CommandLineRunner {
    @Autowired
    private PostRepository postRepository;

    public PostCommandRunner(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        postRepository.save(new PostModel(1,"Hello World...",1001));
//        postRepository.save(new PostModel(2,"I am Anirudh...",1001));
//        postRepository.save(new PostModel(3,"I am Anirudh Not Him...",1001));
    }
}
