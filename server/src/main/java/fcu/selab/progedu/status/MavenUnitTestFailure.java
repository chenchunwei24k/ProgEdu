package fcu.selab.progedu.status;

import fcu.selab.progedu.data.FeedBack;

import java.util.ArrayList;

public class MavenUnitTestFailure implements Status {
  @Override
  public String extractFailureMsg(String consoleText) {
    String unitTest = "";
    String startStr = "Failed tests:";
    String goal = "Tests run:";
    int goalStr = consoleText.indexOf(goal, consoleText.indexOf(goal) + 1);

    unitTest = consoleText.substring(consoleText.indexOf(startStr), goalStr - 1);
    //<, > will be HTML tag, change to the " 
    unitTest = unitTest.replaceAll("<", "\"").replaceAll(">", "\"");
    
    return unitTest.trim();
  }

  @Override
  public ArrayList<FeedBack> formatExamineMsg(String consoleText) {
    return null;
  }

  @Override
  public String toJson(ArrayList<FeedBack> arrayList) {
    return "Instructor Commit";
  }

}