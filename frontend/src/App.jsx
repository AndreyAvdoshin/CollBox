//import { useState } from 'react';
import { Layout } from "antd";
import AppHeader from "./components/layout/AppHeader";
import AppContent from "./components/layout/AppContent";
import "./App.css";
import AppFooter from "./components/layout/AppFooter";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";


function App() {
  //const [count, setCount] = useState(0)

  return (
    <>
      <Router>
        <Layout
          style={{
            minHeight: "100vh",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
          }}
        >
          <AppHeader />
          <div
            style={{
              flex: 1,
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <Routes>
              <Route path="/" element={<AppContent />} />
              <Route path="/login" element={<LoginPage />} />
              <Route path="/register" element={<RegisterPage />} />
            </Routes>
          </div>
          <AppFooter />
        </Layout>
      </Router>
    </>
  );
}

export default App;
