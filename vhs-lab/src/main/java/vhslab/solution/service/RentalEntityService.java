package vhslab.solution.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vhslab.solution.DAL.interfaces.RentalEntityRepository;
import vhslab.solution.DAL.interfaces.UserEntityRepository;
import vhslab.solution.entities.dto.RentalEntityDto;
import vhslab.solution.entities.model.RentalEntity;
import vhslab.solution.service.interfaces.IRentalEntityService;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class RentalEntityService implements IRentalEntityService {
    private final ModelMapper modelMapper;
    private final RentalEntityRepository rentalRepository;

    public RentalEntityService(ModelMapper modelMapper, RentalEntityRepository rentalRepository) {
        this.modelMapper = modelMapper;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public RentalEntityDto rentVhs(long userId, long vhsId, Date dateRented) throws Exception {
        var existingRental = rentalRepository.findByVhsByVhsId_IdAndDateRented(vhsId, dateRented);
        if(existingRental.isEmpty()) throw new Exception("VHS already rented");
        var rentalEntityDto = new RentalEntityDto(dateRented, vhsId, userId);

        var savedRentalEntity = rentalRepository.save(modelMapper.map(rentalEntityDto, RentalEntity.class));
        return modelMapper.map(savedRentalEntity, RentalEntityDto.class);
    }

    @Override
    public RentalEntityDto returnRentedVhs(long rentalId) throws Exception {
        var rental = rentalRepository.findById(rentalId);

        if(rental.isEmpty()) throw new Exception("No such rental exists in our database");
        var rentalEntity = rental.get();

        var dateReturned = Date.valueOf(LocalDate.now());

        rentalEntity.setDateReturned(dateReturned);
        rentalEntity.setDateModified(dateReturned);
        rentalRepository.save(rentalEntity);

        var fee = calculateLateReturnFee(rentalEntity);
        return modelMapper.map(rentalEntity, RentalEntityDto.class);
    }

    @Override
    public BigDecimal calculateLateReturnFee(RentalEntity rental) {

        LocalDate localDate1 = rental.getDateReturned().toLocalDate();
        LocalDate localDate2 = rental.getDateDue().toLocalDate();
        var dateDifference = ChronoUnit.DAYS.between(localDate1, localDate2);

        return BigDecimal.valueOf(dateDifference).multiply(rental.getVhsByVhsId().getLateReturnFee());
    }
}
