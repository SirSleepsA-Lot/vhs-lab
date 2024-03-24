package vhslab.solution.service.interfaces;

import vhslab.solution.entities.dto.RentalEntityDto;
import vhslab.solution.entities.model.RentalEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface IRentalEntityService {
    List<RentalEntityDto> getAllRentalEntities();
    RentalEntityDto getRentalById(long rentalId) throws Exception;
    RentalEntityDto createRental(RentalEntityDto rentalEntityDto);
    RentalEntityDto updateRental(long rentalId, RentalEntityDto rentalDetails);
    void deleteRental(long rentalId);
    RentalEntityDto rentVhs(long userId, long vhsId, Date dateRented, Date datedue) throws Exception;
    RentalEntityDto returnRentedVhs(long rentalId) throws Exception;
    BigDecimal calculateLateReturnFee(RentalEntity rental);
    Boolean payFee(long rentalId) throws Exception;
}
