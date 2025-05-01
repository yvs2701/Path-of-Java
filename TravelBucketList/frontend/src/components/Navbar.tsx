import { useState } from "react";
import { Link, useLocation } from "react-router-dom";
import "../styles/Navbar.scss";

const Navbar: React.FC = () => {
  const location = useLocation();
  const [activeNav, setActiveNav] = useState<string>(location.pathname);
  return (
    <nav>
      <Link
        to="/home"
        className={activeNav === "/home" ? "active-nav" : ""}
        onClick={() => setActiveNav("/home")}
      >
        Home
      </Link>
      <Link
        to="/new"
        className={activeNav === "/new" ? "active-nav" : ""}
        onClick={() => setActiveNav("/new")}
      >
        Add New Bucket
      </Link>
    </nav>
  );
};

export default Navbar;
