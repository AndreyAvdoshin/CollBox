import React, { createContext, useState, useContext, useEffect } from "react";
import axios from "axios";

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  //TODO Написать функцию для проверки аутентификации

  const login = async (email, password) => {
    setLoading(true);
    setError(null);

    try {
      const response = await axios.post(
        "/api/users/auth",
        { email, password },
        { withCredentials: true }
      );
      setUser(response.data.user);
      console.log(response.data.user);
      return true;
    } catch (error) {
      setError("Ошибка логина. Проверьте ваши данные.");
      return false;
    } finally {
      setLoading(false);
    }
  };

  const logout = async () => {
    setLoading(true);
    setError(null);

    try {
      await axios.post("/logout", {}, { withCredentials: true });
      setUser(null);
    } catch (error) {
      setError("Ошибка выхода");
    } finally {
      setLoading(false);
    }
  };

  const register = async (name, password, email) => {
    setLoading(true);
    setError(null);

    try {
      const response = await axios.post(
        "/api/users/register",
        { name, password, email },
        { withCredentials: true }
      );
      setUser(response.data.user);
      return true;
    } catch (error) {
      setError("Ошибка регистрации.");
      return false;
    } finally {
      setLoading(false);
    }
  };

  axios.interceptors.request.use(
    (config) => {
      return config;
    },
    (error) => Promise.reject(error)
  );

  // Понадобится для обновления токена
  /*axios.interceptors.response.use(
    (response) => response,
    async (error) => {
      const originalRequest = error.config;
      if (error.response.status === 401 && !originalRequest._retry) {
        originalRequest._retry = true;
        try {
          await axios.post("/api/refresh-token", {}, { withCredentials: true });
          return axios(originalRequest);
        } catch (refreshError) {
          // If refresh fails, logout the user
          logout();
          return Promise.reject(refreshError);
        }
      }
      return Promise.reject(error);
    }
  );*/

  // if (loading) {
  //   // Рендерим загрузку
  //   return <div>Загрузка...</div>;
  // }

  return (
    <AuthContext.Provider value={{ user, login, logout, register, error }}>
      {children}
      {error && <div style={{ color: "red" }}>{error}</div>}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
