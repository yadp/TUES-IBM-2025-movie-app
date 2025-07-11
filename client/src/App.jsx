import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, useLocation } from 'react-router-dom';
import Header from './components/Header';
import Login from './components/login';
import SignUp from './components/signup';
import LogOut from './components/Logout';
import Home from './pages/Home';
import AdminDashboard from './pages/AdminDashboard';

import './App.css';

function AppContent() {
  const [user, setUser] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [loading, setLoading] = useState(true);
  const location = useLocation();

  const hideHeader = ['/login', '/signup'].includes(location.pathname);

  useEffect(() => {
    checkAuthStatus();
  }, []);

  const checkAuthStatus = async () => {
    try {
      const response = await fetch('http://localhost:8081/user/type', {
        method: 'GET',
        credentials: 'include'
      });

      if (response.ok) {
        const userType = await response.text();

        if (userType) {
          const userInfoResponse = await fetch('http://localhost:8081/user/current', {
            method: 'GET',
            credentials: 'include'
          });

          if (userInfoResponse.ok) {
            const userInfo = await userInfoResponse.json();
            setUser({
              username: userInfo.username,
              email: userInfo.email,
              type: userType
            });
          } else {
            setUser({
              username: 'User',
              email: '',
              type: userType
            });
          }
        }
      }
    } catch (error) {
      console.error('Error checking auth status:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleLoginSuccess = async (userData) => {
    setUser(userData);
    // Optionally refresh auth status from backend to ensure consistency
    await checkAuthStatus();
  };

  const handleLogout = () => {
    setUser(null);
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <>
      {!hideHeader && (
        <Header
          searchTerm={searchTerm}
          onSearch={setSearchTerm}
          user={user}
          setUser={setUser}
        />
      )}
      <Routes>
        <Route path="/" element={<Home searchTerm={searchTerm} onSearch={setSearchTerm} />} />
        <Route path="/admin" element={<AdminDashboard />} />
        <Route path="/login" element={<Login onLoginSuccess={handleLoginSuccess} />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/logout" element={<LogOut onLogout={handleLogout} />} />
      </Routes>
    </>
  );
}

export default function App() {
  return (
    <Router>
      <AppContent />
    </Router>
  );
}