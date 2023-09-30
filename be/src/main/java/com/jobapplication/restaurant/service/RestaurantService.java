package com.jobapplication.restaurant.service;

import com.jobapplication.restaurant.controller.request.CreateRestaurantRequest;
import com.jobapplication.restaurant.repository.RestaurantRepository;
import com.jobapplication.restaurant.repository.SessionRestaurantRepository;
import com.jobapplication.restaurant.repository.model.Restaurant;
import com.jobapplication.restaurant.repository.model.SessionRestaurant;
import com.jobapplication.restaurant.repository.model.SessionRestaurantPK;
import com.jobapplication.restaurant.service.dto.RestaurantDTO;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RestaurantService {

  private final RestaurantRepository restaurantRepository;
  private final SessionRestaurantRepository sessionRestaurantRepository;
  private final Random random;

  public RestaurantService(
      RestaurantRepository restaurantRepository,
      SessionRestaurantRepository sessionRestaurantRepository) {
    this.restaurantRepository = restaurantRepository;
    this.sessionRestaurantRepository = sessionRestaurantRepository;
    random = new Random();
  }

  @Transactional
  public void createRestaurantForSession(CreateRestaurantRequest request, String sessionID) {
    Restaurant restaurant = new Restaurant();
    restaurant.setAddress(request.getAddress());
    restaurant.setName(request.getName());
    restaurant.setCuisine(request.getCuisine());
    restaurant.setDescription(request.getDescription());

    restaurant = restaurantRepository.save(restaurant);

    SessionRestaurant sessionRestaurant = new SessionRestaurant();
    sessionRestaurant.setSessionRestaurantPK(
        new SessionRestaurantPK(sessionID, restaurant.getRestaurantId()));
    sessionRestaurantRepository.save(sessionRestaurant);
  }

  public Optional<RestaurantDTO> getRandomRestaurant(String sessionID) {
    List<Restaurant> restaurantsBySession =
        sessionRestaurantRepository.findAllRestaurantsBySession(sessionID);
    if (restaurantsBySession.isEmpty()) {
      return Optional.empty();
    }

    int randomIdx = random.nextInt(0, restaurantsBySession.size());
    Restaurant pickedRestaurant = restaurantsBySession.get(randomIdx);
    log.info("Picked random restaurant {}", pickedRestaurant);

    return Optional.of(mapRestaurantToDTO(pickedRestaurant));
  }

  public List<RestaurantDTO> getAllRestaurantsForSession(String sessionID) {
    List<Restaurant> restaurants =
        sessionRestaurantRepository.findAllRestaurantsBySession(sessionID);
    return restaurants.stream().map(this::mapRestaurantToDTO).toList();
  }

  private RestaurantDTO mapRestaurantToDTO(Restaurant restaurant) {
    RestaurantDTO restaurantDTO = new RestaurantDTO();
    restaurantDTO.setName(restaurant.getName());
    restaurantDTO.setAddress(restaurant.getAddress());
    restaurantDTO.setCuisine(restaurant.getCuisine());
    restaurantDTO.setDescription(restaurant.getDescription());
    return restaurantDTO;
  }
}
