import { BrowserRouter, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import NewBucket from "./pages/NewBucket";

const App: React.FC = () => {
  //TODO: add more components to make the app more modular and easy to maintain
  //TODO: use SCSS variables for better management of the repeated values
  return (
    <BrowserRouter>
      <Navbar />
      <main>
        <Routes>
          <Route path="/home" element={<Home />} />
          <Route path="/new" element={<NewBucket />} />
        </Routes>
      </main>
    </BrowserRouter>
  );
};

export default App;
