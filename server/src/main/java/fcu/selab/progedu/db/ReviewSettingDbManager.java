package fcu.selab.progedu.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Timestamp;

import fcu.selab.progedu.data.ReviewSetting;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fcu.selab.progedu.utils.ExceptionUtil;

public class ReviewSettingDbManager {
  private static ReviewSettingDbManager dbManager = new ReviewSettingDbManager();

  public static ReviewSettingDbManager getInstance() {
    return dbManager;
  }

  private IDatabase database = new MySqlDatabase();

  private static final Logger LOGGER = LoggerFactory.getLogger(ReviewSettingDbManager.class);

  private ReviewSettingDbManager() {

  }

  /**
   * Add Review_Setting to database
   * 
   * @param aid assignment id
   * @param amount review count
   * @param startTime review start time
   * @param endTime review end time
   */
  public void addRevSetting(int aid, int amount, Date startTime, Date endTime) {
    String query = "INSERT INTO Review_Setting (aId, amount, startTime, endTime) "
        + "VALUES (?, ?, ?, ?)";
    Timestamp start = new Timestamp(startTime.getTime());
    Timestamp end = new Timestamp(endTime.getTime());

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(query)) {
      preStmt.setInt(1, aid);
      preStmt.setInt(2, amount);
      preStmt.setTimestamp(3, start);
      preStmt.setTimestamp(4, end);
      preStmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
  }

  /**
   * Get Review_Setting Id by Assignment Id
   *
   * @param aid Assignment Id
   * @return Id Review_Setting Id
   */
  public int getRevSettingId(int aid) {
    String query = "SELECT id FROM Review_Setting WHERE aId = ?";
    int id = 0;

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(query)) {
      preStmt.setInt(1, aid);

      try (ResultSet rs = preStmt.executeQuery();) {
        while (rs.next()) {
          id = rs.getInt("id");
        }
      }
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
    return id;
  }

  /**
   * Get Review_Setting amount by Assignment Id
   *
   * @param aid Assignment Id
   * @return amount Review_Setting amount
   */
  public int getRevSettingAmount(int aid) {
    String query = "SELECT amount FROM Review_Setting WHERE aId = ?";
    int amount = 0;

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(query)) {
      preStmt.setInt(1, aid);

      try (ResultSet rs = preStmt.executeQuery();) {
        while (rs.next()) {
          amount = rs.getInt("id");
        }
      }
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
    return amount;
  }

  /**
   * Get Review_Setting amount by Assignment Id
   *
   * @param aid Assignment Id
   * @return lsSetting list of amount, startTime and endTime in specific assignment
   */
  public ReviewSetting getRevSettingList(int aid) {
    String sql = "SELECT id, aId, amount, startTime, endTime FROM Review_Setting WHERE aId = ?";
    ReviewSetting reviewSetting = new ReviewSetting();

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, aid);

      try (ResultSet rs = preStmt.executeQuery()) {
        while (rs.next()) {
          reviewSetting.setId(rs.getInt("id"));
          reviewSetting.setAid(rs.getInt("aId"));
          reviewSetting.setAmount(rs.getInt("amount"));
          reviewSetting.setStartTime(rs.getTimestamp("startTime"));
          reviewSetting.setEndTime(rs.getTimestamp("endTime"));
        }
      }
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
    return reviewSetting;
  }

  /**
   * Delete Review_Setting from database
   * 
   * @param aid assignment id
   */
  public void deleteRevSetting(int aid) {
    String sql = "DELETE FROM Review_Setting WHERE aId = ?";

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, aid);
      preStmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
  }

  /**
   * Edit Review_Setting
   *
   * @param amount      amount
   * @param aid     assignment id
   */
  public void editRevSettingAmount(int amount, int aid) {
    String sql = "UPDATE Review_Setting SET amount = ? WHERE aId = ?";

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, amount);
      preStmt.setInt(2, aid);
      preStmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
  }

  /**
   * Edit Review_Setting
   *
   * @param amount      amount
   * @param startTime   startTime
   * @param endTime     endTime
   * @param aid     assignment id
   */
  public void editRevSettingAll(int amount, Date startTime, Date endTime, int aid) {
    String sql = "UPDATE Review_Setting SET amount = ?, startTime = ?, endTime = ? WHERE aId = ?";
    Timestamp start = new Timestamp(startTime.getTime());
    Timestamp end = new Timestamp(endTime.getTime());

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, amount);
      preStmt.setTimestamp(2, start);
      preStmt.setTimestamp(3, end);
      preStmt.setInt(4, aid);
      preStmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
  }
}
