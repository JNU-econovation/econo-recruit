import { Database } from "sqlite";
import { NextRequest, NextResponse } from "next/server";
import { getDB } from "@/database/accecer";

let db: Database;

export const POST = async (req: NextRequest) => {
  if (!db) {
    db = await getDB();
  }
  const body = await req.json();

  let queryString = "INSERT INTO applicant (applicant_data) VALUES(?)";
  await db.run(queryString, JSON.stringify(body));

  return NextResponse.json({ success: true, response: null, error: null });
};
