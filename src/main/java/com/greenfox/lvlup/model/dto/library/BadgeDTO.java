package com.greenfox.lvlup.model.dto.library;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class BadgeDTO {
    @NotBlank(message = "Badge version must not be blank!")
    private String version;
    @NotBlank(message = "Badge name must not be blank!")
    private String name;
    @NotBlank(message = "Badge tag must not be blank!")
    private String tag;
    private List<BadgeLevelDTO> levels;

    public BadgeDTO() {
        levels = new ArrayList<>();
    }

    public BadgeDTO(String version, String name, String tag, List<BadgeLevelDTO> levels) {
        this();
        this.version = version;
        this.name = name;
        this.tag = tag;
        this.levels = levels;
    }

    public BadgeDTO(@NotBlank(message = "Badge version must not be blank!") String version, @NotBlank(message = "Badge name must not be blank!") String name, @NotBlank(message = "Badge tag must not be blank!") String tag) {
        this();
        this.version = version;
        this.name = name;
        this.tag = tag;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<BadgeLevelDTO> getLevels() {
        return levels;
    }

    public void setLevels(List<BadgeLevelDTO> levels) {
        this.levels = levels;
    }
}
