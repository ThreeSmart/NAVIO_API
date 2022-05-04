package am.threesmart.services;

import am.threesmart.mappers.CoordinatesMapper;
import am.threesmart.models.dto.Coordinates;
import am.threesmart.models.entity.CoordinatesEntity;
import am.threesmart.repositories.CoordinatesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinatesService {

    private final CoordinatesRepository coordinatesRepository;

    public CoordinatesService(final CoordinatesRepository coordinatesRepository) {
        this.coordinatesRepository = coordinatesRepository;
    }

    public Coordinates pushCoordinates(final Coordinates coordinates) {
        final CoordinatesEntity saved = coordinatesRepository.save(CoordinatesMapper.instance.dtoToEntity(coordinates));
        return CoordinatesMapper.instance.entityToDto(saved);
    }

    public Coordinates getCurrentPositionOfUser(final Long userId) {
        final List<CoordinatesEntity> allByUserId = coordinatesRepository.findAllByUserId(userId);
        return CoordinatesMapper.instance.entityToDto(allByUserId.get(allByUserId.size() - 1));
    }

}