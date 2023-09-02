import { https } from "@/src/functions/axios";
import { localStorage } from "@/src/functions/localstorage";

interface SignUpRes {
  accessToken: "string";
  refreshToken: "string";
}

export const signIn = async ({ email = "", password = "" }) => {
  try {
    const { data } = await https.post<SignUpRes>("/login", { email, password });
    if (data satisfies SignUpRes) {
      alert("로그인이 실패하였습니다");
    }

    localStorage.set("accessToken", data.accessToken);
    localStorage.set("refreshToken", data.refreshToken);
    return true;
  } catch (error) {
    return false;
  }
};

export const signUp = async ({
  name = "",
  year = 0,
  email = "",
  password = "",
}) => {
  try {
    const { data } = await https.post<string>("/signup", {
      name,
      year,
      email,
      password,
    });
    return true;
  } catch (error) {
    return false;
  }
};
