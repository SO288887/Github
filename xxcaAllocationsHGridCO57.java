package xxca.oracle.apps.ap.oie.entry.accounting.webui;

 import oracle.apps.ap.oie.entry.accounting.webui.AllocationsHGridCO;
 import oracle.apps.ap.oie.entry.accounting.server.*;
 import oracle.apps.ap.oie.entry.accounting.webui.*;
 import oracle.apps.fnd.common.VersionInfo;
 import oracle.apps.fnd.framework.OAViewObject;
 import oracle.apps.fnd.framework.OAApplicationModule;
 import oracle.apps.fnd.framework.webui.OAPageContext;
 import oracle.apps.fnd.framework.webui.beans.OAWebBean;
 import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
 import oracle.apps.fnd.framework.webui.beans.layout.OASubTabLayoutBean;
 import oracle.apps.ap.oie.utility.OIEUtil;
 import oracle.apps.ap.oie.webui.NavigationUtility;
 import com.sun.java.util.collections.HashMap;
 import oracle.apps.fnd.framework.webui.OAPageContext;
 import oracle.apps.fnd.framework.OAException;
 import oracle.apps.fnd.framework.server.OADBTransactionImpl;
 import oracle.apps.fnd.framework.webui.OAControllerImpl;
 import oracle.apps.fnd.framework.webui.beans.layout.OAHeaderBean;
 import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
 import oracle.jbo.ViewObject;
 import java.sql.SQLException;
 import oracle.apps.fnd.framework.webui.beans.message.*;
 import oracle.jbo.JboException;
//import sun.security.krb5.internal.am;


 import oracle.jbo.Row;

 import oracle.jbo.domain.Number;

 import oracle.jdbc.OracleCallableStatement;
 import oracle.apps.fnd.framework.server.OADBTransaction;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import com.sun.java.util.collections.ArrayList;

 import java.io.Serializable;

 import java.sql.Types;

 import oracle.apps.fnd.framework.OARow;
 import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
 import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;

 //import sun.security.krb5.internal.am;


 public class xxcaAllocationsHGridCO57 extends AllocationsHGridCO 
 {
   public static final String RCS_ID = "$Header: xxcaAllocationsHGridCO.java 120.10 2007/04/12 10:40:06 rveliche noship $";
   public static final boolean RCS_ID_RECORDED = VersionInfo.recordClassVersion("$Header: xxcaAllocationsHGridCO.java 120.10 2007/04/12 10:40:06 rveliche noship $", "xxca.oracle.apps.ap.oie.entry.accounting.webui");


   public void processRequest(OAPageContext paramOAPageContext, OAWebBean paramOAWebBean)
   {
      paramOAPageContext.writeDiagnostics(this, "CCN-111 Process Request Starts", 1);
	  //truncating the tables that are required
	  	 
	 OAApplicationModule am = paramOAPageContext.getApplicationModule(paramOAWebBean);   
     OADBTransaction oadbtransaction2 = am.getOADBTransaction(); 
                                   
     OracleCallableStatement callablestatement3; 
     String message;
    String  MultiOUFLag; 
     String s1;
    String str1 = (String)paramOAPageContext.getTransactionValue("ReportHeaderId");
    //now create new button programatically
				OASubmitButtonBean oasb= (OASubmitButtonBean)paramOAPageContext.getWebBeanFactory().createWebBean(paramOAPageContext,"BUTTON_SUBMIT");
				oasb.setID("xxFusionProjectsDetails");
				oasb.setUINodeName("xxFusionProjectsDetails");
				oasb.setEvent("xxFusionProjectsDetails");
				oasb.setText("Fusion Project and Task Details");
				paramOAWebBean.addIndexedChild(oasb);

	 OracleCallableStatement callablestatement10;
				 
                                                                        
				 if (paramOAPageContext.isLoggingEnabled(2)) {
				   paramOAPageContext.writeDiagnostics(this, "start processRequest", 2);
				 }
  
				super.processRequest(paramOAPageContext, paramOAWebBean);


		paramOAPageContext.writeDiagnostics(this, (new StringBuilder()).append("CCN111- Kishore_143").append(paramOAPageContext.getParameter("PFP_PR_FLAG")).toString(), 12);
    
      if( !("Y".equalsIgnoreCase(paramOAPageContext.getParameter("PFP_PR_FLAG"))))
       {   
	          paramOAPageContext.writeDiagnostics(this, "CCN111- Calling Fusion My Allocations Default", 2);
             this.fusionMyAllocationsDefault(paramOAPageContext, paramOAWebBean); 
			    paramOAPageContext.writeDiagnostics(this, "CCN111- Call completed for Fusion Allocations Default", 2);
             this.saveChangesForContext1(paramOAPageContext, paramOAWebBean); 
			 paramOAPageContext.writeDiagnostics(this, "CCN111- Call completed for Save Changes", 2);
       }
	   
     if (paramOAPageContext.isLoggingEnabled(2))
	 //Check for the Current Organization and project organization of the project 
	  
	  

      
        try 
              {
                callablestatement10 = (OracleCallableStatement)oadbtransaction2.createCallableStatement("begin :1 := xxca_fusion_iexp_pkg.xxca_get_multiou_flag(:2); end;", 3);       
                callablestatement10.registerOutParameter(1, Types.VARCHAR);
				callablestatement10.setString(2, str1);
                callablestatement10.execute();   
                MultiOUFLag = callablestatement10.getString(1);
                callablestatement10 .close();
				paramOAPageContext.writeDiagnostics(this, "CCN111- Value of MultiOU Flag is "+MultiOUFLag, 2);
				  
		/*	 if(MultiOUFLag.equals("Y"))
			{			     
				//now create new button programatically
				OASubmitButtonBean oasb= (OASubmitButtonBean)paramOAPageContext.getWebBeanFactory().createWebBean(paramOAPageContext,"BUTTON_SUBMIT");
				oasb.setID("xxFusionProjectsDetails");
				oasb.setUINodeName("xxFusionProjectsDetails");
				oasb.setEvent("xxFusionProjectsDetails");
				oasb.setText("Fusion Project and Task Details");
				paramOAWebBean.addIndexedChild(oasb);
			 throw new OAException("There are some Lines in Expense Report which are Cross OU Lines. Project and Tasks will not default for Same. Kindly click on PROJECT&TASK DETAILS BUTTON TO GET THE INFO FOR THOSE LINES.", OAException.INFORMATION); // Raise Exception Manually  
			}	
			else
			{
			paramOAPageContext.writeDiagnostics(this, "CCN111- Value of MultiOU Flag is null or no ", 2);
			}	 */
          }
        catch(SQLException sqlexception)
              {
           paramOAPageContext.writeDiagnostics(this, (new StringBuilder()).append("IN Catch Kishore_998").toString(), 12); 
               throw new JboException(sqlexception);
              }
			
			  
			  
       paramOAPageContext.writeDiagnostics(this, "end processRequest", 2);
   }

   public void processFormRequest(OAPageContext oapagecontext, OAWebBean oawebbean)
   {        
       		  
String strhid1 = (String)oapagecontext.getTransactionValue("ReportHeaderId");
           		 oapagecontext.writeDiagnostics(this, "form request report header id is "+strhid1, 2);
				HashMap xxTrialHashMap = new HashMap();
               		xxTrialHashMap.put("strhid1",strhid1 );

     oapagecontext.writeDiagnostics(this, "CCN111 -The report header id is " + strhid1 , 1);

		 /*CCN111 Logic starts for Clicking the Button for Details on FUSION Projects and Tasks*/
	String strEvent= oapagecontext.getParameter(EVENT_PARAM) ;

if ( strEvent.equals("xxFusionProjectsDetails"))
{
			oapagecontext.writeDiagnostics(this, "Clicked on form request Button", 2);

		 
		 oapagecontext.setForwardURL("OA.jsp?page=/xxca/oracle/apps/ap/oie/entry/accounting/fusionproj/webui/DefProjTaskPG",
					    null,
					    OAWebBeanConstants.KEEP_MENU_CONTEXT,
                                       null,
                                       xxTrialHashMap,
                                       true,
                                       OAWebBeanConstants.ADD_BREAD_CRUMB_YES,
                                       OAWebBeanConstants.IGNORE_MESSAGES);
									
									oapagecontext.writeDiagnostics(this, "called the Other page", 2);
          }
		 
		 /*CCN111 Logic ends for Clicking the Button for Details on FUSION Projects and Tasks*/
		 
		 
		 Object p_val = ("Y");
         oapagecontext.putParameter("PFP_PR_FLAG",p_val);
         OracleCallableStatement callablestatement;

         oapagecontext.isLoggingEnabled();

         boolean flag = oapagecontext.isLoggingEnabled(1);
         super.processFormRequest(oapagecontext, oawebbean);

        oapagecontext.removeParameter("MyAllocationsChoiceList");
    
      
          callablestatement = null;
          
          OAApplicationModule am = oapagecontext.getApplicationModule(oawebbean);   
       
          OADBTransaction oadbtransaction2 = am.getOADBTransaction(); 
          OracleCallableStatement callablestatement2; 

         try

         {

             OAApplicationModule oaapplicationmodule = oapagecontext.getApplicationModule(oawebbean);

             OADBTransaction oadbtransaction = oaapplicationmodule.getOADBTransaction();
             String str1 = (String)oapagecontext.getTransactionValue("ReportHeaderId");

             oapagecontext.writeDiagnostics(this, "The report header id is " + str1, 1);

             callablestatement = (OracleCallableStatement)oadbtransaction.createCallableStatement("begin :1 :=xxca_pcard_validate.pcardprojectvalidate(:2); end;", 1);

             callablestatement.registerOutParameter(1, 12);

             callablestatement.setString(2, str1);

             callablestatement.execute();

             String s = callablestatement.getString(1);

             oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("The message is ").append(s).toString(), 1);
             if ("OIENavBar".equals(oapagecontext.getParameter("source")) && "goto".equals(oapagecontext.getParameter("event")) && "ExpenseAllocationsPG".equals(NavigationUtility.getCurrentPage(oapagecontext))) {
             if (NavigationUtility.isNextClicked(oapagecontext)){

             if(s != null){
                 Pattern pattern = Pattern.compile("#");
                 Matcher matcher = pattern.matcher(s);
                 int counter = 0;
                 while (matcher.find())
                         counter++;

                 ArrayList exceptions = new ArrayList();
                 String[] parts = s.split("#", counter+1);
                 for (int i=0;i<counter+1;i++)
                   exceptions.add(new OAException(parts[i]));                 

                 OAException.raiseBundledOAException(exceptions);
             }}
            }
         }

         catch(SQLException sqlexception)

         {

             throw new JboException(sqlexception);

         }

         if(callablestatement != null){

             try

             {

                 callablestatement.close();

             }

             catch(Exception exception)

             {

                 throw OAException.wrapperException(exception);

             }
    
         return;
   }
   }
   
 ///////////////////-----------------------------------//////////////////////////////////////////

     protected void handleMyAllocationsApplyCopy(OAPageContext oAPageContext, OAWebBean oAWebBean) {
             if (oAPageContext.isLoggingEnabled(2)) {
                 oAPageContext.writeDiagnostics("oracle.apps.ap.oie.entry.accounting.webui.AllocationsHGridCO", "start handleMyAllocationsApplyCopy", 2);
             }
             OAApplicationModule oAApplicationModule = oAPageContext.getApplicationModule(oAWebBean);
             OAMessageChoiceBean oAMessageChoiceBean = (OAMessageChoiceBean)oAWebBean.findChildRecursive("MyAllocationsChoiceList");
             String string = oAPageContext.getParameter("MyAllocationsChoiceList");

 oAPageContext.writeDiagnostics(this, (new StringBuilder()).append("Kishore_5").append(oAPageContext.getParameter("MyAllocationsChoiceList")).append(string).toString(), 12);                 


             //if (!(string == null || "".equals(string))) {
                 String string2 = AllocationsUtility.getAllocationContext((OAWebBean)oAWebBean);
                 Serializable[] arrserializable = new Serializable[]{string, string2};
                 Class[] arrclass = new Class[]{String.class, String.class};
                 oAApplicationModule.invokeMethod("handleMyAllocationsApplyCopy", arrserializable, arrclass);
                 //this.saveChangesForContext(oAPageContext, oAWebBean);
             //}
             if (oAPageContext.isLoggingEnabled(2)) {
                 oAPageContext.writeDiagnostics("oracle.apps.ap.oie.entry.accounting.webui.AllocationsHGridCO", "end handleMyAllocationsApplyCopy", 2);
             }
         }
  
  
     private void saveChangesForContext(OAPageContext oAPageContext, OAWebBean oAWebBean) {
            OAApplicationModule oAApplicationModule = oAPageContext.getApplicationModule(oAWebBean);
            String string = AllocationsUtility.getAllocationContext((OAWebBean)oAWebBean);
            String string2 = AllocationsUtility.getDisplayMode((OAWebBean)oAWebBean);
            Serializable[] arrserializable = new Serializable[]{string, string2};
            Class[] arrclass = new Class[]{String.class, String.class};
            oAApplicationModule.invokeMethod("saveChanges", arrserializable, arrclass);
        }


     private void processHeaderSelection(OAPageContext oAPageContext, OAWebBean oAWebBean) {
             if (oAPageContext.isLoggingEnabled(2)) {
                 oAPageContext.writeDiagnostics("oracle.apps.ap.oie.entry.accounting.webui.AllocationsHGridCO", "start processHeaderSelection", 2);
             }
             OAApplicationModule oAApplicationModule = oAPageContext.getApplicationModule(oAWebBean);
             String string = AllocationsUtility.getAllocationContext((OAWebBean)oAWebBean);
             String string2 = AllocationsUtility.getDisplayMode((OAWebBean)oAWebBean);
             Serializable[] arrserializable = new Serializable[]{string, string2};
             Class[] arrclass = new Class[]{String.class, String.class};
             oAApplicationModule.invokeMethod("processHeaderSelection", arrserializable, arrclass);
             if (oAPageContext.isLoggingEnabled(2)) {
                 oAPageContext.writeDiagnostics("oracle.apps.ap.oie.entry.accounting.webui.AllocationsHGridCO", "end processHeaderSelection", 2);
             }
         }
                 
          protected void fusionMyAllocationsDefault(OAPageContext oapagecontext, OAWebBean oawebbean) 
  {
      if (oapagecontext.isLoggingEnabled(2)) 
      {
        oapagecontext.writeDiagnostics("xxca.oracle.apps.ap.oie.entry.accounting.webui.xxcaAllocationsHGridCO", "start fusionMyAllocationsDefault", 2);
      }          
      
      OAApplicationModule am = oapagecontext.getApplicationModule(oawebbean);   
      OAMessageChoiceBean choice = (OAMessageChoiceBean)oawebbean.findChildRecursive("MyAllocationsChoiceList");
      
 // logic to return the distribution set id for this employee

       OADBTransaction oadbtransaction2 = am.getOADBTransaction(); 
       OracleCallableStatement callablestatement2;  
       int l_dist_id= 0;
      
        try 
              {
                callablestatement2 = (OracleCallableStatement)oadbtransaction2.createCallableStatement("begin :1 := xxca_fusion_iexp_pkg.xxca_pcard_default_dist; end;", 3);       
                callablestatement2.registerOutParameter(1, Types.INTEGER);
                callablestatement2.execute();   
                l_dist_id = callablestatement2.getInt(1);
                callablestatement2.close();
				oapagecontext.writeDiagnostics(this, "CCN111- Distribution ID received is: "+l_dist_id, 2);
          }
        catch(SQLException sqlexception)
              {
           oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("IN Catch Kishore_998").toString(), 12); 
               throw new JboException(sqlexception);
              }
              
 // logic to return the distribution set id for this employee    
      
      Object val =(Object)(l_dist_id);     // this is Distributation set id from the preferences 
      oapagecontext.putParameter("MyAllocationsChoiceList",val);
	   oapagecontext.writeDiagnostics(this, "CCN111- AllocationChoiceList put parameter", 2);	
         
      oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("CCN111Kishore_99").append(choice.getSelectionValue(oapagecontext)).toString(), 12);    
           
      //this.processHeaderSelection(oapagecontext, oawebbean);
           
      oapagecontext.writeDiagnostics(this, (new StringBuilder()).append(oapagecontext.getParameter("source")).append("CCN111-Kishore_4 EVENT CAPTURED").toString(), 12);          
      oapagecontext.writeDiagnostics(this, (new StringBuilder()).append(oapagecontext.getParameter("source")).append("CCN111-Kishore_94 EVENT CAPTURED").toString(), 12);       
                           
          oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("Before").append("CCN-111Kishore_4").toString(), 12);  
      OAMessageStyledTextBean  oamessagechoicebean = (OAMessageStyledTextBean)oawebbean.findIndexedChildRecursive("Location");    
      

          OADBTransaction oadbtransaction1 = am.getOADBTransaction();
      OracleCallableStatement callablestatement1;                 
      OAViewObject pcoir= (OAViewObject)am.findViewObject("AllocationsLinesVO");  
      AllocationsLinesVORowImpl row =null;
      //ExpenseAllocationsAMImpl  expenseAllocationsAMImpl = new ExpenseAllocationsAMImpl();
       
      oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("Initial POsition").append("CCN111-Kishore_1").toString(), 12);                
      oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("Kishore_5").toString(), 12);                 
                  
      if(pcoir!=null) 
       {                        
				int fetchedRowCount = pcoir.getFetchedRowCount();
           
                oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("Before").append("CCN111-Kishore_4").toString(), 12);  
                oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("fetched rows - ").append(fetchedRowCount).toString(), 12); 
                oracle.jbo.RowSetIterator rowsetiterator = OIEUtil.getNoValIterator(pcoir);  
					if(fetchedRowCount == 0) 
					{oapagecontext.forwardImmediatelyToCurrentPage(null, true, "Y");
					}
           
					if(fetchedRowCount>0)
					{                    
					rowsetiterator.setRangeStart(0);  
					rowsetiterator.setRangeSize(fetchedRowCount);
					oapagecontext.writeDiagnostics(this, (new StringBuilder()).append(pcoir.getRowCount()).append("Kishore_5").append(pcoir.getFetchedRowCount()).toString(), 12);                                   
					OracleCallableStatement callablestatement9;
					String scbox;
							for (int i = 0; i <fetchedRowCount; i++) 
							{                   
							oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("Before Next Kishore_98").append(i).toString(), 12);                              
                            row = (AllocationsLinesVORowImpl)rowsetiterator.next(); 
							oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("after Next Kishore_98").append(i).toString(), 12);  
							// added by ankit for pcard ccn62           
				  
                                  try 
                                  {
                                   callablestatement9= (OracleCallableStatement)oadbtransaction1.createCallableStatement("begin :1 :=xxca_fusion_iexp_pkg.xxca_fusion_checkbox(:2); end;", 1);
								   oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("CCN111called the function-").toString(), 1);
								   callablestatement9.registerOutParameter(1, 12);
								   callablestatement9.setNUMBER(2,row.getReportLineId());
								   callablestatement9.execute();
								   scbox = callablestatement9.getString(1);
								   oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("CCN111This is scbox value -").append(scbox).toString(), 1);
                                   callablestatement9.close();
                                   }
                                 catch(SQLException sqlexception)
                                  {
                                        oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("CCN111IN Catch Kishore_998").toString(), 12); 
                                       throw new JboException(sqlexception);
                                   }   
										if(scbox.equals("Y"))
										{
											oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("CCN111Inside yes condition").append(scbox).toString(), 1);
											row.setSelect("Y");
											oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("CCN111Hav selected Line- ").append(row.getReportLineId()).toString(), 1);
											oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("CCN111Before Try Kishore_98").append(i).toString(), 12);  
														 
																//check for project Availability
																  OracleCallableStatement callablestatement4;
																  
																  try 
                                                                 {															     
                                                                  callablestatement4 = (OracleCallableStatement)oadbtransaction1.createCallableStatement("begin :1 :=xxca_fusion_iexp_pkg.xxca_get_projectid(:2,:3); end;", 1);
																  callablestatement4.registerOutParameter(1, 12);
																  callablestatement4.setNUMBER(2, row.getReportHeaderId());
                                                                  callablestatement4.setNUMBER(3, row.getReportLineId());  
																  callablestatement4.execute();
																  String message1 = callablestatement4.getString(1);
																  if(message1.equals("fine"))
																  {
																  oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("CCN-111 Projects is fine").append(i).toString(), 12); 
																  }
																  else
																  {
																  throw new OAException(message1, OAException.ERROR); // Raise Exception Manually  
																  }
																  callablestatement4.close();
                                                                  }
																  	catch(SQLException sqlexception)
                                                                  {
																oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("CCN-111 IN Catch Kishore_98").append(i).toString(), 12); 
                                                                  throw new JboException(sqlexception);
                                                                  }
																  OracleCallableStatement callablestatement5;
														   try 
                                                                 {
                                                                  callablestatement5 = (OracleCallableStatement)oadbtransaction1.createCallableStatement("begin :1 :=xxca_fusion_iexp_pkg.xxca_get_tasksid(:2,:3); end;", 1);
																  callablestatement5.registerOutParameter(1, 12);
																  callablestatement5.setNUMBER(2, row.getReportHeaderId());
                                                                  callablestatement5.setNUMBER(3, row.getReportLineId());  
																  callablestatement5.execute();
																  String message3 = callablestatement5.getString(1);
																    if(message3.equals("fine"))
																  {
																  oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("CCN-111 Tasks is fine").append(i).toString(), 12); 
																  }
																  else
																  {
																  throw new OAException(message3, OAException.ERROR); // Raise Exception Manually  
																  }
																  callablestatement5.close();																  
                                                                 }
															catch(SQLException sqlexception)
                                                                  {
																oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("CCN-111 IN Catch Kishore_98").append(i).toString(), 12); 
                                                                  throw new JboException(sqlexception);
                                                                  }


														 try 
                                                                 {
                                                                  callablestatement1 = (OracleCallableStatement)oadbtransaction1.createCallableStatement("begin xxca_fusion_iexp_pkg.xxca_updation_proj_task2(:1,:2,:3); end;", 1);
                                                                  callablestatement1.setNUMBER(1, row.getReportLineId());
                                                                  callablestatement1.setNUMBER(2, row.getReportHeaderId());
                                                                  callablestatement1.setInt(3, l_dist_id);
                                                                  callablestatement1.execute();
																  callablestatement1.close();																  
                                                                 }
															catch(SQLException sqlexception)
                                                                  {
																oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("CCN-111 IN Catch Kishore_98").append(i).toString(), 12); 
                                                                  throw new JboException(sqlexception);
                                                                  }
														
															

														
																oapagecontext.writeDiagnostics(this, (new StringBuilder()).append(i).append(row.getCreditCardTrxId()).append("Kishore_6 Report Line ID").append(row.getReportLineId()).toString(), 12);  
																//super.handleMyAllocationsApplyCopy(oapagecontext, oawebbean);
											this.handleMyAllocationsApplyCopy(oapagecontext, oawebbean);
											oapagecontext.writeDiagnostics(this, (new StringBuilder()).append(i).append(row.getCreditCardTrxId()).append("CCN-111Kishore_7 Report Line ID").append(row.getReportLineId()).toString(), 12); 
											row.setSelect("N");
											oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("Hav unselected Line- ").append(row.getReportLineId()).toString(), 1);
										
											     try 
                    									{
													oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("Revert Back Fusion Logic Started").append(i).toString(), 12);
                      								callablestatement2 = (OracleCallableStatement)oadbtransaction2.createCallableStatement("begin xxca_fusion_iexp_pkg.xxca_revert_back_fusn_trxn(:1,:2); end;", 3);       
                      								callablestatement2.setNUMBER(1, row.getReportHeaderId());
                                                    callablestatement2.setNUMBER(2, row.getReportLineId());  
													callablestatement2.execute();   
                      								callablestatement2.close(); 
													oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("Revert Back Fusion Logic Completed").append(i).toString(), 12);
                									}
              									catch(SQLException sqlexception)
                    										{
                 									oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("Reverting back complete_998").toString(), 12); 
                     								throw new JboException(sqlexception);
                   										 }
											}	
										else
										{
										row.setSelect("N");
										oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("CCN111-Have unselected Line as it is not Fusion line").append(row.getReportLineId()).toString(), 1);
										}
			                            oapagecontext.writeDiagnostics(this, (new StringBuilder()).append(i).append(row.getCreditCardTrxId()).append("CCN111-Kishore_8 Report Line ID").append(row.getReportLineId()).toString(), 12); 
							}

                    }                  
        }         
        
      oapagecontext.writeDiagnostics(this, (new StringBuilder()).append("Kishore_98").append(oapagecontext.getParameter("MyAllocationsChoiceList")).toString(), 12);  

          oapagecontext.removeParameter("MyAllocationsChoiceList"); 
                                 
      if (oapagecontext.isLoggingEnabled(2)) 
          {
          oapagecontext.writeDiagnostics("xxca.oracle.apps.ap.oie.entry.accounting.webui.xxcaAllocationsHGridCO", "End fusionMyAllocationsDefault", 2);
      }                                  
      
  }      

     private void saveChangesForContext1(OAPageContext oAPageContext, OAWebBean oAWebBean) {
             OAApplicationModule oAApplicationModule = oAPageContext.getApplicationModule(oAWebBean);
             String string = AllocationsUtility.getAllocationContext((OAWebBean)oAWebBean);
             String string2 = AllocationsUtility.getDisplayMode((OAWebBean)oAWebBean);
             Serializable[] arrserializable = new Serializable[]{string, string2};
             Class[] arrclass = new Class[]{String.class, String.class};
             oAApplicationModule.invokeMethod("saveChanges", arrserializable, arrclass);
         }


     private void saveAllChanges(OAPageContext oAPageContext, OAWebBean oAWebBean) {
             String string;
             Serializable[] arrserializable;
             Class[] arrclass;
             String string2;
             OAApplicationModule oAApplicationModule = oAPageContext.getApplicationModule(oAWebBean);
             boolean bl = !"N".equals(oAPageContext.getTransactionValue("ENABLE_PROJECT_ALLOCATION"));
             boolean bl2 = "Y".equals(oAPageContext.getTransactionValue("ENABLE_ACCOUNT_ALLOCATION"));
             if (bl) {
                 string = "PROJECT";
                 string2 = AllocationsUtility.getDisplayMode((OAWebBean)oAWebBean);
                 arrserializable = new Serializable[]{string, string2};
                 arrclass = new Class[]{String.class, String.class};
                 oAApplicationModule.invokeMethod("saveChanges", arrserializable, arrclass);
             }
             if (bl2) {
                 string = "ACCOUNT";
                 string2 = AllocationsUtility.getDisplayMode((OAWebBean)oAWebBean);
                 arrserializable = new Serializable[]{string, string2};
                 arrclass = new Class[]{String.class, String.class};
                 oAApplicationModule.invokeMethod("saveChanges", arrserializable, arrclass);
             }
         }

 ///////////////////////////////////////------------------------------/////////////////////////////////////////////////  
   
   
 }