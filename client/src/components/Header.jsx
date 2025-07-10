import React, { useEffect, useState } from 'react';
import { Search } from 'lucide-react';
import { useNavigate } from 'react-router-dom';

const Header = ({ onSearch, searchTerm }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const logged = localStorage.getItem("loggedIn") === "true";
    setIsLoggedIn(logged);
  }, []);

  const handleLogout = () => {
    navigate("/logout"); // Go to Logout component route
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
          <a href="/admin" className="nav-link">Admin</a>
          {!isLoggedIn && (
            <>
              <a href="/login" className="nav-link">Login</a>
              <a href="/signup" className="nav-link">SignUp</a>
            </>
          )}
          {isLoggedIn && (
            <button onClick={handleLogout} className="nav-link" style={{ background: 'none', border: 'none', cursor: 'pointer', color: 'inherit' }}>
              Logout
            </button>
          )}
        </nav>
      </div>
    </header>
  );
};

export default Header;
