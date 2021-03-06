package org.openimmunizationsoftware.cdsi.core.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.openimmunizationsoftware.cdsi.core.domain.Antigen;
import org.openimmunizationsoftware.cdsi.core.domain.AntigenAdministeredRecord;
import org.openimmunizationsoftware.cdsi.core.domain.AntigenSeries;
import org.openimmunizationsoftware.cdsi.core.domain.Contraindication;
import org.openimmunizationsoftware.cdsi.core.domain.Forecast;
import org.openimmunizationsoftware.cdsi.core.domain.Immunity;
import org.openimmunizationsoftware.cdsi.core.domain.ImmunizationHistory;
import org.openimmunizationsoftware.cdsi.core.domain.LiveVirusConflict;
import org.openimmunizationsoftware.cdsi.core.domain.Patient;
import org.openimmunizationsoftware.cdsi.core.domain.PatientSeries;
import org.openimmunizationsoftware.cdsi.core.domain.Schedule;
import org.openimmunizationsoftware.cdsi.core.domain.TargetDose;
import org.openimmunizationsoftware.cdsi.core.domain.VaccineGroup;
import org.openimmunizationsoftware.cdsi.core.domain.VaccineGroupForecast;
import org.openimmunizationsoftware.cdsi.core.domain.VaccineType;
import org.openimmunizationsoftware.cdsi.core.domain.datatypes.EvaluationStatus;
import org.openimmunizationsoftware.cdsi.core.logic.LogicStep;

public class DataModel {
  private List<LiveVirusConflict> liveVirusConflictList = new ArrayList<LiveVirusConflict>();
  private Map<String, VaccineType> cvxMap = new HashMap<String, VaccineType>();
  private Map<String, Antigen> antigenMap = new HashMap<String, Antigen>();
  private List<Antigen> antigenList = null;
  private int antigenPos = -1;
  private Antigen antigen = null;
  private List<AntigenSeries> antigenSeriesSelectedList = null;
  private List<PatientSeries> selectedPatientSeriesList = new ArrayList<PatientSeries>();
  private List<PatientSeries> bestPatientSeriesList = new ArrayList<PatientSeries>();

  private Map<String, VaccineGroup> vaccineGroupMap = new HashMap<String, VaccineGroup>();
  private List<Immunity> immunityList = new ArrayList<Immunity>();
  private List<Contraindication> contraindicationList = new ArrayList<Contraindication>();
  private List<Schedule> scheduleList = new ArrayList<Schedule>();

  private TargetDose targetDose = null;
  private List<TargetDose> targetDoseList = null;
  private AntigenAdministeredRecord antigenAdministeredRecordThatSatisfiedPreviousTargetDose = null;
  private EvaluationStatus evaluationStatus = null;
  private Patient patient = null;
  private ImmunizationHistory immunizationHistory = null;
  private Date assessmentDate = new Date();
  private HttpServletRequest request = null;
  private List<AntigenAdministeredRecord> antigenAdministeredRecordList =
      new ArrayList<AntigenAdministeredRecord>();
  private int targetDoseListPos = -1;
  private int antigenAdministeredRecordPos = -1;
  private AntigenAdministeredRecord antigenAdministeredRecord = null;
  private AntigenAdministeredRecord previousAntigenAdministeredRecord = null;
  private List<AntigenSeries> antigenSeriesList = new ArrayList<AntigenSeries>();
  private List<PatientSeries> patientSeriesList = new ArrayList<PatientSeries>();
  private PatientSeries patientSeries = null;
  private List<Forecast> forecastList = new ArrayList<Forecast>();
  private List<VaccineGroupForecast> vaccineGroupForcastList =
      new ArrayList<VaccineGroupForecast>();
  private VaccineGroup vaccineGroup;
  private List<VaccineGroup> vaccineGroupList;
  private int vaccineGroupPos = -1;
  private Forecast forecast = null;

  public Forecast getForecast() {
    return forecast;
  }

  public void setForecast(Forecast forecast) {
    this.forecast = forecast;
  }

  public void setSelectedPatientSeriesList(List<PatientSeries> selectedPatientSeriesList) {
    this.selectedPatientSeriesList = selectedPatientSeriesList;
  }

  public List<PatientSeries> getSelectedPatientSeriesList() {
    return selectedPatientSeriesList;
  }

  public List<PatientSeries> getBestPatientSeriesList() {
    return bestPatientSeriesList;
  }

  public void setBestPatientSeriesList(List<PatientSeries> bestPatientSeriesList) {
    this.bestPatientSeriesList = bestPatientSeriesList;
  }

  public void incVaccineGroupPos() {
    vaccineGroupPos++;
  }

  public VaccineGroup getVaccineGroup() {
    return vaccineGroup;
  }

  public void setVaccineGroup(VaccineGroup vaccineGroup) {
    this.vaccineGroup = vaccineGroup;
  }

  public List<VaccineGroup> getVaccineGroupList() {
    if (vaccineGroupList == null) {
      vaccineGroupList = new ArrayList<VaccineGroup>(vaccineGroupMap.values());
    }
    return vaccineGroupList;
  }

  public void setVaccineGroupList(List<VaccineGroup> vaccineGroupList) {
    this.vaccineGroupList = vaccineGroupList;
  }

  public int getVaccineGroupPos() {
    return vaccineGroupPos;
  }

  public void setVaccineGroupPos(int vaccineGroupPos) {
    this.vaccineGroupPos = vaccineGroupPos;
  }

  public List<Forecast> getForecastList() {
    return forecastList;
  }

  public void setForecastList(List<Forecast> forecastList) {
    this.forecastList = forecastList;
  }

  public List<VaccineGroupForecast> getVaccineGroupForcastList() {
    return vaccineGroupForcastList;
  }

  public void setVaccineGroupForcastList(List<VaccineGroupForecast> vaccienGroupForcastList) {
    this.vaccineGroupForcastList = vaccienGroupForcastList;
  }

  public Antigen getAntigen() {
    return antigen;
  }

  public void setAntigen(Antigen antigen) {
    this.antigen = antigen;
  }

  public List<AntigenSeries> getAntigenSeriesSelectedList() {
    return antigenSeriesSelectedList;
  }

  public void setAntigenSeriesSelectedList(List<AntigenSeries> antigenSeriesSelectedList) {
    this.antigenSeriesSelectedList = antigenSeriesSelectedList;
  }

  public List<Antigen> getAntigenList() {
    if (antigenList == null) {
      antigenList = new ArrayList<Antigen>(antigenMap.values());
    }
    return antigenList;
  }

  public int getAntigenPos() {
    return antigenPos;
  }



  public void setAntigenPos(int antigenPos) {
    this.antigenPos = antigenPos;
  }

  public void incAntigenPos() {
    this.antigenPos++;
  }



  public int getAntigenAdministeredRecordPos() {
    return antigenAdministeredRecordPos;
  }

  public void setAntigenAdministeredRecordPos(int antigenAdministeredRecordPos) {
    this.antigenAdministeredRecordPos = antigenAdministeredRecordPos;
  }

  public void incAntigenAdministeredRecordPos() {
    this.antigenAdministeredRecordPos++;
  }

  public int getTargetDoseListPos() {
    return targetDoseListPos;
  }

  public void incTargetDoseListPos() {
    targetDoseListPos++;
  }

  public void setTargetDoseListPos(int targetDoseListPos) {
    this.targetDoseListPos = targetDoseListPos;
  }

  public AntigenAdministeredRecord getAntigenAdministeredRecordThatSatisfiedPreviousTargetDose() {
    return antigenAdministeredRecordThatSatisfiedPreviousTargetDose;
  }

  public void setAntigenAdministeredRecordThatSatisfiedPreviousTargetDose(
      AntigenAdministeredRecord antigenAdministeredRecordThatSatisfiedPreviousTargetDose) {
    this.antigenAdministeredRecordThatSatisfiedPreviousTargetDose =
        antigenAdministeredRecordThatSatisfiedPreviousTargetDose;
  }

  public List<TargetDose> getTargetDoseList() {
    return targetDoseList;
  }

  public TargetDose findNextTargetDose(TargetDose targetDoseCurrent) {
    TargetDose targetDoseNext = null;
    boolean found = false;
    for (TargetDose targetDose : targetDoseList) {
      if (found) {
        targetDoseNext = targetDose;
        break;
      } else if (targetDose == targetDoseCurrent) {
        found = true;
      }
    }
    return targetDoseNext;
  }

  public void setTargetDoseList(List<TargetDose> targetDoseList) {
    this.targetDoseList = targetDoseList;
  }

  public AntigenAdministeredRecord getAntigenAdministeredRecord() {
    return antigenAdministeredRecord;
  }

  public AntigenAdministeredRecord getPreviousAntigenAdministeredRecord() {
    return previousAntigenAdministeredRecord;
  }

  public void setPreviousAntigenAdministeredRecord(
      AntigenAdministeredRecord previousAntigenAdministeredRecord) {
    this.previousAntigenAdministeredRecord = previousAntigenAdministeredRecord;
  }

  public void setAntigenAdministeredRecord(AntigenAdministeredRecord antigenAdministeredRecord) {
    this.antigenAdministeredRecord = antigenAdministeredRecord;
  }

  public PatientSeries getPatientSeries() {
    return patientSeries;
  }

  public void setPatientSeries(PatientSeries patientSeries) {
    this.patientSeries = patientSeries;
  }

  public List<PatientSeries> getPatientSeriesList() {
    return patientSeriesList;
  }

  public List<AntigenSeries> getAntigenSeriesList() {
    return antigenSeriesList;
  }

  public List<AntigenAdministeredRecord> getAntigenAdministeredRecordList() {
    return antigenAdministeredRecordList;
  }

  public void setAntigenAdministeredRecordList(
      List<AntigenAdministeredRecord> antigenAdministeredRecordList) {
    this.antigenAdministeredRecordList = antigenAdministeredRecordList;
  }

  public HttpServletRequest getRequest() {
    return request;
  }

  public void setRequest(HttpServletRequest request) {
    this.request = request;
  }

  public Date getAssessmentDate() {
    return assessmentDate;
  }

  public void setAssessmentDate(Date assessmentDate) {
    this.assessmentDate = assessmentDate;
  }

  public ImmunizationHistory getImmunizationHistory() {
    return immunizationHistory;
  }

  public void setImmunizationHistory(ImmunizationHistory immunizationHistory) {
    this.immunizationHistory = immunizationHistory;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  private LogicStep logicStepPrevious = null;
  private LogicStep logicStep = null;

  public LogicStep getLogicStepPrevious() {
    return logicStepPrevious;
  }

  public void setLogicStepPrevious(LogicStep logicStepPrevious) {
    this.logicStepPrevious = logicStepPrevious;
  }

  public LogicStep getLogicStep() {
    return logicStep;
  }

  public void setNextLogicStep(LogicStep nextLogicStep) {
    this.logicStepPrevious = this.logicStep;
    this.logicStep = nextLogicStep;
  }

  public TargetDose getTargetDose() {
    return targetDose;
  }

  public void setTargetDose(TargetDose targetDose) {
    this.targetDose = targetDose;
  }

  public EvaluationStatus getEvaluationStatus() {
    return evaluationStatus;
  }

  public void setEvaluationStatus(EvaluationStatus evaluationStatus) {
    this.evaluationStatus = evaluationStatus;
  }

  public Map<String, Antigen> getAntigenMap() {
    return antigenMap;
  }



  public Map<String, VaccineGroup> getVaccineGroupMap() {
    return vaccineGroupMap;
  }

  public Map<String, VaccineType> getCvxMap() {
    return cvxMap;
  }

  public void setCvxMap(Map<String, VaccineType> cvxMap) {
    this.cvxMap = cvxMap;
  }

  public VaccineType getCvx(String cvxCode) {
    return cvxMap.get(cvxCode);
  }

  public Antigen getAntigen(String antigenName) {
    return antigenMap.get(antigenName);
  }

  public Antigen getOrCreateAntigen(String antigenName) {
    Antigen antigen = getAntigen(antigenName);
    if (antigen == null) {
      antigen = new Antigen();
      antigen.setName(antigenName);
      getAntigenMap().put(antigenName, antigen);
    }
    return antigen;
  }

  public VaccineGroup getVaccineGroup(String vaccineGroupName) {
    return vaccineGroupMap.get(vaccineGroupName);
  }

  public VaccineGroup getOrCreateVaccineGroup(String vaccineGroupName) {
    VaccineGroup vaccineGroup = getVaccineGroup(vaccineGroupName);
    if (vaccineGroup == null) {
      vaccineGroup = new VaccineGroup();
      vaccineGroup.setName(vaccineGroupName);
      vaccineGroupMap.put(vaccineGroupName, vaccineGroup);
    }
    return vaccineGroup;
  }

  public List<LiveVirusConflict> getLiveVirusConflictList() {
    return liveVirusConflictList;
  }

  public List<Immunity> getImmunityList() {
    return immunityList;
  }

  public void setImmunityList(List<Immunity> immunityList) {
    this.immunityList = immunityList;
  }

  public List<Contraindication> getContraindicationList() {
    return contraindicationList;
  }

  public void setContraindicationList(List<Contraindication> contraindicationList) {
    this.contraindicationList = contraindicationList;
  }

  public List<Schedule> getScheduleList() {
    return scheduleList;
  }

}
