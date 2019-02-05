package com.greenfox.lvlup.model.entityTestingDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(exclude = "holders")
public class BadgeLevelDTO {
/*    @JsonIgnore
    public long id;
    @JsonIgnore
    public String name;*/
    public int level;
    public String description;

  /* // @ManyToOne
    @JsonIgnore
    public Badge badge;*/


    public Set<UserDTO> holders;

}
