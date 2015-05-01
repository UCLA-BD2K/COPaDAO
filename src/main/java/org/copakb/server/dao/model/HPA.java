package org.copakb.server.dao.model;

import javax.persistence.*;

/**
 * Created by Kevin on 4/30/2015.
 */

@Entity
@Table(name = "HPA")
public class HPA {
    private int HPA_id;
    private ProteinCurrent proteinCurrent;
    private String ensembl_id;
    private String main_sub_cellular;
    private String additional_sub_cellular;
    private String sub_cellular_image;
    private String ihc_image;
    private String ihc_summary;
    private String ihc_heart_expression;

    public HPA(ProteinCurrent proteinCurrent, String ensembl_id, String main_sub_cellular, String additional_sub_cellular, String sub_cellular_image, String ihc_image, String ihc_summary, String ihc_heart_expression) {
        this.proteinCurrent = proteinCurrent;
        this.ensembl_id = ensembl_id;
        this.main_sub_cellular = main_sub_cellular;
        this.additional_sub_cellular = additional_sub_cellular;
        this.sub_cellular_image = sub_cellular_image;
        this.ihc_image = ihc_image;
        this.ihc_summary = ihc_summary;
        this.ihc_heart_expression = ihc_heart_expression;
    }

    public HPA() {
        //empty
    }

    @Id
    @Column(name = "HPA_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getHPA_id() {
        return HPA_id;
    }
    public void setHPA_id(int HPA_id) {
        this.HPA_id = HPA_id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "protein_acc", nullable = false)
    public ProteinCurrent getProteinCurrent() {
        return proteinCurrent;
    }
    public void setProteinCurrent(ProteinCurrent proteinCurrent) {
        this.proteinCurrent = proteinCurrent;
    }

    @Column(name = "ensembl_id")
    public String getEnsembl_id() {
        return ensembl_id;
    }
    public void setEnsembl_id(String ensembl_id) {
        this.ensembl_id = ensembl_id;
    }

    @Column(name = "main_sub_cellular")
    public String getMain_sub_cellular() {
        return main_sub_cellular;
    }
    public void setMain_sub_cellular(String main_sub_cellular) {
        this.main_sub_cellular = main_sub_cellular;
    }

    @Column(name = "additional_sub_cellular")
    public String getAdditional_sub_cellular() {
        return additional_sub_cellular;
    }
    public void setAdditional_sub_cellular(String additional_sub_cellular) {
        this.additional_sub_cellular = additional_sub_cellular;
    }

    @Column(name = "sub_cellular_image")
    public String getSub_cellular_image() {
        return sub_cellular_image;
    }
    public void setSub_cellular_image(String sub_cellular_image) {
        this.sub_cellular_image = sub_cellular_image;
    }

    @Column(name = "ihc_image")
    public String getIhc_image() {
        return ihc_image;
    }
    public void setIhc_image(String ihc_image) {
        this.ihc_image = ihc_image;
    }

    @Column(name = "ihc_summary")
    public String getIhc_summary() {
        return ihc_summary;
    }
    public void setIhc_summary(String ihc_summary) {
        this.ihc_summary = ihc_summary;
    }

    @Column(name = "ihc_heart_expression")
    public String getIhc_heart_expression() {
        return ihc_heart_expression;
    }
    public void setIhc_heart_expression(String ihc_heart_expression) {
        this.ihc_heart_expression = ihc_heart_expression;
    }
}
