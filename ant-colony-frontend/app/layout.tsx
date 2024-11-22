import type { Metadata } from "next";
import "./globals.css";



export const metadata: Metadata = {
  title: "Ant Colony Sim",
  description: "Sim created for Datastructures Final for CS degree",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>
        {children}
      </body>
    </html>
  );
}
