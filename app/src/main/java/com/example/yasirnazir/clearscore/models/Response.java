package com.example.yasirnazir.clearscore.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yasirnazir on 3/14/18.
 */


public class Response extends GenericModel {
    private CreditReportInfo creditReportInfo;

    public Response(CreditReportInfo creditReportInfo) {
        this.creditReportInfo = creditReportInfo;
    }

    public CreditReportInfo getCreditReportInfo() {
        return creditReportInfo;
    }

    public void setCreditReportInfo(CreditReportInfo creditReportInfo) {
        this.creditReportInfo = creditReportInfo;
    }

    public Response() {
    }

}