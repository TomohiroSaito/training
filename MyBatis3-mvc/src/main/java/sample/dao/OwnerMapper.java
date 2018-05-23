package sample.dao;

import org.springframework.stereotype.Component;

import sample.biz.domain.Owner;

@MyMapper
public interface OwnerMapper {
	Owner selectOwner(String id);
}
