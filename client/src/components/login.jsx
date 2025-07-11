import React, { useState } from "react";

export default function Login({ onLoginSuccess }) {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      const response = await fetch("http://localhost:8081/user/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        credentials: "include", 
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        const [typeResponse, userResponse] = await Promise.all([
          fetch("http://localhost:8081/user/type", {
            method: "GET",
            credentials: "include"
          }),
          fetch("http://localhost:8081/user/current", {
            method: "GET",
            credentials: "include"
          })
        ]);

        let userType = "user"; 
        let userInfo = { username: formData.username, email: "" }; 

        if (typeResponse.ok) {
          const type = await typeResponse.text();
          // Clean up the user type - remove any extra quotes
          userType = type ? type.replace(/['"]/g, '').trim() : "user";
          console.log('Login - Original type:', type, 'Cleaned type:', userType);
        }

        if (userResponse.ok) {
          userInfo = await userResponse.json();
        }

        const userData = {
          username: userInfo.username,
          email: userInfo.email,
          type: userType
        };
        
        console.log('Login - Final user data:', userData);
        alert("Login successful!");
        
        if (onLoginSuccess) {
          onLoginSuccess(userData);
        }
        
        window.location.href = "/";
      } else {
        const text = await response.text();
        throw new Error(text || "Invalid credentials");
      }
    } catch (err) {
      console.error(err);
      alert(err.message || "Server error");
    }
  };

  return (
    <div style={styles.container}>
      <h2>Log In</h2>
      <form onSubmit={handleSubmit} style={styles.form}>
        <input
          type="text"
          name="username"
          placeholder="Username"
          value={formData.username}
          onChange={handleChange}
          required
          style={styles.input}
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleChange}
          required
          style={styles.input}
        />
        <button type="submit" style={styles.button}>Log In</button>
      </form>
      <div style={styles.link}>
        Don't have an account? <a href="/signup">Sign Up</a>
      </div>
    </div>
  );
}

const styles = {
  container: {
    background: "#333",
    color: "#eee",
    padding: "30px",
    borderRadius: "8px",
    width: "300px",
    margin: "50px auto",
    textAlign: "center",
    fontFamily: "Arial, sans-serif",
  },
  form: {
    display: "flex",
    flexDirection: "column",
  },
  input: {
    padding: "8px",
    margin: "8px 0",
    borderRadius: "4px",
    border: "none",
  },
  button: {
    padding: "10px",
    background: "#55aa55",
    color: "white",
    border: "none",
    borderRadius: "4px",
    cursor: "pointer",
  },
  link: {
    marginTop: "10px",
  },
};