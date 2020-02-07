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
    String sql = "INSERT INTO Review_Record (pmId, rId, score, time, feedback) "
         + "VALUES(?, ?, ?, ?, ?)";
    Timestamp date = new Timestamp(time.getTime());

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, pmId);
      preStmt.setInt(2, ruleId);
      preStmt.setBoolean(3, score);
      preStmt.setTimestamp(4, date);
      preStmt.setString(5, feedback);
      preStmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
  }

  /**
   * get Review's Record by pmId and rId
   *
   * @param pmId Pair_Matching Id
   * @param ruleId Review_Rule Id
   * @return score, time and feedback
   */
  public JSONObject getRevRecord(int pmId, int ruleId) {
    String sql = "SELECT score, time, feedback FROM Review_Record WHERE pmId = ? AND rId = ?";
    JSONObject ob = new JSONObject();

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, pmId);
      preStmt.setInt(2, ruleId);

      try (ResultSet rs = preStmt.executeQuery()) {
        while (rs.next()) {
          Boolean score = rs.getBoolean("score");
          Date time = rs.getTimestamp("time");
          String feedback = rs.getString("feedback");
          ob.put("score", score);
          ob.put("time", time);
          ob.put("feedback", feedback);
        }
      }
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
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
    String sql = "SELECT score, time, feedback FROM Review_Record WHERE pmId = ?";
    JSONObject ob = new JSONObject();

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, pmId);

      try (ResultSet rs = preStmt.executeQuery()) {
        while (rs.next()) {
          Boolean score = rs.getBoolean("score");
          Date time = rs.getTimestamp("time");
          String feedback = rs.getString("feedback");
          ob.put("score", score);
          ob.put("time", time);
          ob.put("feedback", feedback);
        }
      }
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
    return ob;
  }

  /**
   * delete Review_Record of specific Pair_Matching Id
   *
   * @param pmId pmId
   */
  public void deleteRevRecord(int pmId) {
    String sql = "DELETE FROM Review_Record WHERE pmId = ?";
    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, pmId);
      preStmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
  }
}
