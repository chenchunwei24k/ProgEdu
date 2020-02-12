package fcu.selab.progedu.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fcu.selab.progedu.data.FeedBack;

import fcu.selab.progedu.utils.ExceptionUtil;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MavenCheckstyleFailure implements Status {

  private static final Logger LOGGER = LoggerFactory.getLogger(MavenCheckstyleFailure.class);

  /**
   * get checkstyle information
   * 
   * @param consoleText console text
   * @return checkstyle information
   */
  @Override
  public String extractFailureMsg(String consoleText) {
    try {
      String checkstyleInfo;
      String checkstyleStart = "[INFO] --- maven-checkstyle";
      String checkstyleEnd = "[INFO] BUILD FAILURE";
      checkstyleInfo = consoleText.substring(consoleText.indexOf(checkstyleStart),
          consoleText.indexOf(checkstyleEnd));
      checkstyleInfo = checkstyleInfo.replace("/var/jenkins_home/workspace/", "");
      return checkstyleInfo.trim();
    } catch (Exception e) {
      LOGGER.debug(ExceptionUtil.getErrorInfoFromException(e));
      LOGGER.error(e.getMessage());
      return "ExtractFailureMsg Method Error";
    }
  }

  @Override
  public ArrayList<FeedBack> formatExamineMsg(String consoleText) {
    ArrayList<FeedBack> feedbackList = new ArrayList<>();
    try {
      Pattern pattern = Pattern.compile("(.*?)(.java)(:)([\\d]"
          + "{1,4}(:)[\\d]{1,4})(: error:)(.*?)(\n)");
      Matcher matcher = pattern.matcher(consoleText);
      while (matcher.find()) {
        String fileName = matcher.group(1) + matcher.group(2);
        String line = matcher.group(4);
        String message = matcher.group(7);
        feedbackList.add(new FeedBack(
            StatusEnum.CHECKSTYLE_FAILURE, fileName, line, message,
            "","https://checkstyle.sourceforge.io/"));
      }
      if (feedbackList.isEmpty()) {
        feedbackList.add(
            new FeedBack(StatusEnum.CHECKSTYLE_FAILURE,
                "Please notify teacher or assistant this situation, thank you!", ""));
      }
    } catch (Exception e) {
      feedbackList.add(
          new FeedBack(StatusEnum.CHECKSTYLE_FAILURE,
              "Checkstyle ArrayList error", e.getMessage()));
    }
    return feedbackList;
  }
}

