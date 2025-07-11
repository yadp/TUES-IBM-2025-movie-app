import React, { useState, useEffect } from 'react';
import { Star, Heart, Bookmark, Calendar, Clock } from 'lucide-react';


const MovieModal = ({ movie, isOpen, onClose, onToggleFavorite, onToggleWatchlist, isFavorite, isInWatchlist }) => {
  const [newReview, setNewReview] = useState({ rating: 5, comment: '' });
  const [reviews, setReviews] = useState(movie?.reviews || []);

  useEffect(() => {
    if (movie) {
      setReviews(movie.reviews || []);
    }
  }, [movie]);

  const handleAddReview = () => {
    if (newReview.comment.trim()) {
      const review = {
        id: Date.now(),
        user: "CurrentUser",
        rating: newReview.rating,
        comment: newReview.comment,
        date: new Date().toISOString().split('T')[0]
      };
      setReviews([review, ...reviews]);
      setNewReview({ rating: 5, comment: '' });
    }
  };

  if (!isOpen || !movie) return null;

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        <button className="close-btn" onClick={onClose}>×</button>
        
        <div className="modal-header">
          <img src={movie.poster} alt={movie.title} className="modal-poster" />
          <div className="modal-info">
            <h2 className="modal-title">{movie.title}</h2>
            <div className="modal-meta">
              <span className="rating">
                <Star className="star-icon" />
                {movie.rating}
              </span>
              <span className="year">
                <Calendar className="icon" />
                {movie.year}
              </span>
              <span className="duration">
                <Clock className="icon" />
                {movie.duration}
              </span>
            </div>
            <p className="genre">{movie.genre}</p>
            <p className="director">Directed by {movie.director}</p>
            <p className="cast">Starring: {movie.cast.join(', ')}</p>
            
            <div className="modal-actions">
              <button
                className={`action-btn large ${isFavorite ? 'active' : ''}`}
                onClick={() => onToggleFavorite(movie.id)}
              >
                <Heart className={`icon ${isFavorite ? 'filled' : ''}`} />
                {isFavorite ? 'Remove from Favorites' : 'Add to Favorites'}
              </button>
              <button
                className={`action-btn large ${isInWatchlist ? 'active' : ''}`}
                onClick={() => onToggleWatchlist(movie.id)}
              >
                <Bookmark className={`icon ${isInWatchlist ? 'filled' : ''}`} />
                {isInWatchlist ? 'Remove from Watchlist' : 'Add to Watchlist'}
              </button>
            </div>
          </div>
        </div>

        <div className="modal-description">
          <h3>Plot</h3>
          <p>{movie.description}</p>
        </div>

        <div className="reviews-section">
          <h3>Reviews ({reviews.length})</h3>
          
          <div className="add-review">
            <h4>Add Your Review</h4>
            <div className="review-form">
              <div className="rating-input">
                <label>Rating:</label>
                <select
                  value={newReview.rating}
                  onChange={(e) => setNewReview({...newReview, rating: parseInt(e.target.value)})}
                >
                  <option value={5}>★★★★★ (5)</option>
                  <option value={4}>★★★★☆ (4)</option>
                  <option value={3}>★★★☆☆ (3)</option>
                  <option value={2}>★★☆☆☆ (2)</option>
                  <option value={1}>★☆☆☆☆ (1)</option>
                </select>
              </div>
              <textarea
                placeholder="Write your review..."
                value={newReview.comment}
                onChange={(e) => setNewReview({...newReview, comment: e.target.value})}
                className="review-textarea"
              />
              <button onClick={handleAddReview} className="submit-review-btn">
                Submit Review
              </button>
            </div>
          </div>

          <div className="reviews-list">
            {reviews.map(review => (
              <div key={review.id} className="review-card">
                <div className="review-header">
                  <span className="review-user">{review.user}</span>
                  <div className="review-rating">
                    {'★'.repeat(review.rating)}{'☆'.repeat(5 - review.rating)}
                  </div>
                  <span className="review-date">{review.date}</span>
                </div>
                <p className="review-comment">{review.comment}</p>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default MovieModal;