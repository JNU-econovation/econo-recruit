"use client";

import { localStorage } from "@/src/functions/localstorage";
import { useRouter } from "next/navigation";
import { useEffect } from "react";

const Validate = () => {
  const router = useRouter();

  useEffect(() => {
    if (
      localStorage.get("accessToken", "") === "" ||
      localStorage.get("refreshToken", "") === ""
    ) {
      alert("로그인이 필요합니다.");
      router.replace("/signin");
      return;
    }
  }, []);
  return <></>;
};

export default Validate;
