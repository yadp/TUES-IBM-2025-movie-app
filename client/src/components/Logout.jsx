import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function LogOut() {
  const navigate = useNavigate();

  useEffect(() => {
    fetch("http://localhost:8081/user/logout", {
      method: "GET",
      headers: {
        Authorization: "Basic " + btoa("test:test"),
      },
      credentials: "include", 
    })
      .then(async (res) => {
        let data = {};
        try {
          data = await res.json(); 
        } catch (e) {
          // If no JSON or empty body, just ignore error here
        }

        if (res.ok) {
          alert(data.message || "Logged out successfully.");
        } else {
          throw new Error(data.message || `Logout failed (status ${res.status})`);
        }
      })
      .catch((err) => {
        console.error(err);
        alert(err.message || "Server error");
      })
      .finally(() => {
        navigate("/");
      });
  }, [navigate]);

  return null;
}