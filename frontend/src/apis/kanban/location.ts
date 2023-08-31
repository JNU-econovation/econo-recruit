import { https } from "@/src/functions/axios";

interface LocationReq {
  boardId: number;
  targetBoardId: number;
}

export const postLocations = async (body: LocationReq) => {
  const { data } = await https.post("/boards/locations", body);

  return data;
};
