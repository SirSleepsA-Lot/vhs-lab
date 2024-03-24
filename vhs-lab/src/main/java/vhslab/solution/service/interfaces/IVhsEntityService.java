package vhslab.solution.service.interfaces;

import vhslab.solution.entities.dto.VhsEntityDto;

import java.util.List;

public interface IVhsEntityService {
    List<VhsEntityDto> getAllVhsEntities();
    VhsEntityDto createVHS(VhsEntityDto vhsDto);
    VhsEntityDto getVHSById(Long id);
    VhsEntityDto updateVHS(Long id, VhsEntityDto updatedVhsDto) throws Exception;
    void deleteVHSById(Long id);
}
