import {createTheme} from "@mui/material/styles";

declare module '@mui/material/styles' {
    interface Palette {
        primary: Palette['primary'];
        cancel: Palette['secondary'];
    }

    interface PaletteOptions {
        primary?: PaletteOptions['primary'];
        cancel?: PaletteOptions['secondary'];
    }
}

declare module '@mui/material/Button' {
    interface ButtonPropsColorOverrides {
        primary: true;
        cancel: true;
    }
}

export const theme = createTheme({
    palette: {
        primary: {
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