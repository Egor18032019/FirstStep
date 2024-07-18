import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.scss';
 
import { PointProvider } from './components/Reducer/PointReducer';
import App from './App';
 
 

const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);
root.render(
    <React.StrictMode>
        <PointProvider>
            <App/>
        </PointProvider>
    </React.StrictMode>
);
