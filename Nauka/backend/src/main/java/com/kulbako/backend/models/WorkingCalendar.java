package com.kulbako.backend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Embeddable @Table(name = "days")
public class WorkingCalendar implements Serializable {

    @Transient private static final long serialVersionUID = 4L;
    //jpa не поддерживает вложенные коллекции, поэтому будем костылить
    @Enumerated(EnumType.STRING) @ElementCollection private List<WorkDayCode> januaryDays;
    @Enumerated(EnumType.STRING) @ElementCollection private List<WorkDayCode> februaryDays;
    @Enumerated(EnumType.STRING) @ElementCollection private List<WorkDayCode> marchDays;
    @Enumerated(EnumType.STRING) @ElementCollection private List<WorkDayCode> aprilDays;
    @Enumerated(EnumType.STRING) @ElementCollection private List<WorkDayCode> mayDays;
    @Enumerated(EnumType.STRING) @ElementCollection private List<WorkDayCode> juneDays;
    @Enumerated(EnumType.STRING) @ElementCollection private List<WorkDayCode> julyDays;
    @Enumerated(EnumType.STRING) @ElementCollection private List<WorkDayCode> augustDays;
    @Enumerated(EnumType.STRING) @ElementCollection private List<WorkDayCode> septemberDays;
    @Enumerated(EnumType.STRING) @ElementCollection private List<WorkDayCode> octoberDays;
    @Enumerated(EnumType.STRING) @ElementCollection private List<WorkDayCode> novemberDays;
    @Enumerated(EnumType.STRING) @ElementCollection private List<WorkDayCode> decemberDays;

    public WorkingCalendar() {}

    public List<WorkDayCode> getJanuaryDays() { return januaryDays; }

    public void setJanuaryDays(List<WorkDayCode> januaryDays) { this.januaryDays = januaryDays; }

    public List<WorkDayCode> getFebruaryDays() { return februaryDays; }

    public void setFebruaryDays(List<WorkDayCode> februaryDays) { this.februaryDays = februaryDays; }

    public List<WorkDayCode> getMarchDays() { return marchDays; }

    public void setMarchDays(List<WorkDayCode> marchDays) { this.marchDays = marchDays; }

    public List<WorkDayCode> getAprilDays() { return aprilDays; }

    public void setAprilDays(List<WorkDayCode> aprilDays) { this.aprilDays = aprilDays; }

    public List<WorkDayCode> getMayDays() { return mayDays; }

    public void setMayDays(List<WorkDayCode> mayDays) { this.mayDays = mayDays; }

    public List<WorkDayCode> getJuneDays() { return juneDays; }

    public void setJuneDays(List<WorkDayCode> juneDays) { this.juneDays = juneDays; }

    public List<WorkDayCode> getJulyDays() { return julyDays; }

    public void setJulyDays(List<WorkDayCode> julyDays) { this.julyDays = julyDays; }

    public List<WorkDayCode> getAugustDays() { return augustDays; }

    public void setAugustDays(List<WorkDayCode> augustDays) { this.augustDays = augustDays; }

    public List<WorkDayCode> getSeptemberDays() { return septemberDays; }

    public void setSeptemberDays(List<WorkDayCode> septemberDays) { this.septemberDays = septemberDays; }

    public List<WorkDayCode> getOctoberDays() { return octoberDays; }

    public void setOctoberDays(List<WorkDayCode> octoberDays) { this.octoberDays = octoberDays; }

    public List<WorkDayCode> getNovemberDays() { return novemberDays; }

    public void setNovemberDays(List<WorkDayCode> novemberDays) { this.novemberDays = novemberDays; }

    public List<WorkDayCode> getDecemberDays() { return decemberDays; }

    public void setDecemberDays(List<WorkDayCode> decemberDays) { this.decemberDays = decemberDays; }
}
