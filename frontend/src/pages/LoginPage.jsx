import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { Form, Input, Button, Typography, message } from "antd";
import { UserOutlined, LockOutlined, MailOutlined } from "@ant-design/icons";
import { useAuth } from "../context/AuthContext";

const { Title } = Typography;

const containerStyle = {
  width: "100%",
  maxWidth: 300,
  height: "100%",
  padding: "20px",
};

export default function LoginPage() {
  const { login } = useAuth();
  const navigate = useNavigate();

  const onFinish = async (values) => {
    const response = await login(values.email, values.password);
    if (response) {
      message.success("Вход выполнен успешно");
      navigate("/");
    } else {
      message.error("Ошибка входа");
    }
  };

  return (
    <div style={containerStyle}>
      <Title level={3} style={{ textAlign: "center" }}>
        Вход
      </Title>
      <Form
        name="normal_login"
        initialValues={{ remember: true }}
        onFinish={onFinish}
      >
        {/* <Form.Item
            name="uname"
            rules={[
              {
                required: true,
                message: "Пожалуйста, введите имя пользователя!",
              },
            ]}
          >
            <Input prefix={<UserOutlined />} placeholder="Имя пользователя" />
          </Form.Item> */}
        <Form.Item
          name="email"
          rules={[
            { required: true, message: "Пожалуйста, введите email!" },
            { type: "email", message: "Пожалуйста, введите корректный email!" },
          ]}
        >
          <Input prefix={<MailOutlined />} placeholder="Email" />
        </Form.Item>

        <Form.Item
          name="password"
          rules={[{ required: true, message: "Пожалуйста, введите пароль!" }]}
        >
          <Input.Password
            prefix={<LockOutlined />}
            type="password"
            placeholder="Пароль"
          />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit" style={{ width: "100%" }}>
            Войти
          </Button>
          Или <Link to="/register">зарегистрироваться сейчас!</Link>
        </Form.Item>
      </Form>
    </div>
  );
}
