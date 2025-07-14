import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, useLocation, Navigate } from 'react-router-dom';
import Header from './components/Header';
import Login from './components/login';
import SignUp from './components/signup';
import LogOut from './components/Logout';
import Home from './pages/Home';
import AdminDashboard from './pages/AdminDashboard';

import './App.css';

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
      const response = await fetch('http://localhost:8081/user/current', {
        method: 'GET',
        credentials: 'include'
      });

      if (response.ok) {
        const userInfo = await response.json();
        
        const typeResponse = await fetch('http://localhost:8081/user/type', {
          method: 'GET',
          credentials: 'include'
        });

        let userType = "user";
        if (typeResponse.ok) {
          const type = await typeResponse.text();
          userType = type ? type.replace(/['"]/g, '').trim() : "user";
        }

        setUser({
          username: userInfo.username,
          email: userInfo.email,
          type: userType
        });
        
        console.log('Auth check - User set:', {
          username: userInfo.username,
          email: userInfo.email,
          type: userType
        });
      } else if (response.status === 401) {
        setUser(null);
      } else {
        console.error('Unexpected response from /current:', response.status);
      }
    } catch (error) {
      console.error('Error checking auth status:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleLoginSuccess = async (userData) => {
    console.log('Login success - User data:', userData);
    const cleanedUserData = {
      ...userData,
      type: userData.type ? userData.type.replace(/['"]/g, '').trim() : userData.type
    };
    console.log('Login success - Cleaned user data:', cleanedUserData);
    setUser(cleanedUserData);
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
        <Route path="/" element={<Home searchTerm={searchTerm} onSearch={setSearchTerm} currentUser={user} />} />
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