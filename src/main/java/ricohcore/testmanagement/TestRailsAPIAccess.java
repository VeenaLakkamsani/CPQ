package ricohcore.testmanagement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

//Reference for API Definition: http://docs.gurock.com/testrail-api2/start
public class TestRailsAPIAccess {

	private static APIClient client;
	public static final int result_PASS = 1;
	public static final int result_FAIL = 5;
	public static final int result_BLOCKED = 2;
	public static final int result_RETEST = 4;

	public TestRailsAPIAccess(String serverUrl,String loginEmail, String loginPassword){
		client = new APIClient(serverUrl);
		client.setUser(loginEmail);
		client.setPassword(loginPassword);
	}
	
	public Object getSuiteId(Long projId, String suiteName)
			throws MalformedURLException, IOException, TestRailsAPIException {
		JSONArray r = (JSONArray) client.sendGet("get_suites/" + projId);
		JSONObject suite = null;

		for (int i = 0; i < r.size(); i++) {
			suite = (JSONObject) r.get(i);
			// System.out.println(suite.get("name"));
			if (suite.get("name").equals(suiteName)) {
				// System.out.println(suite.get("id"));
				return suite.get("id");
			}
		}
		System.out.println("No matching suite name found " + suiteName);

		throw new TestRailsAPIException("No matching suites found for Suite name " + suiteName);
	}
	
	public Object getProjectId(String projectName) throws MalformedURLException, IOException, TestRailsAPIException {
		JSONArray r = (JSONArray) client.sendGet("get_projects");
		JSONObject project = null;
		for (int i = 0; i < r.size(); i++) {
			project = (JSONObject) r.get(i);
			// System.out.println(project.get("name"));
			if (project.get("name").equals(projectName)) {
				// System.out.println(project.get("id"));
				return project.get("id");
			}
		}
		System.out.println("No matching Project name found " + projectName);
		throw new TestRailsAPIException("No matching Projects found for name " + projectName);
	}
	
	public Object getMilestoneId(String milestoneName)
			throws MalformedURLException, IOException, TestRailsAPIException {
		JSONArray r = (JSONArray) client.sendGet("get_milestones/" + 1);
		JSONObject milestone = null;
		for (int i = 0; i < r.size(); i++) {
			milestone = (JSONObject) r.get(i);
			// System.out.println(milestone.get("name"));
			if (milestone.get("name").equals(milestoneName)) {
				// System.out.println(milestone.get("id"));
				return milestone.get("id");
			}
		}
		System.out.println("No matching milestone name found " + milestoneName);
		throw new TestRailsAPIException("No matching milestones found for name " + milestoneName);
	}
	
	public Object getTestRun(String projectName, String runName) throws Exception {
		Long projId = (Long) getProjectId(projectName);
		JSONArray jsTestRuns = (JSONArray) client.sendGet("get_runs/" + projId.toString());
		if (projectName.trim().length() > 0 && runName.trim().length() > 0) {
			for (int rCntr = 0; rCntr < jsTestRuns.size(); rCntr++) {
				JSONObject run = (JSONObject) jsTestRuns.get(rCntr);
				String rName = (String) run.get("name");
				System.out.println("test run " + rName);
				if (rName.trim().equalsIgnoreCase(runName)) {
					return run.get("id");
				}
			}
		} else {
			return null;
		}
		System.out.println("No matching run name found " + runName);
		throw new TestRailsAPIException("No matching runs found for name " + runName);
	}
	
	/*public Object getTestPlanId(String projectName, String testPlanName) throws Exception {        

        Long projId = (Long) getProjectId(projectName);

        JSONArray jsTestRuns = (JSONArray) client.sendGet("get_plans/" + projId.toString() );

        

        if(projectName.trim().length() > 0 && testPlanName.trim().length() > 0){

            for(int rCntr=0;rCntr<jsTestRuns.size(); rCntr++){

                

                JSONObject run = (JSONObject) jsTestRuns.get(rCntr);

                

                String rName = (String) run.get("name");

                System.out.println("test plan " + rName);

                

                if(rName.trim().equalsIgnoreCase(testPlanName)){

                    return run.get("id");

                }

            }            

        }else{

            return null;

        }

        

        System.out.println("No matching test plan name found "+testPlanName);

        throw new TestRailsAPIException("No matching test plan found for name " + testPlanName);

    }*/
	
	public Long getTestRunIdUsingTestPlanId(String planId,String testCaseId) throws Exception {        

		Long runId=null;
        
		//long rTc = Long.parseLong(testRailsId.trim());
        JSONObject jsTestRuns =  (JSONObject)client.sendGet("get_plan/" + planId);
        JSONArray jsonRunEntries=(JSONArray)jsTestRuns.get("entries");
        
        //Iterate through entries in give test plan
        for(int i=0;i<jsonRunEntries.size();i++) {
            JSONObject jsEntry=(JSONObject)jsonRunEntries.get(i);
            JSONArray subTestRuns=(JSONArray)jsEntry.get("runs");

            //Iterate through test runs in each test plan entry
            for(int runCntr=0;runCntr<subTestRuns.size();runCntr++) {
                JSONObject runObject=(JSONObject)subTestRuns.get(runCntr);
                Long  rId= Long.valueOf(runObject.get("id").toString());
                //Long  rId=(long)runObject.get("id");
                JSONArray jsTestCases =  (JSONArray)client.sendGet("get_tests/" + rId);
                
                //Iterate through test cases in each test run
                for(int tcCntr=0; tcCntr<jsTestCases.size(); tcCntr++) {
                
                	JSONObject tcObject=(JSONObject)jsTestCases.get(tcCntr);
                    //System.out.println("TestCases::"+tcObject);
                    long tcId = Long.valueOf(tcObject.get("case_id").toString().trim());
                    //System.out.println("TC ID::"+tcId);
                    
                    //Check input test case id match & return its test run id
                    if(Long.parseLong(testCaseId.trim())==tcId) {
                    	runId = rId;
                    	return runId;
                    }
                    
                }//test Cases in each - for-loop
            }// runCntr - for-loop            
        }// planEntries - for-loop        
        return runId;
    }	
	
	public Object addTestRun(String testrunname, String suiteName, JSONObject jsTRDetails) throws Exception {
		String projName = (jsTRDetails.get("project") != null) ? (String) jsTRDetails.get("project") : "";
		String mileStone = (jsTRDetails.get("milestone") != null) ? (String) jsTRDetails.get("milestone") : "";
		// JSONArray jsSuites = (jsTRDetails.get("suites") != null) ? (JSONArray)
		// jsTRDetails.get("suites") : null;
		Map data = new HashMap();
		data.put("name", testrunname);
		Long projectId = (Long) getProjectId(projName);
		try {
			Long runId = (Long) getTestRun(projName, testrunname);
			return runId;
		} catch (TestRailsAPIException tre) {
			if (suiteName.length() > 0) {
				Long suiteId = (Long) getSuiteId(projectId, suiteName);
				data.put("suite_id", suiteId);
				data.put("include_all", true);
			}
			if (mileStone.length() > 0) {
				Long milestoneId = (Long) getMilestoneId(mileStone);
				data.put("milestone_id", milestoneId);
			}
			// data.put("case_ids",cases);
			JSONObject r = (JSONObject) client.sendPost("add_run/" + projectId, data);
			System.out.println(r.get("id"));
			return r.get("id");
		}
	}
	
	public void updateTestRun(Long[] testcaseids, Long runId) throws Exception {
		// APIClient client = TestRailsAPIAccess.client();
		Map data = new HashMap();
		data.put("case_ids", testcaseids);
		data.put("include_all", true);
		JSONObject r = (JSONObject) client.sendPost("update_run/" + runId, data);
	}
	
	public void AddStatus(Long runid, Long caseid, int statusId, String comments)
			throws MalformedURLException, IOException, TestRailsAPIException {
		// APIClient client = TestRailsAPIAccess.client();
		Map data = new HashMap();
		data.put("status_id", statusId);
		data.put("comment", comments);
		JSONObject r = (JSONObject) client.sendPost("add_result_for_case/" + runid + "/" + caseid, data);
		System.out.println(r);
	}
	
	public Object getSectionId(int projectid, int suiteid, String sectionname)
			throws MalformedURLException, IOException, TestRailsAPIException {
		// APIClient client = TestRailsAPIAccess.client();
		JSONArray r = (JSONArray) client.sendGet("get_sections/" + projectid + "&suite_id=" + suiteid);
		JSONObject section = null;
		for (int i = 0; i < r.size(); i++) {
			section = (JSONObject) r.get(i);
			// System.out.println(suite.get("name"));
			if (section.get("name").equals(sectionname))
			// System.out.println(suite.get("id"));
			{
				return section.get("id");
			}
		}
		return null;
	}
	
	public Object addTestCase(String tcTile, String sectionname, int projectid, int suiteid) throws Exception {
		Map data = new HashMap();
		data.put("title", tcTile);
		data.put("type_id", 1);
		data.put("priority_id", 3);
		data.put("custom_automation_status", 3);
		data.put("custom_test_type", 1);
		// int testcaseid
		int sectionid = ((Long) getSectionId(projectid, suiteid, sectionname)).intValue();
		// getProjectId(prop.getProperty("ProjectName"))).intValue();
		JSONObject r = (JSONObject) client.sendPost("add_case/" + sectionid, data);
		// int testcaseid = ((Long) r.get("id")).intValue();
		// client.
		// TestRailAPI.updateTestRun(testcaseid,MobileDriver.test_rail_runid);
		return r.get("id");
	}
	
	public Object getTestCase(Long tcId) {
		Object id = null;
		try {
			JSONObject r = (JSONObject) client.sendGet("get_case/" + tcId);
			id = r.get("id");
		} catch (Exception e) {
			id = null;
		}
		return id;
	}
	
	public Object getTestCaseProperty(Long tcId, String reqKey) {
		Object value = null;
		try {
			JSONObject r = (JSONObject) client.sendGet("get_case/" + tcId);
			value = r.get(reqKey);
		} catch (Exception e) {
			value = null;
		}
		return value;
	}
	
	public Object getTestCase(String title, int suite_id, int section_id)
			throws MalformedURLException, IOException, TestRailsAPIException {
		JSONArray r = (JSONArray) client
				.sendGet("get_cases/" + 1 + "&suite_id=" + suite_id + "&section_id=" + section_id);
		JSONObject section = null;
		for (int i = 0; i < r.size(); i++) {
			section = (JSONObject) r.get(i);
			// System.out.println(suite.get("name"));
			if (section.get("name").equals(title))
			// System.out.println(suite.get("id"));
			{
				return section.get("id");
			}
		}
		return null;
	}
	
	public void methodForTestingPurpose() throws MalformedURLException, IOException, TestRailsAPIException {
		JSONArray r = (JSONArray) client.sendGet("get_milestones/" + 1);
		System.out.println(r);
	}
}