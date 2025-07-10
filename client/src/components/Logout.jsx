import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function LogOut() {
  const navigate = useNavigate();

  useEffect(() => {
    fetch("http://localhost:8081/user/logout", {
      method: "GET",
      credentials: "include", 
    })
      .then((res) => {
        if (res.ok) {
          alert("Logged out successfully.");
        } else {
          return res.text().then((text) => {
            throw new Error(text || "Logout failed");
          });
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