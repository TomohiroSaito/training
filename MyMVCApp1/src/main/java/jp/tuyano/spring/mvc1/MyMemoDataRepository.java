package jp.tuyano.spring.mvc1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyMemoDataRepository extends JpaRepository<MyMemoData, Long> {

}
