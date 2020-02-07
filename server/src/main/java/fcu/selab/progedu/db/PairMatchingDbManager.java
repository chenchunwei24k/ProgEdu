package fcu.selab.progedu.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fcu.selab.progedu.service.ReviewStatusEnum;
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

  private static ReviewStatusDbManager rsDb = ReviewStatusDbManager.getInstance();

  private PairMatchingDbManager() {

  }

  /**
   * Add PairMatching to database
   *
   * @param auId AssignmentUser Id
   * @param reviewId User Id
   */
  public void addPairMatching(int auId, int reviewId, ReviewStatusEnum status) {
    String sql = "INSERT INTO Pair_Matching (auId, reviewId, status) VALUES(?, ?, ?)";
    int statusId = rsDb.getRevStatusId(status.getTypeName());

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, auId);
      preStmt.setInt(2, reviewId);
      preStmt.setInt(3, statusId);
      preStmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
  }

  /**
   * get pmId by assignmentUser Id and user Id
   *
   * @param auId AssignmentUser Id
   * @param reviewId User Id
   * @return pmId PairMatching Id
   */
  public int getPmId(int auId, int reviewId) {
    String sql = "SELECT id FROM Pair_Matching WHERE auId = ? AND reviewId = ?";
    int id = 0;

    try (Connection conn = database.getConnection();
        PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, auId);
      preStmt.setInt(2, reviewId);

      try (ResultSet rs = preStmt.executeQuery();) {
        if (rs.next()) {
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
   * get status by assignmentUser Id and user Id
   *
   * @param auId AssignmentUser Id
   * @param reviewId User Id
   * @return status PairMatching status
   */
  public int getStatus(int auId, int reviewId) {
    String sql = "SELECT status FROM Pair_Matching WHERE auId = ? AND reviewId = ?";
    int id = 0;

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, auId);
      preStmt.setInt(2, reviewId);

      try (ResultSet rs = preStmt.executeQuery();) {
        if (rs.next()) {
          id = rs.getInt("status");
        }
      }
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
    return id;
  }

  /**
   * get status by PairMatching Id
   *
   * @param pmId PairMatching Id
   * @return status PairMatching status
   */
  public int getStatusByPmId(int pmId) {
    String sql = "SELECT status FROM Pair_Matching WHERE id = ?";
    int id = 0;

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, pmId);

      try (ResultSet rs = preStmt.executeQuery();) {
        if (rs.next()) {
          id = rs.getInt("status");
        }
      }
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
    return id;
  }

  /**
   * set pmId by assignmentUser Id and user Id
   *
   * @param auId AssignmentUser Id
   * @param reviewId User Id
   * @param status Review status
   */
  public void setStatus(int auId, int reviewId, ReviewStatusEnum status) {
    String sql = "UPDATE Pair_Matching SET status = ? WHERE auId = ? AND reviewId = ?";
    int statusId = rsDb.getRevStatusId(status.getTypeName());

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, statusId);
      preStmt.setInt(2, auId);
      preStmt.setInt(3, reviewId);
      preStmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
  }

  /**
   * get pmIds by assignmentUser Id
   *
   * @param auId AssignmentUser Id
   * @return pmId PairMatching Id
   */
  public List<Integer> getIdListByAuId(int auId) {
    String sql = "SELECT id FROM Pair_Matching WHERE auId = ?";
    List<Integer> lsPmId = new ArrayList<>();

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, auId);

      try (ResultSet rs = preStmt.executeQuery();) {
        while (rs.next()) {
          int id = rs.getInt("id");
          lsPmId.add(id);
        }
      }
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
    return lsPmId;
  }

  /**
   * get pmIds by user Id
   *
   * @param reviewId User Id
   * @return pmId PairMatching Id
   */
  public List<Integer> getIdListByUId(int reviewId) {
    String sql = "SELECT id FROM Pair_Matching WHERE reviewId = ?";
    List<Integer> lsPmId = new ArrayList<>();

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, reviewId);

      try (ResultSet rs = preStmt.executeQuery();) {
        while (rs.next()) {
          int id = rs.getInt("id");
          lsPmId.add(id);
        }
      }
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
    return lsPmId;
  }

  /**
   * get uIds by assignmentUser Id
   *
   * @param auId AssignmentUser Id
   * @return pmId User Id
   */
  public List<Integer> getUIdListByAuId(int auId) {
    String sql = "SELECT reviewId FROM Pair_Matching WHERE auId = ?";
    List<Integer> lsUId = new ArrayList<>();

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, auId);

      try (ResultSet rs = preStmt.executeQuery();) {
        while (rs.next()) {
          int reviewId = rs.getInt("reviewId");
          lsUId.add(reviewId);
        }
      }
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
    return lsUId;
  }

  /**
   * get auIds by user Id
   *
   * @param reviewId User Id
   * @return pmId PairMatching Id
   */
  public List<Integer> getAuIdListByUId(int reviewId) {
    String sql = "SELECT auId FROM Pair_Matching WHERE reviewId = ?";
    List<Integer> lsAuId = new ArrayList<>();

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, reviewId);

      try (ResultSet rs = preStmt.executeQuery();) {
        while (rs.next()) {
          int auId = rs.getInt("auId");
          lsAuId.add(auId);
        }
      }
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
    return lsAuId;
  }

  /**
   * Delete Pair_Matching from database by assignment_user id
   *
   * @param auId AssignmentUser Id
   */
  public void deletePairMatchingByAuId(int auId) {
    String sql = "DELETE FROM Pair_Matching WHERE auId = ?";

    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, auId);
      preStmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
  }

  /**
   * Delete Pair_Matching from database by User id
   *
   * @param reviewId User Id
   */
  public void deletePairMatchingByUId(int reviewId) {
    String sql = "DELETE FROM Pair_Matching WHERE reviewId = ?";
    try (Connection conn = database.getConnection();
         PreparedStatement preStmt = conn.prepareStatement(sql)) {
      preStmt.setInt(1, reviewId);
      preStmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
    }
  }

}
