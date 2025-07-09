import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, useLocation } from "react-router-dom";
import SignUp from "./components/signup";
import Login from "./components/login";
import Header from "./components/Header";
import Home from './pages/Home';
import AdminDashboard from './pages/AdminDashboard';
import './App.css'

function AppContent() {
  const [searchTerm, setSearchTerm] = useState('');
  const location = useLocation();

  const hideHeader = ['/login', '/signup'].includes(location.pathname);

  return (
    <>
      {!hideHeader && <Header searchTerm={searchTerm} onSearch={setSearchTerm} />}
      <Routes>
        <Route path="/" element={<Home searchTerm={searchTerm} onSearch={setSearchTerm} />} />
        <Route path="/admin" element={<AdminDashboard />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
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
