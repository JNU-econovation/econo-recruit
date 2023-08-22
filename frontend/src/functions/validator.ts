const isCellPhoneNumber = (phoneNumber: string) =>
  /^\(?(\d{3})\)?[- ]?(\d{4})[- ]?(\d{4})$/.test(phoneNumber);

const isUndergradeNumber = (isUndergradeNumber: string) =>
  /^[0-9]{6}$/.test(isUndergradeNumber);

export type ValidatorType = "cellPhoneNumber" | "undergradeNumber";

export const validator = (value: string, type: ValidatorType) => {
  switch (type) {
    case "cellPhoneNumber":
      return isCellPhoneNumber(value);
    case "undergradeNumber":
      return isUndergradeNumber(value);
    default:
      return false;
  }
};
