package com.jobapplication.restaurant.controller;

import com.jobapplication.restaurant.controller.request.CreateRestaurantRequest;
import com.jobapplication.restaurant.service.RestaurantService;
import com.jobapplication.restaurant.service.dto.RestaurantDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/restaurant")
@Slf4j
@Tag(name = "Restaurant", description = "Restaurant management APIs")
public class RestaurantController {

  private final RestaurantService restaurantService;

  public RestaurantController(RestaurantService restaurantService) {
    this.restaurantService = restaurantService;
  }

  @Operation(
      summary = "Retrieve all restaurants tied to a session",
      description =
          "Retrieves all restaurants tied to session defined in a cookie. Call this API first to initialize a usage session")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "List of restaurants present in current session",
        content = {
          @Content(array = @ArraySchema(schema = @Schema(implementation = RestaurantDTO.class)))
        }),
    @ApiResponse(responseCode = "400", description = "No session available"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getAllRestaurantsForSession(HttpServletRequest servletRequest) {
    try {
      String sessionID = validateSessionID(servletRequest);
      if (sessionID == null) {
        // Something went wrong with spring session
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
      log.info("Fetching all restaurants for session: [session={}]", sessionID);

      List<RestaurantDTO> restaurants = restaurantService.getAllRestaurantsForSession(sessionID);
      return new ResponseEntity<>(restaurants, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Unable to fetch all restaurants for session", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Operation(
      summary = "Adds a restaurant for this session",
      description =
          "Adds a restaurant for this session. A session must exist first for this cookie, otherwise the API will reject the call")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Restaurant created for session"),
    @ApiResponse(responseCode = "400", description = "No session available"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @PostMapping(
      path = "",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createRestaurantForSession(
      @Valid @RequestBody CreateRestaurantRequest request, HttpServletRequest servletRequest) {
    try {
      Thread.sleep(10000);
      String sessionID = validateSessionID(servletRequest);
      if (sessionID == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
      log.info("Create restaurant request received: [request={}, session={}]", request, sessionID);

      restaurantService.createRestaurantForSession(request, sessionID);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      log.error("Unable to create restaurant", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Operation(
      summary = "Randomly picks restaurant for current session",
      description = "Randomly picks a restaurant for current session from added restaurants")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Random restaurant picked",
        content = {@Content(schema = @Schema(implementation = RestaurantDTO.class))}),
    @ApiResponse(responseCode = "400", description = "No session available"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping(
      path = "/random",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getRandomRestaurant(HttpServletRequest servletRequest) {
    try {
      String sessionID = validateSessionID(servletRequest);
      if (sessionID == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
      log.info("Generating random restaurants for session: [session={}]", sessionID);

      Optional<RestaurantDTO> optionalRestaurantDTO =
          restaurantService.getRandomRestaurant(sessionID);
      return new ResponseEntity<>(optionalRestaurantDTO.orElse(null), HttpStatus.OK);
    } catch (Exception e) {
      log.error("Unable to get random restaurant", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private String validateSessionID(HttpServletRequest servletRequest) {
    String sessionID = servletRequest.getSession().getId();
    log.info("Validating session ID: [session={}]", sessionID);
    if (sessionID == null || sessionID.isBlank()) {
      return null;
    }

    return sessionID;
  }
}
