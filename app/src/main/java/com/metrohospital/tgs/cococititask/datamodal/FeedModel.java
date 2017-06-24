package com.metrohospital.tgs.cococititask.datamodal;
import java.util.List;
/**
 * Created by pankaj on 6/23/2017.
 */
public class FeedModel {
    public List<Showcases> showcases;
    public List<Showcases> getShowcases() {
        return showcases;
    }
    public void setShowcases(List<Showcases> showcases) {
        this.showcases = showcases;
    }
    public class Showcases{
        String id;
        String title;
        String description;
        String year;
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getYear() {
            return year;
        }
        public void setYear(String year) {
            this.year = year;
        }
    }
}
