import React from "react";
import { Link } from "react-router-dom";
import { Form, Input, Button, Typography } from "antd";
import { UserOutlined, LockOutlined } from "@ant-design/icons";

const { Title } = Typography;

const containerStyle = {
  width: "100%",
  maxWidth: 300,
  height: "100%",
  padding: "20px",
};

export default function LoginPage() {
  const onFinish = (values) => {
    console.log("Received values of form: ", values);
    // Здесь будет логика отправки данных на сервер
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
          <Form.Item
            name="username"
            rules={[
              {
                required: true,
                message: "Пожалуйста, введите имя пользователя!",
              },
            ]}
          >
            <Input prefix={<UserOutlined />} placeholder="Имя пользователя" />
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
