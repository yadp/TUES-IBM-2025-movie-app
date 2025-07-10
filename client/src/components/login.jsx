import React, { useState } from "react";

export default function Login() {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch("http://localhost:8081/user/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      credentials: "include", 
      body: JSON.stringify(formData),
    })
      .then((res) => {
        if (res.ok) {
          alert("Login successful!");
          window.location.href = "/";
        } else {
          return res.text().then(text => {
            throw new Error(text || "Invalid credentials");
          });
        }
      })
      .catch((err) => {
        console.error(err);
        alert(err.message || "Server error");
      });
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