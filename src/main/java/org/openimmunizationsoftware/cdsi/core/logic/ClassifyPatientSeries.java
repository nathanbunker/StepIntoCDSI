package org.openimmunizationsoftware.cdsi.core.logic;

import java.io.PrintWriter;

import org.openimmunizationsoftware.cdsi.core.data.DataModel;
import org.openimmunizationsoftware.cdsi.core.logic.items.LogicTable;

public class ClassifyPatientSeries extends LogicStep
{

  // private ConditionAttribute<Date> caDateAdministered = null;

  public ClassifyPatientSeries(DataModel dataModel)
  {
    super(LogicStepType.CLASSIFY_PATIENT_SERIES, dataModel);
    setConditionTableName("Table ");
    
    // caDateAdministered = new ConditionAttribute<Date>("Vaccine dose administered", "Date Administered");
    
   // caTriggerAgeDate.setAssumedValue(FUTURE);
    
//    conditionAttributesList.add(caDateAdministered);
    
    LT logicTable = new LT();
    logicTableList.add(logicTable);
  }

  @Override
  public LogicStep process() throws Exception {
    setNextLogicStepType(LogicStepType.COMPLETE_PATIENT_SERIES);
    setNextLogicStepType(LogicStepType.IN_PROCESS_PATIENT_SERIES);
    setNextLogicStepType(LogicStepType.NO_VALID_DOSES);
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
    out.println("<p>Classify  patient series  is an attempt to reduce  the total number of  patient series  to only those  which have  a chance to be selected as the best patient series.</p>");

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
