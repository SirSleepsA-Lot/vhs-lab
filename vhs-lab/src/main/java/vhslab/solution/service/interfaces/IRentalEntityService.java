package vhslab.solution.service.interfaces;

import vhslab.solution.entities.dto.RentalEntityDto;
import vhslab.solution.entities.model.RentalEntity;

import java.math.BigDecimal;
import java.sql.Date;

public interface IRentalEntityService {
    RentalEntityDto rentVhs(long userId, long vhsId, Date dateRented) throws Exception;
    RentalEntityDto returnRentedVhs(long rentalId) throws Exception;
    BigDecimal calculateLateReturnFee(RentalEntity rental);
    Boolean payFee(long rentalId) throws Exception;
}
