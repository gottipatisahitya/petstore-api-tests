package models;

import java.util.List;

public class PetModel{
    public long id;
    public Category category;
    public String name;
    public List<String> photoUrls;
    public List<PetTags> tags;
    public String status;
}
