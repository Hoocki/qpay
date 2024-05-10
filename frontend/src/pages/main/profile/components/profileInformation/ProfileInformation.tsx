import React, {useEffect, useState} from 'react';
import {Card, CardContent} from "@mui/material";
import EmailField from "../../../../../components/fields/email/EmailField";
import NameField from "../../../../../components/fields/name/NameField";
import PasswordField from "../../../../../components/fields/password/PasswordField";
import {UserModification} from "../../../../../types/user";
import {useAppDispatch, useAppSelector} from "../../../../../stores/redux/hooks";
import {logOut, selectLoggedUser} from "../../../../../stores/redux/loggedUser/loggedUserSlice";
import {updateUserService} from "../../../../../services/user";
import ConfirmationButton from "../../../../../components/buttons/confirmationButton/ConfirmationButton";
import {FieldsValidation} from "../../../../auth/signup/types";
import {showNotification} from "../../../../../stores/redux/notification/notificationSlice";
import {NotificationType} from "../../../../../types/notification";
import {Notifications} from "../../../../../common/constansts/notifications";
import {Titles} from "../../../../../common/constansts/titles";
import CardTitle from "../../../../../components/typography/cardTitle/CardTitle";

const initialFieldsValidation: FieldsValidation = {
    isEmailValid: false,
    isPasswordValid: true,
    isNameValid: false
};

const initialUserModification: UserModification = {
    name: "",
    email: "",
    password: ""
}

const ProfileInformation = () => {

    const [userModification, setUserModification] = useState<UserModification>(initialUserModification);
    const [fieldsValidation, setFieldsValidation] = useState<FieldsValidation>(initialFieldsValidation);
    const [isValid, setIsValid] = useState<boolean>(false);
    const loggedUser = useAppSelector(selectLoggedUser);
    const dispatch = useAppDispatch();

    const updateEmail = (email: string, isEmailValid: boolean) => {
        setUserModification({...userModification, email});
        setFieldsValidation({...fieldsValidation, isEmailValid});
    }

    const updateName = (name: string, isNameValid: boolean) => {
        setUserModification({...userModification, name});
        setFieldsValidation({...fieldsValidation, isNameValid});
    }

    const updatePassword = (password: string, isPasswordValid: boolean) => {
        setUserModification({...userModification, password});
        if (password === "") {
            const validIfPasswordEmpty = !isPasswordValid;
            setFieldsValidation({...fieldsValidation, isPasswordValid: validIfPasswordEmpty});
        } else {
            setFieldsValidation({...fieldsValidation, isPasswordValid});
        }
    }

    const validateData = () => {
        const fieldsValid = Object.values(fieldsValidation).every(field => field);
        setIsValid(fieldsValid);
    };

    const handleConfirm = async () => {
        const updatedUser = await updateUserService(loggedUser.id, loggedUser.userType, userModification);
        if (!updatedUser) return;
        dispatch(showNotification({
            show: true,
            type: NotificationType.SUCCESS,
            message: Notifications.SUCCESSFUL_UPDATE_USER_DATA
        }));
        dispatch(logOut());
    }

    useEffect(() => {
        validateData();
    }, [fieldsValidation]);

    return (
        <Card className="card-background card-payment">
            <CardContent className="card-confirm-content">
                <CardTitle title={Titles.INFORMATION}/>
                <EmailField defaultEmail={userModification.email} updateEmailFields={updateEmail}/>
                <NameField defaultName={userModification.name} updateNameFields={updateName}/>
                <PasswordField updatePasswordFields={updatePassword}/>
            </CardContent>
            <ConfirmationButton handleClick={handleConfirm} isDisabled={!isValid}/>
        </Card>
    );
};

export default ProfileInformation;