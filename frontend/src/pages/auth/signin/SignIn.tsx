import React, {useEffect, useState} from 'react';
import {Box, Card, Link, Typography} from "@mui/material";
import "../../../common/styles/button.css";
import "./styles.css";
import "../../../common/styles/container.css";
import {useAppDispatch} from "../../../stores/redux/hooks";
import {addToken, signIn} from "../../../stores/redux/loggedUser/loggedUserSlice";
import {getUserService} from "../../../services/user";
import {Buttons} from "../../../common/constansts/buttons";
import {logInService} from "../../../services/auth";
import {Paths} from "../../../common/constansts/paths";
import AuthLogo from "../../../components/logo/AuthLogo";
import EmailField from "../../../components/fields/email/EmailField";
import PasswordField from "../../../components/fields/password/PasswordField";
import {IAuthCredentials} from "../../../types/AuthCredentials";
import {jwtDecode} from "jwt-decode";
import {TokenData} from "../../../types/TokenData";
import {mapLoggedUser} from "../../../common/utils/mappers";
import ConfirmationButton from "../../../components/buttons/confirmationButton/ConfirmationButton";
import {Content} from "../../../common/constansts/content";
import {getWalletByUserService} from "../../../services/wallet";
import {addWalletId} from "../../../stores/redux/wallet/walletSlicer";

const initialAuthCredentials: IAuthCredentials = {
    email: "",
    password: ""
}

const SignIn: React.FC = () => {
    const [authCredentials, setAuthCredentials] = useState<IAuthCredentials>(initialAuthCredentials)
    const [isEmailValid, setIsEmailValid] = useState<boolean>(false);
    const [isPasswordValid, setIsPasswordValid] = useState<boolean>(false);
    const [isValid, setIsValid] = useState(false);
    const dispatch = useAppDispatch();

    const handleSignIn = async () => {
        if (!isValid) return;
        const token = await logInService(authCredentials);
        if (token === "") return;
        dispatch(addToken(token));
        const decodedToken: TokenData = jwtDecode(token);
        const user = await getUserService(decodedToken.userId, decodedToken.userType);
        const loggedUser = mapLoggedUser(token, user, decodedToken);
        const receivedWallet = await getWalletByUserService(loggedUser.id, loggedUser.userType);
        if (!receivedWallet) return;
        dispatch(addWalletId(receivedWallet));
        if (!loggedUser) return;
        dispatch(signIn(loggedUser));
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
        <Box className="main-container">
            <Box className="content-container auth-container">
                <Card className="card-background card-payment card-auth">
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
                    <ConfirmationButton buttonName={Buttons.SIGN_IN} handleClick={handleSignIn} isDisabled={!isValid}/>
                    <Typography
                        variant="body1"
                        className="sign-up-link"
                    >
                        {Content.ACCOUNT_NOT_EXIST}
                        <Link
                            href={Paths.SIGN_UP}
                            color="primary"
                        >
                            {Buttons.SIGN_UP}
                        </Link>
                    </Typography>
                </Card>
            </Box>
        </Box>
    );
}

export default SignIn;