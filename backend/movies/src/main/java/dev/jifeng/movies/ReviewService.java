package dev.jifeng.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Review> singleMovieReviews(String imdbId) {
        return reviewRepository.findReviewsByImdbId(imdbId);
    }

    public Review createReview(String reviewBody, String imdbId) {
        //Review review = reviewRepository.insert(new Review(reviewBody));
        Review review = new Review(reviewBody, imdbId);
        reviewRepository.insert(review);
        //reviewRepository.insert(review);
        // The Fluent Update API
//        mongoTemplate.update(Movie.class)
//                .matching(Criteria.where("imdbId").is(imdbId))
//                .apply(new Update().push("reviewIds").value(review))
//                .first();

        // The "Classic" Approach (updateFirst) - easier for testing
        Query query = new Query(Criteria.where("imdbId").is(imdbId));
        Update update = new Update().push("reviewIds", review);

        mongoTemplate.updateFirst(query, update, Movie.class);

        return review;
    }
}
