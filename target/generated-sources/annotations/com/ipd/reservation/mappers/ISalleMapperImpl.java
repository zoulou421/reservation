package com.ipd.reservation.mappers;

import com.ipd.reservation.dto.SalleDto;
import com.ipd.reservation.dto.SeanceDto;
import com.ipd.reservation.entities.Salle;
import com.ipd.reservation.entities.Seance;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T09:45:48+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class ISalleMapperImpl implements ISalleMapper {

    @Override
    public SalleDto toDto(Salle salle) {
        if ( salle == null ) {
            return null;
        }

        SalleDto salleDto = new SalleDto();

        salleDto.setIdDto( salle.getId() );
        salleDto.setNomDto( salle.getNom() );
        salleDto.setCapaciteDto( salle.getCapacite() );
        salleDto.setSeancesDto( seanceListToSeanceDtoList( salle.getSeances() ) );

        return salleDto;
    }

    @Override
    public Salle toEntity(SalleDto salleDto) {
        if ( salleDto == null ) {
            return null;
        }

        Salle salle = new Salle();

        salle.setId( salleDto.getIdDto() );
        salle.setNom( salleDto.getNomDto() );
        salle.setCapacite( salleDto.getCapaciteDto() );
        salle.setSeances( seanceDtoListToSeanceList( salleDto.getSeancesDto() ) );

        return salle;
    }

    @Override
    public List<SalleDto> toDto(List<Salle> salles) {
        if ( salles == null ) {
            return null;
        }

        List<SalleDto> list = new ArrayList<SalleDto>( salles.size() );
        for ( Salle salle : salles ) {
            list.add( toDto( salle ) );
        }

        return list;
    }

    @Override
    public List<Salle> toEntity(List<SalleDto> salleDtos) {
        if ( salleDtos == null ) {
            return null;
        }

        List<Salle> list = new ArrayList<Salle>( salleDtos.size() );
        for ( SalleDto salleDto : salleDtos ) {
            list.add( toEntity( salleDto ) );
        }

        return list;
    }

    protected SeanceDto seanceToSeanceDto(Seance seance) {
        if ( seance == null ) {
            return null;
        }

        SeanceDto seanceDto = new SeanceDto();

        return seanceDto;
    }

    protected List<SeanceDto> seanceListToSeanceDtoList(List<Seance> list) {
        if ( list == null ) {
            return null;
        }

        List<SeanceDto> list1 = new ArrayList<SeanceDto>( list.size() );
        for ( Seance seance : list ) {
            list1.add( seanceToSeanceDto( seance ) );
        }

        return list1;
    }

    protected Seance seanceDtoToSeance(SeanceDto seanceDto) {
        if ( seanceDto == null ) {
            return null;
        }

        Seance seance = new Seance();

        return seance;
    }

    protected List<Seance> seanceDtoListToSeanceList(List<SeanceDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Seance> list1 = new ArrayList<Seance>( list.size() );
        for ( SeanceDto seanceDto : list ) {
            list1.add( seanceDtoToSeance( seanceDto ) );
        }

        return list1;
    }
}
