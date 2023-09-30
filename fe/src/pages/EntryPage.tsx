import { Button, Stack } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const EntryPage = () => {
  const navigate = useNavigate();

  const enterRestaurantFinder = () => {
    navigate("/restaurant");
  }

  return (
    <div className="container text-center" style={{ height: "100vh" }}>
      <Stack gap={3} className="mt-3">
        <h1>Welcome to the Restaurnt Finder</h1> 
        <h2>Click Enter to start using the application</h2>
        <Button onClick={enterRestaurantFinder}>Enter</Button>
      </Stack>
    </div>
  );
}

export default EntryPage;