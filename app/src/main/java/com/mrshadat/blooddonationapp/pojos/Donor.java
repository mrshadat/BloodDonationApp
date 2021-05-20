package com.mrshadat.blooddonationapp.pojos;

public class Donor {
    private String donorID;
    private String donorName;
    private String donorPhone;
    private String donorArea;
    private String donorAge;
    private String donorBloodGroup;
    private String lastDonation;

    public Donor() {
        // required by firebase;
    }

    public Donor(String donorName, String donorPhone, String donorArea, String donorAge, String donorBloodGroup, String lastDonation) {
        this.donorName = donorName;
        this.donorPhone = donorPhone;
        this.donorArea = donorArea;
        this.donorAge = donorAge;
        this.donorBloodGroup = donorBloodGroup;
        this.lastDonation = lastDonation;
    }

    public String getDonorID() {
        return donorID;
    }

    public void setDonorID(String donorID) {
        this.donorID = donorID;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorPhone() {
        return donorPhone;
    }

    public void setDonorPhone(String donorPhone) {
        this.donorPhone = donorPhone;
    }

    public String getDonorArea() {
        return donorArea;
    }

    public void setDonorArea(String donorArea) {
        this.donorArea = donorArea;
    }

    public String getDonorAge() {
        return donorAge;
    }

    public void setDonorAge(String donorAge) {
        this.donorAge = donorAge;
    }

    public String getDonorBloodGroup() {
        return donorBloodGroup;
    }

    public void setDonorBloodGroup(String donorBloodGroup) {
        this.donorBloodGroup = donorBloodGroup;
    }

    public String getLastDonation() {
        return lastDonation;
    }

    public void setLastDonation(String lastDonation) {
        this.lastDonation = lastDonation;
    }
}
