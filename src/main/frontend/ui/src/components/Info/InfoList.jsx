import React, { useEffect, useState } from "react";
import { url, api, info } from "../../utils/Const.js";
import "./InfoList.scss";
import { Button, Alert } from "react-bootstrap";
function InfoList() {
    const [information, setInformation] = useState(null);

    useEffect(() => {
        console.log("InfoList")
        handleFindAllNews()
    }, []);

    const handleFindAllNews = async () => {
        // Fetch the user email and token from local storage
        const response = await fetch(url + info,
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "Access-Control-Allow-Origin": "*",
                    "Access-Control-Allow-Credentials": "true",
                    "Access-Control-Allow-Methods": "GET",
                }
            });
        console.log(response)
        const data = await response.json();
        if (data) {
            console.log(data)
            setInformation(data)
        }

    };


    return (<div className="category-container">
        <h1>Информация</h1>

        {information ?
            <div>
                <p>{information.info}</p>
                <p>{information.date}</p>
            </div>
            : ""
        }
        <button
            title="Press me"
            onClick={() => console.log("предыдущие Button pressed")
            }
        > предыдущие</button>
        <button onClick={() => console.log("следующее Button pressed")
        } >следующее   </button>

    </div>
    )
}
;

export default InfoList;
