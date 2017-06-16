package xxca.oracle.apps.ap.oie.entry.lines.webui;

//START ADDED AS PART OF CCN111
import java.util.regex.Matcher;    
import java.util.regex.Pattern;    
import oracle.apps.ap.oie.entry.lines.webui.DetailsPageGlueCO;     
import oracle.apps.ap.oie.server.ExpenseReportLinesVORowImpl;   
import oracle.apps.ap.oie.server.ReceiptBasedLinesVOImpl;     
import oracle.apps.ap.oie.utility.OIEUtil; 
//END FOR ADDED AS PART OF CCN111    
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OATipBean;  //ADDED AS PART OF CCN111
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.*;
import oracle.apps.fnd.framework.webui.beans.form.OAFormValueBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageCheckBoxBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean; 
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.*; //ADDED AS PART OF CCN111
import oracle.cabo.ui.action.FireAction;				//ADDED AS PART OF CCN111
import oracle.apps.fnd.framework.webui.beans.OATipBean;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.ap.oie.server.*;
import oracle.apps.ap.oie.entry.server.*;
import com.sun.java.util.collections.HashMap;
import oracle.apps.ap.oie.common.server.NoCreditCardDataException;
import oracle.apps.ap.oie.entry.webui.EntryFlowPageCO;
import oracle.apps.ap.oie.framework.webui.OIEPageCO;
import oracle.apps.ap.oie.webui.NavigationUtility;
import oracle.apps.ap.oie.entry.lines.webui.DetailsPageGlueCO;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OADescriptiveFlexBean;
import oracle.jbo.RowSetIterator;			//ADDED AS PART OF CCN111
import oracle.jbo.Row;
import oracle.jbo.domain.Number;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.apps.ap.oie.utility.OIEUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Enumeration;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.apps.fnd.framework.server.OADBTransaction;
import java.io.Serializable;
import java.sql.SQLException;

import java.sql.Types;

import oracle.jbo.JboException;

import oracle.jdbc.OracleCallableStatement;

import oracle.jdbc.OraclePreparedStatement;   //ADDED AS PART OF CCN111
import oracle.jdbc.OracleResultSet; //ADDED AS PART OF CCN111



public class xxcaDetailsPageGlueCO141 extends DetailsPageGlueCO
{
  public static final String RCS_ID = "$Header: xxcaDetailsPageGlueCO.java 120.30.12010000.9 2009/11/09 19:15:20 chihchan ship $";
  public static final boolean RCS_ID_RECORDED = VersionInfo.recordClassVersion("$Header: xxcaDetailsPageGlueCO.java 120.30.12010000.9 2009/11/09 19:15:20 chihchan ship $", "xxca.oracle.apps.ap.oie.entry.lines.webui");
  

  public void processRequest(OAPageContext paramOAPageContext, OAWebBean paramOAWebBean)
  {                  
            super.processRequest(paramOAPageContext, paramOAWebBean);       
            OAMessageCheckBoxBean legacy = (OAMessageCheckBoxBean)paramOAWebBean.findChildRecursive("XXCA_LEGACY");
            OAMessageChoiceBean vatcode = (OAMessageChoiceBean)paramOAWebBean.findChildRecursive("DetailTaxClassification");
            OAMessageChoiceBean vatcode1 = (OAMessageChoiceBean)paramOAWebBean.findChildRecursive("ChildDetailTaxClassification");
            OAMessageLovInputBean comment = (OAMessageLovInputBean)paramOAWebBean.findIndexedChildRecursive("XXCA_COMMENTS");
            OAMessageLovInputBean costcenter = (OAMessageLovInputBean)paramOAWebBean.findIndexedChildRecursive("XXCA_LEGACY_COST_CENTER");
            OAMessageLovInputBean resourcecode = (OAMessageLovInputBean)paramOAWebBean.findIndexedChildRecursive("XXCA_LEGACY_RESOURCE_CODE");
            OAMessageTextInputBean custref = (OAMessageTextInputBean)paramOAWebBean.findIndexedChildRecursive("XXCA_CUST_REF");
            OAMessageChoiceBean vatrate = (OAMessageChoiceBean)paramOAWebBean.findChildRecursive("XXCA_VAT_RATE");
            OAWebBean localOAWebBean = (OAWebBean)paramOAWebBean.findChildRecursive("ItemizedDetails");
            OAMessageCheckBoxBean legacychild = (OAMessageCheckBoxBean)localOAWebBean.findChildRecursive("XXCA_LEGACY_CHILD");
            OAMessageLovInputBean commentchild = (OAMessageLovInputBean)localOAWebBean.findIndexedChildRecursive("XXCA_COMMENTS_CHILD");
            OAMessageLovInputBean costcenterchild = (OAMessageLovInputBean)localOAWebBean.findIndexedChildRecursive("XXCA_LEGACY_COST_CENTER_CHILD");
            OAMessageLovInputBean resourcecodechild = (OAMessageLovInputBean)paramOAWebBean.findIndexedChildRecursive("XXCA_LEGACY_RESOURCE_CODE1");
			OAMessageCheckBoxBean oamessagecheckboxbean2 = (OAMessageCheckBoxBean)paramOAWebBean.findChildRecursive("XXCA_FUSION"); //ADDED CODE FOR CCN111
	        OAMessageLovInputBean oamessagelovinputbean6 = (OAMessageLovInputBean)paramOAWebBean.findIndexedChildRecursive("XXCA_FUSION_JOB_NUM"); //ADDED CODE FOR CCN111
            OAMessageTextInputBean custrefchild = (OAMessageTextInputBean)localOAWebBean.findIndexedChildRecursive("XXCA_CUST_REF_CHILD");
            OAMessageChoiceBean vatratechild = (OAMessageChoiceBean)localOAWebBean.findChildRecursive("XXCA_VAT_RATE_CHILD");
            OATipBean tipmandate = (OATipBean)localOAWebBean.findChildRecursive("XXCA_MANDATE_TIP");

	        OAApplicationModule localOAApplicationModule = paramOAPageContext.getApplicationModule(paramOAWebBean);
          
            String regex="(.*)Purchase(.*)";
   	    Pattern pattern = Pattern.compile(regex);
   	    String responsibility= paramOAPageContext.getResponsibilityName();
            Matcher m = pattern.matcher(responsibility);
            Number cctrxid = null;
            Number rpthdrid = null;
            Number itemizedparentid = null;
            if(m.find()){  
                  oracle.cabo.ui.action.FireAction FireActionA = new oracle.cabo.ui.action.FireAction();
                  FireActionA.setEvent("CheckBox");
                  FireActionA.setUnvalidated(true);
                  legacy.setPrimaryClientAction(FireActionA);
                  legacychild.setPrimaryClientAction(FireActionA);
				  
				  
				  FireActionA.setEvent("CheckBoxFusion");
                  FireActionA.setUnvalidated(true);
                  oamessagecheckboxbean2.setPrimaryClientAction(FireActionA);
                    		
                  vatcode.setRendered(false);
                  vatcode1.setRendered(false); 
                  legacy.setRendered(true);
                  custref.setRendered(true);
                  vatrate.setRendered(true); 
                  comment.setRendered(true);
                  costcenter.setRendered(true);
                  resourcecode.setRendered(true);
                  legacychild.setRendered(true);
                  custrefchild.setRendered(true);
                  vatratechild.setRendered(true); 
                  commentchild.setRendered(true);
                  costcenterchild.setRendered(true);
                  resourcecodechild.setRendered(true);
                  tipmandate.setRendered(true);

                  String reportlineid = null;
                  String source = paramOAPageContext.getParameter("source");
                  String event = paramOAPageContext.getParameter("event");
                  String cont = paramOAPageContext.getParameter("OIEDetailNavBar");
                  String back = paramOAPageContext.getParameter("Back");
				  String myName=(String)paramOAPageContext.getSessionValue("MyReportLineChecked");                                
                  reportlineid = (String)paramOAPageContext.getTransactionValue("CurrentReportLineId"); 
                     paramOAPageContext.writeDiagnostics(this, "reportlineid  with trx = " + reportlineid, 6);
                  if (reportlineid == null){
                     reportlineid = paramOAPageContext.getDecryptedParameter("SDP_RLID");                     
                     paramOAPageContext.writeDiagnostics(this, "reportlineid  with para = " + reportlineid, 6);
                   }
				   
				   /*logic starts to check the check box Automatically for Fusion Employee BY ANKIT CCN111*/
				   String str1 = (String)paramOAPageContext.getTransactionValue("ReportHeaderId");  
					 paramOAPageContext.writeDiagnostics(this, "The report header id is " + str1, 1);
					String str2 = (String)paramOAPageContext.getTransactionValue("CurrentReportLineId");
 					if (str2 == null){
                     		str2 = paramOAPageContext.getDecryptedParameter("SDP_RLID"); }
					paramOAPageContext.writeDiagnostics(this, "The current report line id is " + str2, 1);
						paramOAPageContext.writeDiagnostics(this, "Fusion Logic to Auto check for Fusion Employee Started", 1);		
						OAApplicationModule am1 = paramOAPageContext.getApplicationModule(paramOAWebBean);
						OADBTransaction oadbtransaction3 = am1.getOADBTransaction(); 
						OracleCallableStatement callablestatement3;  
									String strRID = str2;
									paramOAPageContext.writeDiagnostics(this, "The Current report Line id is " + strRID, 1);
                                      String  returnVal2 ;
									  String  strattr6 ; 
									  String  strattr14;	
						OracleCallableStatement callablestatement5;  
						      try 
                                  {
                                      callablestatement5 = (OracleCallableStatement)oadbtransaction3.createCallableStatement("begin :1 := xxca_fusion_iexp_pkg.xxca_fusion_checkbox_checking; end;", 3);       
                                      callablestatement5.registerOutParameter(1, Types.VARCHAR);
                                      callablestatement5.execute();      
									  paramOAPageContext.writeDiagnostics(this, (new StringBuilder()).append("CCN111-KISHORE1").toString(), 12); 
                                      returnVal2 = callablestatement5.getString(1);
									  paramOAPageContext.writeDiagnostics(this, (new StringBuilder()).append(returnVal2).toString(), 12); 
                                      callablestatement5.close();
                                   }
								   
                        catch(SQLException sqlexception)
                                  {
                                        paramOAPageContext.writeDiagnostics(this, (new StringBuilder()).append("IN Catch KishoreFusionCCN111_998").toString(), 12); 
                                       throw new JboException(sqlexception);
                                  }
						paramOAPageContext.writeDiagnostics(this, (new StringBuilder()).append("ANKIT Value of MyNAME "+myName).toString(), 12);	
						   /*logic Completed to check the check box Automatically for Fusion Employee BY ANKIT CCN111*/
						   
						if (returnVal2.equals("Y")&&(!str2.equals(myName)))   // Check box should not be unchecked automatically if u have already unchecked it
                                  {   paramOAPageContext.writeDiagnostics(this, (new StringBuilder()).append("Check it on").toString(), 12); 
								oamessagecheckboxbean2.setChecked(true);
								oamessagelovinputbean6.setRequired("yes");
								  }	
				   
					  paramOAPageContext.writeDiagnostics(this, "source = " + source , 6);
					  paramOAPageContext.writeDiagnostics(this, "event = " + event , 6);
					  paramOAPageContext.writeDiagnostics(this, "continue = " + cont , 6);
					  paramOAPageContext.writeDiagnostics(this, "back = " + back, 6);
					  ReceiptBasedLinesVOImpl localReceiptBasedLinesVOImpl = (ReceiptBasedLinesVOImpl)localOAApplicationModule.findViewObject("BusinessCreditCardLinesVO");      
					  ExpenseReportLinesVORowImpl localExpenseReportLinesVORowImpl = null;
					  RowSetIterator localRowSetIterator = OIEUtil.getNoValIterator(localReceiptBasedLinesVOImpl);
                      while (localRowSetIterator.hasNext()) {
                      localExpenseReportLinesVORowImpl = (ExpenseReportLinesVORowImpl)localRowSetIterator.next();
	                  //LOGIC ADDED FOR COMPARING FUSION JOB NUMBER WITH CUSTOMER REFRENCE - CCN111 
					  	try 
                                  {
                                      String cusref1 = (String)localExpenseReportLinesVORowImpl.getAttribute6();
									  String valJob;
										if(cusref1!=null)
										{
										  OracleCallableStatement cs = null;     										  										   
											try
											{
											 cs=(OracleCallableStatement)oadbtransaction3.createCallableStatement("begin :1 := apps.xxca_fusion_iexp_pkg.xxca_fun_job_validation_f(:2);end;",1);                          
											 cs.registerOutParameter(1, Types.VARCHAR);  
											 cs.setString(2,cusref1);  
											 paramOAPageContext.writeDiagnostics(this, (new StringBuilder()).append("CCN111-Customer Reference "+cusref1).toString(), 12);                         
											 cs.execute();
											 valJob=cs.getString(1);
											 paramOAPageContext.writeDiagnostics(this, (new StringBuilder()).append("ValidJOborNot_Ankit").append(valJob).toString(), 6);
											if(("N").equals(valJob))
											{
											cusref1=null;
											paramOAPageContext.writeDiagnostics(this, (new StringBuilder()).append("Customer Reference is not a valid job number. Value returned by flag is ").append(valJob).toString(), 6);
											}
											else if(("Y").equals(valJob))
											{
											 paramOAPageContext.writeDiagnostics(this, (new StringBuilder()).append("Customer Reference is valid job number.  Value returned by flag is ").append(valJob).toString(), 6);		
											 oamessagelovinputbean6.setValue(paramOAPageContext,cusref1);
											 oamessagecheckboxbean2.setReadOnly(true);
											 oamessagelovinputbean6.setReadOnly(true);
											 paramOAPageContext.writeDiagnostics(this, (new StringBuilder()).append("have overwritten the Job Number by Customer Reference ").toString(), 6);
											}
											}											
											catch (SQLException sqle)
											{
											try 
											{ 
											cs.close(); 
											} 
											catch (Exception e) {throw OAException.wrapperException(sqle);}
											throw OAException.wrapperException(sqle);
											}  
										}
										}
						catch(Exception e) {throw OAException.wrapperException(e);}
							  
					//LOGIC ENDS FOR CUSTOMER REFERENCE VALIDATION WITH JOB NUMBER
                      try {
                        Number reportlineid1 = (Number)localExpenseReportLinesVORowImpl.getReportLineId();                         
                        paramOAPageContext.writeDiagnostics(this, "reportlineid1 in PR = " + reportlineid1, 6);
                        rpthdrid = (Number)localExpenseReportLinesVORowImpl.getReportHeaderId();
                        cctrxid = (Number) localExpenseReportLinesVORowImpl.getCreditCardTrxId();                        
                        if( reportlineid.equals(reportlineid1.toString())) {
                 	String cusref = (String)localExpenseReportLinesVORowImpl.getAttribute6();
					String fusionval = (String)localExpenseReportLinesVORowImpl.getAttribute14(); //Added for CCN111
                 	String taxflag = (String)localExpenseReportLinesVORowImpl.getAttribute9();
                        String legacychecked = (String)localExpenseReportLinesVORowImpl.getAttribute7();
                        String receiptchecked = (String)localExpenseReportLinesVORowImpl.getReceiptMissingFlag();
                        itemizedparentid = (Number)localExpenseReportLinesVORowImpl.getItemizationParentId();
                        localExpenseReportLinesVORowImpl.setAttribute6(cusref);
                 	custref.setValue(paramOAPageContext,cusref);
                        custrefchild.setValue(paramOAPageContext,cusref); 

						

                 	if("Y".equals(taxflag)){ 
                   	  vatrate.setRequired("yes"); 
                          vatratechild.setRequired("yes");                          
                          vatrate.setRendered(true);
                          vatratechild.setRendered(true);
                        }          
                  	else {
                  	  vatrate.setRequired("no");
                          vatratechild.setRequired("no");
                          vatrate.setRendered(false);
                          vatratechild.setRendered(false);
                        }
                        if("Y".equals(legacychecked)){
                          costcenter.setRequired("yes");
                          costcenterchild.setRequired("yes");
                          resourcecode.setRequired("yes");
                          resourcecodechild.setRequired("yes");
                        }        
                        else {
                          costcenter.setRequired("no");
                          costcenterchild.setRequired("no");
                          resourcecode.setRequired("no");
                          resourcecodechild.setRequired("no");
                          costcenter.setValue(paramOAPageContext,null);
                          costcenterchild.setValue(paramOAPageContext,null);
                          resourcecode.setValue(paramOAPageContext,null);
                          resourcecodechild.setValue(paramOAPageContext,null);
                        }
                        if("Y".equals(receiptchecked)){
                          comment.setRequired("yes");
                          commentchild.setRequired("yes");
                        }        
                        else {
                          comment.setRequired("no");
                  	  commentchild.setRequired("no");
                         } break;
                       }
                     }
            	     catch (OAException localOAException1){
                        localOAException1.setApplicationModule(localOAApplicationModule);
                        throw localOAException1; 
            		}
                   }           
                   if(itemizedparentid != null) {
                         costcenter.setRequired("no");
                         resourcecode.setRequired("no");
                         vatrate.setRequired("no");
                         costcenter.setValue(paramOAPageContext,null);
                         resourcecode.setValue(paramOAPageContext,null);
                         vatrate.setValue(paramOAPageContext,null);
                         if("no".equals(costcenterchild.getRequired())){
                            costcenterchild.setValue(paramOAPageContext,null);
                            resourcecodechild.setValue(paramOAPageContext,null);
                    }
                    }
            
                  SetcustRef(paramOAPageContext,paramOAWebBean,cctrxid,rpthdrid,reportlineid);
                  if (paramOAPageContext.getParameter("ReturnButton") != null )
                       paramOAPageContext.putTransactionValue("CurrentReportLineId",(String)null); 
            }
            else{ 
                  super.processRequest(paramOAPageContext, paramOAWebBean);
                  vatcode.setRendered(true);
                  vatcode1.setRendered(true); 
                  legacy.setRendered(false);
                  comment.setRendered(false);
                  costcenter.setRendered(false);
                  resourcecode.setRendered(false);
                  custref.setRendered(false);
                  vatrate.setRendered(false);
                  legacychild.setRendered(false);
                  custrefchild.setRendered(false);
                  vatratechild.setRendered(false); 
                  commentchild.setRendered(false);
                  costcenterchild.setRendered(false);
                  resourcecodechild.setRendered(false);
                  tipmandate.setRendered(false);
            }
  }

  public void processFormRequest(OAPageContext paramOAPageContext, OAWebBean paramOAWebBean)
  {                         
            OAMessageCheckBoxBean legacy = (OAMessageCheckBoxBean)paramOAWebBean.findChildRecursive("XXCA_LEGACY");
            OAMessageLovInputBean comment = (OAMessageLovInputBean)paramOAWebBean.findIndexedChildRecursive("XXCA_COMMENTS");
            OAMessageLovInputBean costcenter = (OAMessageLovInputBean)paramOAWebBean.findIndexedChildRecursive("XXCA_LEGACY_COST_CENTER");
            OAMessageLovInputBean resourcecode = (OAMessageLovInputBean)paramOAWebBean.findIndexedChildRecursive("XXCA_LEGACY_RESOURCE_CODE");
            OAMessageTextInputBean custref = (OAMessageTextInputBean)paramOAWebBean.findIndexedChildRecursive("XXCA_CUST_REF");
            OAMessageTextInputBean justification = (OAMessageTextInputBean)paramOAWebBean.findIndexedChildRecursive("Justification");
            OAMessageChoiceBean vatrate = (OAMessageChoiceBean)paramOAWebBean.findChildRecursive("XXCA_VAT_RATE");
            OAWebBean localOAWebBean = (OAWebBean)paramOAWebBean.findChildRecursive("ItemizedDetails");
            OAMessageCheckBoxBean legacychild = (OAMessageCheckBoxBean)localOAWebBean.findChildRecursive("XXCA_LEGACY_CHILD");
            OAMessageLovInputBean commentchild = (OAMessageLovInputBean)localOAWebBean.findIndexedChildRecursive("XXCA_COMMENTS_CHILD");
            OAMessageLovInputBean costcenterchild = (OAMessageLovInputBean)localOAWebBean.findIndexedChildRecursive("XXCA_LEGACY_COST_CENTER_CHILD");
            OAMessageLovInputBean resourcecodechild = (OAMessageLovInputBean)paramOAWebBean.findIndexedChildRecursive("XXCA_LEGACY_RESOURCE_CODE1");
            OAMessageCheckBoxBean oamessagecheckboxbean2 = (OAMessageCheckBoxBean)paramOAWebBean.findChildRecursive("XXCA_FUSION"); //ADDED CODE FOR CCN111
	     OAMessageLovInputBean oamessagelovinputbean6 = (OAMessageLovInputBean)paramOAWebBean.findIndexedChildRecursive("XXCA_FUSION_JOB_NUM"); //ADDED CODE FOR CCN111
            OAMessageTextInputBean custrefchild = (OAMessageTextInputBean)localOAWebBean.findIndexedChildRecursive("XXCA_CUST_REF_CHILD");
            OAMessageChoiceBean vatratechild = (OAMessageChoiceBean)localOAWebBean.findChildRecursive("XXCA_VAT_RATE_CHILD");  
  
             String str4 = (String)paramOAPageContext.getTransactionValue("CurrentReportLineId");
 					if (str4 == null){
                     		str4 = paramOAPageContext.getDecryptedParameter("SDP_RLID"); }
					paramOAPageContext.writeDiagnostics(this, "The current report line id2 is " + str4, 1);

            String regex="(.*)Purchase(.*)";
   	    Pattern pattern = Pattern.compile(regex);
   	    String responsibility= paramOAPageContext.getResponsibilityName();
            Matcher m = pattern.matcher(responsibility);
            if(m.find()){
                  OAApplicationModule localOAApplicationModule = paramOAPageContext.getApplicationModule(paramOAWebBean);
                   String reportlineid = null;
                  String source = paramOAPageContext.getParameter("source");
                  String event1 = paramOAPageContext.getParameter("event");
                  String cont = paramOAPageContext.getParameter("OIEDetailNavBar");
                  String back = paramOAPageContext.getParameter("Back");
                  
                  reportlineid = (String)paramOAPageContext.getTransactionValue("CurrentReportLineId");                   
                  paramOAPageContext.writeDiagnostics(this, "reportlineid  with trx = " + reportlineid, 6);
                  if (reportlineid == null){
                     reportlineid = paramOAPageContext.getDecryptedParameter("SDP_RLID");                     
                     paramOAPageContext.writeDiagnostics(this, "reportlineid  with para = " + reportlineid, 6);
                   }
                  paramOAPageContext.writeDiagnostics(this, "source = " + source , 6);
                  paramOAPageContext.writeDiagnostics(this, "event = " + event1 , 6);
                  paramOAPageContext.writeDiagnostics(this, "continue = " + cont , 6);
                  paramOAPageContext.writeDiagnostics(this, "back = " + back, 6);
           	  ReceiptBasedLinesVOImpl localReceiptBasedLinesVOImpl = (ReceiptBasedLinesVOImpl)localOAApplicationModule.findViewObject("BusinessCreditCardLinesVO");
            	  ExpenseReportLinesVORowImpl localExpenseReportLinesVORowImpl = null;
           	  RowSetIterator localRowSetIterator = OIEUtil.getNoValIterator(localReceiptBasedLinesVOImpl);
                  String cusref = null;
                  String legacychecked = null;
                  String comments = null;
                  String costcenters = null;
                  String vatrates = null;
				  String fusionlegacychecked = null; //Added for CCN111
                  String taxflag = null;
                  String receiptchecked = null;
                  Number itemizedparentid = null;
                  Number cctrxid = null;
                  Number rpthdrid = null;
                  while (localRowSetIterator.hasNext()) {
                      localExpenseReportLinesVORowImpl = (ExpenseReportLinesVORowImpl)localRowSetIterator.next();
                      try {                        
                        Number reportlineid1 = (Number)localExpenseReportLinesVORowImpl.getReportLineId();                         
                        paramOAPageContext.writeDiagnostics(this, "reportlineid1 in PFR = " + reportlineid1, 6);
                        rpthdrid = (Number)localExpenseReportLinesVORowImpl.getReportHeaderId(); 
                        cctrxid = (Number) localExpenseReportLinesVORowImpl.getCreditCardTrxId();                        
                        if( reportlineid.equals(reportlineid1.toString())) {
                 	cusref = (String)localExpenseReportLinesVORowImpl.getAttribute6();
                        taxflag = (String)localExpenseReportLinesVORowImpl.getAttribute9();
						fusionlegacychecked = (String)localExpenseReportLinesVORowImpl.getAttribute10(); //Added for CCN111 
                        legacychecked = (String)localExpenseReportLinesVORowImpl.getAttribute7();
                        comments = (String)localExpenseReportLinesVORowImpl.getAttribute11();
                        costcenters = (String)localExpenseReportLinesVORowImpl.getAttribute8();
                        vatrates = (String)localExpenseReportLinesVORowImpl.getAttribute12();
                        receiptchecked = (String)localExpenseReportLinesVORowImpl.getReceiptMissingFlag();
                        itemizedparentid = (Number)localExpenseReportLinesVORowImpl.getItemizationParentId();
                         break;
                         } 
                        }
            	     catch (OAException localOAException1){
                        localOAException1.setApplicationModule(localOAApplicationModule);
                        throw localOAException1; 
            		}
                   } 
                
                String event = paramOAPageContext.getParameter("event");
                paramOAPageContext.writeDiagnostics(this, "LOV Input Source = " + event, 6);
                if("ReceiptMissingToggle".equals(event)){
                if ("Y".equals(receiptchecked)){
                  comment.setRequired("yes");
                  commentchild.setRequired("yes");
                 }        
                 else {
                  comment.setRequired("no");
                  commentchild.setRequired("no");
                  comment.setValue(paramOAPageContext,null);
                  commentchild.setValue(paramOAPageContext,null);
                 }
                }
                if("Y".equals(taxflag)){ 
                   	  vatrate.setRequired("yes"); 
                          vatratechild.setRequired("yes");                          
                          vatrate.setRendered(true);
                          vatratechild.setRendered(true);
                        }          
                  	else {
                  	  vatrate.setRequired("no");
                          vatratechild.setRequired("no");
                          vatrate.setRendered(false);
                          vatratechild.setRendered(false);
                        }
						
				/* code piece STARTS for CCN111*/			   
			 if("CheckBoxFusion".equals(paramOAPageContext.getParameter(EVENT_PARAM)))
			 {
			paramOAPageContext.writeDiagnostics(this, "Fusion Legacy Checked Value" + event, 6); 			
			if("N".equals(fusionlegacychecked)){
                         oamessagelovinputbean6.setRequired("no");
			     paramOAPageContext.putSessionValue("MyReportLineChecked",str4);
				paramOAPageContext.writeDiagnostics(this, "AnkitFusion checkbox unchecked" + event, 6);
			paramOAPageContext.forwardImmediatelyToCurrentPage(null, true, "N");
                        }
                    
				   if("Y".equals(fusionlegacychecked))
                      {
                        oamessagelovinputbean6.setRequired("yes");
                       }
					else
                      {
                        oamessagelovinputbean6.setRequired("no");
                       }
}

			  if("Y".equals(fusionlegacychecked)){
                 oamessagelovinputbean6.setRequired("yes");
                        }
            /* code piece completed for CCN111*/

                if("CheckBox".equals(paramOAPageContext.getParameter(EVENT_PARAM))){
                       if("Y".equals(legacychecked)){
                          costcenter.setRequired("yes");
                          costcenterchild.setRequired("yes");
                          resourcecode.setRequired("yes");
                          resourcecodechild.setRequired("yes");
                        }
                        else {
                          costcenter.setRequired("no");
                          costcenterchild.setRequired("no");
                          resourcecode.setRequired("no");
                          resourcecodechild.setRequired("no");
                          costcenter.setValue(paramOAPageContext,null);
                          costcenterchild.setValue(paramOAPageContext,null);
                          resourcecode.setValue(paramOAPageContext,null);
                          resourcecodechild.setValue(paramOAPageContext,null);
                        }
                }
                if (paramOAPageContext.getParameter("ItemizeButton") != null) {                            
                         costcenter.setRequired("no");
                         resourcecode.setRequired("no");
                         vatrate.setRequired("no");
                         costcenter.setValue(paramOAPageContext,null);
                         resourcecode.setValue(paramOAPageContext,null);
                         vatrate.setValue(paramOAPageContext,null);
                            if (justification.getValue(paramOAPageContext) == null)
                              throw new OAException("Please enter value for Justification before proceeding with Itemization",OAException.WARNING);
                }
                if (paramOAPageContext.getParameter("ReturnButton") != null && itemizedparentid == null) {
                        if("yes".equals(comment.getRequired())){
                            if (comment.getValue(paramOAPageContext) == null)
                              throw new OAException("Please enter value for Comments since Original Receipts Missing is selected",OAException.WARNING);
                         } 
                        if("yes".equals(costcenter.getRequired())){
                            if (costcenter.getValue(paramOAPageContext) == null)
                              throw new OAException("Please enter value for Causeway/Fusion Cost Center since Causeway/Fusion System is selected",OAException.WARNING);
                         }
                         if("yes".equals(resourcecode.getRequired())){
                            if (resourcecode.getValue(paramOAPageContext) == null)
                              throw new OAException("Please enter value for Causeway/Fusion Resource Code since Causeway/Fusion System is selected",OAException.WARNING);
                         }
                         if("yes".equals(vatrate.getRequired())){
                            if (vatrate.getValue(paramOAPageContext) == null)
                              throw new OAException("Please enter value for VAT Rate",OAException.WARNING);
                         }
                  }

                  if((paramOAPageContext.getParameter("ReturnButton") != null || paramOAPageContext.getParameter("SingleSelectionChange") != null || paramOAPageContext.getParameter("DuplicateItemization") != null || paramOAPageContext.getParameter("RemoveItemization") != null || paramOAPageContext.getParameter("AddItemization") != null || paramOAPageContext.getParameter("ItemizationMasterTable") != null ) && itemizedparentid != null) {                        
                         if("yes".equals(commentchild.getRequired())){
                            if (commentchild.getValue(paramOAPageContext) == null)
                              throw new OAException("Please enter value for Comments since Original Receipts Missing is selected",OAException.WARNING);
                         }
                         if("yes".equals(costcenterchild.getRequired())){
                            if (costcenterchild.getValue(paramOAPageContext) == null)
                              throw new OAException("Please enter value for Causeway/Fusion Cost Center at Itemized Level",OAException.WARNING);
                         }
                         else
                            costcenterchild.setValue(paramOAPageContext,null); 
                         if("yes".equals(resourcecodechild.getRequired())){
                            if (resourcecodechild.getValue(paramOAPageContext) == null)
                              throw new OAException("Please enter value for Causeway/Fusion Resource Code at Itemized Level",OAException.WARNING);
                         }
                         else
                            resourcecodechild.setValue(paramOAPageContext,null); 
                         if("yes".equals(vatratechild.getRequired())){
                            if (vatratechild.getValue(paramOAPageContext) == null)
                              throw new OAException("Please enter value for VAT Rate at Itemized Level",OAException.WARNING);
                         }
                  }
                  if (paramOAPageContext.getParameter("ReturnButton") != null )
                       paramOAPageContext.putTransactionValue("CurrentReportLineId",(String)null); 
                  super.processFormRequest(paramOAPageContext, paramOAWebBean);
                  SetcustRef(paramOAPageContext,paramOAWebBean,cctrxid,rpthdrid,reportlineid);
      }
      else
         super.processFormRequest(paramOAPageContext, paramOAWebBean);
  }

  public void SetcustRef(OAPageContext paramOAPageContext,OAWebBean paramOAWebBean,Number cctrxid, Number rpthdrid, String reportlineid){
                 OAMessageChoiceBean vatrate = (OAMessageChoiceBean)paramOAWebBean.findChildRecursive("XXCA_VAT_RATE");
                 OAMessageTextInputBean custref = (OAMessageTextInputBean)paramOAWebBean.findIndexedChildRecursive("XXCA_CUST_REF");
                 OAWebBean localOAWebBean = (OAWebBean)paramOAWebBean.findChildRecursive("ItemizedDetails");
                 OAMessageChoiceBean vatratechild = (OAMessageChoiceBean)localOAWebBean.findChildRecursive("XXCA_VAT_RATE_CHILD");
                 OAMessageTextInputBean custrefchild = (OAMessageTextInputBean)localOAWebBean.findIndexedChildRecursive("XXCA_CUST_REF_cHILD");
                 OAApplicationModule localOAApplicationModule = paramOAPageContext.getApplicationModule(paramOAWebBean);
                 OraclePreparedStatement oraclePreparedStatement = null;
		 OracleResultSet oracleResultSet = null;                 
                 
		 String attribute6 = null;
                 String attribute9 = null;
                 String attribute12 = null;
		 StringBuffer stringBuffer = new StringBuffer();

		 stringBuffer.append("SELECT attribute9,attribute6,attribute12 FROM apps.ap_expense_report_lines_all");
		 stringBuffer.append(" WHERE report_header_id = '");
		 stringBuffer.append(rpthdrid.toString());
		 stringBuffer.append("' and credit_card_trx_id = '");
                 stringBuffer.append(cctrxid.toString());
                 stringBuffer.append("' and report_line_id = '");
                 stringBuffer.append(reportlineid);
                 stringBuffer.append("'");
		 OADBTransaction oadbTransaction = localOAApplicationModule.getOADBTransaction();
		 try{
			oraclePreparedStatement = (OraclePreparedStatement)oadbTransaction.createPreparedStatement(stringBuffer.toString(), 1);
			oracleResultSet = (OracleResultSet)oraclePreparedStatement.executeQuery();
			if(oracleResultSet.next()){
			    attribute9 = oracleResultSet.getString(1);
                            attribute6 = oracleResultSet.getString(2);
                            attribute12 = oracleResultSet.getString(3);
			}
			else
			    oracleResultSet.close();
			}
			catch(Exception sqlexception){
			    paramOAPageContext.putDialogMessage(new OAException("OraclePreparedStatement OR OracleResultSet Exception"));
			    sqlexception.printStackTrace();
		 } 
		 
                if("Y".equals(attribute9)){
                          vatrate.setRequired("yes"); 
                          vatratechild.setRequired("yes");
                          vatrate.setRendered(true);
                          vatratechild.setRendered(true); 
                        }       
                        else {
                          vatrate.setRequired("no");
                          vatratechild.setRequired("no");
                          vatrate.setRendered(false);
                          vatratechild.setRendered(false);
                        }
                
             	custref.setValue(paramOAPageContext,attribute6);
                custrefchild.setValue(paramOAPageContext,attribute6);
               // vatrate.setValue(paramOAPageContext,attribute12);
               // vatratechild.setValue(paramOAPageContext,attribute12); 
  }
}