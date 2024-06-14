import full_map from "./img/full_map.jpg";

export default function Map({mapRepresentation}) {
    return <div className="map">
        <img src={full_map} alt="Map" />
        <div className="map-elements">
            {mapRepresentation.map((tile) =>
                tile.map((item, colIndex) => (
                    <div key={colIndex}>
                        <img src={item} alt="Tile" />
                    </div>
                ))
            )}
        </div>
    </div>
}