package org.copakb.server.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

/**
 * Created by Kevin on 5/28/2015.
 */

@Document
public class Report {
    //mongodb ID
    @Id
    private String id;

    private String taskID;
    private String mzFile;
    private String libModule;
    private String searchFilter;
    private String idProteins;
    private ArrayList<ReportProtein> reportProteins;

    public Report(String id, String taskID, String mzFile, String libModule,
                  String searchFilter, String idProteins,
                  ArrayList<ReportProtein> reportProteins) {
        this.id = id;
        this.taskID = taskID;
        this.mzFile = mzFile;
        this.libModule = libModule;
        this.searchFilter = searchFilter;
        this.idProteins = idProteins;
        this.reportProteins = reportProteins;
    }

    public Report(String id, String taskID, String mzFile, String libModule,
                  String searchFilter, String idProteins) {
        this.id = id;
        this.taskID = taskID;
        this.mzFile = mzFile;
        this.libModule = libModule;
        this.searchFilter = searchFilter;
        this.idProteins = idProteins;
    }

    public Report() {
        //default
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getMzFile() {
        return mzFile;
    }

    public void setMzFile(String mzFile) {
        this.mzFile = mzFile;
    }

    public String getLibModule() {
        return libModule;
    }

    public void setLibModule(String libModule) {
        this.libModule = libModule;
    }

    public String getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(String searchFilter) {
        this.searchFilter = searchFilter;
    }

    @Field("iDProteins")
    public String getIdProteins() {
        return idProteins;
    }

    public void setIdProteins(String idProteins) {
        this.idProteins = idProteins;
    }

    @Field("proteins")
    public ArrayList<ReportProtein> getReportProteins() {
        return reportProteins;
    }

    public void setReportProteins(ArrayList<ReportProtein> reportProteins) {
        this.reportProteins = reportProteins;
    }

    @Override
    public String toString() {
        String result = "id: " + id + "\n" +
                        "mzFile: " + mzFile + "\n" +
                        "libModule: " + libModule + "\n" +
                        "searchFilter: " + searchFilter + "\n" +
                        "idProteins: " + idProteins + "\n";

        return result;
    }
}