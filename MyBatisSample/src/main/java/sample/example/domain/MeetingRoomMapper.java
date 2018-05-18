package sample.example.domain;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import sample.MeetingRoomCriteria;

public interface MeetingRoomMapper {
	MeetingRoom findOne(String roomId);
	void create(
			@Param("roomId") String roomId,
			@Param("roomName") String roomName,
			@Param("capacity") int capacity);
	
	List<MeetingRoom> findAllByCriteria(
			@Param("criteria") MeetingRoomCriteria criteria,
			@Param("orderByColumn") String orderByColumn);
	
	List<MeetingRoom> findRangeByCapacity(int capacity, RowBounds rouBounds);
	void collectByCapacity(int capacity, ResultHandler resultHandler);
	
	MeetingRoom findOne(String roomId);
	long count();
	List<MeetingRoom> findAll();
	
	@Select("SELECT room_id AS roomId, room_name AS roomName, capacity FROM meeting_room WHERE room_id = #{roomId}")
	MeetingRoom findOne(String roomId);
	
	@Results({
		@Result(column = "room_id", property = "roomId", id = true),
		@Result(column = "room_name", property = "roomName")
	})
	@Select("SELECT room_id, room_name, capacity FROM meeting_room WHERE room_id = #{roomId")
	MeetingRoom findOne(String roomId);
	
	List<MeetingRoom> findByRoomIds(List<String> roomIds);
	
	boolean update(MeetingRoom meetingRoom);
	
	@SelectProvider(type = MeetingRoomSqlProvider.class, method = "findByCriteria")
	MeetingRoom findByCriteria(MeetingRoomCriteria criteria);
	
	class MeetingRoomSqlProvider {
		
		public String findByCriteria(final MeetingRoomCriteria criteria) {
			return new SQL() {{
				SELECT("room_id AS roomId, room_name AS roomName, capacity");
				FROM("meeting_room");
				if (criteria.getRoomId() != null) {
					WHERE("room_id like #{roomId} || '%'");
				}
				if (criteria.getRoomName() != null) {
					WHERE("room_name like #{roomName} || '%'");
				}
				if (criteria.getCapacity() != null) {
					WHERE("capacity >= #{capacity}");
				}
				ORDER_BY("room_id");
			}}.toString();
	}
		
	List<MeetingRoom> findAll(RowBounds rouBounds);
	
	void collectAll(ResultHandler<MeetingRoom> resultHandler);
}
