import { BrowserRouter, Routes, Route } from "react-router-dom";
import Nav from "./components/Navigation/Navigation";
import './App.scss';

import React, { useEffect, useState } from 'react';
import Contact from "./components/Contact/Contact";
import InfoList from "./components/Info/InfoList";
import { useContextMap } from "./components/Reducer/PointReducer";
import Home from "./components/Home/Home";
import Login from "./components/Login/Login";



function App() {
    const [loggedIn, setLoggedIn] = useState(false)
    const [email, setEmail] = useState('')
    const { setCoordinates, points, setPoint, setPoints, setOriginalPoints, setDataLoaded } = useContextMap();


    return (
        <>


            <BrowserRouter>
                <Nav />
                <Routes>
                    <Route path="/" element={<Home email={email} loggedIn={loggedIn} setLoggedIn={setLoggedIn} />} />
                    <Route path="/login" element={<Login setLoggedIn={setLoggedIn} setEmail={setEmail}/>}></Route>
                    <Route path="/info" element={<InfoList />}></Route>
                    <Route path="/contact" element={<Contact />}></Route>
                </Routes>
            </BrowserRouter>
        </>
    );
}

export default App;
