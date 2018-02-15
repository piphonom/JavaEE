package ru.otus.rik.service.persistence;

import lombok.Getter;

@Getter
public final class SearchParams implements Comparable<SearchParams> {
    private String name;
    private String department;
    private String location;

    public SearchParams(String name, String department, String location) {
        setName(name);
        setDepartment(department);
        setLocation(location);
    }

    @Override
    public int compareTo(SearchParams compareArg) {
        if (!this.name.equalsIgnoreCase(compareArg.name)) {
            return this.name.compareTo(compareArg.name);
        }
        if (!this.department.equalsIgnoreCase(compareArg.department)) {
            return this.department.compareTo(compareArg.department);
        }
        if (!this.location.equalsIgnoreCase(compareArg.location)) {
            return this.location.compareTo(compareArg.location);
        }
        return 0;
    }

    @Override
    public int hashCode() {
        return name.hashCode() ^ department.hashCode() ^ location.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null)
            return false;

        return (obj instanceof SearchParams) &&
                this.name.equals(((SearchParams) obj).name) &&
                this.department.equals(((SearchParams) obj).department) &&
                this.location.equals(((SearchParams) obj).location);
    }

    public void setName(String name) {
        if (name == null || name.equals(""))
            this.name = getAnyMatchPrepared();
        else
            this.name = getPartialMatchesPrepared(name);
    }

    public void setDepartment(String department) {
        if (department == null || department.equals(""))
            this.department = getAnyMatchPrepared();
        else
            this.department = getPartialMatchesPrepared(department);
    }

    public void setLocation(String location) {
        if (location == null || location.equals(""))
            this.location = getAnyMatchPrepared();
        else
            this.location = getPartialMatchesPrepared(location);
    }

    private String getAnyMatchPrepared() {
        return "%";
    }

    private String getPartialMatchesPrepared(String initial) {
        return "%" + initial + "%";
    }
}
