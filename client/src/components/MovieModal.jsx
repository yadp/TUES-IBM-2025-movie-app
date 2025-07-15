import React, { useState, useEffect } from 'react';
import { Star, Heart, Bookmark, Calendar, Clock } from 'lucide-react';

const MovieModal = ({ movie, isOpen, onClose, onToggleFavorite, onToggleWatchlist, isFavorite, isInWatchlist, currentUser }) => {
  const [newReview, setNewReview] = useState({ comment: '', rating: 5 });
  const [reviews, setReviews] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (movie && isOpen && currentUser) {
      fetchReviews();
    }
  }, [movie, isOpen, currentUser]);

const fetchReviews = async () => {
  try {
    const response = await fetch('http://localhost:8081/review/of-media', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + btoa('test:test'),
      },
      body: movie.title,
      credentials: 'include'
    });
      
      if (response.ok) {
        const backendReviews = await response.json();
        const transformedReviews = backendReviews.map(review => ({
          id: review.id,
          user: review.user.username,
          comment: review.contents,
          rating: review.rating,
          date: review.date
        }));
        setReviews(transformedReviews);
      }
    } catch (error) {
      console.error('Error fetching reviews:', error);
      setReviews(movie?.reviews || []);
    }
  };


  const handleAddReview = async () => {
    if (!newReview.comment.trim()) return;

    setLoading(true);
    try {
      const reviewResponse = await fetch('http://localhost:8081/review/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: 'Basic ' + btoa('test:test'),
        },
        body: JSON.stringify({
          title: movie.title,
          contents: newReview.comment,
          rating: newReview.rating
        }),
        credentials: 'include'
      });




      if (reviewResponse.ok) {
        await fetch('http://localhost:8081/watch-history/add', {
          method: 'POST',
          headers: {
            Authorization: 'Basic ' + btoa('test:test'),
          },
          body: movie.title,
          credentials: 'include'
        });

        await fetchReviews();
        
        setNewReview({ comment: '', rating: 5 });
      } else {
        const error = await response.text();
      }
    } catch (error) {
      console.error('Error adding review:', error);
      alert('Server error. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  const handleDeleteReview = async () => {
    if (!confirm('Are you sure you want to delete your review?')) return;

    try {
      const response = await fetch('http://localhost:8081/review/delete', {
        method: 'DELETE',
        headers: {
          Authorization: 'Basic ' + btoa('test:test'),
        },
        body: movie.title,
        credentials: 'include'
      });

      if (response.ok) {
        await fetchReviews();
      } else {
        const error = await response.text();
        alert(error || 'Failed to delete review');
      }
    } catch (error) {
      console.error('Error deleting review:', error);
      alert('Server error. Please try again.');
    }
  };


  const userHasReviewed = reviews.some(review => review.user === currentUser?.username);

  if (!isOpen || !movie) return null;

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        <button className="close-btn" onClick={onClose}>×</button>
        
        <div className="modal-header">
          <img src={"../../public/" + movie.title + ".jpg"} alt={movie.title} className="modal-poster" />
          <div className="modal-info">
            <h2 className="modal-title">{movie.title}</h2>
            <div className="modal-meta">
              <span className="rating">
                <Star className="star-icon" />
                {movie.averageRating}
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
          
          {currentUser && !userHasReviewed && (
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
                <button 
                  onClick={handleAddReview} 
                  className="submit-review-btn"
                  disabled={loading}
                >
                  {loading ? 'Submitting...' : 'Submit Review'}
                </button>
              </div>
            </div>
          )}
          
          

          {currentUser && userHasReviewed && (
            <div className="user-review-notice">
              <p>You have already reviewed this movie. 
                <button onClick={handleDeleteReview} className="delete-review-btn">
                  Delete your review
                </button>
              </p>
            </div>
          )}

          {!currentUser && (
            <div className="login-prompt">
              <p>Please <a href="/login">log in</a> to add a review.</p>
            </div>
          )}

          <div className="reviews-list">
            {reviews.map(review => (
              <div key={review.id} className="review-card">
                <div className="review-header">
                  <span className="review-user">{review.user}</span>
                  <span className="review-date">{review.date}</span>
                </div>
                
                <div className="review-stars">
                {(() => {
                  switch (review.rating) {
                    case 5:
                      return '★★★★★';
                    case 4:
                      return '★★★★☆';
                    case 3:
                      return '★★★☆☆';
                    case 2:
                      return '★★☆☆☆';
                    case 1:
                      return '★☆☆☆☆';
                    default:
                      return '☆☆☆☆☆';
                  }
                })()}
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