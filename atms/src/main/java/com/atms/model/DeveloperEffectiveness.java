package com.atms.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * @author Alex Kazanovskiy.
 */

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "developerEffectivenessId")
public class DeveloperEffectiveness {
    private Double developerEffectivenessId;
    private Developer developer;
    private Keyword keyword;
    private Double deviation;

    public DeveloperEffectiveness() {
    }

    public DeveloperEffectiveness(Developer developer, Keyword keyword, Double deviation) {
        this.developer = developer;
        this.keyword = keyword;
        this.deviation = deviation;
    }

    @Id
    @GeneratedValue
    @Column
    public Double getDeveloperEffectivenessId() {
        return developerEffectivenessId;
    }

    public void setDeveloperEffectivenessId(Double developerEffectivenessId) {
        this.developerEffectivenessId = developerEffectivenessId;
    }

    @ManyToOne
    @JoinColumn(name = "developer_id")
    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    @ManyToOne
    @JoinColumn(name = "keyword_id")
    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    @Column
    public Double getDeviation() {
        return deviation;
    }

    public void setDeviation(Double deviation) {
        this.deviation = deviation;
    }
}
