import {LOWER_AMOUNT_LIMIT, NAME_MIN_SIZE, PASSWORD_MIN_SIZE, UPPER_AMOUNT_LIMIT} from "./validation";
import {CURRENCY_USD} from "./currency";

export const ValidationErrorMessages = {
    EMAIL_NOT_VALID: "Please enter a valid email address",
    NAME_NOT_VALID: `Name must contain at least ${NAME_MIN_SIZE} symbols`,
    PASSWORD_NOT_VALID: `Password must contain at least ${PASSWORD_MIN_SIZE} symbols`,
    AMOUNT_NOT_VALID: `Amount must be between ${LOWER_AMOUNT_LIMIT}${CURRENCY_USD} and ${UPPER_AMOUNT_LIMIT}${CURRENCY_USD}`
}
