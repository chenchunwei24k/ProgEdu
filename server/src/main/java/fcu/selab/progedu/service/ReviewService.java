package fcu.selab.progedu.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabUser;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fcu.selab.progedu.db.PairMatchingDbManager;
import fcu.selab.progedu.db.ReviewRecordDbManager;
import fcu.selab.progedu.db.ReviewRuleDbManager;
import fcu.selab.progedu.db.ReviewSettingDbManager;
import fcu.selab.progedu.service.ReviewStatusEnum;

public class ReviewService {
  private static ReviewService instance = new ReviewService();

  public static ReviewService getInstance() {
    return instance;
  }

  private ReviewRecordDbManager dbManager = ReviewRecordDbManager.getInstance();
  private PairMatchingDbManager pmDbManager = PairMatchingDbManager.getInstance();
  private ReviewRuleDbManager revRuleDbManager = ReviewRuleDbManager.getInstance();
  private ReviewSettingDbManager revSettingDbManager = ReviewSettingDbManager.getInstance();
  private static final Logger LOGGER = LoggerFactory.getLogger(ReviewService.class);

}
