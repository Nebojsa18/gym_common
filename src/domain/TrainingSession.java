/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class TrainingSession extends AbstractDO implements Serializable{
    private Long id;
    private Member member;
    private Coach coach;
    private Date date;
    List<TrainingItem> trainingItems;
    
    public TrainingSession() {
    }

    public TrainingSession(Long id, Member member, Coach trainer, Date date) {
        this.id = id;
        this.member = member;
        this.coach = trainer;
        this.date = date;
        this.trainingItems = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<TrainingItem> getTrainingItems() {
        return trainingItems;
    }

    public void setTrainingItems(List<TrainingItem> trainingItems) {
        this.trainingItems = trainingItems;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 83 * hash + Objects.hashCode(this.member);
        hash = 83 * hash + Objects.hashCode(this.coach);
        hash = 83 * hash + Objects.hashCode(this.date);
        hash = 83 * hash + Objects.hashCode(this.trainingItems);
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
        final TrainingSession other = (TrainingSession) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.member, other.member)) {
            return false;
        }
        if (!Objects.equals(this.coach, other.coach)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }

    

    @Override
    public String toString() {
        return "TrainingSession{" + "id=" + id + ", member=" + member + ", coach=" + coach + ", date=" + date + ", trainingItems=" + trainingItems + '}';
    }

    @Override
    public String getAttributeList() {
        return "sessionid,memberid,coachid,date";
    }

    @Override
    public String getClassName() {
        return "trainingsession";
    }

    @Override
    public String getAttributeValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId()).append(",").append(getMember().getId()).append(",")
                .append(getCoach().getId()).append(",'")
                .append(new java.sql.Date(getDate().getTime())).append("'");
        System.out.println(sb.toString());
        return sb.toString();
    }

    @Override
    public String getQueryCondition() {
        return "sessionid="+getId();
    }

    @Override
    public String getBoundQueryCondition() {
        return "sessionid="+getId(); //polje u trainingitem
    }

    @Override
    public int getNumberOfBountObject() {
        return getTrainingItems().size();
    }

    @Override
    public Object getBoundObject(int numberOfObjects, int numberOfAttributesBoundObjects) {
        return getTrainingItems().get(numberOfObjects);
    }

    @Override
    public int getNumberOfAttributesBoundObjects() {
        return 1;
    }

    @Override
    public String getBoundType() {
        return "trainingitem";
    }

    @Override
    public String setAttributeValues() {
        return "memberid="+getMember().getId()+","
                +"coachid="+getCoach().getId()+","
                +"date='"+new java.sql.Date(getDate().getTime())+"'";
                
    }

    @Override
    public void setForeignId(Long id) {
        setId(id);
    }

    @Override
    public AbstractDO getEntityFromResultSet(ResultSet rs) throws SQLException {
        Member m = new Member(rs.getLong("s.memberid"), rs.getString("m.firstname"), rs.getString("m.lastname"), rs.getDate("m.birthday"), Gender.valueOf(rs.getString("m.gender")), rs.getString("m.phone"));
        Coach c = new Coach(rs.getLong("s.coachid"), rs.getString("c.firstname"), rs.getString("c.lastname"), rs.getDate("c.birthday"), Gender.valueOf(rs.getString("c.gender")), rs.getString("c.phone"), rs.getString("c.username"), rs.getString("c.password"));
        return new TrainingSession(rs.getLong("s.sessionid"), m, c, rs.getDate("s.date"));
    }

    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT s.sessionid,s.memberid,s.coachid,s.date,m.firstname,m.lastname,m.birthday,m.gender,m.phone,c.firstname,c.lastname,c.birthday,c.gender,c.phone,c.username,c.password"
                + " FROM trainingsession AS s INNER JOIN MEMBER AS m ON s.memberid=m.memberid "
                + "INNER JOIN coach AS c ON s.coachid=c.coachid";
    }

    @Override
    public ItemAction getAction() { //vidi za ovo
        return getAction();
    }

    

    

    

    
    
    
}
