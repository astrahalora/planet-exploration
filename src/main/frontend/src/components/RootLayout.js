import { NavLink, Outlet } from "react-router-dom";

export default function RootLayout() {

    return (
        <div className="root-layout">
            <header>
                <nav>
                    <div>
                        <NavLink to="/" id="home">Home</NavLink>
                    </div>
                </nav>
            </header>
            <main>
                <Outlet/>
            </main>
        </div>
    )
}