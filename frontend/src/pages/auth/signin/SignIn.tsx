import React from 'react';
import {Box, Button, Link, Typography} from "@mui/material";
import "../../../common/styles/button.css";
import {useAppDispatch} from "../../../stores/redux/hooks";
import {signIn} from "../../../stores/redux/loggedUser/loggedUserSlice";
import {getUserService} from "../../../services/user";
import {Buttons} from "../../../common/constansts/buttons";
import {logInService} from "../../../services/auth";
import {ILoggedUser, User, UserType} from "../../../types/user";
import {decodeToken} from "../../../common/utils";
import {Paths} from "../../../common/constansts/paths";
import {AuthContent} from "../../../common/constansts/authContent";

const SignIn: React.FC = () => {
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
        const token = await logInService({email: "user@mail.com", password: "password"});
        const decodedToken = decodeToken(token);
        const user = await getUserService(decodedToken.userId, decodedToken.userType);
        const loggedUser = createLoggedUser(token, user);
        if (loggedUser) {
            dispatch(signIn(loggedUser));
        }
    }

    return (
        <Box>
            <Button
                variant="contained"
                className="button"
                onClick={handleSignIn}
            >
                {Buttons.SIGN_IN}
            </Button>
            <Typography
                variant="body2"
                sx={{marginTop: 1}}
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
    );
}

export default SignIn;