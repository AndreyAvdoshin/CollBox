import React from "react";
import { Form, Input, Button, Typography } from "antd";
import { UserOutlined, LockOutlined, MailOutlined} from "@ant-design/icons";

const { Title } = Typography;

const containerStyle = {
  width: "100%",
  maxWidth: 300,
  padding: "20px",
};

export default function RegisterPage() {
  const onFinish = (values) => {
    console.log("Received values of form: ", values);
    // Здесь будет логика отправки данных регистрации на сервер
  };

  return (
    <div style={containerStyle}>
      <Title level={3} style={{ textAlign: "center" }}>
        Регистрация
      </Title>
      <Form
        name="register"
        initialValues={{ remember: true }}
        onFinish={onFinish}
      >
        <Form.Item
          name="username"
          rules={[{ required: true, message: "Пожалуйста, введите имя пользователя!" }]}
        >
          <Input prefix={<UserOutlined />} placeholder="Имя пользователя" />
        </Form.Item>
        <Form.Item
          name="email"
          rules={[
            { required: true, message: "Пожалуйста, введите email!" },
            { type: "email", message: "Пожалуйста, введите корректный email!" }
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
            placeholder="Пароль"
          />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit" style={{ width: "100%" }}>
            Зарегистрироваться
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
}