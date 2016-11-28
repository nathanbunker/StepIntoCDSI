package org.openimmunizationsoftware.cdsi.core.logic;

import java.io.PrintWriter;
import java.util.Date;

import org.openimmunizationsoftware.cdsi.core.data.DataModel;
import org.openimmunizationsoftware.cdsi.core.logic.items.ConditionAttribute;
import org.openimmunizationsoftware.cdsi.core.logic.items.LogicTable;

public class EvaluateLiveVirusConflict extends LogicStep
{

   private ConditionAttribute<Date> caDateAdministered = null;
   private ConditionAttribute<Date> caConflictBeginIntervalDate = null;
   private ConditionAttribute<Date> caConflictEndIntervalDate = null;
   private ConditionAttribute<String> caCurrentVaccineType = null;
   private ConditionAttribute<String> caPreviousVaccineType = null;
   

  public EvaluateLiveVirusConflict(DataModel dataModel)
  {
    super(LogicStepType.EVALUATE_FOR_LIVE_VIRUS_CONFLICT, dataModel);
    setConditionTableName("Table ");
    
    caDateAdministered = new ConditionAttribute<Date>("Vaccine dose administered", "Date Administered");
    caConflictBeginIntervalDate = new ConditionAttribute<Date>("Calculated date (CALCDTLIVE-1)" , "Conflict Begin Interval Date");
    caConflictEndIntervalDate = new ConditionAttribute<Date>("Calculated date(CALCDTLIVE-2 & CALCDTLIVE-3" , "Conflict End Interval Date");
    caCurrentVaccineType = new ConditionAttribute<String>("Supporting Data (Live Virus Conflict)" , "Current Vaccine Type");
    caPreviousVaccineType = new ConditionAttribute<String>("Supporting Data (Live Virus Conflict)" ,  "Previous Vaccine Type");
    
    
    
   // caTriggerAgeDate.setAssumedValue(FUTURE);
    
//    conditionAttributesList.add(caDateAdministered);
    
    LT logicTable = new LT();
    logicTableList.add(logicTable);
  }

  @Override
  public LogicStep process() throws Exception {
    setNextLogicStepType(LogicStepType.EVALUATE_PREFERABLE_VACCINE_ADMINISTERED);
    return next();
  }

  @Override
  public void printPre(PrintWriter out) throws Exception {
    printStandard(out);
  }

  @Override
  public void printPost(PrintWriter out) throws Exception {
    printStandard(out);
  }

  private void printStandard(PrintWriter out) {
    out.println("<h1> " + getTitle() + "</h1>");
    
    out.println("<p>Evaluate  live virus conflict  validates the  date  administered  of a live virus  vaccine dose administered  against previous  live  virus  administered  vaccines  to  ensure  proper  spacing  between  administrations.  For  some  live virus vaccines and for inactivated  virus or recombinant  vaccines, this condition does not exist. Therefore, if no live  virus  supporting  data  exists  for  the  vaccine  dose  administered  being  evaluated,  the  vaccine  dose administered is not in conflict with any other vaccine dose administered.</p>");//<------------------------------------------------------

    printConditionAttributesTable(out);
    printLogicTables(out);
  }

  private class LT extends LogicTable
  {
    public LT() {
      super(0, 0, "Table ?-?");

      //      setLogicCondition(0, new LogicCondition("date administered > lot expiration date?") {
      //        @Override
      //        public LogicResult evaluateInternal() {
      //          if (caDateAdministered.getFinalValue() == null || caTriggerAgeDate.getFinalValue() == null) {
      //            return LogicResult.NO;
      //          }
      //          if (caDateAdministered.getFinalValue().before(caTriggerAgeDate.getFinalValue())) {
      //            return LogicResult.YES;
      //          }
      //          return LogicResult.NO;
      //        }
      //      });

      //      setLogicResults(0, LogicResult.YES, LogicResult.NO, LogicResult.NO, LogicResult.ANY);

      //      setLogicOutcome(0, new LogicOutcome() {
      //        @Override
      //        public void perform() {
      //          log("No. The target dose cannot be skipped. ");
      //          log("Setting next step: 4.3 Substitute Target Dose");
      //          setNextLogicStep(LogicStep.SUBSTITUTE_TARGET_DOSE_FOR_EVALUATION);
      //        }
      //      });
      //      
    }
  }


}
