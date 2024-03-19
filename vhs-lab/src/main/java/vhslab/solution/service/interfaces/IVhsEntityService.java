package vhslab.solution.service.interfaces;

import vhslab.solution.entities.dto.VhsEntityDto;

public interface IVhsEntityService {
    VhsEntityDto createVHS(VhsEntityDto vhsDto);
    VhsEntityDto getVHSById(Long id);
    VhsEntityDto updateVHS(Long id, VhsEntityDto updatedVhsDto);
    void deleteVHSById(Long id);
}
