package Util;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AlarmData implements PropertyChangeListener {
    private int hour = 0;
    private int minute = 0;
    private boolean isPm = false;
    private String label;
    private boolean mon,tue,wed,thu,fri,sat,sun = false;
    private boolean oneTimeTrigger;
    private boolean isActive = false;




    public AlarmData() {
        this.hour = 0;
        this.minute = 0;
        this.isPm = false;
        this.label = "Default Alarm Label";
        this.mon = false;
        this.isActive = false;
    }

    public AlarmData(int hour, int minute, boolean isPm, String label, boolean mon, boolean tue, boolean wed, boolean thu, boolean fri, boolean sat, boolean sun, boolean isActive) {
        this.hour = hour;
        this.minute = minute;
        this.isPm = isPm;
        this.label = label;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
        this.isActive = isActive;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        oneTimeTrigger = isOneTimeAlarm();

    }




    private boolean isOneTimeAlarm(){
        if( mon || tue || wed || thu || fri || sat || sun ){
            return false;
        }
        return true;
    }



    //Setter & Getter:



    public boolean isPm() {
        return isPm;
    }

    public void setPm(boolean pm) {
        isPm = pm;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isMon() {
        return mon;
    }

    public void setMon(boolean mon) {
        this.mon = mon;
    }

    public boolean isTue() {
        return tue;
    }

    public void setTue(boolean tue) {
        this.tue = tue;
    }

    public boolean isWed() {
        return wed;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }

    public boolean isThu() {
        return thu;
    }

    public void setThu(boolean thu) {
        this.thu = thu;
    }

    public boolean isFri() {
        return fri;
    }

    public void setFri(boolean fri) {
        this.fri = fri;
    }

    public boolean isSat() {
        return sat;
    }

    public void setSat(boolean sat) {
        this.sat = sat;
    }

    public boolean isSun() {
        return sun;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


}
