package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.Consultas;
import com.consultorio.dental_s.Entities.Hallazgos;
import com.consultorio.dental_s.Models.ConsultasDTO;
import com.consultorio.dental_s.Models.HallazgosDTO;
import lombok.Data;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {HallazgosMapper.class, CatalogoProcedimientosMapper.class, CitasMapper.class})
public interface ConsultasMapper {

    @Mapping(target = "hallazgos", source = "hallazgos")
    @Mapping(target = "catalogoProcedimientos", source = "catalogoProcedimientos")
    @Mapping(target = "cita", source = "cita")
    Consultas toConsultasEntity(ConsultasDTO consultasDTO);

    @InheritInverseConfiguration
    @Mapping(target = "hallazgos", source = "hallazgos")
    @Mapping(target = "catalogoProcedimientos", source = "catalogoProcedimientos")
    @Mapping(target = "cita", source = "cita")
    ConsultasDTO toConsultasDTO(Consultas consultas);
}
