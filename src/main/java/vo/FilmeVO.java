package vo;

import org.springframework.hateoas.RepresentationModel;

public class FilmeVO extends RepresentationModel<FilmeVO> {

    private Long id;

    private String title;

    private String year;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
