import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, useLocation, Navigate } from 'react-router-dom';
import Header from './components/Header';
import Login from './components/login';
import SignUp from './components/signup';
import LogOut from './components/Logout';
import Home from './pages/Home';
import AdminDashboard from './pages/AdminDashboard';

import './App.css';

// Protected Route Component for Admin
function ProtectedAdminRoute({ children, user }) {
  if (!user) {
    return <Navigate to="/login" replace />;
  }
  
  if (user.type !== 'admin') {
    return (
      <div style={{ 
        padding: '50px', 
        textAlign: 'center', 
        color: '#fff', 
        backgroundColor: '#333' 
      }}>
        <h2>Access Denied</h2>
        <p>You don't have permission to access this page.</p>
        <p>Only administrators can access the admin dashboard.</p>
      </div>
    );
  }
  
  return children;
}

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
          // Clean up the user type - remove any extra quotes
          const cleanUserType = userType.replace(/['"]/g, '').trim();
          
          const userInfoResponse = await fetch('http://localhost:8081/user/current', {
            method: 'GET',
            credentials: 'include'
          });

          if (userInfoResponse.ok) {
            const userInfo = await userInfoResponse.json();
            setUser({
              username: userInfo.username,
              email: userInfo.email,
              type: cleanUserType
            });
            console.log('Auth check - User set:', {
              username: userInfo.username,
              email: userInfo.email,
              type: cleanUserType,
              originalType: userType
            });
          } else {
            setUser({
              username: 'User',
              email: '',
              type: cleanUserType
            });
            console.log('Auth check - User set with default username:', {
              username: 'User',
              email: '',
              type: cleanUserType,
              originalType: userType
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
    console.log('Login success - User data:', userData);
    // Clean up the user type in case it comes with extra quotes
    const cleanedUserData = {
      ...userData,
      type: userData.type ? userData.type.replace(/['"]/g, '').trim() : userData.type
    };
    console.log('Login success - Cleaned user data:', cleanedUserData);
    setUser(cleanedUserData);
    // Optionally refresh auth status from backend to ensure consistency
    await checkAuthStatus();
  };

  const handleLogout = () => {
    setUser(null);
  };

  if (loading) {
    return <div style={{ 
      display: 'flex', 
      justifyContent: 'center', 
      alignItems: 'center', 
      height: '100vh',
      backgroundColor: '#111',
      color: '#fff'
    }}>
      Loading...
    </div>;
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
        <Route 
          path="/admin" 
          element={
            <ProtectedAdminRoute user={user}>
              <AdminDashboard />
            </ProtectedAdminRoute>
          } 
        />
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