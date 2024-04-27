import React, {useEffect, useState} from 'react';
import {Box, Button, Checkbox, FormControlLabel, Link, Radio, RadioGroup, Typography} from '@mui/material';
import {Paths} from "../../../common/constansts/paths";
import {Titles} from "../../../common/constansts/titles";
import "../../../common/styles/button.css";
import "./styles.css";
import "../styles.css";
import {useNavigate} from "react-router-dom";
import {Buttons} from "../../../common/constansts/buttons";
import {AuthContent} from "../../../common/constansts/authContent";
import {Roles} from "../../../common/constansts/roles";
import {signUpService} from "../../../services/user";
import AuthLogo from "../../../components/logo/AuthLogo";
import EmailField from "../../../components/fields/email/EmailField";
import NameField from "../../../components/fields/name/NameField";
import PasswordField from "../../../components/fields/password/PasswordField";
import {useAppDispatch} from "../../../stores/redux/hooks";
import {createNotification} from "../../../components/notification/Notification";
import {Notifications} from "../../../common/constansts/notifications";
import {showNotification} from "../../../stores/redux/notification/notificationSlice";
import {FieldsValidation} from "./types";
import {UserCredentials, UserType} from "../../../types/user";
import {NotificationType} from "../../../types/notification";

const initialUserCredentials: UserCredentials = {
    email: "",
    name: "",
    password: ""
}

const initialFieldsValidation: FieldsValidation = {
    isEmailValid: false,
    isPasswordValid: false,
    isNameValid: false
};

const SignUp: React.FC = () => {
    const [userCredentials, setUserCredentials] = useState<UserCredentials>(initialUserCredentials);
    const [selectedRole, setSelectedRole] = useState<UserType>(UserType.Customer);
    const [agreeChecked, setAgreeChecked] = useState(false);
    const [fieldsValidation, setFieldsValidation] = useState<FieldsValidation>(initialFieldsValidation);
    const [isValid, setIsValid] = useState(false);
    const navigate = useNavigate();
    const dispatch = useAppDispatch();

    const handleRoleChange = (event: React.ChangeEvent<HTMLInputElement>) => setSelectedRole(event.target.value as UserType);
    const handleAgreeCheckboxChange = (event: React.ChangeEvent<HTMLInputElement>) => setAgreeChecked(event.target.checked);

    const handleSignUp = async () => {
        if (!isValid) return;
        const user = await signUpService(userCredentials, selectedRole);
        if (user !== null) {
            const notification = createNotification(Notifications.SIGN_UP, NotificationType.SUCCESS);
            dispatch(showNotification(notification));
            navigate(Paths.SignIn);
        }

    };

    const updateEmailChange = (email: string, isEmailValid: boolean) => {
        setUserCredentials({...userCredentials, email});
        setFieldsValidation({...fieldsValidation, isEmailValid});
    };

    const updateNameChange = (name: string, isNameValid: boolean) => {
        setUserCredentials({...userCredentials, name});
        setFieldsValidation({...fieldsValidation, isNameValid});
    };

    const updatePasswordChange = (password: string, isPasswordValid: boolean) => {
        setUserCredentials({...userCredentials, password});
        setFieldsValidation({...fieldsValidation, isPasswordValid});
    };

    const validateData = () => {
        const fieldsValid = Object.values(fieldsValidation).every(field => field);
        setIsValid(fieldsValid && selectedRole && agreeChecked);
    };

    useEffect(() => {
        validateData();
    }, [fieldsValidation, selectedRole, agreeChecked]);

    return (
        <Box className="auth-container">
            <Box className="auth-content">
                <AuthLogo/>
                <EmailField
                    defaultEmail={userCredentials.email}
                    updateEmailFields={updateEmailChange}
                />
                <NameField
                    defaultName={userCredentials.name}
                    updateNameFields={updateNameChange}
                />
                <PasswordField
                    updatePasswordFields={updatePasswordChange}
                />
                <Typography variant="h5" gutterBottom>
                    {Titles.SELECT_ROLES}
                </Typography>
                <RadioGroup
                    row
                    className="role-selector"
                    value={selectedRole}
                    onChange={handleRoleChange}
                >
                    <Box className="role-option">
                        <FormControlLabel value={UserType.Customer} control={<Radio/>} label={Roles.CUSTOMER}/>
                    </Box>
                    <Box className="role-option">
                        <FormControlLabel value={UserType.Merchant} control={<Radio/>} label={Roles.MERCHANT}/>
                    </Box>
                </RadioGroup>

                <Box className="terms-checkbox">
                    <Checkbox
                        checked={agreeChecked}
                        onChange={handleAgreeCheckboxChange}
                    />
                    <Typography variant="body2">
                        {AuthContent.TERMS}
                    </Typography>
                </Box>

                <Button
                    onClick={handleSignUp}
                    variant="contained"
                    className="button"
                    disabled={!isValid}
                >
                    {Buttons.SIGN_UP}
                </Button>

                <Typography variant="body1" className="sign-in-link">
                    {AuthContent.ACCOUNT_EXIST}
                    <Link href={Paths.SignIn}>
                        {Titles.SIGN_IN}
                    </Link>
                </Typography>
            </Box>
        </Box>
    );
};

export default SignUp;