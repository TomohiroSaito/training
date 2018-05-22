package sample.dao;

import org.apache.ibatis.annotations.Select;

import sample.biz.domain.Pet;

@MyMapper
public interface PetMapper {
	Pet selectPet(String id);
	
	@Select("SELECT * FROM PET WHERE PET_ID=#{petId}")
	Pet selectPetAnnotation(String id);
}
