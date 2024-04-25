package com.turysbay.UserPortalRestApp.service;

import com.turysbay.UserPortalRestApp.entity.News;
import com.turysbay.UserPortalRestApp.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public News createNews(News news){
        return newsRepository.save(news);
    }

    public List<News> getAllNews(){
        return newsRepository.findAll();
    }

    public News getNewsById(Long id){
        return newsRepository.getNewsById(id);
    }

    public void updateNewsById(News news) {
        newsRepository.updateNews(news.getTitle(), news.getContent(), news.getId());
    }

    public void deleteNewsById(Long id){
        newsRepository.deleteById(id);
    }

    public List<News> getNewsByUserId(Long userId){
        return newsRepository.findByCreatedBy(userId);
    }
}
