package sample.domain;

import sample.biz.domain.Owner;

public interface FriendlyDao {

	Owner getOwner(String i);

	void insertOwner(Owner owner);

	void updateOwner(Owner owner);

}
