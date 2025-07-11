import React, { useState } from 'react';
import { Search } from 'lucide-react';
import UserDropdown from './UserDropdown';

const Header = ({ onSearch, searchTerm, user, setUser }) => {

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
          <a href="/" className="nav-link">Movies</a>
          {user && user.type === 'admin' && (
            <a href="/admin" className="nav-link">Admin</a>
          )}
          
          {user ? (
            <>
              <UserDropdown user={user} onLogout={handleLogoutOnly} />
              <a href="/logout" className="nav-link">LogOut</a>
            </>
          ) : (
            <>
              <a href="/login" className="nav-link">Login</a>
              <a href="/signup" className="nav-link">SignUp</a>
            </>
          )}
        </nav>
      </div>
    </header>
  );
};

export default Header;