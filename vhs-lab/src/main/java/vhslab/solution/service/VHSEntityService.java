package vhslab.solution.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vhslab.solution.DAL.interfaces.VHSEntityRepository;
import vhslab.solution.entities.dto.VhsEntityDto;
import vhslab.solution.entities.model.VhsEntity;
import vhslab.solution.service.interfaces.IVhsEntityService;

@Service
public class VHSEntityService implements IVhsEntityService {
    private final VHSEntityRepository vhsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VHSEntityService(VHSEntityRepository vhsRepository, ModelMapper modelMapper) {
        this.vhsRepository = vhsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VhsEntityDto createVHS(VhsEntityDto vhsDto) {
        VhsEntity vhsEntity = modelMapper.map(vhsDto, VhsEntity.class);
        VhsEntity savedVhsEntity = vhsRepository.save(vhsEntity);
        return modelMapper.map(savedVhsEntity, VhsEntityDto.class);
    }

    @Override
    public VhsEntityDto getVHSById(Long id) {
        VhsEntity vhsEntity = vhsRepository.findById(id).orElse(null);
        return vhsEntity != null ? modelMapper.map(vhsEntity, VhsEntityDto.class) : null;
    }

    @Override
    public VhsEntityDto updateVHS(Long id, VhsEntityDto updatedVhsDto) {
        VhsEntity existingVhsEntity = vhsRepository.findById(id).orElse(null);
        if (existingVhsEntity != null) {
            modelMapper.map(updatedVhsDto, existingVhsEntity);
            VhsEntity updatedVhsEntity = vhsRepository.save(existingVhsEntity);
            return modelMapper.map(updatedVhsEntity, VhsEntityDto.class);
        }
        return null;
    }

    @Override
    public void deleteVHSById(Long id) {
        vhsRepository.deleteById(id);
    }
}
