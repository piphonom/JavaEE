package ru.otus.rik.web.xml;

import ru.otus.rik.domain.UserEntity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement(name = "Users")
@XmlSeeAlso(UserEntity.class)
public class UserEntitiesList extends ArrayList<UserEntity> {

    public UserEntitiesList() {
        super();
    }

    public UserEntitiesList(Collection<? extends UserEntity> collection) {
        super(collection);
    }

    @XmlElement(name = "User")
    public List<UserEntity> getUserEntities() {
        return this;
    }
}
