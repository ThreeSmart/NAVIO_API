package am.threesmart.controllers;


import am.threesmart.models.dto.Coordinates;
import am.threesmart.services.CoordinatesService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coordinates")
@PreAuthorize("permitAll()")
public class CoordinatesController {

    private final CoordinatesService coordinatesService;

    public CoordinatesController(final CoordinatesService coordinatesService) {
        this.coordinatesService = coordinatesService;
    }

    @PostMapping("/put_new")
    public ResponseEntity<?> puNewCoordinate(@RequestBody final Coordinates coordinates) {
        final Coordinates pushed = coordinatesService.pushCoordinates(coordinates);
        return ResponseEntity.ok("Pushed new coordinates with id: " + pushed.getId());
    }

    @GetMapping("/get_current")
    public ResponseEntity<?> getCurrentPositionOfUser(@RequestParam final String userId) {
        final Coordinates coordinates = coordinatesService.getCurrentPositionOfUser(Long.parseLong(userId));
        return ResponseEntity.ok(coordinates);
    }


}
