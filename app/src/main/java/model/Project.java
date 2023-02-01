
package model;

import java.util.Date;

/**
 *
 * @author breno
 */
public class Project {
    private int id;
    private String name;
    private String description;
    private Date dateCreated;
    private Date updatedDate;

    public Project(int id, String name, String description, Date creatDate, Date updatedDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = creatDate;
        this.updatedDate = updatedDate;
    }
    
     public Project(){
         this.dateCreated = new Date();
         this.updatedDate = new Date();

     }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

 
    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
    
    
    
}
