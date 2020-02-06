package fcu.selab.progedu.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fcu.selab.progedu.data.Assignment;
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
   * @param assignment Project
   */
   public void addRevSetting(Assignment assignment) {

   }

  /**
   * Get Review_Setting Id by Assignment Id
   *
   * @param aId Assignment Id
   * @return Id Review_Setting Id
   */
   public int getRevSettingId(int aId) {
     return 0;
   }

  /**
   * Get Review_Setting amount by Assignment Id
   *
   * @param aId Assignment Id
   * @return amount Review_Setting amount
   */
   public int getRevSettingAmount(int aId) {
     return 0;
   }

  /**
   * Delete Review_Setting from database
   * 
   * @param aId assignment id
   */
   public void deleteRevSetting(int aId) {

   }

  /**
   * Edit Review_Setting
   * 
   * @param amount      amount
   * @param startTime   startTime
   * @param endTime     endTime
   * @param id          id
   */
   public void editRevSetting(int amount, Date startTime, Date endTime, int id) {

   }
}
