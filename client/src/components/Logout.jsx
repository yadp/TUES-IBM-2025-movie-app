import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function LogOut({ onLogout }) {
  const navigate = useNavigate();

  useEffect(() => {
    const performLogout = async () => {
      try {
        const logoutResponse = await fetch("http://localhost:8081/user/logout", {
          method: "GET",
          headers: {
            Authorization: "Basic " + btoa("test:test"),
          },
          credentials: "include",
        });

        console.log("Logout response:", logoutResponse);

        const currentUserResponse = await fetch("http://localhost:8081/user/current", {
          method: "GET",
          credentials: "include",
        });

        if (currentUserResponse.status === 401) {
          alert("Logged out successfully.");
          if (onLogout) {
            onLogout();
          }
        } else if (currentUserResponse.ok) {
          throw new Error("Logout failed - user is still logged in");
        } else {
          throw new Error(`Error checking logout status (status ${currentUserResponse.status})`);
        }
      } catch (error) {
        console.error("Logout error:", error);
        alert(error.message || "Server error during logout");
        
        if (onLogout) {
          onLogout();
        }
      } finally {
        navigate("/");
      }
    };

    performLogout();
  }, [navigate, onLogout]);

  return (
    <div style={{ 
      display: 'flex', 
      justifyContent: 'center', 
      alignItems: 'center', 
      height: '100vh',
      backgroundColor: '#111',
      color: '#fff'
    }}>
      Logging out...
    </div>
  );
}