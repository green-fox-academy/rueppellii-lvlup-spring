package com.greenfox.lvlup.model.entityTestingDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {
        public String name;
/*        @JsonIgnore
        public Set<BadgeLevel> bagdes = new HashSet<>();*/
}
