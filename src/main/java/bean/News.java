package bean;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 9:40 2018/05/29
 * @Modificd :
 */
public class News extends BaseEntity{
    private String date;
    private String tilte;
    private String content;

    public News(Long id,String date, String tilte) {
        this.id = id;
        this.date = date;
        this.tilte = tilte;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
