package com.emanuelrichieri.todoapp.utils;

import com.emanuelrichieri.todoapp.dto.IEntityDTO;

public interface IEntityDTOMapper {

	/***
	 * Converts entity to an IEntityDTO
	 * @param entity
	 * @return IEntityDTO - DTO that represents the entity
	 */
	public IEntityDTO convertToDTO(Object entity);
	
	/***
	 * Converts a DTO to the respective entity
	 * @param entityDTO
	 * @return Object entity 
	 */
	public Object convertToEntity(IEntityDTO entityDTO);
}
