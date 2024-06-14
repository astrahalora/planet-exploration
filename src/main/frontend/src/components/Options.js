export default function Options({mountain, pit, mineral, water, getMap}) {
    return <div className="options">
        <div className="descriptions">
            <div className="resource">
                <img src={mountain} alt="Mountain"/>
                <h2>Mountain</h2>
                <p>Random Placement</p>
            </div>
            <div className="resource">
                <img src={pit} alt="Pit"/>
                <h2>Pit</h2>
                <p>Random Placement</p>
            </div>
            <div className="resource">
                <img src={mineral} alt="Mineral"/>
                <h2>Mineral</h2>
                <p>Placed next to Mountain</p>
            </div>
            <div className="resource">
                <img src={water} alt="Pocket of Water"/>
                <h2>Pocket of Water</h2>
                <p>Placed next to Pit</p>
            </div>
        </div>
        <div className="action-btns">
            <button id="generate-map-btn"
                    onClick={getMap}>
                Generate New Map
            </button>
        </div>
    </div>
}