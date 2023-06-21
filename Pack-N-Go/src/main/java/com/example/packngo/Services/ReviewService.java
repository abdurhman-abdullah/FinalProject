package com.example.packngo.Services;

import com.example.packngo.Exceptions.ApiException;
import com.example.packngo.Models.Review;
import com.example.packngo.Repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> getAll(){
        return reviewRepository.findAll();
    }

    public void add(Review review){
        reviewRepository.save(review);
    }

    public void update(int id, Review review){
        Review reviewFind = reviewRepository.findById(id).
                orElseThrow(()-> new ApiException("id not found"));

        reviewFind.setReview(review.getReview());
        reviewRepository.save(reviewFind);
    }

    public void delete(int id){
        Review reviewFind = reviewRepository.findById(id).
                orElseThrow(()-> new ApiException("id not found"));

        reviewRepository.delete(reviewFind);
    }
}
