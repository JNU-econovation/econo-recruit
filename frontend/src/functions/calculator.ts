export const getScoreAverage = (scores: number[]) => {
  const sum = scores.reduce((acc, cur) => acc + cur, 0);
  return sum / scores.length;
};

export const clamp = (value: number, min: number, max: number) => {
  return Math.min(Math.max(value, min), max);
};
