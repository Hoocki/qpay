import {createTheme} from "@mui/material/styles";

declare module '@mui/material/styles' {
    interface Palette {
        blue: Palette['primary'];
        cancel: Palette['primary'];
    }

    interface PaletteOptions {
        blue?: PaletteOptions['primary'];
        cancel?: PaletteOptions['primary'];
    }
}

declare module '@mui/material/Button' {
    interface ButtonPropsColorOverrides {
        blue: true;
        cancel: true;
    }
}

export const buttonTheme = createTheme({
    palette: {
        blue: {
            main: '#657FB4',
            light: '#8798be',
            dark: '#4d618c',
            contrastText: '#ffffff',
        },
        cancel: {
            main: '#F3F3F3',
            light: '#ffffff',
            dark: '#d2d2d2',
            contrastText: '#000000',
        },
    },
});