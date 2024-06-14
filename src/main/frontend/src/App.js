import { createBrowserRouter, Route, createRoutesFromElements, RouterProvider } from "react-router-dom";
import RootLayout from "./components/RootLayout";
import Home from "./components/Home";
import './App.scss';

const router = createBrowserRouter(
    createRoutesFromElements(
        <Route path="/" element={<RootLayout/>}>
          <Route index element={<Home/>}/>
        </Route>
    )
)

function App() {
  return <RouterProvider router={router}/>
}

export default App;
