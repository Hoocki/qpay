import React, {useEffect, useState} from 'react';
import {Box, Button, Card, Checkbox, FormControlLabel, Link, Radio, RadioGroup, Typography} from '@mui/material';
import {Paths} from "../../../common/constansts/paths";
import {Titles} from "../../../common/constansts/titles";
import "../../../common/styles/button.css";
import "./styles.css";
import "../styles.css";
import {useNavigate} from "react-router-dom";
import {Buttons} from "../../../common/constansts/buttons";
import {AuthContent} from "../../../common/constansts/authContent";
import {Roles} from "../../../common/constansts/roles";
import AuthLogo from "../../../components/logo/AuthLogo";
import EmailField from "../../../components/fields/email/EmailField";
import NameField from "../../../components/fields/name/NameField";
import PasswordField from "../../../components/fields/password/PasswordField";
import {useAppDispatch} from "../../../stores/redux/hooks";
import {createNotification} from "../../../components/notification/Notification";
import {Notifications} from "../../../common/constansts/notifications";
import {showNotification} from "../../../stores/redux/notification/notificationSlice";
import {FieldsValidation} from "./types";
import {IUserCredentials, UserType} from "../../../types/user";
import {NotificationType} from "../../../types/notification";
import {signUpService} from "../../../services/auth";
import {Content} from "../../../common/constansts/content";
import {DISABLE_FIELD} from "../../../common/constansts/fields";

const initialUserCredentials: IUserCredentials = {
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
    const [userCredentials, setUserCredentials] = useState<IUserCredentials>(initialUserCredentials);
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
            navigate(Paths.SIGN_IN);
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
        <Box className="main-container">
            <Box className="content-container">
                <Card className="card-background card-payment card-auth">
                    <AuthLogo/>
                    <EmailField
                        defaultEmail={userCredentials.email}
                        updateEmailFields={updateEmailChange}
                    />
                    <NameField
                        defaultName={userCredentials.name}
                        updateNameFields={updateNameChange}
                        isDisabled={!DISABLE_FIELD}
                    />
                    <PasswordField
                        updatePasswordFields={updatePasswordChange}
                    />
                    <Typography variant="h5" gutterBottom>
                        {Content.SELECT_ROLES}
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

                    <Typography variant="body1">
                        {AuthContent.ACCOUNT_EXIST}
                        <Link href={Paths.SIGN_IN}>
                            {Titles.SIGN_IN}
                        </Link>
                    </Typography>
                </Card>
            </Box>
        </Box>
    );
};

export default SignUp;