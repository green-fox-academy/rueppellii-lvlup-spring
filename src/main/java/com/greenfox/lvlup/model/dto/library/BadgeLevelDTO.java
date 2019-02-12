package com.greenfox.lvlup.model.dto.library;

import java.util.Set;

public class BadgeLevelDTO {
    public int level;
    public String description;
    public Set<UserForLibraryDTO> holders;

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

    public Set<UserForLibraryDTO> getHolders() {
        return holders;
    }

    public void setHolders(Set<UserForLibraryDTO> holders) {
        this.holders = holders;
    }
}
