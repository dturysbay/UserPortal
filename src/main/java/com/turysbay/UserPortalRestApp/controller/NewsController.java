package com.turysbay.UserPortalRestApp.controller;

import com.turysbay.UserPortalRestApp.entity.News;
import com.turysbay.UserPortalRestApp.entity.User;
import com.turysbay.UserPortalRestApp.service.NewsService;
import com.turysbay.UserPortalRestApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {
    private final NewsService newsService;
    private final UserService userService;

    @Tag(name = "NEWS")
    @Operation(summary = "Create news")
    @PostMapping
    public ResponseEntity<String> createNews(@RequestBody News news){
        Optional<User> createdBy = userService.findByUserId(news.getCreatedBy());
        if (createdBy == null) {
            return ResponseEntity.badRequest().body("User with provided ID does not exist.");
        }
        News savedNews = newsService.createNews(news);
        if(savedNews!=null){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @Tag(name = "NEWS")
    @Operation(summary = "Get all news",
            description = "Get the list of all news")
    @GetMapping
    public ResponseEntity<List<News> >getAllNews(){
        return ResponseEntity.ok().body(newsService.getAllNews());
    }

    @Tag(name = "NEWS")
    @Operation(summary = "Get News by Id",
            description = "Get the news by id")
    @GetMapping("/{id}")
    public News getNewsById(@PathVariable Long id){
        return newsService.getNewsById(id);
    }

    @Tag(name = "NEWS")
    @Operation(summary = "Update News by Id")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNewsById(@PathVariable Long id,@RequestBody News news){
        news.setId(id);
        newsService.updateNewsById(news);
        return ResponseEntity.ok().build();
    }

    @Tag(name = "NEWS")
    @Operation(summary = "Delete News by Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNewsById(@PathVariable Long id){
        newsService.deleteNewsById(id);
        return ResponseEntity.ok().build();
    }

    @Tag(name = "NEWS")
    @Operation(summary = "Get News by Id of Creator")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<News>> getNewsByUserId(@PathVariable Long userId){
        List<News> news = newsService.getNewsByUserId(userId);
        return ResponseEntity.ok().body(news);
    }
}
