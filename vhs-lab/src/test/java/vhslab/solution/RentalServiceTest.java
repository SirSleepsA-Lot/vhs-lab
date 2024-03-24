package vhslab.solution;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import vhslab.solution.DAL.interfaces.RentalEntityRepository;
import vhslab.solution.DAL.interfaces.UserEntityRepository;
import vhslab.solution.DAL.interfaces.VHSEntityRepository;
import vhslab.solution.entities.dto.RentalEntityDto;
import vhslab.solution.entities.model.RentalEntity;
import vhslab.solution.entities.model.UserEntity;
import vhslab.solution.entities.model.VhsEntity;
import vhslab.solution.service.RentalEntityService;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RentalServiceTest {

    @Mock
    private RentalEntityRepository rentalRepository;

    @Mock
    private UserEntityRepository userRepository;

    @Mock
    private VHSEntityRepository vhsRepository;
    @Mock
    private RentalEntityService rentalEntityService2;

    @Mock
    private ModelMapper modelMapper;

    @Spy
    @InjectMocks
    private RentalEntityService rentalEntityService;


    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testRentVhs_SuccessfulRental() throws Exception {
        long userId = 1;
        long vhsId = 2;
        Date dateRented = new Date(System.currentTimeMillis());
        Date dateDue = new Date(System.currentTimeMillis() + 86400000); // Dodan jedan dan
        RentalEntityDto expectedRentalEntityDto = new RentalEntityDto(dateRented, dateDue, userId, vhsId);

        doReturn(expectedRentalEntityDto).when(rentalEntityService).createRental(any(RentalEntityDto.class));
        when(rentalRepository.existsRentalForVhsOnDate(any(long.class), any(Date.class))).thenReturn(false);

        RentalEntityDto rentalEntityDto = rentalEntityService.rentVhs(userId, vhsId, dateRented, dateDue);
        verify(rentalRepository, times(1)).existsRentalForVhsOnDate(vhsId, dateRented);
        verify(rentalEntityService, times(1)).createRental(any(RentalEntityDto.class));
        Assertions.assertEquals(dateRented, rentalEntityDto.getDateRented());
        Assertions.assertEquals(dateDue, rentalEntityDto.getDateDue());
        Assertions.assertEquals(userId, rentalEntityDto.getUserId());
        Assertions.assertEquals(vhsId, rentalEntityDto.getVhsId());
    }
    @Test
    public void testRentVhs_UnsuccessfulRental() throws Exception {
        long userId = 1;
        long vhsId = 2;
        Date dateRented = new Date(System.currentTimeMillis());
        Date dateDue = new Date(System.currentTimeMillis() + 86400000); // Dodan jedan dan
        RentalEntityDto expectedRentalEntityDto = new RentalEntityDto(dateRented, dateDue, userId, vhsId);

        when(modelMapper.map(any(), eq(RentalEntity.class))).thenReturn(new RentalEntity());
        when(modelMapper.map(any(), eq(RentalEntityDto.class))).thenReturn(expectedRentalEntityDto);
        when(rentalRepository.save(any())).thenReturn(new RentalEntity());
        when(rentalRepository.existsRentalForVhsOnDate(any(long.class), any(Date.class))).thenReturn(true);

        assertThrows(Exception.class, () -> {
            rentalEntityService.rentVhs(userId, vhsId, dateRented, dateDue);
        });

        // Verify that the repository methods are not called
        verify(rentalRepository, never()).save(any(RentalEntity.class));
    }
    @Test
    public void testReturnRentedVhs_SuccessfulReturn() throws Exception {
        // Arrange

        long rentalId = 1;
        RentalEntity rentalEntity = new RentalEntity();
        rentalEntity.setDateReturned(null); // Rental not returned yet
        RentalEntityDto rentalEntityDto = new RentalEntityDto();
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rentalEntity));
        when(modelMapper.map(rentalEntity, RentalEntityDto.class)).thenReturn(rentalEntityDto);
        doReturn(BigDecimal.ZERO).when(rentalEntityService).calculateLateReturnFee(any(RentalEntity.class));
        //when(spyService.calculateLateReturnFee(any())).thenReturn(BigDecimal.ZERO);

        // Act
        RentalEntityDto result = rentalEntityService.returnRentedVhs(rentalId);

        // Assert
        verify(rentalRepository, times(1)).findById(rentalId);
        verify(rentalRepository, times(1)).save(rentalEntity);
        Assertions.assertEquals(rentalEntityDto, result);
    }

    @Test
    public void testReturnRentedVhs_RentalNotFound() {
        // Arrange
        long rentalId = 1;
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(Exception.class, () -> rentalEntityService.returnRentedVhs(rentalId));
        verify(rentalRepository, times(1)).findById(rentalId);
        verify(rentalRepository, never()).save(any(RentalEntity.class));
    }

    @Test
    public void testReturnRentedVhs_AlreadyReturned() {
        // Arrange
        long rentalId = 1;
        RentalEntity rentalEntity = new RentalEntity();
        rentalEntity.setDateReturned(new Date(System.currentTimeMillis())); // Rental already returned
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rentalEntity));

        // Act and Assert
        assertThrows(Exception.class, () -> rentalEntityService.returnRentedVhs(rentalId));
        verify(rentalRepository, times(1)).findById(rentalId);
        verify(rentalRepository, never()).save(any(RentalEntity.class));
    }

}