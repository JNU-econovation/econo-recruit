export const getScoreAverage = (scores: number[]): number => {
  const sum = scores.reduce((acc, cur) => acc + cur, 0);
  return sum / scores.length;
};

export const clamp = (value: number, min: number, max: number): number => {
  return Math.min(Math.max(value, min), max);
};
