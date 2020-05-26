package app.generator.modol;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Station  implements Serializable {
    private Integer id;

    private String stationName;

    private String stationIntroduction;

    private String category;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    public String getStationIntroduction() {
        return stationIntroduction;
    }

    public void setStationIntroduction(String stationIntroduction) {
        this.stationIntroduction = stationIntroduction == null ? null : stationIntroduction.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}