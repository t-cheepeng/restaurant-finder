export interface CreateRestaurantRequest {
  name: string;
  address?: string;
  cuisine?: string;
  description?: string;
}

export interface Restaurant {
  name: string;
  address?: string;
  cuisine?: string;
  description?: string;
}

export class RestaurantService {
  static host: string = "http://localhost:8080";
  static apiPrefix: string = "/api";
  static resource: string = "/restaurant";

  addRestaurant(request: CreateRestaurantRequest) {
    console.log("Sending add restaurant request", request);
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(request),
      credentials: "include" as RequestCredentials,
    };

    return fetch(this.getRestaurantURI(), requestOptions).then(
      (value) => {
        return value.ok;
      },
      (e) => {
        console.error(e);
        return false;
      }
    );
  }

  async getAllRestaurants() {
    console.log("Fetching all restaurants");
    const requestOptions = {
      method: "GET",
      headers: { "Content-Type": "application/json" },
      credentials: "include" as RequestCredentials,
    };

    return fetch(this.getRestaurantURI(), requestOptions).then(
      (value) => {
        if (!value.ok) {
          return undefined;
        }

        return value.json().then((result) => {
          console.log(result);
          return result.map((restaurant: any) => ({
            name: restaurant.name,
            address: restaurant.address,
            cuisine: restaurant.cuisine,
            description: restaurant.description,
          }));
        });
      },
      (e) => {
        console.error(e);
        return undefined;
      }
    );
  }

  getRandomRestaurant() {
    console.log("Fetching random restaurant");
    const requestOptions = {
      method: "GET",
      headers: { "Content-Type": "application/json" },
      credentials: "include" as RequestCredentials,
    };

    return fetch(this.getRestaurantURI() + "/random", requestOptions).then(
      (value) => {
        if (!value.ok) {
          return undefined;
        }

        return value.json().then((result) => ({
          name: result.name,
          address: result.address,
          cuisine: result.cuisine,
          description: result.description,
        }));
      },
      (e) => {
        console.error(e);
        return undefined;
      }
    );
  }

  getRestaurantURI() {
    return (
      RestaurantService.host +
      RestaurantService.apiPrefix +
      RestaurantService.resource
    );
  }
}
