package com.nttdata.proyecto01.movementservice._5Utils.convert;

import com.nttdata.proyecto01.movementservice._3Domain.model.MovementDTO;
import com.nttdata.proyecto01.movementservice._4Infraestructure.data.document.Movement;
import org.springframework.beans.BeanUtils;

public class Convert {
    public static MovementDTO entityToDto(Movement movement){
        MovementDTO movementDTO=new MovementDTO();
        BeanUtils.copyProperties(movement,movementDTO);
        return movementDTO;
    }
    public static Movement DtoToEntity(MovementDTO movementDTO){
        Movement movement=new Movement();
        BeanUtils.copyProperties(movementDTO,movement);
        return movement;
    }
}
