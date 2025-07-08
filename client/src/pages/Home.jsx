import React, { useState } from 'react';
import Header from '../components/Header';
import FilterTabs from '../components/FilterTabs';
import MovieCard from '../components/MovieCard';
import MovieModal from '../components/MovieModal';
import { sampleMovies } from '../data/movieData';

const Movies = () => {
  const [movies, setMovies] = useState(sampleMovies);
  const [searchTerm, setSearchTerm] = useState('');
  const [activeFilter, setActiveFilter] = useState('all');
  const [selectedMovie, setSelectedMovie] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [favorites, setFavorites] = useState([]);
  const [watchlist, setWatchlist] = useState([]);
  const filteredMovies = movies.filter(movie => {
    const matchesSearch = movie.title.toLowerCase().includes(searchTerm.toLowerCase()) || movie.genre.toLowerCase().includes(searchTerm.toLowerCase()) || movie.cast.some(actor => actor.toLowerCase().includes(searchTerm.toLowerCase()));

    switch (activeFilter) {
      case 'movies':
        return matchesSearch && movie.type === 'movie';
      case 'series':
        return matchesSearch && movie.type === 'series';
      case 'favorites':
        return matchesSearch && favorites.includes(movie.id);
      case 'watchlist':
        return matchesSearch && watchlist.includes(movie.id);
      default:
        return matchesSearch;
    }
  });
  const handleMovieSelect = (movie) => {
    setSelectedMovie(movie);
    setIsModalOpen(true);
  };
  const handleToggleFavorite = (movieId) => {
    setFavorites(prev => 
      prev.includes(movieId) 
        ? prev.filter(id => id !== movieId)
        : [...prev, movieId]
    );
  };
  const handleToggleWatchlist = (movieId) => {
    setWatchlist(prev => 
      prev.includes(movieId) 
        ? prev.filter(id => id !== movieId)
        : [...prev, movieId]
    );
  };
  return (
    <div className="app">
      <Header onSearch={setSearchTerm} searchTerm={searchTerm} />

      <main className="main">
        <div className="container">
          <FilterTabs activeFilter={activeFilter} onFilterChange={setActiveFilter} />

          <div className="content-grid">
            {filteredMovies.map(movie => (
              <MovieCard
                key={movie.id}
                movie={movie}
                onSelect={handleMovieSelect}
                onToggleFavorite={handleToggleFavorite}
                onToggleWatchlist={handleToggleWatchlist}
                isFavorite={favorites.includes(movie.id)}
                isInWatchlist={watchlist.includes(movie.id)}
              />
            ))}
          </div>

          {filteredMovies.length === 0 && (
            <div className="no-results">
              <p>No movies or series found matching your criteria.</p>
            </div>
          )}
        </div>
      </main>
      <MovieModal
        movie={selectedMovie}
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onToggleFavorite={handleToggleFavorite}
        onToggleWatchlist={handleToggleWatchlist}
        isFavorite={selectedMovie && favorites.includes(selectedMovie.id)}
        isInWatchlist={selectedMovie && watchlist.includes(selectedMovie.id)}
      />
    </div>
  );
};
export default Movies;