package demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

@Getter
@Setter
@MappedSuperclass // MappedSuperclass is used to map its own attributes to its children, when mapping to database fields.
public class baseEntity {
    @Id // necessary for default primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // one of Generation strategy
    private Long id;
    @Version // version for this entity, related to db locking, related to internal query
    private Integer version;
    @Column(name = "time_created")
    private Long timeCreated;
    @Column(name = "time_updated")
    private Long timeUpdated;
    @Column(name = "time_deleted")
    private Long timeDeleted;
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @PrePersist // useful for some fields related
    public void prePersist() {
        if (timeCreated == null) {
            timeCreated = System.currentTimeMillis();
        }
        if (timeUpdated == null) {
            timeUpdated = System.currentTimeMillis();
        }
    }

    @PreUpdate // useful for some fields
    public void preUpdate() {
        timeUpdated = System.currentTimeMillis();
    }

    @PreRemove
    public void preDelete() {
        timeDeleted = System.currentTimeMillis();
    }

}
