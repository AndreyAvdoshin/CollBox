import { Flex, Layout } from "antd";

const contentStyle = {
  width: "100%",
  minHeight: 120,
  color: "black",
  padding: "20px",
  // backgroundColor: "#0958d9",
};

export default function AppContent() {
  return (
    <Layout.Content style={contentStyle}>
      <h2>Content</h2>
    </Layout.Content>
  );
}
