import React from 'react';
import { Star, Heart, Bookmark, Play } from 'lucide-react';


const MovieCard = ({ movie, onSelect, onToggleFavorite, onToggleWatchlist, isFavorite, isInWatchlist }) => {
  return (
    <div className="movie-card" onClick={() => onSelect(movie)}>
      <div className="movie-poster">
        <img src={movie.poster} alt={movie.title} />
        <div className="movie-overlay">
          <Play className="play-icon" />
        </div>
        <div className="movie-actions">
          <button
            className={`action-btn ${isFavorite ? 'active' : ''}`}
            onClick={(e) => {
              e.stopPropagation();
              onToggleFavorite(movie.id);
            }}
          >
            <Heart className={`icon ${isFavorite ? 'filled' : ''}`} />
          </button>
          <button
            className={`action-btn ${isInWatchlist ? 'active' : ''}`}
            onClick={(e) => {
              e.stopPropagation();
              onToggleWatchlist(movie.id);
            }}
          >
            <Bookmark className={`icon ${isInWatchlist ? 'filled' : ''}`} />
          </button>
        </div>
      </div>
      <div className="movie-info">
        <h3 className="movie-title">{movie.title}</h3>
        <div className="movie-meta">
          <span className="movie-year">{movie.year}</span>
          <span className="movie-type">{movie.type}</span>
          <div className="movie-rating">
            <Star className="star-icon" />
            <span>{movie.rating}</span>
          </div>
        </div>
        <p className="movie-genre">{movie.genre}</p>
      </div>
    </div>
  );
};

export default MovieCard;