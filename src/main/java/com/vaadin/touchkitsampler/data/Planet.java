package com.vaadin.touchkitsampler.data;

public class Planet {
    String name;
    double orbitRadius;
    int    moons;
    
    public Planet(String name, double orbitRadius, int moons) {
        this.name = name;
        this.orbitRadius = orbitRadius;
        this.moons = moons;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getOrbitRadius() {
        return orbitRadius;
    }
    public void setOrbitRadius(Double orbitRadius) {
        this.orbitRadius = orbitRadius;
    }
    public Integer getMoons() {
        return moons;
    }
    public void setMoons(Integer moons) {
        this.moons = moons;
    }
}