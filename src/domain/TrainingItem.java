/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author Admin
 */
    public class TrainingItem extends AbstractDO implements Serializable{
    private Long id;
    private TrainingSession trainingSession;
    private Excercise excercise; 
    private int reps;
    private ItemAction action;
    

    public TrainingItem() {
    }

    public TrainingItem(Long itemId, TrainingSession trainingSession, Excercise excercise, int reps) {
        this.id = itemId;
        this.trainingSession = trainingSession;
        this.excercise = excercise;
        this.reps = reps;
        this.action = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrainingSession getTrainingSession() {
        return trainingSession;
    }

    public void setTrainingSession(TrainingSession trainingSession) {
        this.trainingSession = trainingSession;
    }

    public Excercise getExcercise() {
        return excercise;
    }

    public void setExcercise(Excercise excercise) {
        this.excercise = excercise;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public ItemAction getAction() {
        return action;
    }

    public void setAction(ItemAction action) {
        this.action = action;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TrainingItem other = (TrainingItem) obj;
        if (this.reps != other.reps) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.trainingSession, other.trainingSession)) {
            return false;
        }
        if (!Objects.equals(this.excercise, other.excercise)) {
            return false;
        }
        return this.action == other.action;
    }

    @Override
    public String toString() {
        return "TrainingItem{" + "itemId=" + id + ", trainingSession=" + trainingSession.getId() + ", excercise=" + excercise + ", reps=" + reps + ", action=" + action + '}';
    }

    @Override
    public String getAttributeList() {
        return "itemid,sessionid,excerciseid,reps";
    }

    @Override
    public String getClassName() {
        return "trainingitem";
    }

    @Override
    public String getAttributeValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId()).append(",").append(getTrainingSession().getId()).append(",")
                .append(getExcercise().getId()).append(",").append(getReps());
        System.out.println(sb.toString());
        return sb.toString();
    }

    @Override
    public String getQueryCondition() {
        return "itemid="+getId();
    }

    @Override
    public String getBoundQueryCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getNumberOfBountObject() {
        return 1;
    }

    @Override
    public Object getBoundObject(int numberOfObjects, int numberOfAttributesBoundObjects) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getNumberOfAttributesBoundObjects() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getBoundType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String setAttributeValues() {
        return "sessionid="+getTrainingSession().getId()+","+
                "excerciseid="+getExcercise().getId()+","+
                "reps="+ getReps();
    }

    @Override
    public void setForeignId(Long id) {
        getTrainingSession().setId(id);
    }

    @Override
    public AbstractDO getEntityFromResultSet(ResultSet rs) throws SQLException {
        Excercise e = new Excercise(rs.getLong("ti.excerciseid"), rs.getString("e.name"));
        Member m = new Member(rs.getLong("s.memberid"), rs.getString("m.firstname"), rs.getString("m.lastname"), rs.getDate("m.birthday"), Gender.valueOf(rs.getString("m.gender")), rs.getString("m.phone"));
        Coach c = new Coach(rs.getLong("s.coachid"), rs.getString("c.firstname"), rs.getString("c.lastname"), rs.getDate("c.birthday"), Gender.valueOf(rs.getString("c.gender")), rs.getString("c.phone"), rs.getString("c.username"), rs.getString("c.password"));
        TrainingSession s = new TrainingSession(rs.getLong("ti.sessionid"), m, c, rs.getDate("s.date"));
        return new TrainingItem(rs.getLong("ti.itemid"), s, e, rs.getInt("ti.reps"));
    }

    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT ti.itemid,ti.sessionid,ti.excerciseid,ti.reps,s.memberid,s.coachid,s.date,e.name,"
                + "m.firstname,m.lastname,m.birthday,m.gender,m.phone,"
                + "c.firstname,c.lastname,c.birthday,c.gender,c.phone,c.username,c.password"
                + " FROM trainingitem AS ti INNER JOIN trainingsession AS s ON ti.sessionid=s.sessionid"
                + " INNER JOIN excercise AS e ON ti.excerciseid=e.excerciseid"
                + " INNER JOIN MEMBER AS m ON s.memberid=m.memberid"
                + " INNER JOIN coach AS c ON s.coachid=c.coachid";
    }
    

    

    

    

    
    

    

    

    
   
    
}
