package vhslab.solution.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vhslab.solution.DAL.interfaces.VHSEntityRepository;
import vhslab.solution.entities.dto.VhsEntityDto;
import vhslab.solution.entities.model.VhsEntity;
import vhslab.solution.service.interfaces.IVhsEntityService;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<VhsEntityDto> getAllVhsEntities(){
        var foundVhs = vhsRepository.findAll();
        return foundVhs.stream()
                .map(vhsEntity -> modelMapper.map(vhsEntity, VhsEntityDto.class))
                .collect(Collectors.toList());
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
    public VhsEntityDto updateVHS(Long id, VhsEntityDto updatedVhsDto) throws Exception {
        VhsEntity existingVhsEntity = vhsRepository.findById(id).orElse(null);
        if (existingVhsEntity != null) {
            VhsEntity updatedVhsEntity = modelMapper.map(updatedVhsDto, VhsEntity.class);
            updatedVhsEntity.setId(existingVhsEntity.getId());
            updatedVhsEntity.setDateCreated(existingVhsEntity.getDateCreated());
            updatedVhsEntity = vhsRepository.save(updatedVhsEntity);
            return modelMapper.map(updatedVhsEntity, VhsEntityDto.class);
        }
        else{
            throw new Exception("update Error");
        }
    }

    @Override
    public void deleteVHSById(Long id) {
        vhsRepository.deleteById(id);
    }
}
