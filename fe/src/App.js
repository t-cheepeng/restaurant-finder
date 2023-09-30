import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import RestaurantNav from "./components/RestaurantNav";
import "./index.css";
import EntryPage from "./pages/EntryPage";
import RestaurantPage from "./pages/restaurant/RestaurantPage";

const router = createBrowserRouter([
  {
    path: "/",
    element: <EntryPage/>,
  },
  {
    path: "/restaurant",
    element: <RestaurantPage/>
  }
]);

const App = () => {
  return (
    <RestaurantNav>
      <RouterProvider router={router} />
    </RestaurantNav>
  );
};

export default App;