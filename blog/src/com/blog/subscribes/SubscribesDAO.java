// 5-1. 내가 구독한 사람 리스트
public List<Subscribes> followingList(String loginId) throws Exception {
	List<Subscribes> list = new ArrayList<>();

  try { // try~catch : 에러방지
    // 드라이버 확인+연결 객체
    con = DB.getConnection();
    
    // 실행할 쿼리 작성
    String sql = " select following_id from subscribes where follower_id = ? order by following_id asc";
    
    // 실행 객체+데이터 세팅
    pstmt = con.prepareStatement(sql);
    pstmt.setString(1, loginId);
    
    // 실행
    rs = pstmt.executeQuery();
    
    // DB에서 가져온 데이터 채우기
    if (rs != null) {
      while (rs.next()) {
        Subscribes vo = new Subscribes();
        vo.setFollowingId(rs.getString("following_id"));
        list.add(vo);
      }
    }
  } catch (Exception e) {
    e.printStackTrace();
    throw e;
    
  } finally {
    DB.close(con, pstmt, rs);
  }
  return list;
}

// 5-1-1. 구독하기
public int insert(Subscribes vo) throws Exception {
  int result = 0;

  try {
    con = DB.getConnection();

    String sql = " insert into subscribes(sub_no, following_id, follower_id) "
      + " values(subscribes_seq.nextval, ?, ?) ";

    pstmt = con.prepareStatement(sql);

    pstmt.setString(1, vo.getFollowingId());
    pstmt.setString(2, vo.getFollowerId());

    result = pstmt.executeUpdate();

  } catch (Exception e) {
    e.printStackTrace();
    throw e;
  } finally {
    DB.close(con, pstmt);
  }

  return result;
}

// 5-1-2. 구독 취소하기
public int delete(Subscribes vo) throws Exception {
  int result = 0;

  try {
    con = DB.getConnection();

    String sql = " delete from subscribes where following_id = ? and follower_id = ?";

    pstmt = con.prepareStatement(sql);

    pstmt.setString(1, vo.getFollowingId());
    pstmt.setString(2, vo.getFollowerId());

    result = pstmt.executeUpdate();

  } catch (Exception e) {
    e.printStackTrace();
    throw e;
  } finally {
    DB.close(con, pstmt);
  }

  return result;
}

// 5-2. 나를 구독한 사람 리스트
public List<Subscribes> followerList(String loginId) throws Exception {
	List<Subscribes> list = new ArrayList<>();

  try { // try~catch : 에러방지
    // 드라이버 확인+연결 객체
    con = DB.getConnection();
    
    // 실행할 쿼리 작성
    String sql = " select follower_id from subscribes where following_id = ? order by follower_id asc";
    
    // 실행 객체+데이터 세팅
    pstmt = con.prepareStatement(sql);
    pstmt.setString(1, loginId);
    
    // 실행
    rs = pstmt.executeQuery();
    
    // DB에서 가져온 데이터 채우기
    if (rs != null) {
      while (rs.next()) {
        Subscribes vo = new Subscribes();
        vo.setFollowerId(rs.getString("follower_id"));
        list.add(vo);
      }
    }
  } catch (Exception e) {
    e.printStackTrace();
    throw e;
    
  } finally {
    DB.close(con, pstmt, rs);
  }
  
  return list;
}
