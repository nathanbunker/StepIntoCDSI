package org.openimmunizationsoftware.cdsi.servlet.dataModelView;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openimmunizationsoftware.cdsi.core.data.DataModel;
import org.openimmunizationsoftware.cdsi.core.domain.AntigenAdministeredRecord;
import org.openimmunizationsoftware.cdsi.core.domain.Immunity;
import org.openimmunizationsoftware.cdsi.core.domain.LiveVirusConflict;
import org.openimmunizationsoftware.cdsi.core.domain.TargetDose;
import org.openimmunizationsoftware.cdsi.servlet.ForecastServlet;

public class MainServlet extends ForecastServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    DataModel dataModel = (DataModel) session.getAttribute("dataModel");
    if (dataModel == null) {
      return;
    }

    resp.setContentType("text/html");

    PrintWriter out = new PrintWriter(resp.getOutputStream());
    try {
      String section = null;
      printHeader(out, section);
      printViewDataModel(dataModel, out);
      printFooter(out);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      out.close();
    }
  }

  protected void printFooter(PrintWriter out) {
    out.println("  </body>");
    out.println("</html>");
  }

  protected void printHeader(PrintWriter out, String section) {
    out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">");
    out.println("<html>");
    out.println("  <head>");
    if (section == null) {
    out.println("    <title>CDSi - Data Model View</title>");
    }
    else {
      out.println("    <title>CDSi - Data Model View - " + section + "</title>");
    }
    out.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"index.css\">");
    out.println("  </head>");
    out.println("  <body>");
    out.println("<p>");
    out.println("      <a href=\"dataModelView\">Main</a> | ");
    out.println("      <a href=\"dataModelViewAntigen\">Antigen</a> | ");
    out.println("      <a href=\"dataModelViewCvx\">CVX</a> | ");
    out.println("      <a href=\"dataModelViewPatient\">Patient</a> | ");
    out.println("      <a href=\"dataModelViewSchedule\">Schedule</a> | ");
    out.println("      <a href=\"dataModelViewVaccineGroup\">Vaccine Group</a> ");
    out.println("</p>");
  }

  private String n(Object o) {
    if (o == null) {
      return "<center>-</center>";
    } else {
      return o.toString();
    }
  }

  private void printViewDataModel(DataModel dataModel, PrintWriter out) {
    out.println("   <table>");
    out.println("     <tr>");
    out.println("       <caption>Data Model</caption>");

    out.println("       <th>Evaluation Status</th>");
    out.println("       <td>" + n(dataModel.getEvaluationStatus()) + "</td>");
    out.println("     </tr>");
    out.println("     <tr>");
    out.println("       <th>Patient</th>");
    out.println("       <td>" + PatientServlet.makeLink(dataModel.getPatient()) + "</td>");
    out.println("     </tr>");
    out.println("       <th>Assessment Date</th>");
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    out.println("       <td>" + sdf.format(dataModel.getAssessmentDate()) + "</td>");
    out.println("     </tr>");
    out.println("   </table>");

    if (dataModel.getImmunityList() != null && dataModel.getImmunityList().size() > 0) {
      out.println("   <table>");
      out.println("     <tr>");
      out.println("       <caption>Immunity List</caption>");
      out.println("       <th>Antigen</th>");
      out.println("       <th>Immunity Language</th>");
      out.println("       <th>Concept</th>");
      out.println("       <th>Concept Code</th>");
      out.println("       <th>Concept Text</th>");
      out.println("     </tr>");
      for (Immunity immunity : dataModel.getImmunityList()) {
        printRowImmunity(immunity, out);
      }
      out.println("   </table>");
    }

    if (dataModel.getTargetDoseList() != null) {
      out.println("   <table>");
      out.println("     <tr>");
      out.println("       <caption>Target Dose List</caption>");
      out.println("       <th>Target Dose Status</th>");
      out.println("       <th>Tracked Dose Series</th>");
      out.println("       <th>Satisfied By Vaccine Dose Administered</th>");
      out.println("     </tr>");
      for (TargetDose targetDose : dataModel.getTargetDoseList()) {
        printRowTargetDoseList(targetDose, out);
      }
    }
    out.println("   </table>");

    out.println("   <table>");
    out.println("     <tr>");
    out.println("       <caption>Live Virus Conflict</caption>");
    out.println("       <th>Schedule</th>");
    out.println("       <th>Previous Vaccine Type</th>");
    out.println("       <th>Current Vaccine Type</th>");
    out.println("       <th>Conflict Begin Interval</th>");
    out.println("       <th>Minimal Conflict End Interval</th>");
    out.println("       <th>Conflict End Interval</th>");
    out.println("     </tr>");
    for (LiveVirusConflict liveVirusConflict : dataModel.getLiveVirusConflictList()) {
      printRowLiveVirusConflict(liveVirusConflict, out);
    }
    out.println("   </table>");

    if (dataModel.getTargetDose() != null) {

      out.println("   <table>");
      out.println("     <tr>");
      out.println("       <caption>Target Dose</caption>");
      out.println("       <th>Target Dose Status</th>");
      out.println("       <td>" + dataModel.getTargetDose().getTargetDoseStatus() + "</td>");
      out.println("     </tr>");
      out.println("     <tr>");
      out.println("       <th>Tracked Series Dose</th>");
      out.println("       <td>" + dataModel.getTargetDose().getTrackedSeriesDose() + "</td>");
      out.println("     </tr>");
      out.println("     <tr>");
      out.println("       <th>Satisfied By Vaccine Dose Administered</th>");
      out.println("       <td>" + dataModel.getTargetDose().getSatisfiedByVaccineDoseAdministered() + "</td>");
      out.println("     </tr>");
      out.println("   </table>");
    }
    /*
     * out.println("     <tr>"); out.println("       <th>cvx Map</th>");
     * out.println("       <td>" + + "</td>"); out.println("     </tr>");
     * out.println("     <tr>"); out.println("       <th>Antigen Map</th>");
     * out.println("       <td>" + + "</td>"); out.println("     </tr>");
     * out.println("     <tr>"); out.println("       <th>Vaccine Group Map</th>"
     * ); out.println("       <td>" + + "</td>"); out.println("     </tr>");
     */

    /*
     * out.println("     <tr>"); out.println(
     * "       <th>Constraindictation List</th>"); out.println("       <td>" + +
     * "</td>"); out.println("     </tr>"); out.println("     <tr>");
     * out.println("       <th>Schedule List</th>"); out.println("       <td>" +
     * + "</td>"); out.println("     </tr>");
     */

    AntigenAdministeredRecord antigenAdministeredRecordThatSatisfiedPreviousTargetDose = dataModel
        .getAntigenAdministeredRecordThatSatisfiedPreviousTargetDose();
    printAntigenAdministeredRecordTable(antigenAdministeredRecordThatSatisfiedPreviousTargetDose,
        "Antigen Administered Record That Satisfied Previous Target Dose", out);

    AntigenAdministeredRecord antigenAdministeredRecord = dataModel.getAntigenAdministeredRecord();
    printAntigenAdministeredRecordTable(antigenAdministeredRecord, "Antigen Administered Record", out);

    AntigenAdministeredRecord previousAntigenAdministeredRecord = dataModel.getPreviousAntigenAdministeredRecord();
    printAntigenAdministeredRecordTable(previousAntigenAdministeredRecord, "Previous Antigen Administered Record", out);

    /*
     * out.println("     <tr>");
     * 
     * out.println("       <th>Antigen Administered Record List</th>");
     * out.println("       <td>" + + "</td>"); out.println("     </tr>");
     * out.println("     <tr>"); out.println(
     * "       <th>Antigen Series List</th>"); out.println("       <td>" + +
     * "</td>"); out.println("     </tr>"); out.println("     <tr>");
     * out.println("       <th>Patient Series List</th>"); out.println(
     * "       <td>" + + "</td>"); out.println("     </tr>"); out.println(
     * "     <tr>"); out.println("       <th>Patient Series</th>"); out.println(
     * "       <td>" + + "</td>"); out.println("     </tr>"); out.println(
     * "   </table>");
     */
  }

  private void printAntigenAdministeredRecordTable(AntigenAdministeredRecord antigenAdministeredRecord, String caption,
      PrintWriter out) {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    if (antigenAdministeredRecord != null) {
      out.println("   <table>");
      out.println("     <tr>");
      out.println("       <caption>" + caption + "</caption>");

      out.println("       <th>Antigen</th>");
      out.println("       <td>" + antigenAdministeredRecord.getAntigen() + "/td>");
      out.println("     </tr>");

      out.println("     <tr>");
      out.println("       <th>Date Administered</th>");

      out.println("       <td>" + sdf.format(antigenAdministeredRecord.getDateAdministered()) + "</td>");
      out.println("     </tr>");
      out.println("       <th>Vaccine Type</th>");
      out.println("       <td>" + antigenAdministeredRecord.getVaccineType() + "</td>");
      out.println("     </tr>");
      out.println("     </tr>");
      out.println("       <th>Manufacturer</th>");
      out.println("       <td>" + antigenAdministeredRecord.getManufacturer() + "</td>");
      out.println("     </tr>");
      out.println("     </tr>");
      out.println("       <th>Trade Name</th>");
      out.println("       <td>" + antigenAdministeredRecord.getTradeName() + "</td>");
      out.println("     </tr>");
      out.println("     </tr>");
      out.println("       <th>Amount</th>");
      out.println("       <td>" + antigenAdministeredRecord.getAmount() + "</td>");
      out.println("     </tr>");
      out.println("     </tr>");
      out.println("       <th>Lot Expiration Date</th>");
      out.println("       <td>" + sdf.format(antigenAdministeredRecord.getVaccineType()) + "</td>");
      out.println("     </tr>");
      out.println("     </tr>");
      out.println("       <th>Dose Condition</th>");
      out.println("       <td>" + antigenAdministeredRecord.getDoseCondition() + "</td>");
      out.println("     </tr>");
      out.println("     </tr>");
      out.println("       <th>Evaluation</th>");
      out.println("       <td>" + antigenAdministeredRecord.getEvaluation() + "</td>");
      out.println("     </tr>");
      out.println("   </table>");
    }
  }

  private void printRowLiveVirusConflict(LiveVirusConflict liveVirusConflict, PrintWriter out) {
    out.println("     <tr>");
    out.println("       <td>" + n(liveVirusConflict.getSchedule()) + "</td>");

    out.println("       <td>" + CvxServlet.makeLink(liveVirusConflict.getPreviousVaccineType()) + "</td>");

    out.println("       <td>" + CvxServlet.makeLink(liveVirusConflict.getCurrentVaccineType()) + "</td>");

    out.println("       <td>" + liveVirusConflict.getConflictBeginInterval() + "</td>");

    out.println("       <td>" + liveVirusConflict.getMinimalConflictEndInterval() + "</td>");

    out.println("       <td>" + liveVirusConflict.getConflictEndInterval() + "</td>");
    out.println("     </tr>");
  }

  private void printRowImmunity(Immunity immunity, PrintWriter out) {
    // out.println(" <tr>");
    // out.println(" <td>" + immunity.getAntigen() + "</td>");
    //
    // out.println(" <td>" + immunity.getImmunityLanguage() + "</td>");
    //
    // out.println(" <td>" + immunity.getConcept() + "</td>");
    //
    // out.println(" <td>" + immunity.getConceptCode() + "</td>");
    //
    // out.println(" <td>" + immunity.getConceptText() + "</td>");
    //
    // out.println(" </tr>");
  }

  private void printRowTargetDoseList(TargetDose targetDose, PrintWriter out) {
    out.println("     <tr>");
    out.println("       <td>" + targetDose.getTargetDoseStatus() + "</td>");

    out.println("       <td>" + targetDose.getTrackedSeriesDose() + "</td>");

    out.println("       <td>" + targetDose.getSatisfiedByVaccineDoseAdministered() + "</td>");

    out.println("     </tr>");
  }
}
