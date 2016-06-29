package org.openimmunizationsoftware.cdsi.core.domain;

import java.util.ArrayList;
import java.util.List;

public class AntigenSeries
{
  private String seriesName = "";
  private List<SeriesDose> seriesDoseList = new ArrayList<SeriesDose>();
  private List<SelectBestPatientSeries> selectBestPatientSeriesList = new ArrayList<SelectBestPatientSeries>();
  private String targetDisease = "";
  private VaccineGroup vaccineGroup = null;

  public String getTargetDisease() {
    return targetDisease;
  }

  public void setTargetDisease(String targetDisease) {
    this.targetDisease = targetDisease;
  }

  public VaccineGroup getVaccineGroup() {
    return vaccineGroup;
  }

  public void setVaccineGroup(VaccineGroup vaccineGroup) {
    this.vaccineGroup = vaccineGroup;
  }

  public String getSeriesName() {
    return seriesName;
  }

  public void setSeriesName(String seriesName) {
    this.seriesName = seriesName;
  }

  public List<SeriesDose> getSeriesDoseList() {
    return seriesDoseList;
  }

  public List<SelectBestPatientSeries> getSelectBestPatientSeriesList() {
    return selectBestPatientSeriesList;
  }

}
