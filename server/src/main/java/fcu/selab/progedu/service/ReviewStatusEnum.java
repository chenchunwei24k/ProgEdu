package fcu.selab.progedu.service;

public enum ReviewStatusEnum {
  UN_OPEN("unOpen"), OPEN("open"), UN_FINISH("unFinish"), FINISH("finish");

  private String status;

  private ReviewStatusEnum(String status) {
    this.status = status;
  }

  /**
   *
   * @param status is RecordStatus String
   * @return status is getStatusTypeEnum object
   */
  public static ReviewStatusEnum getStatus(String status) {
    for (ReviewStatusEnum statusType : ReviewStatusEnum.values()) {
      if (statusType.getTypeName().equals(status)) {
        return statusType;
      }
    }
    return null;
  }

  public String getTypeName() {
    return this.status;
  }

}
