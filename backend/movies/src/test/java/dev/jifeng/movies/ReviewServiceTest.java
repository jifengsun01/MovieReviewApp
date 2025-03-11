package dev.jifeng.movies;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSingleMovieReviews() {
        // given
        String imdbId = "tt1234567";
        Review review1 = new Review(new ObjectId(), "Great movie!", imdbId);
        Review review2 = new Review(new ObjectId(), "Not bad at all.", imdbId);
        List<Review> mockReviews = Arrays.asList(review1, review2);

        given(reviewRepository.findReviewsByImdbId(imdbId)).willReturn(mockReviews);

        // when
        List<Review> foundReviews = reviewService.singleMovieReviews(imdbId);

        // then
        assertThat(foundReviews).hasSize(2);
        assertThat(foundReviews.get(0).getBody()).isEqualTo("Great movie!");
        assertThat(foundReviews.get(1).getBody()).isEqualTo("Not bad at all.");
    }

    @Test
    void testCreateReview() {
        // given
        String imdbId = "tt9999999";
        String reviewBody = "A masterpiece.";
        Review reviewToInsert = new Review(reviewBody, imdbId);

        // The repository should return the saved review (can simply return same object).
        given(reviewRepository.insert(any(Review.class))).willReturn(reviewToInsert);

        // when
        Review createdReview = reviewService.createReview(reviewBody, imdbId);

        // then
        assertThat(createdReview.getBody()).isEqualTo(reviewBody);
        assertThat(createdReview.getImdbId()).isEqualTo(imdbId);

        // verify repository interaction
        verify(reviewRepository, times(1)).insert(any(Review.class));

        // verify that mongoTemplate.update(...) was called once
        verify(mongoTemplate, times(1))
                .updateFirst(any(Query.class), any(Update.class), eq(Movie.class));
    }
}
