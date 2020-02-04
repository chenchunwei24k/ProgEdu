package fcu.selab.progedu.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fcu.selab.progedu.utils.ExceptionUtil;

public class PairMatchingDbManager {
  private static PairMatchingDbManager dbManager = new PairMatchingDbManager();

  public static PairMatchingDbManager getInstance() {
    return dbManager;
  }

  private IDatabase database = new MySqlDatabase();

  private static final Logger LOGGER = LoggerFactory.getLogger(PairMatchingDbManager.class);

  private PairMatchingDbManager() {

  }

  /**
   * Add PairMatching to database
   *
   * @param auId AssignmentUser Id
   * @param reviewId User Id
   */
  public void addPairMatching(int auId, int reviewId) {

  }

  /**
   * get pmId by assignmentUser Id and user Id
   *
   * @param auId AssignmentUser Id
   * @param reviewId User Id
   * @return pmId PairMatching Id
   */
  public int getPmId(int auId, int reviewId) {
    return 0;
  }

  /**
   * get pmIds by assignmentUser Id
   *
   * @param auId AssignmentUser Id
   * @return pmId PairMatching Id
   */
  public List<Integer> getIdListByAuId(int auId) {
    List<Integer> lsPmId = new ArrayList<>();
    return lsPmId;
  }

  /**
   * get pmIds by user Id
   *
   * @param reviewId User Id
   * @return pmId PairMatching Id
   */
  public List<Integer> getIdListByUId(int reviewId) {
    List<Integer> lsPmId = new ArrayList<>();
    return lsPmId;
  }

  /**
   * get uIds by assignmentUser Id
   *
   * @param auId AssignmentUser Id
   * @return pmId User Id
   */
  public List<Integer> getUIdListByAuId(int auId) {
    List<Integer> lsUId = new ArrayList<>();
    return lsUId;
  }

  /**
   * get auIds by user Id
   *
   * @param reviewId User Id
   * @return pmId PairMatching Id
   */
  public List<Integer> getAuIdListByUId(int reviewId) {
    List<Integer> lsAuId = new ArrayList<>();
    return lsAuId;
  }

  /**
   * Delete Pair_Matching from database by assignment_user id
   *
   * @param auId AssignmentUser Id
   */
  public void deletePairMatchingByAuId(int auId) {

  }

  /**
   * Delete Pair_Matching from database by User id
   *
   * @param reviewId User Id
   */
  public void deletePairMatchingByUId(int reviewId) {

  }

}
