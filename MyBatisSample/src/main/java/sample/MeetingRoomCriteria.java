package sample;

public class MeetingRoomCriteria {
	private Integer capacity;
	
	RowBounds rowBounds = new RowBounds(5, 5);
	List<MeetingRoom> meetingRooms = mapper.findAll(rowBounds);
	
	meetingRoomMapper.collectAll(context -> {
		int resultPosition = context.getResultCount();
		MeetingRoom meetingRoom = context.getResultObject();
	});
}
