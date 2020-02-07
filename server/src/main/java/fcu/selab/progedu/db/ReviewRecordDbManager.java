package fcu.selab.progedu.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fcu.selab.progedu.utils.ExceptionUtil;

public class ReviewRecordDbManager {
  private static ReviewRecordDbManager dbManager = new ReviewRecordDbManager();

  public static ReviewRecordDbManager getInstance() {
    return dbManager;
  }

  private IDatabase database = new MySqlDatabase();

  private static final Logger LOGGER = LoggerFactory.getLogger(ReviewRecordDbManager.class);

  private ReviewRecordDbManager() {

  }
  
  /**
   * insert Review_Record into database
   * 
   * @param pmId          pmId
   * @param ruleId        rId
   * @param score         score  
   * @param time          record time
   * @param feedback      feedback
   */
  public void addRevRecord(int pmId, int ruleId, boolean score, Date time, String feedback) {
    
  }

  /**
   * get Review's Record by pmId and rId
   *
   * @param pmId Pair_Matching Id
   * @param ruleId Review_Rule Id
   * @return score, time and feedback
   */
  public JSONObject getRevRecord(int pmId, int ruleId) {
    JSONObject ob = new JSONObject();
    return ob;
  }

  /**
   * get all Review's Record Score List by pmId
   *
   * @param pmId Pair_Matching pmId
   * @return lsRecord list of score, time and feedback
   *          which score by specific student in specific assignment
   */
  public JSONObject getRevRecordList(int pmId) {
    JSONObject ob = new JSONObject();
    return ob;
  }

  /**
   * delete Review_Record of specific Pair_Matching Id
   *
   * @param pmId pmId
   */
  public void deleteRevRecord(int pmId) {

  }
}
