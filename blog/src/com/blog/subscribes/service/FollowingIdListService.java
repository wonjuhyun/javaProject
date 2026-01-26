public class FollowingIdListService implements Service {

	@Override
	public Object service(Object obj) throws Exception {

    String loginId = (String) obj; // 넘어온 obj를 다시 String 타입으로 변환
		SubscribesDAO dao = new SubscribesDAO();
		return dao.list(loginId);
	}
}
