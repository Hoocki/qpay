import React, {useEffect, useState} from 'react';
import {Box, Button, Link, Typography} from "@mui/material";
import "../../../common/styles/button.css";
import "./styles.css";
import {useAppDispatch} from "../../../stores/redux/hooks";
import {addToken, signIn} from "../../../stores/redux/loggedUser/loggedUserSlice";
import {getUserService} from "../../../services/user";
import {Buttons} from "../../../common/constansts/buttons";
import {logInService} from "../../../services/auth";
import {ILoggedUser, User, UserType} from "../../../types/user";
import {Paths} from "../../../common/constansts/paths";
import {AuthContent} from "../../../common/constansts/authContent";
import AuthLogo from "../../../components/logo/AuthLogo";
import EmailField from "../../../components/fields/email/EmailField";
import PasswordField from "../../../components/fields/password/PasswordField";
import {AuthCredentials} from "../../../types/authCredentials";
import {jwtDecode} from "jwt-decode";
import {TokenData} from "../../../types/tokenData";

const initialAuthCredentials: AuthCredentials = {
    email: "",
    password: ""
}

const SignIn: React.FC = () => {
    const [authCredentials, setAuthCredentials] = useState<AuthCredentials>(initialAuthCredentials)
    const [isEmailValid, setIsEmailValid] = useState<boolean>(false);
    const [isPasswordValid, setIsPasswordValid] = useState<boolean>(false);
    const [isValid, setIsValid] = useState(false);
    const dispatch = useAppDispatch();

    const createLoggedUser = (token: string, user: User): ILoggedUser => {
        return {
            id: user.id,
            email: user.email,
            name: user.name,
            userType: UserType.Customer,
            token
        };
    }

    const handleSignIn = async () => {
        if (!isValid) return;
        const token = await logInService(authCredentials);
        if (token === "") return;
        dispatch(addToken(token));
        const decodedToken: TokenData = jwtDecode(token);
        const user = await getUserService(decodedToken.userId, decodedToken.userType);
        const loggedUser = createLoggedUser(token, user);
        if (loggedUser) {
            dispatch(signIn(loggedUser));
        }
    }

    const updateEmailChange = (email: string, isEmailValid: boolean) => {
        setAuthCredentials({...authCredentials, email});
        setIsEmailValid(isEmailValid);
    };

    const updatePasswordChange = (password: string, isPasswordValid: boolean) => {
        setAuthCredentials({...authCredentials, password});
        setIsPasswordValid(isPasswordValid);
    };

    const validateData = () => {
        setIsValid(isPasswordValid && isEmailValid);
    };

    useEffect(() => {
        validateData();
    }, [isPasswordValid, isEmailValid]);

    return (
        <Box className="auth-container">
            <Box className="auth-content">
                <AuthLogo/>
                <Box className="sign-in-fields">
                    <EmailField
                        defaultEmail={authCredentials.email}
                        updateEmailFields={updateEmailChange}
                    />
                    <PasswordField
                        updatePasswordFields={updatePasswordChange}
                    />
                </Box>
                <Button
                    variant="contained"
                    className="button"
                    disabled={!isValid}
                    onClick={handleSignIn}
                >
                    {Buttons.SIGN_IN}
                </Button>
                <Typography
                    variant="body1"
                    className="sign-up-link"
                >
                    {AuthContent.ACCOUNT_NOT_EXIST}
                    <Link
                        href={Paths.SignUp}
                        color="primary"
                    >
                        {Buttons.SIGN_UP}
                    </Link>
                </Typography>
            </Box>
        </Box>
    );
}

export default SignIn;