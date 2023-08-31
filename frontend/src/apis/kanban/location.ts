import { https } from "@/src/functions/axios";

export const postLocations = async () => {
  const { data } = await https.post("/boards/locations");

  return data;
};
