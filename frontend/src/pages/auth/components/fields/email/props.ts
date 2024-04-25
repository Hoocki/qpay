export interface EmailFieldProps {
    defaultEmail: string;
    updateEmailFields: (email: string, isEmailValid: boolean) => void;
}