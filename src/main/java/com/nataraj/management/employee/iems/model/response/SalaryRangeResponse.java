package com.nataraj.management.employee.iems.model.response;

public class SalaryRangeResponse {
    private Double min;
    private Double max;

    public SalaryRangeResponse(Double min, Double max){
        this.max= max;
        this.min=min;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
