import React, { useState } from 'react';
import { sampleMovies } from '../data/movieData';

const AdminDashboard = () => {
  const [movies, setMovies] = useState(sampleMovies);
  const [form, setForm] = useState({
    title: '',
    genre: '',
    type: 'movie',
    cast: '',
    poster: '',
    year: '',
    duration: '',
    rating: '',
    description: '',
    director: ''
  });
  const [editIndex, setEditIndex] = useState(null);

  const handleInputChange = (e) => {
    const { name, value, type } = e.target;
    setForm({
      ...form,
      [name]: type === 'number' ? (value === '' ? '' : Number(value)) : value
    });
  };

  const handleAddOrUpdate = () => {
    if (!form.title || !form.genre || !form.cast) return;

    const clampedRating = Math.min(Number(form.rating), 5);

    const movieData = {
      ...form,
      id: editIndex !== null ? movies[editIndex].id : Date.now(),
      year: Number(form.year),
      duration: Number(form.duration),
      rating: clampedRating,
      cast: form.cast.split(',').map(c => c.trim()),
      reviews: form.reviews || []
    };

    if (editIndex !== null) {
      const updated = [...movies];
      updated[editIndex] = movieData;
      setMovies(updated);
      setEditIndex(null);
    } else {
      setMovies([...movies, movieData]);
    }

    setForm({
      title: '',
      genre: '',
      type: 'movie',
      cast: '',
      poster: '',
      year: '',
      duration: '',
      rating: '',
      description: '',
      director: ''
    });
  };

  const handleEdit = (index) => {
    const movie = movies[index];
    setForm({
      ...movie,
      cast: movie.cast.join(', ')
    });
    setEditIndex(index);
  };

  const handleDelete = (index) => {
    const updated = [...movies];
    updated.splice(index, 1);
    setMovies(updated);
    setForm({
      title: '',
      genre: '',
      type: 'movie',
      cast: '',
      poster: '',
      year: '',
      duration: '',
      rating: '',
      description: '',
      director: ''
    });
    setEditIndex(null);
  };

  return (
    <div className="admin-dashboard">
      <h2>Admin Dashboard</h2>

      <div className="form">
        <input name="title" placeholder="Title" value={form.title} onChange={handleInputChange} />
        <input name="genre" placeholder="Genre" value={form.genre} onChange={handleInputChange} />
        <input name="year" type="number" placeholder="Year" value={form.year} onChange={handleInputChange} />
        <input name="duration" type="number" placeholder="Duration (minutes)" value={form.duration} onChange={handleInputChange} />
        <input
          name="rating"
          type="number"
          min="0"
          max="5"
          step="0.1"
          placeholder="Rating (0 to 5)"
          value={form.rating}
          onChange={handleInputChange}
        />
        <select name="type" value={form.type} onChange={handleInputChange}>
          <option value="movie">Movie</option>
          <option value="series">Series</option>
        </select>
        <input name="cast" placeholder="Cast (comma-separated)" value={form.cast} onChange={handleInputChange} />
        <input name="director" placeholder="Director" value={form.director} onChange={handleInputChange} />
        <input name="poster" placeholder="Poster URL" value={form.poster} onChange={handleInputChange} />
        <textarea name="description" placeholder="Description" value={form.description} onChange={handleInputChange} />

        <button onClick={handleAddOrUpdate}>
          {editIndex !== null ? 'Update Movie' : 'Add Movie'}
        </button>
      </div>

      <div className="movie-list">
        <h3>Movies</h3>
        {movies.length === 0 ? (
          <p>No movies available.</p>
        ) : (
          <ul>
            {movies.map((movie, index) => (
              <li key={movie.id}>
                <img src={movie.poster} alt={movie.title} />
                <div className="movie-info">
                  <h4>{movie.title} ({movie.year})</h4>
                  <p><strong>Genre:</strong> {movie.genre}</p>
                  <p><strong>Type:</strong> {movie.type}</p>
                  <p><strong>Rating:</strong> {movie.rating}</p>
                  <p><strong>Cast:</strong> {movie.cast.join(', ')}</p>
                  <p><strong>Director:</strong> {movie.director}</p>
                  <div className="actions">
                    <button onClick={() => handleEdit(index)}>Edit</button>
                    <button onClick={() => handleDelete(index)} className="delete">Delete</button>
                  </div>
                </div>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
};

export default AdminDashboard;
