import { useEffect, useState } from "react";
import { Button, Stack } from "react-bootstrap";
import LoadingSpinner from "../../components/LoadingSpinner";
import { Restaurant, RestaurantService } from "../../service/RestaurantService";
import RestaurantCard from "./RestaurantCard";
import RestaurantForm from "./RestaurantForm";
import RestaurantList from "./RestaurantList";
import Usage from "./Usage";

export interface RestaurantModel {
  name: string;
  address?: string;
  cuisine?: string;
  description?: string;
}

const service = new RestaurantService();

const RestaurantPage = () => {
  const [restaurants, setRestaurants] = useState<RestaurantModel[]>([]);
  const [randomRestaurant, setRandomRestaurant] = useState<RestaurantModel>();
  const [formErrors, setFormErrors] = useState<string[]>([]);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    setIsLoading(true);
    service.getAllRestaurants().then(
      (restaurants) => {
        setIsLoading(false);
        if (restaurants) {
          setRestaurants(
            restaurants.map((restaurant: Restaurant) => ({
              name: restaurant.name,
              address: restaurant.address,
              cuisine: restaurant.cuisine,
              description: restaurant.description,
            }))
          );
        }
      },
      (_) => {
        setIsLoading(false);
      }
    );
  }, []);

  const onAdd = (form: React.FormEvent<HTMLFormElement>) => {
    form.preventDefault();
    const target = form.target as typeof form.target & {
      name: { value?: string };
      address: { value?: string };
      cuisine: { value?: string };
      description: { value?: string };
    };

    if (!target.name.value) {
      setFormErrors(["Restaurant name cannot be empty"]);
      return;
    }

    if (formErrors) {
      setFormErrors([]);
    }

    const newRestaurant = {
      name: target.name.value,
      address: target.address.value,
      cuisine: target.cuisine.value,
      description: target.description.value,
    };

    setIsLoading(true);
    service.addRestaurant(newRestaurant).then(
      (isSuccess) => {
        setIsLoading(false);
        if (isSuccess) {
          setRestaurants([...restaurants, newRestaurant]);
        }
      },
      (_) => {
        setIsLoading(false);
      }
    );
  };

  const getRandomRestaurant = () => {
    setIsLoading(true);
    service.getRandomRestaurant().then(
      (restaurant) => {
        setIsLoading(false);
        if (!restaurant) {
          return;
        }

        setRandomRestaurant({
          name: restaurant.name,
          address: restaurant.address,
          cuisine: restaurant.cuisine,
          description: restaurant.description,
        });
      },
      (_) => {
        setIsLoading(false);
      }
    );
  };

  return (
    <>
      <div className="container" style={{ height: "100vh" }}>
        <Stack gap={3} className="mt-3">
          <Usage />
          <RestaurantForm
            onAdd={onAdd}
            formErrors={formErrors}
            isLoading={isLoading}
          />
          <h2 className="mt-3 mb-1">Restaurants</h2>
          <RestaurantList restaurants={restaurants} />

          <hr />

          <h2>Find your random restaurant here!</h2>
          <Button
            type="button"
            onClick={getRandomRestaurant}
            disabled={isLoading}
          >
            {isLoading ? <LoadingSpinner type="button" /> : null}
            Random
          </Button>
          {randomRestaurant ? (
            <>
              <h3>Here is a random restaurant!</h3>
              <div className="mb-5">
                <RestaurantCard restaurant={randomRestaurant} />
              </div>
            </>
          ) : null}
        </Stack>
      </div>
    </>
  );
};

export default RestaurantPage;
