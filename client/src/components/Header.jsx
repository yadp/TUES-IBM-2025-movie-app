import React from 'react';
import { Search } from 'lucide-react';


const Header = ({ onSearch, searchTerm }) => {
  return (
    <header className="header">
      <div className="container">
        <h1 className="logo">DKMDB</h1>
        <div className="search-container">
          <div className="search-bar">
            <Search className="search-icon" />
            <input
              type="text"
              placeholder="Search movies, TV shows, actors..."
              value={searchTerm}
              onChange={(e) => onSearch(e.target.value)}
              className="search-input"
            />
          </div>
        </div>
        <nav className="nav">
          <a href="#" className="nav-link">Movies</a>
          <a href="/login" className="nav-link">Login</a>
          <a href="/signup" className="nav-link">SignUp</a>
        </nav>
      </div>
    </header>
  );
};

export default Header;