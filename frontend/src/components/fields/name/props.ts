export interface NameFieldProps {
    defaultName: string;
    updateNameFields: (name: string, isNameValid: boolean) => void;
}