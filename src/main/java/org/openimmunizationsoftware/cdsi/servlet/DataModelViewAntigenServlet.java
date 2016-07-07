package org.openimmunizationsoftware.cdsi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openimmunizationsoftware.cdsi.core.data.DataModel;
import org.openimmunizationsoftware.cdsi.core.domain.Antigen;
import org.openimmunizationsoftware.cdsi.core.domain.VaccineType;

public class DataModelViewAntigenServlet extends ForecastServlet {

  public static final String SERVLET_NAME = "dataModelViewAntigen";
  
  private static final String PARAM_SEARCH = "search_term";
  
  public static String makeLink(Antigen antigen)
  {
    return "<a href=\"" + SERVLET_NAME + "?" + PARAM_SEARCH + "=" + antigen.getName() + "\" target=\"_new\">" + antigen.getName() + "</a>";
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    DataModel dataModel = (DataModel) session.getAttribute("dataModel");
    if (dataModel == null) {
      return;
    }

    resp.setContentType("text/html");

    String search_term = req.getParameter(PARAM_SEARCH);
    if (search_term == null) {
      search_term = "";
    }


    PrintWriter out = new PrintWriter(resp.getOutputStream());
    try {
      out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">");
      out.println("<html>");
      out.println("  <head>");
      out.println("    <title>CDSi - Data Model View - Antigen</title>");
      out.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"index.css\">");
      out.println("  </head>");
      out.println("  <body>");
      out.println("    <form action=\"" + SERVLET_NAME + "\">");

      out.println("      <input type=\"text\" name=\"search_term\"><br>");
      out.println("      <input type=\"submit\" value=\"Submit\">");

      out.println("    </form>");
      printAntigen(dataModel, search_term, out);
      out.println("  </body>");
      out.println("</html>");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      out.close();
    }
  }

  private void printAntigen(DataModel dataModel, String searchParam, PrintWriter out) {
    out.println("    <table>");
    out.println("      <tr>");
    out.println("        <caption>Antigen</caption>");
    out.println("        <th>Name</th>");
    out.println("        <th>Vaccine Group</th>");
    out.println("        <th>Cvx List</th>");
    out.println("        <th>Immunity List</th>");

    out.println("      </tr>");
    if (searchParam.equals("")) {
      for (Antigen antigen : dataModel.getAntigenMap().values()) {
        printRowAntigen(antigen, out);
      }

    } else {
      printRowAntigen(dataModel.getAntigenMap().get(searchParam), out);
    }
    out.println("    </table>");


  }

  private void printRowAntigen(Antigen antigen, PrintWriter out) {
    out.println("      <tr>");
    out.println("        <td>" + antigen.getName() + "</td>");
    out.println("        <td>" + antigen.getVaccineGroup() + "</td>");
    printCvxList(antigen, out);
    out.println("        <td>" + antigen.getImmunityList() + "</td>");

    out.println("      </tr>");

  }

  private void printCvxList(Antigen antigen, PrintWriter out) {
    out.println("        <td>");
    for(VaccineType cvx:antigen.getCvxList()) {
      out.println("         <ul><a href=\"dataModelViewCvx?search_term=" + cvx.getCvxCode() + "\">" + cvx + "</a></ul>");
    }
    out.println("        </td>");
  }



}
