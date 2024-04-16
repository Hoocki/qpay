import React from 'react';
import Providers from "./providers/Providers";
import {StyledEngineProvider} from "@mui/material";

const App: React.FC = () => {

    return (
        <StyledEngineProvider injectFirst>
            <Providers/>
        </StyledEngineProvider>
    )
}

export default App;