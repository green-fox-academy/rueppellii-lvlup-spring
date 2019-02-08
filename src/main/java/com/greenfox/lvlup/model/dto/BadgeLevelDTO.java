package com.greenfox.lvlup.model.dto;

import java.util.Set;

public class BadgeLevelDTO {
    public int level;
    public String description;
    public Set<UserDTO> holders;

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

    public Set<UserDTO> getHolders() {
        return holders;
    }

    public void setHolders(Set<UserDTO> holders) {
        this.holders = holders;
    }
}
