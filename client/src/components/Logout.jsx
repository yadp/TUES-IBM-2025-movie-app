import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Logout = () => {
  const navigate = useNavigate();

  useEffect(() => {
    fetch("http://localhost:8081/user/logout", {
      method: "POST",
      credentials: "include", // Required for Spring Boot session handling
    })
      .then((res) => {
        if (res.ok) {
          localStorage.removeItem("loggedIn");
          alert("Logged out successfully!");
        } else {
          return res.text().then(text => {
            throw new Error(text || "Logout failed");
          });
        }
      })
      .catch((err) => {
        console.error("Logout error:", err);
        alert(err.message || "Logout error");
      })
      .finally(() => {
        navigate("/login");
      });
  }, [navigate]);

  return <div>Logging out...</div>;
};

export default Logout;
