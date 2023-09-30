import { Card } from "react-bootstrap";
import { RestaurantModel } from "./RestaurantPage";

interface RestaurantCardProps {
  restaurant: RestaurantModel;
}

const RestaurantCard = (props: RestaurantCardProps) => {
  const { restaurant } = props;

  return (
    <Card>
      <Card.Body>
        <Card.Title>{restaurant.name}</Card.Title>
        {restaurant.cuisine ? (
          <Card.Subtitle className="mb-2 text-muted">
            {restaurant.cuisine}
          </Card.Subtitle>
        ) : null}
        {restaurant.address ? (
          <Card.Subtitle className="mb-2 text-muted">
            {restaurant.address}
          </Card.Subtitle>
        ) : null}
        {restaurant.description ? (
          <Card.Text>{restaurant.description}</Card.Text>
        ) : null}
      </Card.Body>
    </Card>
  );
};

export default RestaurantCard;
