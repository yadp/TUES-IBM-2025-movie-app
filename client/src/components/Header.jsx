import React, { useState } from 'react';
import { Search } from 'lucide-react';
import { Link } from 'react-router-dom';
import UserDropdown from './UserDropdown';

const Header = ({ onSearch, searchTerm, user, setUser }) => {
  console.log('Header - Current user:', user);

  const handleLogoutOnly = () => {
    setUser(null);
  };

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
          <Link to="/" className="nav-link">Movies</Link>
          {user && user.type === 'admin' && (
            <Link to="/admin" className="nav-link">Admin</Link>
          )}
          
          {user ? (
            <UserDropdown user={user} onLogout={handleLogoutOnly} />
          ) : (
            <>
              <Link to="/login" className="nav-link">Login</Link>
              <Link to="/signup" className="nav-link">SignUp</Link>
            </>
          )}
        </nav>
      </div>
    </header>
  );
};

export default Header;