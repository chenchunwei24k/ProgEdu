package fcu.selab.progedu.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
   * @param rId           rId
   * @param status        status
   * @param score         score  
   * @param time          record time
   * @param feedback      feedback
   */
  public void addRevRecord(int pmId, int rId, int status, boolean score, Date time, String feedback) {
    
  }

  /**
   * get Review's Record by pmId and rId
   *
   * @param pmId Pair_Matching Id
   * @param rId Review_Rule Id
   * @return score which score by designated 
   */
   public boolean getRevRecord(int pmId, int rId) {
     return false;
   }

  /**
   * get all Review's Record Lsit by pmId
   *
   * @param pmId Pair_Matching pmId
   * @return lsRecord list of score which score by designated student in designated assignment
   */
   public List<boolean> getRevRecordList(int pmId) {
     List<boolean> lsRecord = new ArrayList<>();
     return lsRecord;
   }  
}
