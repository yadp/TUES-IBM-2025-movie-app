import React, { useState } from "react";

export default function SignUp() {
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
    type: "user",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch("http://localhost:8081/user/signup", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formData),
    })
      .then((res) => {
        if (res.ok) {
          alert("Account created! Please log in.");
          window.location.href = "/login";
        } else {
          return res.text().then(text => {
            throw new Error(text || "Error creating account");
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
      <h2>Sign Up</h2>
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
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
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
        <select
          name="type"
          value={formData.type}
          onChange={handleChange}
          style={styles.input}
        >
          <option value="user">User</option>
          <option value="admin">Admin</option>
        </select>
        <button type="submit" style={styles.button}>Create Account</button>
      </form>
      <div style={styles.link}>
        Already have an account? <a href="/login">Log In</a>
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
    background: "#ff5555",
    color: "white",
    border: "none",
    borderRadius: "4px",
    cursor: "pointer",
  },
  link: {
    marginTop: "10px",
  },
};