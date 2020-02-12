package fcu.selab.progedu.data;

import java.util.Date;

public class ReviewRecord {

  private  int id;

  private int pmId;

  private int rid;

  private boolean score;

  private Date time;

  private String feedback;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPmId() {
    return pmId;
  }

  public void setPmId(int pmId) {
    this.pmId = pmId;
  }

  public int getRid() {
    return rid;
  }

  public void setRid(int rid) {
    this.rid = rid;
  }

  public boolean isScore() {
    return score;
  }

  public void setScore(boolean score) {
    this.score = score;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }
}
