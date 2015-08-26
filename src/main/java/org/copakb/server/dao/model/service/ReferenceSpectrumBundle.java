package org.copakb.server.dao.model.service;

import org.copakb.server.dao.model.SpectraDataEntry;
import org.copakb.server.dao.model.SpectraDataEntry;

/**
 * Created by vincekyi on 8/16/15.
 */
public class ReferenceSpectrumBundle {

    private SpectraDataEntry specData;
    private double xcorr;
    private double delta_cn;
    private double th_precursor_mz;
    private String rawfileid;

    public ReferenceSpectrumBundle(SpectraDataEntry specData, double xcorr, double delta_cn, double th_precursor_mz, String rawfileid) {
        this.specData = specData;
        this.xcorr = xcorr;
        this.delta_cn = delta_cn;
        this.th_precursor_mz = th_precursor_mz;
        this.rawfileid = rawfileid;
    }

    public SpectraDataEntry getSpecData() {
        return specData;
    }

    public void setSpecData(SpectraDataEntry specData) {
        this.specData = specData;
    }

    public double getXcorr() {
        return xcorr;
    }

    public void setXcorr(double xcorr) {
        this.xcorr = xcorr;
    }

    public double getDelta_cn() {
        return delta_cn;
    }

    public void setDelta_cn(double delta_cn) {
        this.delta_cn = delta_cn;
    }

    public double getTh_precursor_mz() {
        return th_precursor_mz;
    }

    public void setTh_precursor_mz(double th_precursor_mz) {
        this.th_precursor_mz = th_precursor_mz;
    }

    public String getRawfileid() {
        return rawfileid;
    }

    public void setRawfileid(String rawfileid) {
        this.rawfileid = rawfileid;
    }

    @Override
    public String toString() {
        return "ReferenceSpectrumBundle{" +
                "specData=" + specData +
                ", xcorr=" + xcorr +
                ", delta_cn=" + delta_cn +
                ", th_precursor_mz=" + th_precursor_mz +
                ", rawfileid='" + rawfileid + '\'' +
                '}';
    }
}
