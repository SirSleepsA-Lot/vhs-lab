package vhslab.solution.service.interfaces;

import vhslab.solution.entities.dto.RentalEntityDto;

public interface IRentalEntityService {
    RentalEntityDto rentVhs(long userId, long vhsId);
    RentalEntityDto returnRentedVhs(long rentalId);
    long calculateLateReturnFee(long rentalId);
}
