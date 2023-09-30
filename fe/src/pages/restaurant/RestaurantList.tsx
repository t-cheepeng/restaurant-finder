import { Col, Container, Row } from "react-bootstrap";
import RestaurantCard from "./RestaurantCard";
import { RestaurantModel } from "./RestaurantPage";

interface RestaurantListProps {
  restaurants: RestaurantModel[];
}

const RestaurantList = (props: RestaurantListProps) => {
  const { restaurants } = props;
  return (
    <Container>
      <Row>
        {restaurants.map((restaurant) => (
          <Col
            xs={6}
            className="mb-3"
            key={`${restaurant.name}${restaurant.address}${restaurant.cuisine}${restaurant.description}`}
          >
            <RestaurantCard restaurant={restaurant} />
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default RestaurantList;
