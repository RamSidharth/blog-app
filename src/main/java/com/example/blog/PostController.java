package com.example.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
    
    @Autowired
    private PostRepository postRepository;
    
    // Show all posts
    @GetMapping("/")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "index";
    }
    
    // Form to create a new post
    @GetMapping("/posts/new")
    public String showNewPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "new-post";
    }
    
    // Save new post
    @PostMapping("/posts")
    public String savePost(@ModelAttribute Post post) {
        postRepository.save(post);
        return "redirect:/";
    }
    
    // Show a single post
    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        model.addAttribute("post", post);
        return "post-detail";
    }
}