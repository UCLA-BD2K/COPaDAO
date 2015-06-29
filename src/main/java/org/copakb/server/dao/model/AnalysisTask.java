package org.copakb.server.dao.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.Set;

/**
 * Created by Sneha on 6/25/2015.
 */
@Entity
@Table(name = "analysis_task")
public class AnalysisTask {
    private int task_id;
    private String token;
    private Date upload_date;                //Check how date is stored
    private String experiment_directory;
    private int status;
    private String email;
    private LibraryModule mod;
    private Version version;

    public AnalysisTask(String experiment_directory,  String email, LibraryModule mod, Version version)
    {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        int len = 16;

            StringBuilder sb = new StringBuilder( len );
            for( int i = 0; i < len; i++ )
                sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
            this.token = sb.toString();
        this.experiment_directory = experiment_directory;
        this.email = email;
        this.mod = mod;
        this.version = version;

    }

    public AnalysisTask()
    {}

    @Id
    @Column(name = "task_id")
    public int getTask_id() {
        return task_id;
    }
    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mod_id", nullable = false)
    public LibraryModule getMod() {
        return mod;
    }
    public void setMod(LibraryModule mod) {
        this.mod = mod;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "version", nullable = false)
    public Version getVersion() {
        return version;
    }
    public void setVersion(Version version) {
        this.version = version;
    }


    @Column(name = "token")
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "upload_date")
    public Date getUpload_date() {
        return upload_date;
    }
    public void setUpload_date(Date upload_date) {
        this.upload_date = upload_date;
    }

    @Column(name = "experiment_directory")
    public String getExperiment_directory() {
        return experiment_directory;
    }
    public void setExperiment_directory(String experiment_directory) {
        this.experiment_directory = experiment_directory;
    }

    @Column(name = "status")
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
