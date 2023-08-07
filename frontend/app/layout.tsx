import "./globals.css";
import type { Metadata } from "next";

export const metadata: Metadata = {
  title: "Econovation Recruit",
  description: "Recruit site for econovation",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="ko">
      <body className="max-w-[1920px] m-auto">{children}</body>
    </html>
  );
}
