const sqlite3 = require("sqlite3").verbose();
import { open } from "sqlite";

export const getDB = async () => {
  return await open({
    filename: "./database/database.db",
    driver: sqlite3.Database,
  });
};
