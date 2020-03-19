package demo.entity;

import lombok.Getter;
import lombok.Setter;

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
    private Long time_created;
    private Long time_updated;
    private Long time_deleted;
    private Boolean is_deleted = false;

    @PrePersist // useful for some fields related
    public void prePersist() {
        if (time_created == null) {
            time_created = System.currentTimeMillis();
        }
        if (time_updated == null) {
            time_updated = System.currentTimeMillis();
        }
    }

    @PreUpdate // useful for some fields
    public void preUpdate() {
        time_updated = System.currentTimeMillis();
    }

    @PreRemove
    public void preDelete() {
        time_deleted = System.currentTimeMillis();
    }

}
