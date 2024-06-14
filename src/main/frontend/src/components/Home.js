import { useState } from "react";

import Map from "./Map";
import Options from "./Options";

const empty_space = "https://images2.imgbox.com/b4/cb/cXkD81KO_o.png";
const blue_square_water = "https://images2.imgbox.com/31/7f/KCXfK2kt_o.png";
const brown_square_mountain = "https://images2.imgbox.com/48/bf/weSKyYy5_o.png";
const gold_square_mineral = "https://images2.imgbox.com/3b/6a/hZKPrgab_o.png";
const gold_square_pit = "https://images2.imgbox.com/36/5a/UnHSZIOl_o.png";

const createEmpty2DArray = (rows, columns) => {
    const arr = [];
    for (let i = 0; i < rows; i++) {
        const row = Array(columns).fill(empty_space);
        arr.push(row);
    }
    return arr;
};

function populate2DArray(rows, columns, dataArray) {
    const newArray = [];
    let dataIndex = 0;

    for (let i = 0; i < rows; i++) {
        const newRow = [];
        for (let j = 0; j < columns; j++) {
            if (dataIndex < dataArray.length) {
                newRow.push(dataArray[dataIndex]);
                dataIndex++;
            } else {
                newRow.push(null);
            }
        }
        newArray.push(newRow);
    }
    return newArray;
}

export default function Home() {
    const emptyMap = createEmpty2DArray(32, 32);
    const [mapRepresentation, setMapRepresentation] = useState(emptyMap);

    const getRandomMapRepresentation = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/map");
            const data = await response.json();
            const usableData = data["stringMap"]
                .replaceAll("#", brown_square_mountain + ",")
                .replaceAll("&", gold_square_pit + ",")
                .replaceAll("%", gold_square_mineral + ",")
                .replaceAll("*", blue_square_water + ",")
                .replaceAll("-", empty_space + ",")
                .split(",");
            const newRandomMap = populate2DArray(32, 32, usableData);
            setMapRepresentation(newRandomMap);
        } catch (error) {
            console.log(error.message);
        }
    };

    return (
        <div className="home">
            <Map mapRepresentation={mapRepresentation}/>
            <Options mountain={brown_square_mountain}
                     pit={gold_square_pit}
                     mineral={gold_square_mineral}
                     water={blue_square_water} 
                     getMap={() => getRandomMapRepresentation()}
            />
        </div>
    );
}
