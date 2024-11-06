import { Layout, Button, Space } from "antd";
import { Link } from "react-router-dom";

const headerStyle = {
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
  color: "#fff",
  height: 60,
  paddingInline: 48,
  backgroundColor: "white",
  boxShadow: "0 2px 8px rgba(0, 0, 0, 0.15)", // Добавляем тень
  position: "relative", // Добавляем позиционирование
  zIndex: 1, // Устанавливаем z-index, чтобы тень была над контентом
};

const logoStyle = {
  color: "black",
  fontSize: "18px",
  fontWeight: "bold",
};

export default function AppHeader() {
  return (
    <Layout.Header style={headerStyle}>
      <div style={logoStyle}>
        <Link to="/">CollBox</Link>
      </div>
      <Space>
        <Link to="/login">
          <Button type="primary">Вход</Button>
        </Link>
        <Link to="/register">
          <Button type="primary">Регистрация</Button>
        </Link>
      </Space>
    </Layout.Header>
  );
}
