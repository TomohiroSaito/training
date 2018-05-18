package sample;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import sample.biz.domain.Owner;

public class SampleRun {

	public static void main(String[] args) {
		try {
			Reader reader = Resources.getResourceAsReader("sample/sqlMapConfig.xml");
			SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
			
			Owner owner = (Owner)sqlMap.queryForObject("findOwner", "2");
			System.out.println("OwnerId = " + owner.getOwnerId() + " :OwnerName = " + owner.getOwnerName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
