import React from 'react';
import {Box, Button} from "@mui/material";
import "../../../common/styles/button.css";
import {useAppDispatch} from "../../../stores/redux/hooks";
import {signIn} from "../../../stores/redux/loggedUser/loggedUserSlice";
import {getUserService} from "../../../services/user";
import {Buttons} from "../../../common/constansts/buttons";
import {logInService} from "../../../services/auth";
import {User} from "../../../types/user";
import {UserType} from "../../../types/userType";
import {ILoggedUser} from "../../../types/loggedUser";
import {decodeToken} from "../../../common/utils";

const SignIn: React.FC = () => {
    const dispatch = useAppDispatch()

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
        </Box>
    );
}

export default SignIn;