package org.copakb.server.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Kevin on 5/28/2015.
 */

@Document(collection = "Reports")
public class Report {
    //mongodb ID
    @Id
    private String Id;

    private String taskID;
    private String mzFile;
    private String libModule;
    private String searchFilter;
    private String iDProteins;
    private List<ReportProtein> reportProteins;

    public Report(String id, String taskID, String mzFile, String libModule,
                  String searchFilter, String idProteins,
                  List<ReportProtein> reportProteins) {
        Id = id;
        this.taskID = taskID;
        this.mzFile = mzFile;
        this.libModule = libModule;
        this.searchFilter = searchFilter;
        this.iDProteins = idProteins;
        this.reportProteins = reportProteins;
    }

    public Report(String id, String taskID, String mzFile, String libModule,
                  String searchFilter, String idProteins) {
        Id = id;
        this.taskID = taskID;
        this.mzFile = mzFile;
        this.libModule = libModule;
        this.searchFilter = searchFilter;
        this.iDProteins = idProteins;
    }

    public Report() {
        //default
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public String getIdProteins() {
        return iDProteins;
    }

    public void setIdProteins(String idProteins) {
        this.iDProteins = idProteins;
    }

    public List<ReportProtein> getReportProteins() {
        return reportProteins;
    }

    public void setReportProteins(List<ReportProtein> reportProteins) {
        this.reportProteins = reportProteins;
    }

    @Override
    public String toString() {
        String result = "Id: " + Id + "\n" +
                        "mzFile: " + mzFile + "\n" +
                        "libModule: " + libModule + "\n" +
                        "searchFilter: " + searchFilter + "\n" +
                        "iDProteins: " + iDProteins + "\n";

        return result;
    }
}