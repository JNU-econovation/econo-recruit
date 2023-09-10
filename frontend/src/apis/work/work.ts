import { https } from "@/src/functions/axios";

export const getWork = async (id: string) => {
  const response = await https.get(`/api/work/${id}`);
  return response.data;
};
