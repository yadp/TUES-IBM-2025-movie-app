import React, { useState } from 'react';
import { Search } from 'lucide-react';
import { Link } from 'react-router-dom'; // Add this import
import UserDropdown from './UserDropdown';

const Header = ({ onSearch, searchTerm, user, setUser }) => {
  console.log('Header - Current user:', user); // Debug log

  const handleLogout = async () => {
    try {
      const response = await fetch('http://localhost:8081/user/logout', {
        method: 'GET',
        headers: {
          Authorization: "Basic " + btoa("test:test"),
        },
      });

      console.log("Logout response:", response);
      if (response.ok) {
        alert('Logged out successfully.');
        setUser(null);
        window.location.href = '/';
      } else {
        const error = await response.text();
        throw new Error(error || 'Logout failed (status ' + response.status + ')');
      }
    } catch (error) {
      console.error('Error logging out:', error);
      alert(error.message || 'Server error');
    }
  };

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
            <>
              <UserDropdown user={user} onLogout={handleLogoutOnly} />
              <Link to="/logout" className="nav-link">LogOut</Link>
            </>
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