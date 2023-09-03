const sqlite3 = require("sqlite3").verbose();

const db = new sqlite3.Database("./database/database.db", (err) => {
  if (err) {
    console.error(err.message);
  }
  console.log("Connected to the database.");
});

const applicant_sql = `CREATE TABLE IF NOT EXISTS applicant (id INTEGER PRIMARY KEY AUTOINCREMENT, applicant_data TEXT)`;

db.serialize(() => {
  db.run(applicant_sql);
});
