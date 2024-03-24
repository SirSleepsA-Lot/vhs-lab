package vhslab.solution.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vhslab.solution.DAL.interfaces.RentalEntityRepository;
import vhslab.solution.DAL.interfaces.UserEntityRepository;
import vhslab.solution.DAL.interfaces.VHSEntityRepository;
import vhslab.solution.entities.dto.RentalEntityDto;
import vhslab.solution.entities.model.RentalEntity;
import vhslab.solution.entities.model.UserEntity;
import vhslab.solution.entities.model.VhsEntity;
import vhslab.solution.service.interfaces.IRentalEntityService;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalEntityService implements IRentalEntityService {
    private final ModelMapper modelMapper;
    private final RentalEntityRepository rentalRepository;
    private final UserEntityRepository userRepository;
    private final VHSEntityRepository vhsRepository;
    private static final Logger logger = LoggerFactory.getLogger(RentalEntityService.class);


    public RentalEntityService(ModelMapper modelMapper, RentalEntityRepository rentalRepository, UserEntityRepository userRepository, VHSEntityRepository vhsRepository) {
        this.modelMapper = modelMapper;
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.vhsRepository = vhsRepository;
    }

    public RentalEntity convertToEntity(RentalEntityDto rentalDto){
        RentalEntity rentalEntity = modelMapper.map(rentalDto, RentalEntity.class);
        UserEntity userEntity = userRepository.findById(rentalDto.getUserId()).orElse(null);
        VhsEntity vhsEntity = vhsRepository.findById(rentalDto.getVhsId()).orElse(null);
        rentalEntity.setUserByUserId(userEntity);
        rentalEntity.setVhsByVhsId(vhsEntity);
        return rentalEntity;
    }

    @Override
    public RentalEntityDto getRentalById(long rentalId) throws Exception {
        var rentalEntity = rentalRepository.findById(rentalId);
        if(rentalEntity.isEmpty()) throw new Exception("Rental records not found");

        var rental = rentalEntity.get();
        return modelMapper.map(rental, RentalEntityDto.class);
    }

    @Override
    public RentalEntityDto createRental(RentalEntityDto rentalEntityDto) {
        var savedRentalEntity = rentalRepository.save(convertToEntity(rentalEntityDto));
        return modelMapper.map(savedRentalEntity, RentalEntityDto.class);

    }

    @Override
    public RentalEntityDto updateRental(long rentalId, RentalEntityDto rentalDetails) {
        RentalEntity existingRental = rentalRepository.findById(rentalId).orElse(null);
        if (existingRental != null) {
            var rentalUpdated = convertToEntity(rentalDetails);
            rentalUpdated.setId(existingRental.getId());
            rentalUpdated.setDateCreated(existingRental.getDateCreated());
            rentalUpdated = rentalRepository.save(rentalUpdated);
            return modelMapper.map(rentalUpdated, RentalEntityDto.class);
        }
        return null;
    }

    @Override
    public void deleteRental(long rentalId) {
        rentalRepository.deleteById(rentalId);
    }

    @Override
    public RentalEntityDto rentVhs(long userId, long vhsId, Date dateRented, Date dateDue) throws Exception {
        var existingRental = rentalRepository.existsRentalForVhsOnDate(vhsId, dateRented);
        if(existingRental) throw new Exception("VHS already rented");

        var rentalEntityDto = new RentalEntityDto(dateRented, dateDue, userId, vhsId);

        return createRental(rentalEntityDto);
    }

    @Override
    public RentalEntityDto returnRentedVhs(long rentalId) throws Exception {
        var rental = rentalRepository.findById(rentalId);

        if(rental.isEmpty()) throw new Exception("No such rental exists in our database");
        var rentalEntity = rental.get();
        if(rentalEntity.getDateReturned() != null) throw new Exception("Vhs already returned");

        rentalEntity.setDateReturned(new Date(System.currentTimeMillis()));

        var fee = calculateLateReturnFee(rentalEntity);
        if(fee.compareTo(BigDecimal.ZERO) != 0){
            rentalEntity.setFee(fee);
            rentalEntity.setFeePaid(false);
        }
        else{
            rentalEntity.setFeePaid(true);
        }

        rentalRepository.save(rentalEntity);
        return modelMapper.map(rentalEntity, RentalEntityDto.class);
    }

    @Override
    public BigDecimal calculateLateReturnFee(RentalEntity rental) {

        var difference = rental.getDateReturned().compareTo(rental.getDateDue());
        if(difference <= 0){
            return BigDecimal.ZERO;
        }
        else{
            return BigDecimal.valueOf(difference).multiply(rental.getVhsByVhsId().getLateReturnFee());
        }
    }

    @Override
    public Boolean payFee(long rentalId) throws Exception {
        var rental = rentalRepository.findById(rentalId);

        if(rental.isEmpty()) throw new Exception("No such rental exists in our database");
        var rentalEntity = rental.get();

        rentalEntity.setFeePaid(true);
        rentalRepository.save(rentalEntity);

        return true;
    }
    @Override
    public List<RentalEntityDto> getAllRentalEntities() {
        var foundRentals = rentalRepository.findAll();
        return foundRentals.stream()
                .map(rentalEntity -> modelMapper.map(rentalEntity, RentalEntityDto.class))
                .collect(Collectors.toList());
    }
}
