export const getScoreAverage = (scores: number[]) => {
  const sum = scores.reduce((acc, cur) => acc + cur, 0);
  return sum / scores.length;
};
