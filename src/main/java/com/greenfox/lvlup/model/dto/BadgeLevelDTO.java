package com.greenfox.lvlup.model.dto;

import java.util.Set;

public class BadgeLevelDTO {
    public int level;
    public String description;
    public Set<UserDto> holders;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserDto> getHolders() {
        return holders;
    }

    public void setHolders(Set<UserDto> holders) {
        this.holders = holders;
    }
}
