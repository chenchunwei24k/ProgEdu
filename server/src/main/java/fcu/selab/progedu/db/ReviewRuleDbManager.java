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

public class ReviewRuleDbManager {
  private static ReviewRuleDbManager dbManager = new ReviewRuleDbManager();

  public static ReviewRuleDbManager getInstance() {
    return dbManager;
  }

  private IDatabase database = new MySqlDatabase();

  private static final Logger LOGGER = LoggerFactory.getLogger(ReviewRuleDbManager.class);

  private ReviewRuleDbManager() {

  }

  /**
   * Get Review_Rule Id by description
   *
   * @param description description
   * @return Id Review_Rule Id
   */
  public int getRevRuleId(String description) {
    String sql = "SELECT id FROM Review_Rule WHERE description = ?";
    int id = 0;

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setString(1, description);

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
   * Get Review_Rule description by id
   *
   * @param id Review_Rule Id
   * @return description
   */
  public String getRevRuleDesc(int id) {
    String sql = "SELECT description FROM Review_Rule WHERE id = ?";
    String description = "";

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, id);

      try (ResultSet rs = preStmt.executeQuery();) {
        while (rs.next()) {
          description = rs.getString("description");
        }
      }
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
    return description;
  }

  /**
   * Get Review_Rule all descriptions
   *
   */
  public List<String> getRevRuleAllDesc() {
    String sql = "SELECT description FROM Review_Rule";
    List<String> lsDesc = new ArrayList<>();

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.executeUpdate();

      try (ResultSet rs = preStmt.executeQuery();) {
        while (rs.next()) {
          String description = rs.getString("description");
          lsDesc.add(description);
        }
      }
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
    return lsDesc;
  }

}
