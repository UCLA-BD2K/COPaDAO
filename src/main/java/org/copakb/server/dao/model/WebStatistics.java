package org.copakb.server.dao.model;

import javassist.tools.web.Webserver;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sneha on 6/29/2015.
 */

@Entity
@Table(name = "Web_Statistics")
public class WebStatistics
{
    private int ws_id;
    private Date date;                //Check how date is stored
    private String month;
    private int year;
    private int pageviews;
    private double ppv;                     //pageviews per visitor
    private int unique_visitors;
    private int countries;

    public WebStatistics(int pageviews, double ppv, int unique_visitors, int countries)
    {
        this.pageviews = pageviews;
        this.unique_visitors = unique_visitors;
        /*
        mCalendar = Calendar.getInstance();
        String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        this.month = month;
         */
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        this.month = monthNames[month];
        this.year = Calendar.getInstance().get(Calendar.YEAR);
        this.ppv = ppv;
        this.countries = countries;
    }

    public WebStatistics()
    {}

    @Id
    @Column(name = "ws_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getWs_id() {return ws_id;}
    public void setWs_id(int ws_id) { this.ws_id = ws_id; }

    @Column(name = "pageviews")
    public int getPageviews(){return pageviews;}
    public void setPageviews(int pageviews){this.pageviews = pageviews;}

    @Column(name = "unique_visitors")
    public int getUnique_visitors(){return unique_visitors;}
    public void setUnique_visitors(int unique_visitors){this.unique_visitors = unique_visitors; }

    @Column(name = "countries")
    public int getCountries(){return countries;}
    public void setCountries(int countries) {this.countries = countries;}

    @Column(name = "year")
    public int getYear(){return year;}
    public void setYear(int year){this.year = year;}

    @Column(name = "month")
    public String getMonth(){return month;}
    public void setMonth(String month){this.month = month;}

    @Column(name = "ppv")
    public double getPpv(){return ppv;}
    public void setPpv(double ppv){this.ppv = ppv;}

    @Column(name = "date")
    public Date getDate(){return date;}
    public void setDate(Date date){this.date = date;}

}
