import { Flex, Layout } from "antd";

const contentStyle = {
  width: "100%",
  minHeight: 120,
  color: "black",
  // backgroundColor: "#0958d9",
};

export default function AppContent() {
  return (
    <Layout.Content style={contentStyle}>
      Content
    </Layout.Content>
  );
}
