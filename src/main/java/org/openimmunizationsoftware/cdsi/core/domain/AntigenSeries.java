package org.openimmunizationsoftware.cdsi.core.domain;

import java.util.ArrayList;
import java.util.List;

public class AntigenSeries {
  private String seriesName = "";
  private List<SeriesDose> seriesDoseList = new ArrayList<SeriesDose>();
  private SelectBestPatientSeries selectBestPatientSeries = null;
  private Antigen targetDisease = null;
  private VaccineGroup vaccineGroup = null;

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof AntigenSeries) {
      AntigenSeries as = (AntigenSeries) obj;
      return as.getSeriesName().equals(this.getSeriesName());
    }
    return super.equals(obj);
  }

  public Antigen getTargetDisease() {
    return targetDisease;
  }

  public void setTargetDisease(Antigen targetDisease) {
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

  public SelectBestPatientSeries getSelectBestPatientSeries() {
    return selectBestPatientSeries;
  }

  public void setSelectBestPatientSeries(SelectBestPatientSeries selectBestPatientSeries) {
    this.selectBestPatientSeries = selectBestPatientSeries;
  }


}
