import React, { useState, useEffect, useRef } from 'react';
import { User, ChevronDown, History, Clock, Lock, Edit, LogOut } from 'lucide-react';

const UserDropdown = ({ user, onLogout }) => {
  const [isOpen, setIsOpen] = useState(false);
  const [showChangePassword, setShowChangePassword] = useState(false);
  const [showChangeUsername, setShowChangeUsername] = useState(false);
  const [showReviewHistory, setShowReviewHistory] = useState(false);
  const [showWatchHistory, setShowWatchHistory] = useState(false);
  const [newPassword, setNewPassword] = useState('');
  const [newUsername, setNewUsername] = useState('');
  const [userReviews, setUserReviews] = useState([]);
  const [watchHistory, setWatchHistory] = useState([]);
  const dropdownRef = useRef(null);

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsOpen(false);
      }
    };
    document.addEventListener('mousedown', handleClickOutside);
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, []);

  useEffect(() => {
    if (user) {
      fetchUserReviews();
      fetchWatchHistory();
    }
  }, [user]);

  const fetchUserReviews = async () => {
    try {
      const mockReviews = [
        { id: 1, movieTitle: "The Dark Knight", rating: 5, comment: "Amazing movie!", date: "2024-01-15" },
        { id: 2, movieTitle: "Inception", rating: 4, comment: "Mind-bending!", date: "2024-01-10" }
      ];
      setUserReviews(mockReviews);
    } catch (error) {
      console.error('Error fetching user reviews:', error);
    }
  };

  const fetchWatchHistory = async () => {
    try {
      const mockHistory = [
        { id: 1, title: "The Dark Knight", watchedAt: "2024-01-15", type: "movie" },
        { id: 2, title: "Breaking Bad", watchedAt: "2024-01-12", type: "series" }
      ];
      setWatchHistory(mockHistory);
    } catch (error) {
      console.error('Error fetching watch history:', error);
    }
  };

  const handleChangePassword = async () => {
    if (!newPassword.trim()) {
      alert('Please enter a new password');
      return;
    }
    try {
      const response = await fetch('http://localhost:8081/user/pass', {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newPassword),
        credentials: 'include'
      });

      if (response.ok) {
        alert('Password changed successfully!');
        setNewPassword('');
        setShowChangePassword(false);
      } else {
        const error = await response.text();
        alert(error || 'Failed to change password');
      }
    } catch (error) {
      console.error('Error changing password:', error);
      alert('Server error. Please try again.');
    }
  };

  const handleChangeUsername = async () => {
    if (!newUsername.trim()) {
      alert('Please enter a new username');
      return;
    }
    try {
      const response = await fetch('http://localhost:8081/user/name', {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newUsername),
        credentials: 'include'
      });

      if (response.ok) {
        alert('Username changed successfully! Please log in again.');
        setNewUsername('');
        setShowChangeUsername(false);
        onLogout();
      } else {
        const error = await response.text();
        alert(error || 'Failed to change username');
      }
    } catch (error) {
      console.error('Error changing username:', error);
      alert('Server error. Please try again.');
    }
  };

  return (
    <div className="user-dropdown" ref={dropdownRef}>
      <button onClick={() => setIsOpen(!isOpen)} className="user-dropdown-trigger">
        <User size={20} />
        <span>{user.username}</span>
        <ChevronDown size={16} className={`dropdown-arrow ${isOpen ? 'open' : ''}`} />
      </button>

      {isOpen && (
        <div className="user-dropdown-menu">
          <div className="user-info">
            <div className="user-avatar"><User size={20} /></div>
            <div className="user-details">
              <p className="username">{user.username}</p>
              <p className="email">{user.email}</p>
            </div>
          </div>

          <div className="dropdown-items">
            <button onClick={() => setShowReviewHistory(!showReviewHistory)} className="dropdown-item">
              <History size={16} /><span>Review History</span>
            </button>
            {showReviewHistory && (
              <div className="dropdown-submenu">
                <h4>Your Reviews</h4>
                {userReviews.length > 0 ? (
                  <div className="reviews-list">
                    {userReviews.map(review => (
                      <div key={review.id} className="review-item">
                        <div className="review-title">{review.movieTitle}</div>
                        <div className="review-rating">Rating: {review.rating}/5</div>
                        <div className="review-date">{review.date}</div>
                      </div>
                    ))}
                  </div>
                ) : <p className="no-data">No reviews yet</p>}
              </div>
            )}

            <button onClick={() => setShowWatchHistory(!showWatchHistory)} className="dropdown-item">
              <Clock size={16} /><span>Watch History</span>
            </button>
            {showWatchHistory && (
              <div className="dropdown-submenu">
                <h4>Recently Watched</h4>
                {watchHistory.length > 0 ? (
                  <div className="watch-list">
                    {watchHistory.map(item => (
                      <div key={item.id} className="watch-item">
                        <div className="watch-title">{item.title}</div>
                        <div className="watch-date">{item.watchedAt}</div>
                      </div>
                    ))}
                  </div>
                ) : <p className="no-data">No watch history yet</p>}
              </div>
            )}

            <button onClick={() => setShowChangePassword(!showChangePassword)} className="dropdown-item">
              <Lock size={16} /><span>Change Password</span>
            </button>
            {showChangePassword && (
              <div className="dropdown-submenu">
                <input type="password" placeholder="New Password" value={newPassword}
                  onChange={(e) => setNewPassword(e.target.value)} className="change-input" />
                <div className="change-buttons">
                  <button onClick={handleChangePassword} className="btn-primary">Update</button>
                  <button onClick={() => { setShowChangePassword(false); setNewPassword(''); }} className="btn-secondary">Cancel</button>
                </div>
              </div>
            )}

            <button onClick={() => setShowChangeUsername(!showChangeUsername)} className="dropdown-item">
              <Edit size={16} /><span>Change Username</span>
            </button>
            {showChangeUsername && (
              <div className="dropdown-submenu">
                <input type="text" placeholder="New Username" value={newUsername}
                  onChange={(e) => setNewUsername(e.target.value)} className="change-input" />
                <div className="change-buttons">
                  <button onClick={handleChangeUsername} className="btn-primary">Update</button>
                  <button onClick={() => { setShowChangeUsername(false); setNewUsername(''); }} className="btn-secondary">Cancel</button>
                </div>
              </div>
            )}

            <div className="dropdown-divider"></div>
            <button onClick={onLogout} className="dropdown-item logout-item">
              <LogOut size={16} /><span>Logout</span>
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default UserDropdown;
