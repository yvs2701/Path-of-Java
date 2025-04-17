import { Navigate, Route, Routes } from "react-router-dom";
import Screen1 from "./pages/Screen1";
import Screen2 from "./pages/Screen2";

function Router() {
  return (
    <Routes>
      <Route path="/screen1" element={<Screen1 />} />
      <Route path="/screen2" element={<Screen2 />} />
      <Route path="/*" element={<Navigate to={"/screen1"} />} />
    </Routes>
  );
}

export default Router;
