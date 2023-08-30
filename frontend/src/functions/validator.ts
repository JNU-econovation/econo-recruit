const isCellPhoneNumber = (phoneNumber: string) =>
  /^\(?(\d{3})\)?[- ]?(\d{4})[- ]?(\d{4})$/.test(phoneNumber);

const isUndergradeNumber = (isUndergradeNumber: string) =>
  /^[0-9]{6}$/.test(isUndergradeNumber);

const isEmailString = (emailString: string) =>
  /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[A-Za-z]+$/.test(emailString);

const isConfirmationString = (confirmationString: string) =>
  /확인했습니다/.test(confirmationString);

export type ValidatorType =
  | "cellPhoneNumber"
  | "undergradeNumber"
  | "emailString"
  | "confirmationString";

export const validator = (value: string, type: ValidatorType) => {
  switch (type) {
    case "cellPhoneNumber":
      return isCellPhoneNumber(value);
    case "undergradeNumber":
      return isUndergradeNumber(value);
    case "emailString":
      return isEmailString(value);
    case "confirmationString":
      return isConfirmationString(value);
    default:
      return false;
  }
};
