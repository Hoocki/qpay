import {User} from "../types/user";

export const mockEnabled = true;

export const mockToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyVHlwZSI6IkNVU1RPTUVSIiwidXNlcklkIjo3OSwiZW1haWwiOiJ1c2VydGVzdEBtYWlsLnJ1Iiwic3ViIjoidXNlcnRlc3RAbWFpbC5ydSIsImlhdCI6MTcxNDI5MTkxM30.AyL1LnHCeIr78ch5owlWw57aMFbG_4Z0c69H_-3n-jw";

export const mockUser: User = {
    id: 79,
    email: "usertest@mail.ru",
    name: "userTest",
    updatedAt: new Date(),
    createdAt: new Date()
}