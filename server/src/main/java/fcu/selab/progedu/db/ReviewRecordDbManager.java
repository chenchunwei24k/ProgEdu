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

public class ReviewRecordDbManager {
  private static ReviewRecordDbManager dbManager = new ReviewRecordDbManager();

  public static ReviewRecordDbManager getInstance() {
    return dbManager;
  }

  private IDatabase database = new MySqlDatabase();

  private static final Logger LOGGER = LoggerFactory.getLogger(ReviewRecordDbManager.class);

  private ReviewRecordDbManager() {

  }
}
